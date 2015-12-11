package sousController;

import beanMetier.beanCuisineLocal;
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

public class Cuisine implements ControllerInterface, Serializable {

    beanCuisineLocal beanCuisine = lookupbeanCuisineLocal();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response, HttpServlet servlet) {
        HttpSession session = request.getSession();
        String action = request.getParameter("action");

        if ("afficherCuisine".equalsIgnoreCase(action)) {
            request.setAttribute("cmd", beanCuisine.afficherCommande());
            return "/WEB-INF/cuisine.jsp";
        }

        if ("modifierEtat".equalsIgnoreCase(action)) {
            Long id = Long.parseLong(request.getParameter("id"));
            //request.setAttribute("mod", beanCuisine.afficherCommande());
            request.setAttribute("cmd", beanCuisine.afficherCommande());
            return "/WEB-INF/cuisine.jsp";
        }
        return "/WEB-INF/index.jsp";
    }

    private beanCuisineLocal lookupbeanCuisineLocal() {
        try {
            Context c = new InitialContext();
            return (beanCuisineLocal) c.lookup("java:global/bahia_beach/bahia_beach-ejb/beanCuisine!beanMetier.beanCuisineLocal");
        } catch (NamingException ne) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", ne);
            throw new RuntimeException(ne);
        }
    }

}
