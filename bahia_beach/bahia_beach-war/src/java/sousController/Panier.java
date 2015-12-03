
package sousController;

import beanMetier.beanPanierLocal;
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
            Long id = Long.parseLong(request.getParameter("id"));
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
    
    
    
}
