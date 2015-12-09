
package sousController;

import beanMetier.beanLogLocal;
import entities.Cuisinier;
import entities.Serveur;
import java.io.Serializable;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.persistence.NoResultException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class Log implements ControllerInterface, Serializable {
    beanLogLocal beanLog = lookupbeanLogLocal();
    
    
    

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response, HttpServlet servlet) {
        
        String action = request.getParameter("action");
        String code = request.getParameter("code");
        
        if("log".equalsIgnoreCase(action)) {
            return "/WEB-INF/log.jsp";
        }
        
        
        if("verifierCode".equalsIgnoreCase(action)) {
            
            if(code.substring(0, 1).equalsIgnoreCase("S")) {
            Serveur s = new Serveur();
            try {
            s = beanLog.connexionServeur(code);
            request.setAttribute("msg", "Bonjour "+s.getNom() + " " + s.getPrenom());
            } catch (NoResultException ex) {
                System.out.println(ex);
                request.setAttribute("msg", ex + " le code entré est invalide");
                return "/WEB-INF/log.jsp";
            }
            
            return "/WEB-INF/log.jsp";
        }
            
            
            if(code.substring(0, 1).equalsIgnoreCase("C")) {
            Cuisinier c = new Cuisinier();
            try {
            c = beanLog.connexionCuisinier(code);
            request.setAttribute("msg", "Bonjour "+c.getNom() + " " + c.getPrenom());
            } catch (NoResultException ex) {
                request.setAttribute("msg", "le code entré est invalide");
                return "/WEB-INF/log.jsp";
            }
            return "/WEB-INF/log.jsp";
        }
            
        }
 
        
        return "/WEB-INF/index.jsp";
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

    
    
    
}
