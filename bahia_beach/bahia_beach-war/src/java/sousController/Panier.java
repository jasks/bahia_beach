
package sousController;

import beanMetier.beanCarte;
import beanMetier.beanCarteLocal;
import beanMetier.beanPanierLocal;
import beanMetier.beanServeurLocal;
import entities.Commande;
import entities.Commentaire;
import entities.Menu;
import entities.Serveur;
import entities.Tablee;
import java.io.Serializable;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


public class Panier implements ControllerInterface, Serializable{
    beanCarteLocal beanCarte = lookupbeanCarteLocal();
    beanServeurLocal beanServeur = lookupbeanServeurLocal();
    beanPanierLocal beanPanier = lookupbeanPanierLocal();
    

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response, HttpServlet servlet) {
        
        HttpSession session = request.getSession();
    
        String action = request.getParameter("action");
        if ("afficherPanier".equalsIgnoreCase(action)) {
            return "/WEB-INF/client/panier.jsp";
        }
        
        
        if ("add".equalsIgnoreCase(action)) {
            Long id = Long.parseLong(request.getParameter("id"));
            beanPanier.add(id);
            session.setAttribute("panier", beanPanier.getListe());
            session.setAttribute("total", beanPanier.getTotalHT());
            request.setAttribute("msg", "Le produit a bien été ajouté à votre commande.");
            return "/WEB-INF/client/panier.jsp";
        }
        
        if ("remove".equalsIgnoreCase(action)) {
            Integer id = Integer.parseInt(request.getParameter("id"));
            beanPanier.delete(id);
            session.setAttribute("panier", beanPanier.getListe());
            session.setAttribute("total", beanPanier.getTotalHT());
            request.setAttribute("msg", "Le produit a bien été retiré de votre commande.");
            return "/WEB-INF/client/panier.jsp";
        }
        
         if ("clear".equalsIgnoreCase(action)) {
            beanPanier.clearPanier();
            session.setAttribute("panier", beanPanier.getListe());
            request.setAttribute("msg", "La commande a été supprimé.");
            return "/WEB-INF/client/panier.jsp";
        }
         
        if("commenter".equalsIgnoreCase(action)) {
//            Integer id = Integer.parseInt(request.getParameter("id"));
//            String contenu = beanPanier.getPanier().get(id).getCommentaire().getContenu();
//            request.setAttribute("contenu", this);
            return "/WEB-INF/client/commenterProduit.jsp";
        } 
         
        if("modifierCommenter".equalsIgnoreCase(action)) {
            Integer id = Integer.parseInt(request.getParameter("id"));
//            pour cibler le contenu du commentaire de la ligne de commande en cour
//            et l'afficher ds le textarea
            String contenu = beanPanier.getPanier().get(id).getCommentaire().getContenu();
            request.setAttribute("contenu", contenu);
            return "/WEB-INF/client/commenterProduit.jsp";
        } 
         
        if("setCommentaire".equalsIgnoreCase(action)) {
            Integer id = Integer.parseInt(request.getParameter("id"));
            String contenuCommentaire = request.getParameter("commentaire");
            beanPanier.ajoutCommentaire(id, contenuCommentaire);
            beanPanier.isCommentaire(id);
            request.setAttribute("msg", "votre commentaire a bien été ajouté");
            return "/WEB-INF/client/panier.jsp";
        }
         
        if("setCuisson".equalsIgnoreCase(action)) {
            Integer id = Integer.parseInt(request.getParameter("id"));
            Integer cuisson = Integer.parseInt(request.getParameter("cuisson"));
            beanPanier.cuissonViande(id, cuisson);
            request.setAttribute("msg", beanPanier.getPanier().get(id));
            return "/WEB-INF/client/panier.jsp";
        }
         
        if("setCuissonMenu".equalsIgnoreCase(action)) {
            Integer idMenu = Integer.parseInt(request.getParameter("idMenu"));
            Integer idLc = Integer.parseInt(request.getParameter("idLc"));
            Integer cuisson = Integer.parseInt(request.getParameter("cuisson"));
            beanPanier.cuissonViandeMenu(idMenu, idLc, cuisson);
            request.setAttribute("msg", beanPanier.getPanier().get(idMenu));
            return "/WEB-INF/client/panier.jsp";
        }
        
        if("modifierCommentaire".equalsIgnoreCase(action)) {
            Integer id = Integer.parseInt(request.getParameter("id"));
            String contenuCommentaire = request.getParameter("commentaire");
            beanPanier.modifierCommentaire(id, contenuCommentaire);
            beanPanier.isCommentaire(id);
            request.setAttribute("msg", "votre commentaire a bien été ajouté");
            return "/WEB-INF/client/panier.jsp";
        }
        
        if("supprimerCommentaire".equalsIgnoreCase(action)) {
            Integer id = Integer.parseInt(request.getParameter("id"));
            beanPanier.supprimerCommentaire(id);
            request.setAttribute("msg", "Votre commentaire a bien été supprimé");
            return "/WEB-INF/client/panier.jsp";
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
            session.setAttribute("total", beanPanier.getTotalHT());
            request.setAttribute("msg", "Votre menu a bien été ajouté !!!");
            return "/WEB-INF/client/panier.jsp";
            
        }
        
        if("commander".equalsIgnoreCase(action)) {
//            supprimer les id(L) en dur
            Commande c = beanPanier.validerPanier(beanServeur.getServeur(3L), beanServeur.getTablee(2L));
            session.setAttribute("panier", beanPanier.clearPanier());
            request.setAttribute("commande", c);
            request.setAttribute("msg", "votre commande a bien été prise en compte. Vous pouvez voir l'avancée de la commande");
            return "/WEB-INF/client/panier.jsp";
            //return "/WEB-INF/recapCommande.jsp";
        }
        
        return "/WEB-INF/index.jsp";
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

    private beanServeurLocal lookupbeanServeurLocal() {
        try {
            Context c = new InitialContext();
            return (beanServeurLocal) c.lookup("java:global/bahia_beach/bahia_beach-ejb/beanServeur!beanMetier.beanServeurLocal");
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
    
    
    
}
