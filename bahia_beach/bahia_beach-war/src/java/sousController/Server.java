package sousController;

import beanMetier.beanServeurLocal;
import entities.Tablee;
import entities.Serveur;
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
import javax.servlet.http.HttpSession;


public class Server implements ControllerInterface, Serializable{
    beanServeurLocal beanServeur = lookupbeanServeurLocal();

    
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response, HttpServlet servlet) {
      
        HttpSession session = request.getSession();
        Serveur s = (Serveur) session.getAttribute("auth"); //je recupere le serveur en cour de session qui s'est logger
        
        String action = request.getParameter("action");
        String x = request.getParameter("x");
        
        if("interface".equalsIgnoreCase(action)) {
        return "/WEB-INF/serveur/interfaceServeur.jsp";
        }
        
        if("table".equalsIgnoreCase(action)) {
        List<Tablee> lt = beanServeur.afficherTable();
        request.setAttribute("tables", lt);
        return "/WEB-INF/serveur/afficherTable.jsp";
        }
        
        if("tableLibre".equalsIgnoreCase(action)) {
        List<Tablee> lt = beanServeur.afficherTableLibre();
        request.setAttribute("tables", lt);
        return "/WEB-INF/serveur/afficherTable.jsp";
        }
        
        if("attribuerTable".equalsIgnoreCase(action)) {
        Long id = Long.parseLong(request.getParameter("id"));    
        List<Tablee> lt = beanServeur.attribuerTable(s, id);
        request.setAttribute("tables", lt);
        request.setAttribute("msg", "vous avez attribué une table");
        return "/WEB-INF/serveur/tableAttribuee.jsp";
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
    
    
}
