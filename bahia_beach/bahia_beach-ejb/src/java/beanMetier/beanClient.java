package beanMetier;

import entities.Tablee;
import entities.Type;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;


@Stateless
public class beanClient implements beanClientLocal {
    
    @PersistenceContext(unitName = "RestaurantPU")
    private EntityManager em;

    @Override
    public Tablee selectTable(Long id) {
        Tablee t = em.find(Tablee.class, id);
        return t;
    }
    
    @Override
    public List<Tablee> tableAttribueByServeur() {
        String req = "select s.tables from Serveur s where s.actif = 1 ";
        Query qr = em.createQuery(req);
        return qr.getResultList();
    }
    
   
}
