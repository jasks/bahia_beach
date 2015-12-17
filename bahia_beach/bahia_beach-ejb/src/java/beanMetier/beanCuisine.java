package beanMetier;

import entities.Commande;
import entities.LigneCommande;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

@Stateless
public class beanCuisine implements beanCuisineLocal {

    @PersistenceContext(unitName = "RestaurantPU")
    private EntityManager em;
    
    //Methode d'affichage des plats commandés
    @Override
    public List<LigneCommande> afficher() {
        String req = "SELECT lc FROM LigneCommande lc "
                + "WHERE lc.etat <> 4 ";
        Query qr = em.createQuery(req);
        return qr.getResultList();
    }
    
    //Methode d'ajout de plat commandés
    @Override
    public Commande add(String id) {
        Long idCommande = Long.parseLong(id);
        return em.find(Commande.class, idCommande);
    }
    
    //Methode de changement d'état d'un plat commandés
    @Override
    public LigneCommande toggle(String id) {
        Long idLc = Long.parseLong(id);
        return em.find(LigneCommande.class, idLc);
    }
}
