package inputBDD;

import entities.Categorie;
import entities.Commande;
import entities.Commentaire;
import entities.Coordonnee;
import entities.Cuisinier;
import entities.Famille;
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
        Coordonnee coo01 = new Coordonnee("3, rue de la baguette au fromage", "Paris", "75000");
        Coordonnee coo02 = new Coordonnee("7, avenue de la chance", "Las Vegas", "77777");
        Coordonnee coo03 = new Coordonnee("63, boulevard du brouillard épais", "Silent Hill", "60006");

//CUISINIER---------------------------------------------------------------------
        List<Cuisinier> lcuisinier = new ArrayList();
        lcuisinier.add(new Cuisinier("VERSAIRE", "Annie", "C2001", coo01));
        lcuisinier.add(new Cuisinier("ROUETTE", "Sanji", "C2002", coo02));
        lcuisinier.add(new Cuisinier("BLEU", "Gordon", "C2003"));

//SERVEUR-----------------------------------------------------------------------
        List<Serveur> lserveur = new ArrayList();
        lserveur.add(new Serveur("LAWRENCE", "Jennifer", "S3001"));
        lserveur.add(new Serveur("STONE", "Emma", "S3002", coo03));
        lserveur.add(new Serveur("ANISTON", "Jennifer", "S3003",coo01));

//TYPE--------------------------------------------------------------------------
        List<Type> lty = new ArrayList();
        lty.add(new Type("Entrée"));
        lty.add(new Type("Plat"));
        lty.add(new Type("Dessert"));
        lty.add(new Type("Boisson"));

//FAMILLE--------------------------------------------------------------------------
        List<Famille> lf = new ArrayList();
        lf.add(new Famille("gourmand"));
        lf.add(new Famille("vegetarien"));

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

//QUALITE NUTRITIVE-------------------------------------------------------------        
        QualiteNutritive qn01 = new QualiteNutritive(610, 7, 19, 3, 4, 1, 362);
        QualiteNutritive qn02 = new QualiteNutritive(266, 2, 16, 5, 19, 9, 12);

//TVA---------------------------------------------------------------------------
        List<Tva> ltva = new ArrayList();
        ltva.add(new Tva(0.1f, "Tva Restauration", new Date(2015 - 11 - 30)));

