
package sousController;

import java.io.Serializable;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author cdi418
 */
public class VersPage01 implements ControllerInterface, Serializable{

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response, HttpServlet servlet) {
      String s = "dans versPage01";
      request.setAttribute("msg", s);
      return "/WEB-INF/page01.jsp";
    }
    
}
