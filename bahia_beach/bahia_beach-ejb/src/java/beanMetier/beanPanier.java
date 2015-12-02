
package beanMetier;

import entities.LigneCommande;
import entities.Produit;
import java.util.Collection;
import java.util.HashMap;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ejb.Stateful;


@Stateful
public class beanPanier implements beanPanierLocal {
    @EJB
    private beanCarteLocal beanCarte;
    
    private HashMap<String, LigneCommande> panier;

  
    @PostConstruct
    public void init() {
        panier = new HashMap();
    }
    

    
    public void add(String ref){
        if(panier.containsKey(ref)){
            update(ref, 1);
        }else {
            Produit p = beanCarte.selectByRef(ref);
            LignePanier lg = new LignePanier(p);
            panier.put(lg.getReference(), lg);
        }
    }
    
    public void decrementer(String ref){
        if(panier.containsKey(ref)){
            update(ref, -1);
        }
    }
    
    public Double getTotalHT(){
        Double total =0.0;
        for( LignePanier l: getListe()){
            total += l.getPrixHT();
        }
        return total;
    }
    
    public Collection<LigneCommande> getListe(){
        return panier.values();
    }
    
    public boolean isEmpty(){
        return panier.isEmpty();
    }
    
}
