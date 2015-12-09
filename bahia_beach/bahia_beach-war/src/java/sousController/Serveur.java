/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sousController;

import beanMetier.beanServeurLocal;
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

/**
 *
 * @author cdi418
 */
public class Serveur implements ControllerInterface, Serializable{
    beanServeurLocal beanServeur = lookupbeanServeurLocal();

    
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response, HttpServlet servlet) {
      
        List<Tablee> lt = beanServeur.afficherTable();
        request.setAttribute("tables", lt);
        
         return "/WEB-INF/serveur/log.jsp";
    }

    private beanServeurLocal lookupbeanServeurLocal() {
        try {
            Context c = new InitialContext();
            return (beanServeurLocal) c.lookup("java:global/bahia_beach/bahia_beach-ejb/beanServeur!beanMetier.beanServeurLocal");
        } catch (NamingException ne) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", ne);
            throw new RuntimeException(ne);
        }
    }
    
    
}
