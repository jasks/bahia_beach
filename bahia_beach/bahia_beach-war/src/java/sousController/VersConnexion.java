package sousController;

import java.io.Serializable;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class VersConnexion implements ControllerInterface, Serializable {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response, HttpServlet servlet) {
      String s = "dans versConnexion";
      request.setAttribute("msg", s);
      return "/WEB-INF/connexion.jsp";
    }
}