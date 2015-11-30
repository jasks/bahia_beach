package inputBDD;

import entities.Categorie;
import entities.Produit;
import entities.Type;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
public class beanEssaie implements beanEssaieLocal {

    @PersistenceContext(unitName = "RestaurantPU")
    private EntityManager em;

    @Override
    public void creerJeuxDonnees() {
//TYPE--------------------------------------------------------------------------
        List<Type> lty = new ArrayList();
        lty.add(new Type("Entrée"));
        lty.add(new Type("Plat"));
        lty.add(new Type("Dessert"));
        lty.add(new Type("Boisson"));

        for (Type ty : lty) {
            em.persist(ty);
        }

//CATEGORIE---------------------------------------------------------------------
        List<Categorie> lca = new ArrayList();
        lca.add(new Categorie("Crudité"));
        lca.add(new Categorie("Salade"));
        lca.add(new Categorie("Viande"));
        lca.add(new Categorie("Poisson"));
        lca.add(new Categorie("Légume"));
        lca.add(new Categorie("Alcool"));
        lca.add(new Categorie("Soda"));
        lca.add(new Categorie("Boisson Chaude"));
        lca.add(new Categorie("Glace"));
        lca.add(new Categorie("Pâtisserie"));
        lca.add(new Categorie("Fruit"));
        lca.add(new Categorie("Fromage"));
        lca.add(new Categorie("Yaourt"));

        for (Categorie ca : lca) {
            em.persist(ca);
        }

//PRODUIT---------------------------------------------------------------------
        List<Produit> lp = new ArrayList();
        //lp.add(new Produit(nomProduit, prixHT, descritption, image, qualiteNutritive, historique));
 

        for (Produit p : lp) {
            em.persist(p);
        }

    }
}
