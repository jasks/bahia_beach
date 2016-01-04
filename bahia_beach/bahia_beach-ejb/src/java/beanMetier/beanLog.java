package beanMetier;

import entities.Cuisinier;
import entities.Serveur;
import entities.Tablee;
import javax.ejb.Stateful;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

@Stateful
public class beanLog implements beanLogLocal {

    @PersistenceContext(unitName = "RestaurantPU")
    private EntityManager em;

    @Override
    public Serveur connexionServeur(String code) throws Exception {
        String req = "select s from Serveur s where s.code = :code";
        Query qr = em.createQuery(req);
        qr.setParameter("code", code);
        return (Serveur) qr.getSingleResult();
    }

    @Override
    public Cuisinier connexionCuisinier(String code) throws Exception {
        String req = "select c from Cuisinier c where c.code = :code";
        Query qr = em.createQuery(req);
        qr.setParameter("code", code);
        return (Cuisinier) qr.getSingleResult();
    }
    
    @Override
    public Tablee connexionTablee(String code) throws Exception {
        String req = "select t from Tablee t where t.num = :code AND t.serveur != null";
        Query qr = em.createQuery(req);
        qr.setParameter("code", code);
        return (Tablee) qr.getSingleResult();
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
    
    @Override
    public void setActif(Tablee t, int etat) {
        t.setStatut(etat);
        em.merge(t);
    }
    
    @Override
    public void viderTable(Tablee t) {
        t.setServeur(null);
        em.merge(t);
    }
    
    @Override
    public Serveur logout(String code) {
        // comparer le code avec le code serveur dans la bdd
        String req = "select s from Serveur s where s.code = :code";
        Query qr = em.createQuery(req);
        qr.setParameter("code", code);
        return (Serveur)qr.getSingleResult();
    }
}
