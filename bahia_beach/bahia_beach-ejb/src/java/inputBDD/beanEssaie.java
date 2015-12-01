package inputBDD;

import entities.Categorie;
import entities.Produit;
import entities.QualiteNutritive;
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

//PRODUIT-----------------------------------------------------------------------
        List<Produit> lp = new ArrayList();
        lp.add(new Produit("Fusilli à la Bolognaises", 
                14F, 
                "Une sauce à base de tomates, de viande de bœuf, d'oignons et de carottes, cuisinée avec du laurier et du romarin qui vient napper des fusilli", 
                "/Photo/Fusilli_Bolognaise.png", 
                "Selon la recette traditionnelle mentionnée en 1982 par la délégation bolognaise de l'Accademia Italiana della Cucina, la liste des ingrédients ne comprend que : bœuf, pancetta, oignons, carottes, céleri, sauce tomate, mie de pain, vin rouge ou vin blanc sec, lait ou crème. Cependant, différentes recettes existent et font usage d'émincé de porc ou saucisse de porc, de poulet, lapin ou de foie d'oie qui peuvent être ajoutés au bœuf ou au veau en diverses occasions. L'huile d'olive ou du beurre peuvent être employés pour préparer le céleri, les carottes et les oignons. Le jambon, la mortadelle ou des champignons frais, selon la saison, peuvent être ajoutés au ragù pour l'enrichir. Le lait apparaît au début de la recette pour adoucir les saveurs. La crème est rarement utilisée, et si c'est le cas, seule une petite quantité est employée. D'après Marcella Hazan, le ragù doit cuire entre 5 ou 6 heures ; plus la cuisson est longue, meilleure est la sauce1."));
        lp.add(new Produit("Tomates Mozzarella",
                9F,
                "La mozzarella est un fromage à pâte filée d'origine italienne à base de lait de vache ou de bufflonne.",
                "/Photo/Tomate-Mozzarella.png",
                "La mozzarella di Bufala Campana, produite en Campanie avec du lait de bufflonne, est l'objet d'une appellation d'origine protégée (AOP) depuis 1996. En Italie, la mozzarella de lait de vache est fabriquée et commercialisée sous le nom de fior di latte ou de bocconcini. Ailleurs dans le monde, la mozzarella est un fromage industriel qui fait partie des fromages de lait de vache les plus produits et consommés. Ce fromage industriel, commercialisé sous le nom de mozzarella, crée une confusion avec la mozzarella traditionnelle au lait de bufflonne."));
                

        for (Produit p : lp) {
            em.persist(p);
        }
        
//QUALITE NUTRITIVE-------------------------------------------------------------        
        List<QualiteNutritive> lqn = new ArrayList();
        lqn.add(new QualiteNutritive(610,7,19,3,4,1,362));
        lqn.add(new QualiteNutritive(266,2,16,5,19,9,12));
        
        for (QualiteNutritive qn : lqn) {
            em.persist(qn);
        }
//ASSOCIATION--------------------------------------------------------------------------        
        
    }
}
