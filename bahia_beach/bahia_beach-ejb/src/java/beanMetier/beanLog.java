/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beanMetier;

import entities.Cuisinier;
import entities.Serveur;
import javax.ejb.Stateful;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;


@Stateful
public class beanLog implements beanLogLocal {

    @PersistenceContext(unitName = "RestaurantPU")
    private EntityManager em;
    
    
    @Override
    public Serveur connexionServeur(String code) throws Exception{

        
        String req = "select s from Serveur s "
                + "where s.code = :code";
        
        Query qr = em.createQuery(req);
        qr.setParameter("code", code);
        
        return (Serveur) qr.getSingleResult();

    }
    
    @Override
    public Cuisinier connexionCuisinier(String code) throws Exception{
        
        String req = "select c from Cuisinier c "
                + "where c.code = :code";
        
        Query qr = em.createQuery(req);
        qr.setParameter("code", code);
        
        return (Cuisinier) qr.getSingleResult();
    }

    
    @Override
    public void setActif(Serveur s, int etat) {
        s.setActif(etat);
        em.merge(s);
    }
    
    @Override
    public void setActif(Cuisinier c, int etat) {
        c.setActif(etat);
        em.merge(c);
    }
    

    
}
