package inputBDD;

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
    public void creerJeuxDonnees(){
        List<Type> lty = new ArrayList();
        lty.add(new Type("Entrée"));
        lty.add(new Type("Plat"));
        lty.add(new Type("Dessert"));
        lty.add(new Type("Boisson"));
        lty.add(new Type("Menu"));

        for(Type ty:lty){
            em.persist(ty);
        }
    }
}
