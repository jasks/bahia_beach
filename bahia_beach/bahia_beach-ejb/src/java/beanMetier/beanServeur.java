
package beanMetier;

import entities.Serveur;
import entities.Tablee;
import java.util.List;
import javax.ejb.Stateful;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;


@Stateful
public class beanServeur implements beanServeurLocal {

    @PersistenceContext(unitName = "RestaurantPU")
    private EntityManager em;
    

    
    @Override
    public List<Tablee> afficherTable() {
        String req = "select t from Tablee t";
        Query qr = em.createQuery(req);
        return qr.getResultList();
    }
    
    @Override
    public List<Tablee> afficherTableLibre() {
        String req = "select t from Tablee t where t.statut = 0";
        Query qr = em.createQuery(req);
        return qr.getResultList();
    }
    
    @Override
    public List<Tablee> afficherTableAttribue(Serveur s) {
        String req = "select s.tables from Serveur s where s =:serveur";
        Query qr = em.createQuery(req);
        qr.setParameter("serveur", s);
        List<Tablee> ltbl = qr.getResultList();
        return ltbl;
    }
    
    @Override
    public void attribuerTable(Serveur s, Long id) {
        Tablee t = em.find(Tablee.class, id); //on identifie l'objet table en question
        t.setStatut(1); // statut à 1
        //s.getTables().add(t); // on met la table ds le serveur
        t.setServeur(s);
        em.merge(t);
    }
    
    @Override
    public Tablee validerNomTable(String num) throws NoResultException{
        
        String req = "select t from Tablee t "
                + "where t.num = :num";
        
        Query qr = em.createQuery(req);
        qr.setParameter("num", num);
        
        return (Tablee) qr.getSingleResult();
    }
    
    @Override
    public Serveur getServeur(Long id) {
        Serveur s = em.find(Serveur.class, id);
        return s;
    }
    
    @Override
    public Tablee getTablee(Long id) {
        Tablee t = em.find(Tablee.class, id);
        return t;
    }
}