//PRODUIT-----------------------------------------------------------------------
        List<Produit> lp = new ArrayList();
        //plat-------------------------------
        lp.add(new Produit("Fusilli à la Bolognaises",
                14.0f,
                "Une sauce à base de tomates, de viande de bœuf, d'oignons et de carottes, cuisinée avec du laurier et du romarin qui vient napper des fusilli",
                "/Photo/Fusilli_Bolognaise.png",
                "Selon la recette traditionnelle mentionnée en 1982 par la délégation bolognaise de l'Accademia Italiana della Cucina, la liste des ingrédients ne comprend que : bœuf, pancetta, oignons, carottes, céleri, sauce tomate, mie de pain, vin rouge ou vin blanc sec, lait ou crème.",
                lca.get(2),
                lty.get(1),
                lf.get(0),
                ltva.get(0),
                qn01));
        lp.add(new Produit("lasagne",
                14.0f,
                "Une sauce à base de tomates, de viande de bœuf, d'oignons et de carottes, cuisinée avec du laurier et du romarin qui vient napper des fusilli",
                "/Photo/Fusilli_Bolognaise.png",
                "Selon la recette traditionnelle mentionnée en 1982 par la délégation bolognaise de l'Accademia Italiana della Cucina, la liste des ingrédients ne comprend que : bœuf, pancetta, oignons, carottes, céleri, sauce tomate, mie de pain, vin rouge ou vin blanc sec, lait ou crème.",
                lca.get(2),
                lty.get(1),
                lf.get(0),
                ltva.get(0),
                qn01));
        lp.add(new Produit("steak frite",
                14.0f,
                "Une sauce à base de tomates, de viande de bœuf, d'oignons et de carottes, cuisinée avec du laurier et du romarin qui vient napper des fusilli",
                "/Photo/Fusilli_Bolognaise.png",
                "Selon la recette traditionnelle mentionnée en 1982 par la délégation bolognaise de l'Accademia Italiana della Cucina, la liste des ingrédients ne comprend que : bœuf, pancetta, oignons, carottes, céleri, sauce tomate, mie de pain, vin rouge ou vin blanc sec, lait ou crème.",
                lca.get(2),
                lty.get(1),
                lf.get(0),
                ltva.get(0),
                qn01));
        lp.add(new Produit("gratin aux aubergines",
                14.0f,
                "Une sauce à base de tomates, de viande de bœuf, d'oignons et de carottes, cuisinée avec du laurier et du romarin qui vient napper des fusilli",
                "/Photo/Fusilli_Bolognaise.png",
                "Selon la recette traditionnelle mentionnée en 1982 par la délégation bolognaise de l'Accademia Italiana della Cucina, la liste des ingrédients ne comprend que : bœuf, pancetta, oignons, carottes, céleri, sauce tomate, mie de pain, vin rouge ou vin blanc sec, lait ou crème.",
                lca.get(2),
                lty.get(1),
                lf.get(1),
                ltva.get(0),
                qn01));
        lp.add(new Produit("soja maxxi mix",
                14.0f,
                "Une sauce à base de tomates, de viande de bœuf, d'oignons et de carottes, cuisinée avec du laurier et du romarin qui vient napper des fusilli",
                "/Photo/Fusilli_Bolognaise.png",
                "Selon la recette traditionnelle mentionnée en 1982 par la délégation bolognaise de l'Accademia Italiana della Cucina, la liste des ingrédients ne comprend que : bœuf, pancetta, oignons, carottes, céleri, sauce tomate, mie de pain, vin rouge ou vin blanc sec, lait ou crème.",
                lca.get(2),
                lty.get(1),
                lf.get(1),
                ltva.get(0),
                qn01));
        lp.add(new Produit("pizza 4 fromages",
                14.0f,
                "Une sauce à base de tomates, de viande de bœuf, d'oignons et de carottes, cuisinée avec du laurier et du romarin qui vient napper des fusilli",
                "/Photo/Fusilli_Bolognaise.png",
                "Selon la recette traditionnelle mentionnée en 1982 par la délégation bolognaise de l'Accademia Italiana della Cucina, la liste des ingrédients ne comprend que : bœuf, pancetta, oignons, carottes, céleri, sauce tomate, mie de pain, vin rouge ou vin blanc sec, lait ou crème.",
                lca.get(2),
                lty.get(1),
                lf.get(1),
                ltva.get(0),
                qn01));
        
        //entree-------------------------------
        lp.add(new Produit("briques",
                9.0f,
                "La mozzarella est un fromage à pâte filée d'origine italienne à base de lait de vache ou de bufflonne.",
                "/Photo/Tomate_Mozzarella.png",
                "La mozzarella di Bufala Campana, produite en Campanie avec du lait de bufflonne, est l'objet d'une appellation d'origine protégée (AOP) depuis 1996. En Italie, la mozzarella de lait de vache est fabriquée et commercialisée sous le nom de fior di latte ou de bocconcini. Ailleurs dans le monde, la mozzarella est un fromage industriel qui fait partie des fromages de lait de vache les plus produits et consommés. Ce fromage industriel, commercialisé sous le nom de mozzarella, crée une confusion avec la mozzarella traditionnelle au lait de bufflonne.",
                lca.get(1),
                lty.get(0),
                lf.get(0),
                ltva.get(0),
                qn02));
        lp.add(new Produit("tajine tounsie",
                9.0f,
                "La mozzarella est un fromage à pâte filée d'origine italienne à base de lait de vache ou de bufflonne.",
                "/Photo/Tomate_Mozzarella.png",
                "La mozzarella di Bufala Campana, produite en Campanie avec du lait de bufflonne, est l'objet d'une appellation d'origine protégée (AOP) depuis 1996. En Italie, la mozzarella de lait de vache est fabriquée et commercialisée sous le nom de fior di latte ou de bocconcini. Ailleurs dans le monde, la mozzarella est un fromage industriel qui fait partie des fromages de lait de vache les plus produits et consommés. Ce fromage industriel, commercialisé sous le nom de mozzarella, crée une confusion avec la mozzarella traditionnelle au lait de bufflonne.",
                lca.get(1),
                lty.get(0),
                lf.get(0),
                ltva.get(0),
                qn02));
        lp.add(new Produit("quiche",
                9.0f,
                "La mozzarella est un fromage à pâte filée d'origine italienne à base de lait de vache ou de bufflonne.",
                "/Photo/Tomate_Mozzarella.png",
                "La mozzarella di Bufala Campana, produite en Campanie avec du lait de bufflonne, est l'objet d'une appellation d'origine protégée (AOP) depuis 1996. En Italie, la mozzarella de lait de vache est fabriquée et commercialisée sous le nom de fior di latte ou de bocconcini. Ailleurs dans le monde, la mozzarella est un fromage industriel qui fait partie des fromages de lait de vache les plus produits et consommés. Ce fromage industriel, commercialisé sous le nom de mozzarella, crée une confusion avec la mozzarella traditionnelle au lait de bufflonne.",
                lca.get(1),
                lty.get(0),
                lf.get(0),
                ltva.get(0),
                qn02));
        lp.add(new Produit("Tomates Mozzarella",
                9.0f,
                "La mozzarella est un fromage à pâte filée d'origine italienne à base de lait de vache ou de bufflonne.",
                "/Photo/Tomate_Mozzarella.png",
                "La mozzarella di Bufala Campana, produite en Campanie avec du lait de bufflonne, est l'objet d'une appellation d'origine protégée (AOP) depuis 1996. En Italie, la mozzarella de lait de vache est fabriquée et commercialisée sous le nom de fior di latte ou de bocconcini. Ailleurs dans le monde, la mozzarella est un fromage industriel qui fait partie des fromages de lait de vache les plus produits et consommés. Ce fromage industriel, commercialisé sous le nom de mozzarella, crée une confusion avec la mozzarella traditionnelle au lait de bufflonne.",
                lca.get(1),
                lty.get(0),
                lf.get(1),
                ltva.get(0),
                qn02));
        lp.add(new Produit("salade nicoise",
                9.0f,
                "La mozzarella est un fromage à pâte filée d'origine italienne à base de lait de vache ou de bufflonne.",
                "/Photo/Tomate_Mozzarella.png",
                "La mozzarella di Bufala Campana, produite en Campanie avec du lait de bufflonne, est l'objet d'une appellation d'origine protégée (AOP) depuis 1996. En Italie, la mozzarella de lait de vache est fabriquée et commercialisée sous le nom de fior di latte ou de bocconcini. Ailleurs dans le monde, la mozzarella est un fromage industriel qui fait partie des fromages de lait de vache les plus produits et consommés. Ce fromage industriel, commercialisé sous le nom de mozzarella, crée une confusion avec la mozzarella traditionnelle au lait de bufflonne.",
                lca.get(1),
                lty.get(0),
                lf.get(1),
                ltva.get(0),
                qn02));
        lp.add(new Produit("raïta de concombres",
                9.0f,
                "La mozzarella est un fromage à pâte filée d'origine italienne à base de lait de vache ou de bufflonne.",
                "/Photo/Tomate_Mozzarella.png",
                "La mozzarella di Bufala Campana, produite en Campanie avec du lait de bufflonne, est l'objet d'une appellation d'origine protégée (AOP) depuis 1996. En Italie, la mozzarella de lait de vache est fabriquée et commercialisée sous le nom de fior di latte ou de bocconcini. Ailleurs dans le monde, la mozzarella est un fromage industriel qui fait partie des fromages de lait de vache les plus produits et consommés. Ce fromage industriel, commercialisé sous le nom de mozzarella, crée une confusion avec la mozzarella traditionnelle au lait de bufflonne.",
                lca.get(1),
                lty.get(0),
                lf.get(1),
                ltva.get(0),
                qn02));
        
        //dessert-------------------------------
        lp.add(new Produit("Tiramisu aux speculoos",
                5.5f,
                "Description Tiramisu aux speculoos",
                "",
                "Historique du Tiramisu aux speculoos",
                lca.get(8),
                lty.get(2),
                ltva.get(0),
                qn01));
        
        //boisson-------------------------------
        lp.add(new Produit("Coca-Cola",
                2.5f,
                "Description Coca-Cola",
                "",
                "Historique du Coca-Cola",
                lca.get(6),
                lty.get(3),
                ltva.get(0),
                qn02));
        
        //alcool-------------------------------
        lp.add(new Produit("Grant",
                5.49f,
                "Description du Grant",
                "",
                "Historique du Grant",
                lca.get(5),
                lty.get(3),
                ltva.get(0),
                qn01));
        
