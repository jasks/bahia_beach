
package beanMetier;

import entities.Produit;
import java.util.List;
import javax.ejb.Local;


@Local
public interface beanCuisineLocal {

    public List<Produit> afficher();
    
}
