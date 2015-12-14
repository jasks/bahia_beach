
package sousController;

import beanMetier.beanPanierLocal;
import beanMetier.beanServeurLocal;
import entities.Commande;
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
    beanServeurLocal beanServeur = lookupbeanServeurLocal();
    beanPanierLocal beanPanier = lookupbeanPanierLocal();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response, HttpServlet servlet) {
        
        HttpSession session = request.getSession();
    
        String action = request.getParameter("action");
        if ("afficherPanier".equalsIgnoreCase(action)) {
            return "/WEB-INF/panier.jsp";
        }
        
        
        if ("add".equalsIgnoreCase(action)) {
            Long id = Long.parseLong(request.getParameter("id"));
            beanPanier.add(id);
            session.setAttribute("panier", beanPanier.getListe());
            session.setAttribute("total", beanPanier.getTotalHT());
            request.setAttribute("msg", "Le produit a bien été ajouté à votre commande.");
            return "/WEB-INF/panier.jsp";
        }
        
        if ("remove".equalsIgnoreCase(action)) {
            Integer id = Integer.parseInt(request.getParameter("id"));
            beanPanier.delete(id);
            session.setAttribute("panier", beanPanier.getListe());
            session.setAttribute("total", beanPanier.getTotalHT());
            request.setAttribute("msg", "Le produit a bien été retiré de votre commande.");
            return "/WEB-INF/panier.jsp";
        }
        
         if ("clear".equalsIgnoreCase(action)) {
            beanPanier.clearPanier();
            session.setAttribute("panier", beanPanier.getListe());
            request.setAttribute("msg", "La commande a été supprimé.");
            return "/WEB-INF/panier.jsp";
        }
         
        if("commenter".equalsIgnoreCase(action)) {
//            Integer id = Integer.parseInt(request.getParameter("id"));
//            String contenu = beanPanier.getPanier().get(id).getCommentaire().getContenu();
//            request.setAttribute("contenu", this);
            return "/WEB-INF/commenterProduit.jsp";
        } 
         
        if("modifierCommenter".equalsIgnoreCase(action)) {
            Integer id = Integer.parseInt(request.getParameter("id"));
//            pour cibler le contenu du commentaire de la ligne de commande en cour
//            et l'afficher ds le textarea
            String contenu = beanPanier.getPanier().get(id).getCommentaire().getContenu();
            request.setAttribute("contenu", contenu);
            return "/WEB-INF/commenterProduit.jsp";
        } 
         
        if("setCommentaire".equalsIgnoreCase(action)) {
            Integer id = Integer.parseInt(request.getParameter("id"));
            String contenuCommentaire = request.getParameter("commentaire");
            beanPanier.ajoutCommentaire(id, contenuCommentaire);
            request.setAttribute("msg", "votre commentaire a bien été ajouté");
            return "/WEB-INF/panier.jsp";
        }
        
        if("ajouterMenu".equalsIgnoreCase(action)) {
            Float prix = Float.parseFloat(request.getParameter("prixMenu"));
            String nom = request.getParameter("nomMenu");
            Long idPlat = Long.parseLong(request.getParameter("plat"));
            Long idEntree = Long.parseLong(request.getParameter("entree"));
            beanPanier.addMenu("menu "+nom, prix, idPlat, idEntree);
            session.setAttribute("panier", beanPanier.getListe());
            session.setAttribute("total", beanPanier.getTotalHT());
            request.setAttribute("msg", "Votre menu a bien été ajouté !!!");
            return "/WEB-INF/panier.jsp";
            
        }
        
        if("commander".equalsIgnoreCase(action)) {
            Commande c = beanPanier.validerPanier(beanServeur.getServeur(3L), beanServeur.getTablee(2L));
            request.setAttribute("commande", c);
            return "/WEB-INF/recapCommande.jsp";
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
    
    
    
}
