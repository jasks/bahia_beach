package sousController;

import beanMetier.beanClientLocal;
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


public class Client  implements ControllerInterface, Serializable {
    beanClientLocal beanClient = lookupbeanClientLocal();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response, HttpServlet servlet) {
        
        String action = request.getParameter("action");
        
        if("init".equalsIgnoreCase(action)) {
            List<Tablee> lt = beanClient.tableAttribueByServeur();
            request.setAttribute("tab", lt);
            return "/WEB-INF/client/afficherTable.jsp";
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
