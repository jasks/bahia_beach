package beanMetier;

import entities.Produit;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

@Stateless
public class beanCuisine implements beanCuisineLocal {
    
    @PersistenceContext(unitName = "RestaurantPU")
    private EntityManager em;

    public void persist(Object object) {
        em.persist(object);
    }
    
    @Override
    public List<Produit> afficher(){
        String req = "SELECT lc.produit FROM LigneCommande lc "
                + "WHERE lc.etat = 2";
        Query qr = em.createQuery(req);
        return qr.getResultList();
    }
    
}