//MENU--------------------------------------------------------------------------
        List<Menu> lmenu = new ArrayList();
        lmenu.add(new Menu("gourmand", 19.99F));
        lmenu.add(new Menu("vegetarien", 6.99F));

//COMMENTAIRE-------------------------------------------------------------------
        List<Commentaire> lcommentaire = new ArrayList();
        lcommentaire.add(new Commentaire("Sans sauce."));
        lcommentaire.add(new Commentaire("Sans sauce, sans tomate, sans oignon, sans pain, sans viande, sans légume, sans serviette."));

//LIGNE DE COMMANDE-------------------------------------------------------------
        List<LigneCommande> llc = new ArrayList();
        llc.add(new LigneCommande(0, 0));
        llc.add(new LigneCommande(1, 0));
        llc.add(new LigneCommande(2, 1));
        llc.add(new LigneCommande(3, 2));
        llc.add(new LigneCommande(4, 3));

//COMMANDE----------------------------------------------------------------------
        List<Commande> lcommande = new ArrayList();
        Commande c = new Commande();
        lcommande.add(new Commande("CMD"+c.genererCode(10), 0, new Date(2015 - 11 - 25)));
        lcommande.add(new Commande("CMD"+c.genererCode(10), 2, new Date(2015 - 11 - 20)));
        lcommande.add(new Commande("CMD"+c.genererCode(10), 1, new Date(2015 - 11 - 28)));
        lcommande.add(new Commande("CMD"+c.genererCode(10), 1, new Date(2015 - 11 - 28)));
        lcommande.add(new Commande("CMD"+c.genererCode(10), 1, new Date(2015 - 11 - 28)));
        lcommande.add(new Commande("CMD"+c.genererCode(10), 1, new Date(2015 - 11 - 28)));
        lcommande.get(0).setServeur(lserveur.get(0));
        lcommande.get(1).setServeur(lserveur.get(0));
        lcommande.get(2).setServeur(lserveur.get(1));
        lcommande.get(3).setServeur(lserveur.get(2));
        lcommande.get(4).setServeur(lserveur.get(1));
        lcommande.get(5).setServeur(lserveur.get(2));
        
