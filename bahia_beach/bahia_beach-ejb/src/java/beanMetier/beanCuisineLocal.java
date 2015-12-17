
package beanMetier;

import entities.Commande;
import entities.LigneCommande;
import java.util.List;
import javax.ejb.Local;


@Local
public interface beanCuisineLocal {

    public List<LigneCommande> afficher();

    public Commande add(String id);

    public LigneCommande toggle(String id);
    
}
