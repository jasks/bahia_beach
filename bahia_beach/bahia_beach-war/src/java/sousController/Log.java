
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
import javax.servlet.http.HttpSession;


public class Log implements ControllerInterface, Serializable {
    beanLogLocal beanLog = lookupbeanLogLocal();
    
    
    
    

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response, HttpServlet servlet) {
        
        HttpSession session = request.getSession();
        
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
                    session.setAttribute("auth", s);
                    request.setAttribute("msg", "Bonjour "+s.getNom() + " " + s.getPrenom() + ": vous etes bien un "+s.getClass());
                    return "/WEB-INF/serveur/interfaceServeur.jsp";
                } catch (Exception ex) {
                    request.setAttribute("msg", ex);
                    return "/WEB-INF/log.jsp";
                }
            
        } else
            
            
            if(code.substring(0, 1).equalsIgnoreCase("C")) {
            Cuisinier c = new Cuisinier();
            
                try {
                    c = beanLog.connexionCuisinier(code);
                    session.setAttribute("auth", c);
                    request.setAttribute("msg", "Bonjour "+c.getNom() + " " + c.getPrenom() + ": vous etes bien un "+c.getClass());
                } catch (Exception ex) {
                    request.setAttribute("msg", ex);
                    return "/WEB-INF/log.jsp";
                }
            
            
            return "/WEB-INF/log.jsp";
        } else {
                try{
                request.setAttribute("msg", "code invalide");
                return "/WEB-INF/log.jsp";
                } catch(StringIndexOutOfBoundsException ex){
                    request.setAttribute("msg", ex);
                    return "/WEB-INF/log.jsp";
                }
                
            }
            
            
            
        }
 
        
        return "/WEB-INF/log.jsp";
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
