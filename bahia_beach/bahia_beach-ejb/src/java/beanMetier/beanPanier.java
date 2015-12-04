
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
    
    private HashMap<Integer, LigneCommande> panier;


  
    @PostConstruct
    @Override
    public void init() {
        panier = new HashMap();
    }
    

    
    @Override
    public void add(Long id){
        
            Produit p = beanCarte.selectProduit(id);
            LigneCommande lc = new LigneCommande(p);
            panier.put(lc.getIdentifiant(), lc);
            
    }
    
    @Override
    public void delete(int id){
        panier.remove(id);
    }
    
    @Override
    public void clearPanier(){
        panier.clear();
    }
    

    @Override
    public Collection<LigneCommande> getListe(){
        return panier.values();
    }
    
    @Override
    public boolean isEmpty(){
        return panier.isEmpty();
    }
    
    @Override
    public Float getTotalHT(){
        Float total = 0.0F;
        for(LigneCommande lc : getListe()){
            total += lc.getPrixHT();
        }
        return total;
    }
    
    
    
    
    
    
}
