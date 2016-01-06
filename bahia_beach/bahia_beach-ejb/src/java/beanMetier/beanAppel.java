/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beanMetier;

import com.sun.org.apache.bcel.internal.generic.INSTANCEOF;
import entities.Serveur;
import entities.Tablee;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author kevin
 */
@Stateless
public class beanAppel implements beanAppelLocal {

    @EJB
    private beanServeurLocal beanServeur;

    @PersistenceContext(unitName = "RestaurantPU")
    private EntityManager em;

    @Override
    public void addAppel(Tablee t) {
        t.setCall(1);
        em.merge(t);
    }

    @Override
    public void responseAppel(Tablee t) {
        t.setCall(0);
        em.merge(t);
    }

    @Override
    public List<Tablee> afficherAppelTable(Serveur s) {
        List<Tablee> lt = new ArrayList();
        //pour chaque Table que le serveur a attribué

        for (Tablee t : this.beanServeur.afficherTableAttribue(s)) {
//           //si une de ces tables a appelé le serveur, donc getCall() == 1
            if (t != null) {
                if (t.getCall() == 1) {
//               //on ajoute cette table à la liste d'AppelTable
               lt.add(t);
                }
                System.out.println("table : " + t);
            }
//           
        }
        System.out.println(this.beanServeur.afficherTableAttribue(s).size());
        return lt;

    }

    @Override
    public int getNombreAppel(Serveur s) {
//           if((Integer)this.afficherAppelTable(s).size() instanceof Integer) {
//        return this.afficherAppelTable(s).size();
//           } else
//           
//               return 0;
        return this.afficherAppelTable(s).size();
    }

}
