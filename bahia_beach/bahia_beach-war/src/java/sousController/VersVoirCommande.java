/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sousController;

import beanMetier.beanVoirCommande;
import beanMetier.beanVoirCommandeLocal;
import entities.Commande;
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
 * @author cdi415
 */
public class VersVoirCommande implements ControllerInterface, Serializable {

    beanVoirCommandeLocal beanVoirCommande = lookupbeanVoirCommandeLocal();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response, HttpServlet servlet) {
        System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>VersVoirCommande");
        String s = "dans voir la commande";
        String action = request.getParameter("action");
        System.out.println("action>>>>>>>>>>>>>>>>>>>>"+action);

        if ("voirCommande".equalsIgnoreCase(action)) {
            System.out.println(">>>>>>>>>>>>>if");

            List<Commande> lesCommandes = beanVoirCommande.voirLesCommandesEnCours();
            request.setAttribute("lescommandes", lesCommandes);
            for(Commande c : lesCommandes){
                System.out.println(":::::::::::::::"+c.getNumero());
            }
            request.setAttribute("msg", s);
            return "/WEB-INF/voirCommande.jsp";
        }

        return "/WEB-INF/voirCommande.jsp";
    }

    private beanVoirCommandeLocal lookupbeanVoirCommandeLocal() {
        try {
            Context c = new InitialContext();
            return (beanVoirCommandeLocal) c.lookup("java:global/bahia_beach/bahia_beach-ejb/beanVoirCommande!beanMetier.beanVoirCommandeLocal");
        } 
        catch(NamingException ne){
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", ne);
            throw new RuntimeException(ne);
        }
    }

    

}
