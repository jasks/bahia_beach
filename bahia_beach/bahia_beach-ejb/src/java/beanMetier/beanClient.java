package beanMetier;

import entities.Serveur;
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
     
    @Override
    public Tablee appelerServeur(String numTable){
        String req = "select t from Tablee t where t.num= :valeur";
        Query qr = em.createQuery(req);
        qr.setParameter("valeur", numTable);
        System.out.println("tablle "+qr.getSingleResult().toString());
        
        return (Tablee) qr.getSingleResult();
    }

   
}
