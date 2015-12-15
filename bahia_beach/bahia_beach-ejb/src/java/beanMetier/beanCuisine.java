package beanMetier;

import entities.Commande;
import entities.LigneCommande;
import entities.Produit;
import java.util.List;
import javax.ejb.Stateless;
import javax.json.JsonObject;
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
            System.out.println(lc.getCommande().getNumero());
            System.out.println(lc.getProduit());
            System.out.println("-------------");
        }
        System.out.println(">>>>>>>>FIN AFFICHER<<<<<<<<");
        return qr.getResultList();
    }
    
    //----------------------------------------------------------------
    @Override
     public List<LigneCommande> afficherProduitCommande(String numcommande){
        String req = "SELECT lc.produit FROM LigneCommande lc "
                + "WHERE lc.commande = :numcommande";
        Query qr = em.createQuery(req);
        qr.setParameter("numcommande", numcommande);
        return qr.getResultList();
    }
    
    @Override
      public List<LigneCommande> afficherLigneCommande(){
        String req = "SELECT lc FROM LigneCommande lc";
        Query qr = em.createQuery(req);
        return qr.getResultList();
    }
      
    @Override
      public List<Commande> afficherCommande(){
        String req = "SELECT cmd FROM Commande cmd";
        Query qr = em.createQuery(req);
        return qr.getResultList();
      }
      
      /*
      public 
      */
      
      
}

