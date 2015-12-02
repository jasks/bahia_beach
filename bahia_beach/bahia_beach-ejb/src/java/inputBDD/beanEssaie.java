package inputBDD;

import entities.Categorie;
import entities.Commande;
import entities.Commentaire;
import entities.Coordonnee;
import entities.Cuisinier;
import entities.LigneCommande;
import entities.Menu;
import entities.Produit;
import entities.QualiteNutritive;
import entities.Serveur;
import entities.Tablee;
import entities.Tva;
import entities.Type;
import java.util.ArrayList;
import java.util.Date;
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
//COORDONNEE--------------------------------------------------------------------
        List<Coordonnee> lcoordonnee = new ArrayList();
        lcoordonnee.add(new Coordonnee("3, rue de la baguette au fromage", "Paris", "75000"));
        lcoordonnee.add(new Coordonnee("7, avenue de la chance", "Las Vegas", "77777"));
        lcoordonnee.add(new Coordonnee("63, boulevard du brouillard épais", "Silent Hill", "60006"));

//CUISINIER---------------------------------------------------------------------
        List<Cuisinier> lcuisinier = new ArrayList();
        lcuisinier.add(new Cuisinier("VERSAIRE", "Annie", "C2001"));
        lcuisinier.add(new Cuisinier("ROUETTE", "Sanji", "C2002"));
        lcuisinier.add(new Cuisinier("BLEU", "Gordon", "C2003"));
        lcuisinier.get(0).setCoordonnee(lcoordonnee.get(0));
        lcuisinier.get(2).setCoordonnee(lcoordonnee.get(1));
        lcuisinier.get(1).setCoordonnee(lcoordonnee.get(2));

        for (Cuisinier cuisinier : lcuisinier) {
            em.persist(cuisinier);
        }

//SERVEUR-----------------------------------------------------------------------
        List<Serveur> lserveur = new ArrayList();
        lserveur.add(new Serveur("LAWRENCE", "Jennifer", "S3001"));
        lserveur.add(new Serveur("STONE", "Emma", "S3002"));
        lserveur.add(new Serveur("ANISTON", "Jennifer", "S3003"));
        lserveur.get(2).setCoordonnee(lcoordonnee.get(1));
        lserveur.get(0).setCoordonnee(lcoordonnee.get(0));
        lserveur.get(1).setCoordonnee(lcoordonnee.get(2));

        for (Serveur serveur : lserveur) {
            em.persist(serveur);
        }

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
//QUALITE NUTRITIVE-------------------------------------------------------------        
        QualiteNutritive qn01 = new QualiteNutritive(610, 7, 19, 3, 4, 1, 362);
        QualiteNutritive qn02 = new QualiteNutritive(266, 2, 16, 5, 19, 9, 12);
      
//PRODUIT-----------------------------------------------------------------------
        List<Produit> lp = new ArrayList();
        lp.add(new Produit("Fusilli à la Bolognaises",
                14.0f,
                "Une sauce à base de tomates, de viande de bœuf, d'oignons et de carottes, cuisinée avec du laurier et du romarin qui vient napper des fusilli",
                "/Photo/Fusilli_Bolognaise.png",
                "Selon la recette traditionnelle mentionnée en 1982 par la délégation bolognaise de l'Accademia Italiana della Cucina, la liste des ingrédients ne comprend que : bœuf, pancetta, oignons, carottes, céleri, sauce tomate, mie de pain, vin rouge ou vin blanc sec, lait ou crème.",
                qn01));
        lp.add(new Produit("Tomates Mozzarella",
                9.0f,
                "La mozzarella est un fromage à pâte filée d'origine italienne à base de lait de vache ou de bufflonne.",
                "/Photo/Tomate_Mozzarella.png",
                "La mozzarella di Bufala Campana, produite en Campanie avec du lait de bufflonne, est l'objet d'une appellation d'origine protégée (AOP) depuis 1996. En Italie, la mozzarella de lait de vache est fabriquée et commercialisée sous le nom de fior di latte ou de bocconcini. Ailleurs dans le monde, la mozzarella est un fromage industriel qui fait partie des fromages de lait de vache les plus produits et consommés. Ce fromage industriel, commercialisé sous le nom de mozzarella, crée une confusion avec la mozzarella traditionnelle au lait de bufflonne.",
                qn02));
        
        for (Produit p : lp) {
            em.persist(p);
        }

//TVA---------------------------------------------------------------------------
        List<Tva> ltva = new ArrayList();
        ltva.add(new Tva(0.1f, "Tva Restauration", new Date(2015 - 11 - 30)));

        for (Tva tva : ltva) {
            em.persist(tva);
        }


//MENU--------------------------------------------------------------------------
        List<Menu> lmenu = new ArrayList();
        lmenu.add(new Menu("Gourmand", 19.99F));
        lmenu.add(new Menu("Végétarien", 6.99F));

        for (Menu menu : lmenu) {
            em.persist(menu);
        }

//COMMENTAIRE-------------------------------------------------------------------
        List<Commentaire> lcommentaire = new ArrayList();
        lcommentaire.add(new Commentaire("Sans sauce."));
        lcommentaire.add(new Commentaire("Sans sauce, sans tomate, sans oignon, sans pain, sans viande, sans légume, sans serviette."));
        
        for (Commentaire commentaire : lcommentaire) {
            em.persist(commentaire);
        }

//LIGNE DE COMMANDE-------------------------------------------------------------
        List<LigneCommande> llc = new ArrayList();
        llc.add(new LigneCommande(0, 0));
        llc.add(new LigneCommande(1, 0));
        llc.add(new LigneCommande(2, 1));
        llc.add(new LigneCommande(3, 2));

        for (LigneCommande lc : llc) {
            em.persist(lc);
        }

//COMMANDE----------------------------------------------------------------------
        List<Commande> lcommande = new ArrayList();
        lcommande.add(new Commande("CMD01", 0, new Date(2015 - 11 - 25)));
        lcommande.add(new Commande("CMD02", 2, new Date(2015 - 11 - 20)));
        lcommande.add(new Commande("CMD03", 1, new Date(2015 - 11 - 28)));

        for (Commande commande : lcommande) {
            em.persist(commande);
        }

//TABLEE------------------------------------------------------------------------
        List<Tablee> ltablee = new ArrayList();
        ltablee.add(new Tablee("T01", 4, 1));
        ltablee.add(new Tablee("T02", 6, 0));
        ltablee.add(new Tablee("T03", 2, 2));

        for (Tablee tablee : ltablee) {
            em.persist(tablee);
        }
        
//ASSOCIATION-------------------------------------------------------------------
        
    }
}
