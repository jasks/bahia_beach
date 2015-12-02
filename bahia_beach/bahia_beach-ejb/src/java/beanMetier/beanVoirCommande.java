/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beanMetier;

import entities.Commande;
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
        System.out.println("voirLesCommandes:::::::::::"+qr.getResultList().get(0));
       return qr.getResultList();
    }
}
