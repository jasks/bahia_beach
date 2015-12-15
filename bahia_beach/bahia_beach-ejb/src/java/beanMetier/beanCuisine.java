package beanMetier;

import entities.Commande;
import entities.LigneCommande;
import java.util.List;
import java.util.Set;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.websocket.Session;

@Stateless
public class beanCuisine implements beanCuisineLocal {

    @PersistenceContext(unitName = "RestaurantPU")
    private EntityManager em;

    public void persist(Object object) {
        em.persist(object);
    }

    @Override
    public List<LigneCommande> afficher() {
        String req = "SELECT lc FROM LigneCommande lc "
                + "WHERE lc.etat <> 4 ";
        Query qr = em.createQuery(req);
        return qr.getResultList();
    }

    @Override
    public Commande add(String id) {
        Long idCommande = Long.parseLong(id);
        return em.find(Commande.class, idCommande);
    }

}
