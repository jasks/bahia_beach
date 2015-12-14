package sousController;

import beanMetier.beanConnexionLocal;
import java.io.Serializable;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class VersConnexion implements ControllerInterface, Serializable {

    beanConnexionLocal beanConnexion = lookupbeanConnexionLocal();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response, HttpServlet servlet) {
        String s = "dans versConnexion";
        request.setAttribute("msg", s);
        if (request.getParameter("valider") != null) {
            return beanConnexion.verifCode(request.getParameter("code"));
        } else {
            return "/WEB-INF/connexion.jsp";
        }
    }

    private beanConnexionLocal lookupbeanConnexionLocal() {
        try {
            Context c = new InitialContext();
            return (beanConnexionLocal) c.lookup("java:global/bahia_beach/bahia_beach-ejb/beanConnexion!beanMetier.beanConnexionLocal");
        } catch (NamingException ne) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", ne);
            throw new RuntimeException(ne);
        }
    }
}
