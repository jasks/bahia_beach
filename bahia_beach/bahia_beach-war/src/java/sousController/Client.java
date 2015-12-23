package sousController;

import beanMetier.beanClientLocal;
import entities.Serveur;
import entities.Tablee;
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


public class Client  implements ControllerInterface, Serializable {
    beanClientLocal beanClient = lookupbeanClientLocal();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response, HttpServlet servlet) {
        
        HttpSession session = request.getSession();
        String action = request.getParameter("action");
        
        if("initTable".equalsIgnoreCase(action)) {
            List<Tablee> lt = beanClient.tableAttribueByServeur();
            request.setAttribute("tab", lt);
            return "/WEB-INF/client/afficherTable.jsp";
        }
        
        if("accederTable".equalsIgnoreCase(action)) {
            Long id = Long.parseLong(request.getParameter("id"));
            Tablee t = beanClient.selectTable(id);
            session.setAttribute("table", t);
            request.setAttribute("msg", t.getNum() + " mis en session" );
            System.out.println(t);
            return "/WEB-INF/client/interfaceClient.jsp";
        }
        if("appelerServeur".equalsIgnoreCase(action)){
            Tablee t = beanClient.appelerServeur("T01");
            request.setAttribute("uneTable", t);
            request.setAttribute("serveur", t.getServeur());
            
            return "/WEB-INF/client/appelerServeur.jsp";
        }
        
        return "/WEB-INF/index.jsp";
    }

    private beanClientLocal lookupbeanClientLocal() {
        try {
            Context c = new InitialContext();
            return (beanClientLocal) c.lookup("java:global/bahia_beach/bahia_beach-ejb/beanClient!beanMetier.beanClientLocal");
        } catch (NamingException ne) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", ne);
            throw new RuntimeException(ne);
        }
    }
    
    
    
}
