
package beanMetier;

import entities.Commande;
import entities.LigneCommande;
import entities.Produit;
import java.util.List;
import javax.ejb.Local;


@Local
public interface beanCuisineLocal {

    public List<Produit> afficher();

    public List<LigneCommande> afficherProduitCommande(String numcommande);

    public List<LigneCommande> afficherLigneCommande();

    public List<Commande> afficherCommande();
    
}
