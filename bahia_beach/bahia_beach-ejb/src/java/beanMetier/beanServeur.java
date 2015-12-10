
package beanMetier;

import entities.Serveur;
import entities.Tablee;
import java.util.List;
import javax.ejb.Stateful;
import javax.persistence.EntityManager;
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
    public List<Tablee> attribuerTable(Serveur s, Long id) {
        Tablee t = em.find(Tablee.class, id); //on identifie l'objet table en question
        t.setStatut(1); // statut à 1
        s.getTables().add(t); // on met la table ds le serveur
        em.persist(t);
        em.persist(s);
        return (List<Tablee>) s.getTables();
    }
    
}
