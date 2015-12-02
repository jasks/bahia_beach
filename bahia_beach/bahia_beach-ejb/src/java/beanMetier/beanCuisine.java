package beanMetier;

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

    public void persist(Object object) {
        em.persist(object);
    }
    
    @Override
    public List<LigneCommande> afficher(){
        System.out.println(">>>>>>>>DEBUT AFFICHER<<<<<<<<");
        String req = "SELECT lc FROM LigneCommande lc "
                + "WHERE lc.etat = 2";
        Query qr = em.createQuery(req);
        List<LigneCommande> llc = qr.getResultList();
        System.out.println(">>>>>>>>MILIEU AFFICHER<<<<<<<<");
        for(LigneCommande lc : llc){
            //System.out.println(lc.getCommande().getNumero());
            System.out.println(lc.getProduit());
            System.out.println("-------------");
        }
        System.out.println(">>>>>>>>FIN AFFICHER<<<<<<<<");
        return qr.getResultList();
    }

}
