/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sousController;

import java.io.Serializable;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author cdi418
 */
public class VersPage02  implements ControllerInterface, Serializable{

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response, HttpServlet servlet) {
     String s = "dans versPage02";
      request.setAttribute("msg", s);
      return "/WEB-INF/page02.jsp";
    }
    
}
