/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sousController;

import beanMetier.beanCarteLocal;
import entities.Produit;
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
public class Carte implements ControllerInterface, Serializable {
    beanCarteLocal beanCarte = lookupbeanCarteLocal();
    
    

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response, HttpServlet servlet) {
        
        String action = request.getParameter("action");
        
        if("produits".equalsIgnoreCase(action)) {
            List<Produit> lp = beanCarte.selectAllproduit();
               request.setAttribute("produits", lp);
               request.setAttribute("msg", "affiche liste produit");
               return "/WEB-INF/carte.jsp";
        }
        
        return "/WEB-INF/index.jsp";
      }

    private beanCarteLocal lookupbeanCarteLocal() {
        try {
            Context c = new InitialContext();
            return (beanCarteLocal) c.lookup("java:global/bahia_beach/bahia_beach-ejb/beanCarte!beanMetier.beanCarteLocal");
        } catch (NamingException ne) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", ne);
            throw new RuntimeException(ne);
        }
    }
    
    
    
}
