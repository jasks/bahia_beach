
package beanMetier;

import entities.LigneCommande;
import java.util.List;
import javax.ejb.Local;


@Local
public interface beanCuisineLocal {

    public List<LigneCommande> afficher();
    
}
