/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beanMetier;

import entities.Commande;
import entities.LigneCommande;
import entities.Produit;
import entities.Serveur;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;


@Stateless
public class beanVoirCommande implements beanVoirCommandeLocal {

    @PersistenceContext(unitName = "RestaurantPU")
    private EntityManager em;    
  
    @Override
    public Serveur getLeServeur(Long id) {
        Serveur s01=em.find(Serveur.class, id);
        
         return s01;
    }     
    
   
    @Override
    public Serveur getLeServeur(String code){
        String req="select s from Serveur s where s.code= :valeur";
        Query qr= em.createQuery(req);
        qr.setParameter("valeur", code);  
        
        return (Serveur) qr.getSingleResult();
    }
    
   
    @Override
    public List<Commande> getLesCommandesEncours(String codeServeur){
        String r="select s.commandes from Serveur s where s.code= :valeur  ";      
        Query qr = em.createQuery(r);
        qr.setParameter("valeur", codeServeur);
        List<Commande> liste =qr.getResultList();
        for(Commande c : liste){
            if (c.getEtat()==2){
                liste.remove(c);  
            }
        }  
        return liste;
    }
   
 
    @Override
    public List<LigneCommande> getLesLignes(String numCommande){
        String req = "select lc from LigneCommande lc where lc.commande.numero= :valeur";
        Query qr = em.createQuery(req);
        qr.setParameter("valeur", numCommande);
        return qr.getResultList();
    }
  
 

}
