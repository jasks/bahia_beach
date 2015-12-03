/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beanMetier;

import entities.Commande;
import entities.LigneCommande;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author cdi415
 */
@Stateless
public class beanVoirCommande implements beanVoirCommandeLocal {

    @PersistenceContext(unitName = "RestaurantPU")
    private EntityManager em;
  
    @Override
    public List<Commande> voirLesCommandesEnCours() {
       String req = "select c from Commande c " ;
        
       Query qr= em.createQuery(req);
       System.out.println("rq::::::::::::"+qr);
       return qr.getResultList();
    }
    @Override
    public List<LigneCommande> voirLesLignesDeCommandes(){
     String req = "select lc from LigneCommande lc join c Commande c";
     Query qr= em.createQuery(req);
     return qr.getResultList();
    }
}
