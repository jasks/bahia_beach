package sousController;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public interface ControllerInterface {
    public String execute(HttpServletRequest request, HttpServletResponse response, HttpServlet servlet);
}
