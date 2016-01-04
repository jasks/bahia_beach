package sousController;

import beanMetier.beanAppelLocal;
import beanMetier.beanCarteLocal;
import beanMetier.beanLogLocal;
import beanMetier.beanPanierLocal;
import beanMetier.beanPanierServeurLocal;
import beanMetier.beanServeurLocal;
import entities.Commande;
import entities.LigneCommande;
import entities.Menu;
import entities.Produit;
import entities.Tablee;
import entities.Serveur;
import entities.Type;
import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


public class Server implements ControllerInterface, Serializable{
    beanPanierLocal beanPanier = lookupbeanPanierLocal();
    beanCarteLocal beanCarte = lookupbeanCarteLocal();
    beanPanierServeurLocal beanPanierServeur = lookupbeanPanierServeurLocal();
    beanAppelLocal beanAppel = lookupbeanAppelLocal();
    beanLogLocal beanLog = lookupbeanLogLocal();
    beanServeurLocal beanServeur = lookupbeanServeurLocal();

    
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response, HttpServlet servlet) {
      
        HttpSession session = request.getSession();
        ServletContext application = servlet.getServletContext();
    
        
        Serveur s = (Serveur) session.getAttribute("auth"); //je recupere le serveur en cour de session qui s'est logger
        
        String action = request.getParameter("action");
        String x = request.getParameter("x");
        
        if("interface".equalsIgnoreCase(action)) {
            request.setAttribute("nombre", beanAppel.getNombreAppel(s));
        return "/WEB-INF/serveur/interfaceServeur.jsp";
        }
        
        if("table".equalsIgnoreCase(action)) {
        List<Tablee> lt = beanServeur.afficherTable();
        request.setAttribute("tables", lt);
        request.setAttribute("nombre", beanAppel.getNombreAppel(s));
        return "/WEB-INF/serveur/afficherTable.jsp";
        }
        
        if("tableLibre".equalsIgnoreCase(action)) {
        List<Tablee> lt = beanServeur.afficherTableLibre();
        request.setAttribute("tables", lt);
        request.setAttribute("nombre", beanAppel.getNombreAppel(s));
        return "/WEB-INF/serveur/afficherTable.jsp";
        }
        
        if("attribuerTable".equalsIgnoreCase(action)) {
        Long id = Long.parseLong(request.getParameter("id"));
        beanServeur.attribuerTable(s, id);
        List<Tablee> lt = beanServeur.afficherTableAttribue(s);
        request.setAttribute("tables", lt);
        request.setAttribute("msg", "vous avez attribué une table");
        request.setAttribute("nombre", beanAppel.getNombreAppel(s));
        return "/WEB-INF/serveur/tableAttribuee.jsp";
        }
        
        if("tableAttribue".equalsIgnoreCase(action)) {
            List<Tablee> lt = beanServeur.afficherTableAttribue(s);
            request.setAttribute("tables", lt);
            request.setAttribute("nombre", beanAppel.getNombreAppel(s));
            return "/WEB-INF/serveur/tableAttribuee.jsp";
        }

        
        if("tableAppel".equalsIgnoreCase(action)) {
            List<Tablee> lt = beanAppel.afficherAppelTable(s);
            request.setAttribute("tables", lt);
            request.setAttribute("nombre", beanAppel.getNombreAppel(s));
            return "/WEB-INF/serveur/afficherAppelTable.jsp";
        }
        
        if("repondreAppel".equalsIgnoreCase(action)) {
            Long id = Long.parseLong(request.getParameter("id"));
            beanAppel.responseAppel(beanServeur.getTablee(id));
            request.setAttribute("msg", "vous avez bien répondu à l'appel de la table : "+beanServeur.getTablee(id));
            List<Tablee> lt = beanAppel.afficherAppelTable(s);
            request.setAttribute("tables", lt);
            request.setAttribute("nombre", beanAppel.getNombreAppel(s));
            return "/WEB-INF/serveur/afficherAppelTable.jsp";
        }
        
                
        if("deconnexion".equalsIgnoreCase(action)) {
            beanLog.setActif((Serveur)session.getAttribute("auth"), 0);
            session.setAttribute("auth", null);
            request.setAttribute("msg", "vous avez été deconnecté");
            return "/WEB-INF/log.jsp";
        }
        
        
        
                
        /* ---------------------- PARTIE CLIENT ! ---------------------- */
        
                //CARTE
        if("partieClient".equalsIgnoreCase(action)) {
               
            Long id = Long.parseLong(request.getParameter("id"));
            //reccuperer la valeur (donc la hashmap) panier dont la clé est la table t
            HashMap <Integer, LigneCommande> panierServeur = beanPanierServeur.getPanierServeur().get(beanServeur.getTablee(id));
            beanPanier.setPanier(panierServeur);
            session.setAttribute("panier", panierServeur);
            List<Type> lt = beanCarte.selectAllType();
               request.setAttribute("types", lt);
            List<Menu> lm = beanCarte.selectAllMenu();
               request.setAttribute("menus", lm);
               
               return "/WEB-INF/serveur/client/carte.jsp";
        }
        if("carte".equalsIgnoreCase(action)) {
            List<Type> lt = beanCarte.selectAllType();
               request.setAttribute("types", lt);
            List<Menu> lm = beanCarte.selectAllMenu();
               request.setAttribute("menus", lm);
               
            Long id = Long.parseLong(request.getParameter("id"));
               return "/WEB-INF/serveur/client/carte.jsp";
        }
        
        if("produits".equalsIgnoreCase(action)) {
            
            if(request.getParameter("notype") != null) {
                List<Produit> lp = beanCarte.selectAllproduit();
               request.setAttribute("produits", lp);
               return "/WEB-INF/serveur/client/carteProduits.jsp";
            }
            
            Long id = Long.parseLong(request.getParameter("type"));
            List<Produit> lp = beanCarte.selectProduitByType(beanCarte.selectType(id));
               request.setAttribute("produits", lp);
               request.setAttribute("msg", id);
               return "/WEB-INF/serveur/client/carteProduits.jsp";
        }
        
        if("voirMenu".equalsIgnoreCase(action)) {
//            Long idMenu = Long.parseLong(request.getParameter("idMenu"));
//            Menu m = beanCarte.selectMenu(idMenu);
            String nom = request.getParameter("nom");
            List<Produit> lp = beanCarte.selectProduitByMenu(nom);
            request.setAttribute("produits", lp);
            return "/WEB-INF/serveur/client/menuProduits.jsp";
        }
        
        
                //panier du client
        
        
        if ("afficherPanier".equalsIgnoreCase(action)) {
            return "/WEB-INF/serveur/client/panier.jsp";
        }
        
        
        if ("add".equalsIgnoreCase(action)) {
            Long id = Long.parseLong(request.getParameter("id"));
            beanPanier.add(id);
            session.setAttribute("panier", beanPanier.getListe());
            session.setAttribute("nombre", beanPanier.getNombreProduit());
            session.setAttribute("total", beanPanier.getTotalHT());
            //update panier donc update panierServeur
            Tablee t = (Tablee) session.getAttribute("table");
            application.setAttribute("panierServeur", beanPanierServeur.updatePanier(t, beanPanier.getPanier()));
            request.setAttribute("panierServeurRequest", beanPanierServeur.updatePanier(t, beanPanier.getPanier()));
            request.setAttribute("msg", "Le produit a bien été ajouté à votre commande.");
            return "/WEB-INF/serveur/client/panier.jsp";
        }
        
        if ("remove".equalsIgnoreCase(action)) {
            Integer id = Integer.parseInt(request.getParameter("id"));
            beanPanier.delete(id);
            session.setAttribute("panier", beanPanier.getListe());
            session.setAttribute("nombre", beanPanier.getNombreProduit());
            session.setAttribute("total", beanPanier.getTotalHT());
            //update panier donc update panierServeur
            Tablee t = (Tablee) session.getAttribute("table");
            application.setAttribute("panierServeur", beanPanierServeur.updatePanier(t, beanPanier.getPanier()));
            request.setAttribute("panierServeurRequest", beanPanierServeur.updatePanier(t, beanPanier.getPanier()));
            request.setAttribute("msg", "Le produit a bien été retiré de votre commande.");
            return "/WEB-INF/serveur/client/panier.jsp";
        }
        
         if ("clear".equalsIgnoreCase(action)) {
            beanPanier.clearPanier();
            session.setAttribute("panier", beanPanier.getListe());
            //update panier donc update panierServeur
            Tablee t = (Tablee) session.getAttribute("table");
            application.setAttribute("panierServeur", beanPanierServeur.updatePanier(t, beanPanier.getPanier()));
            request.setAttribute("panierServeurRequest", beanPanierServeur.updatePanier(t, beanPanier.getPanier()));
            request.setAttribute("msg", "La commande a été supprimé.");
            return "/WEB-INF/serveur/client/panier.jsp";
        }
         
        if("commenter".equalsIgnoreCase(action)) {
//            Integer id = Integer.parseInt(request.getParameter("id"));
//            String contenu = beanPanier.getPanier().get(id).getCommentaire().getContenu();
//            request.setAttribute("contenu", this);
            return "/WEB-INF/serveur/client/commenterProduit.jsp";
        } 
         
        if("modifierCommenter".equalsIgnoreCase(action)) {
            Integer id = Integer.parseInt(request.getParameter("id"));
//            pour cibler le contenu du commentaire de la ligne de commande en cour
//            et l'afficher ds le textarea
            String contenu = beanPanier.getPanier().get(id).getCommentaire().getContenu();
            request.setAttribute("contenu", contenu);
            return "/WEB-INF/serveur/client/commenterProduit.jsp";
        } 
         
        if("setCommentaire".equalsIgnoreCase(action)) {
            Integer id = Integer.parseInt(request.getParameter("id"));
            String contenuCommentaire = request.getParameter("commentaire");
            beanPanier.ajoutCommentaire(id, contenuCommentaire);
            beanPanier.isCommentaire(id);
            request.setAttribute("msg", "votre commentaire a bien été ajouté");
            return "/WEB-INF/serveur/client/panier.jsp";
        }
         
        if("setCuisson".equalsIgnoreCase(action)) {
            Integer id = Integer.parseInt(request.getParameter("id"));
            Integer cuisson = Integer.parseInt(request.getParameter("cuisson"));
            beanPanier.cuissonViande(id, cuisson);
            request.setAttribute("msg", beanPanier.getPanier().get(id));
            System.out.println(beanPanier.getPanier().get(id));
            return "/WEB-INF/serveur/client/panier.jsp";
        }
         
        if("setCuissonMenu".equalsIgnoreCase(action)) {
            Integer idMenu = Integer.parseInt(request.getParameter("idMenu"));
            Integer idLc = Integer.parseInt(request.getParameter("idLc"));
            Integer cuisson = Integer.parseInt(request.getParameter("cuisson"));
            beanPanier.cuissonViandeMenu(idMenu, idLc, cuisson);
            request.setAttribute("msg", beanPanier.getPanier().get(idMenu));
            System.out.println(beanPanier.getPanier().get(idMenu));
            return "/WEB-INF/serveur/client/panier.jsp";
        }
        
        if("modifierCommentaire".equalsIgnoreCase(action)) {
            Integer id = Integer.parseInt(request.getParameter("id"));
            String contenuCommentaire = request.getParameter("commentaire");
            beanPanier.modifierCommentaire(id, contenuCommentaire);
            beanPanier.isCommentaire(id);
            request.setAttribute("msg", "votre commentaire a bien été ajouté");
            return "/WEB-INF/serveur/client/panier.jsp";
        }
        
        if("supprimerCommentaire".equalsIgnoreCase(action)) {
            Integer id = Integer.parseInt(request.getParameter("id"));
            beanPanier.supprimerCommentaire(id);
            request.setAttribute("msg", "Votre commentaire a bien été supprimé");
            return "/WEB-INF/serveur/client/panier.jsp";
        }
        
        
        
        if("ajouterMenu".equalsIgnoreCase(action)) {
//            Float prix = Float.parseFloat(request.getParameter("prixMenu"));
//            String nom = request.getParameter("nomMenu");
            Long idMenu = Long.parseLong(request.getParameter("idMenu"));
            Menu m = beanCarte.selectMenu(idMenu);
            Long idPlat = Long.parseLong(request.getParameter("plat"));
            Long idEntree = Long.parseLong(request.getParameter("entree"));
            beanPanier.addMenu(m, idPlat, idEntree);
            session.setAttribute("panier", beanPanier.getListe());
            session.setAttribute("nombre", beanPanier.getNombreProduit());
            session.setAttribute("total", beanPanier.getTotalHT());
            //update panier donc update panierServeur
            Tablee t = (Tablee) session.getAttribute("table");
            application.setAttribute("panierServeur", beanPanierServeur.updatePanier(t, beanPanier.getPanier()));
            request.setAttribute("panierServeurRequest", beanPanierServeur.updatePanier(t, beanPanier.getPanier()));
            request.setAttribute("msg", "Votre menu a bien été ajouté !!!");
            return "/WEB-INF/serveur/client/panier.jsp";
            
        }
        
        if("commander".equalsIgnoreCase(action)) {
            Commande c = beanPanier.validerPanier(beanServeur.getServeur(3L), beanServeur.getTablee(2L));
            session.setAttribute("panier", beanPanier.clearPanier());
            request.setAttribute("commande", c);
            request.setAttribute("msg", "votre commande a bien été prise en compte. Vous pouvez voir l'avancée de la commande");
            return "/WEB-INF/serveur/client/panier.jsp";
            //return "/WEB-INF/recapCommande.jsp";
        }
        
        
        return "/WEB-INF/serveur/interfaceServeur.jsp";
    }

    private beanServeurLocal lookupbeanServeurLocal() {
        try {
            Context c = new InitialContext();
            return (beanServeurLocal) c.lookup("java:global/bahia_beach/bahia_beach-ejb/beanServeur!beanMetier.beanServeurLocal");
        } catch (NamingException ne) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", ne);
            throw new RuntimeException(ne);
        }
    }

    private beanLogLocal lookupbeanLogLocal() {
        try {
            Context c = new InitialContext();
            return (beanLogLocal) c.lookup("java:global/bahia_beach/bahia_beach-ejb/beanLog!beanMetier.beanLogLocal");
        } catch (NamingException ne) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", ne);
            throw new RuntimeException(ne);
        }
    }

    private beanAppelLocal lookupbeanAppelLocal() {
        try {
            Context c = new InitialContext();
            return (beanAppelLocal) c.lookup("java:global/bahia_beach/bahia_beach-ejb/beanAppel!beanMetier.beanAppelLocal");
        } catch (NamingException ne) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", ne);
            throw new RuntimeException(ne);
        }
    }

    private beanPanierServeurLocal lookupbeanPanierServeurLocal() {
        try {
            Context c = new InitialContext();
            return (beanPanierServeurLocal) c.lookup("java:global/bahia_beach/bahia_beach-ejb/beanPanierServeur!beanMetier.beanPanierServeurLocal");
        } catch (NamingException ne) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", ne);
            throw new RuntimeException(ne);
        }
    }

    private beanCarteLocal lookupbeanCarteLocal() {
        try {
            Context c = new InitialContext();
            return (beanCarteLocal) c.lookup("java:global/bahia_beach/bahia_beach-ejb/beanCarte!beanMetier.beanCarteLocal");
        } catch (NamingException ne) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", ne);
            throw new RuntimeException(ne);
        }
    }

    private beanPanierLocal lookupbeanPanierLocal() {
        try {
            Context c = new InitialContext();
            return (beanPanierLocal) c.lookup("java:global/bahia_beach/bahia_beach-ejb/beanPanier!beanMetier.beanPanierLocal");
        } catch (NamingException ne) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", ne);
            throw new RuntimeException(ne);
        }
    }
    
    
}
