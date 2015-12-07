
package sousController;

import beanMetier.beanCarteLocal;
import entities.Menu;
import entities.Produit;
import entities.Type;
import java.io.Serializable;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class Carte implements ControllerInterface, Serializable {
    beanCarteLocal beanCarte = lookupbeanCarteLocal();
    
    

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response, HttpServlet servlet) {
        
        String action = request.getParameter("action");
        
        if("carte".equalsIgnoreCase(action)) {
            List<Type> lt = beanCarte.selectAllType();
               request.setAttribute("types", lt);
               request.setAttribute("msg", "affiche liste typeProduit et liste menu");
            List<Menu> lm = beanCarte.selectAllMenu();
               request.setAttribute("menus", lm);
               
               return "/WEB-INF/carte.jsp";
        }
        
        if("produits".equalsIgnoreCase(action)) {
            
            if(request.getParameter("notype") != null) {
                List<Produit> lp = beanCarte.selectAllproduit();
               request.setAttribute("produits", lp);
               return "/WEB-INF/carteProduits.jsp";
            }
            
            Long id = Long.parseLong(request.getParameter("type"));
            List<Produit> lp = beanCarte.selectProduitByType(beanCarte.selectType(id));
               request.setAttribute("produits", lp);
               request.setAttribute("msg", id);
               return "/WEB-INF/carteProduits.jsp";
        }
        
        if("voirMenu".equalsIgnoreCase(action)) {
            String nom = request.getParameter("nom");
            List<Produit> lp = beanCarte.selectProduitByMenu(nom);
            request.setAttribute("produits", lp);
            return "/WEB-INF/menuProduits.jsp";
        }
        
        if("description".equalsIgnoreCase(action)) {
            Long id = Long.parseLong(request.getParameter("id"));
            Produit p = beanCarte.selectProduit(id);
            request.setAttribute("description", p);
            return "/WEB-INF/carteDescriptionProduit.jsp";
        }
        
//        if("produits".equalsIgnoreCase(action)) {
//            Long type = Long.parseLong(request.getParameter("type"));
//            List<Produit> lp = beanCarte.selectProduitByType(type);
//               request.setAttribute("produits", lp);
//               return "/WEB-INF/carteProduits.jsp";
//        }

        
        if("type".equalsIgnoreCase(action)) {
            Type t = beanCarte.selectType(3l);
            request.setAttribute("type", t);
               return "/WEB-INF/test.jsp";
        }
 
        
        return "/WEB-INF/index.jsp";
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