//TABLEE------------------------------------------------------------------------
        List<Tablee> ltablee = new ArrayList();
        ltablee.add(new Tablee("T01", 4, 0));
        ltablee.add(new Tablee("T02", 6, 0));
        ltablee.add(new Tablee("T03", 4, 0));
        ltablee.add(new Tablee("T04", 8, 0));
        ltablee.add(new Tablee("T05", 4, 0));
        ltablee.add(new Tablee("T06", 2, 0));

//ASSOCIATION-------------------------------------------------------------------
        lcommande.get(0).getLigneCommandes().add(llc.get(0));
        lcommande.get(0).getLigneCommandes().add(llc.get(1));
        lcommande.get(0).getLigneCommandes().add(llc.get(2));
        lcommande.get(1).getLigneCommandes().add(llc.get(3));
        
        lcommande.get(0).setTable(ltablee.get(0));
        lcommande.get(2).setTable(ltablee.get(2));
        lcommande.get(1).setTable(ltablee.get(1));
        lcommande.get(0).setTable(ltablee.get(3));
        lcommande.get(1).setTable(ltablee.get(4));
        lcommande.get(2).setTable(ltablee.get(5));
        lcommande.get(3).setTable(ltablee.get(0));
        lcommande.get(4).setTable(ltablee.get(1));
        lcommande.get(5).setTable(ltablee.get(2));
        
//        lcommande.get(1).setTable(ltablee.get(6));
        
        llc.get(0).setEtat(2);
        llc.get(1).setEtat(2);
        llc.get(2).setEtat(2);
        llc.get(3).setEtat(2);
        llc.get(0).setProduit(lp.get(3));
        llc.get(1).setProduit(lp.get(1));
        llc.get(2).setProduit(lp.get(2));
        llc.get(3).setProduit(lp.get(0));
        llc.get(0).setCommentaire(lcommentaire.get(0));
        llc.get(0).setCommande(lcommande.get(0)); //association ligneCommande -> commande
        llc.get(1).setCommande(lcommande.get(1));
        llc.get(2).setCommande(lcommande.get(2));
        llc.get(3).setCommande(lcommande.get(0));
        
        lcommande.get(0).setTable(ltablee.get(0));
        
        //test table attribué par un serveur
        lserveur.get(0).setActif(1);
//        ltablee.get(0).setServeur(lserveur.get(0));
//        ltablee.get(1).setServeur(lserveur.get(0));
//        
//        ltablee.get(2).setServeur(lserveur.get(1));
//        ltablee.get(3).setServeur(lserveur.get(1));
//        
//        ltablee.get(4).setServeur(lserveur.get(2));
//        ltablee.get(5).setServeur(lserveur.get(2));
        
//PERSIST-----------------------------------------------------------------------
        for (Cuisinier cuisinier : lcuisinier) {
            em.persist(cuisinier);
        }

        for (Serveur serveur : lserveur) {
            em.persist(serveur);
        }

        for (Type ty : lty) {
            em.persist(ty);
        }

        for (Famille f : lf) {
            em.persist(f);
        }

        for (Categorie ca : lca) {
            em.persist(ca);
        }

        for (Produit p : lp) {
            em.persist(p);
        }

        for (Tva tva : ltva) {
            em.persist(tva);
        }

        for (Menu menu : lmenu) {
            em.persist(menu);
        }

        for (Commentaire commentaire : lcommentaire) {
            em.persist(commentaire);
        }

        for (LigneCommande lc : llc) {
            em.persist(lc);
        }

        for (Commande commande : lcommande) {
            em.persist(commande);
        }

        for (Tablee tablee : ltablee) {
            em.persist(tablee);
        }
    }
}
