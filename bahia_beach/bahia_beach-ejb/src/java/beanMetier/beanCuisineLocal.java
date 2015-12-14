
package beanMetier;

import entities.LigneCommande;
import java.util.List;
import javax.ejb.Local;
import javax.json.JsonObject;


@Local
public interface beanCuisineLocal {

    public List<LigneCommande> afficher();

    public void add(JsonObject jsObject);
    
}
