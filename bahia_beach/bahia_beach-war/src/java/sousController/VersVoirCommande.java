/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sousController;

import beanMetier.beanVoirCommandeLocal;
import entities.Commande;

import entities.LigneCommande;
import entities.Produit;
import entities.Serveur;
import entities.Tablee;
import entities.Produit;
import entities.Serveur;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
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
    public String execute(HttpServletRequest request,
            HttpServletResponse response, HttpServlet servlet) {
    
        String s = "dans voir la commande";
        String action = request.getParameter("action");
      
        if ("voirCommande".equalsIgnoreCase(action)) {
            
            List<Serveur> serveur= beanVoirCommande.getLeServeur("S3001");
            request.setAttribute("serveur", serveur);
            
            System.out.println("serveur code>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>><<<"+serveur.get(0).getCode());
            
            List<Commande>  lesCommandes = beanVoirCommande.getLesCommandesEncours(serveur.get(0).getCode());
            
            request.setAttribute("lesCommandes",lesCommandes );
            
            System.out.println("nombre de commande::::::"+lesCommandes.size());
            HashMap<String,List<Produit>> mp= new HashMap();
            for(int i=0;i<lesCommandes.size();i++){
                System.out.println("index:::::::::::::"+lesCommandes.get(i).getNumero());
            List<Produit> lesProduits= beanVoirCommande.getLesProduits(lesCommandes.get(i).getNumero());
            mp.put(lesCommandes.get(i).getNumero(),lesProduits );
            }
          
            System.out.println(">>>>>>>>>>>>>>>>>>Parcourt hashMap"+mp.size());
            for(String c:mp.keySet()){
                mp.get(c);
                System.out.println(mp.keySet()+">>>>>>>>>>>>>>>>>>>"+mp.get(c));
            }
            System.out.println(" Fin>>>>>>>>>>>>>>>>>>Parcourt hashMap");            
            request.setAttribute("lesProduits",mp);
           
            return "/WEB-INF/voirCommande.jsp";
        }

        return "/WEB-INF/index.jsp";
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
