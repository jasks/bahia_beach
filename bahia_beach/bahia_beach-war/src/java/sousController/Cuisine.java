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

public class Cuisine implements ControllerInterface, Serializable {

    beanCuisineLocal beanCuisine = lookupbeanCuisineLocal();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response, HttpServlet servlet) {
      request.setAttribute("cmd", beanCuisine.afficher());
      return "/WEB-INF/cuisine.jsp";
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
