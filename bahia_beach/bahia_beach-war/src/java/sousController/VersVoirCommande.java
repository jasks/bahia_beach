
package sousController;

import beanMetier.beanPanierLocal;
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
import javax.servlet.http.HttpSession;

/**
 *
 * @author cdi415
 */
public class VersVoirCommande implements ControllerInterface, Serializable {

    beanVoirCommandeLocal beanVoirCommande = lookupbeanVoirCommandeLocal();
    
    @Override
    public String execute(HttpServletRequest request,
            HttpServletResponse response, HttpServlet servlet) {
        HttpSession session = request.getSession();
        String action = request.getParameter("action");
      
        if ("voirCommande".equalsIgnoreCase(action)) {
            
            Serveur serveur = (Serveur) session.getAttribute("auth");
        //    Serveur serveur= beanVoirCommande.getLeServeur(s.getCode());
            session.setAttribute("serveur", serveur); 
            
            List<Commande> lesCommandes =beanVoirCommande.getLesCommandesEncours(serveur.getCode());     
            request.setAttribute("lesCommandes",lesCommandes );            
            return "/WEB-INF/voirCommande.jsp";
        }
        
        if("lignecommande".equalsIgnoreCase(action)){
            String numCommande=request.getParameter("numCommande");            
            List<LigneCommande> lesLignesCommandes = beanVoirCommande.getAllLigneCommande(request.getParameter("numCommande"));
            request.setAttribute("numCommande",numCommande);
            request.setAttribute("numTable",request.getParameter("numTable"));
            request.setAttribute("lesLignesCommandes", lesLignesCommandes);
            request.setAttribute("propriete",request.getParameter("propriete"));
            
            return "/WEB-INF/ligneCommande.jsp";
        }
        
        if("mettreAjourCommande".equalsIgnoreCase(action)){
            String numCommande = request.getParameter("numCommande");
            beanVoirCommande.mettreAjourCommande(numCommande);            
            return "/WEB-INF/ligneCommande.jsp";
        }
        if("modifierCommande".equalsIgnoreCase(action)){
            String numCommande = request.getParameter("numCommande");
            Commande commande=beanVoirCommande.getCommandeByNum(numCommande);
            List<LigneCommande> lesLignesCommandes = beanVoirCommande.getAllLigneCommande(numCommande);
            commande.setLigneCommandes(lesLignesCommandes);
            request.setAttribute("commande", commande);
            request.setAttribute("lesLignesCommandes", lesLignesCommandes);
            return "/WEB-INF/recapCommande.jsp";
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
