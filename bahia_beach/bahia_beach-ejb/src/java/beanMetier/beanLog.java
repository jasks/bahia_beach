/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beanMetier;

import entities.Cuisinier;
import entities.Serveur;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;


@Stateless
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
    
    
//    public Cuisinier connexion(String code){
//        
//        try{
//        
//        String req = "select s from Serveur s "
//                + "where s.code = :code";
//        
//        Query qr = em.createQuery(req);
//        qr.setParameter("code", code);
//        
//        return (Cuisinier) qr.getSingleResult();
//        } catch(NoResultException ex) {
//            return null;
//        }   
//        
//    }
    

    
}
