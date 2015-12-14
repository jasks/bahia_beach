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
    public List<Serveur> getLeServeur() {
        Serveur s01=em.find(Serveur.class, 3L);
         List<Serveur> liste = new ArrayList();
         liste.add(s01);

         return liste;
    }     
    
   
    @Override
    public List<Serveur> getLeServeur(String code){
        String req="select s from Serveur s where s.code= :valeur";
        Query qr= em.createQuery(req);
        qr.setParameter("valeur", code);  
        List<Serveur> list=qr.getResultList();
        return qr.getResultList();
    }
    
   
    @Override
    public List<Commande> getLesCommandesEncours(String codeServeur){
        String r="select s.commandes from Serveur s where s.code= :valeur  ";
        String req="select c from Commande c join Serveur s on c.id=s.id"
                + " where s.code= :valeur and c.etat < :valeur1";
            
        Query qr = em.createQuery(r);
        qr.setParameter("valeur", codeServeur);
//        qr.setParameter("valeur1", 3);
        
        System.out.println("taille donnees:::::::::::::::::"+qr.getResultList().size());  
        return qr.getResultList();
    }
    
  
    @Override
    public List<Produit> getLesProduits(String numCommande){

       String req ="select lc.produit from LigneCommande lc where lc.commande.numero = :valeur";     
       Query qr=em.createQuery(req);
       qr.setParameter("valeur",numCommande);
       return qr.getResultList();
    }

}
