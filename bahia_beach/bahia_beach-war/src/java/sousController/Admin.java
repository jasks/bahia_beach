package sousController;

import inputBDD.beanEssaieLocal;
import java.io.Serializable;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Admin implements ControllerInterface, Serializable {

    beanEssaieLocal beanEssaie = lookupbeanEssaieLocal();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response, HttpServlet servlet) {

        String action = request.getParameter("action");

        if ("jeuxDonnee".equalsIgnoreCase(action)) {
            beanEssaie.creerJeuxDonnees();
            String s = "Jeux de données crées. Vérifier dans votre base de données";
            request.setAttribute("msg", s);
            return "/WEB-INF/index.jsp";
        }

        return "/WEB-INF/index.jsp";

    }

    private beanEssaieLocal lookupbeanEssaieLocal() {
        try {
            Context c = new InitialContext();
            return (beanEssaieLocal) c.lookup("java:global/bahia_beach/bahia_beach-ejb/beanEssaie!inputBDD.beanEssaieLocal");
        } catch (NamingException ne) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", ne);
            throw new RuntimeException(ne);
        }
    }

}
