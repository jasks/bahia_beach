package beanMetier;

import entities.LigneCommande;
import entities.Tablee;
import java.util.HashMap;
import javax.annotation.PostConstruct;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;


@Stateless
public class beanPanierServeur implements beanPanierServeurLocal {

    @PersistenceContext(unitName = "RestaurantPU")
    private EntityManager em;
        
    private HashMap < Tablee, HashMap<Integer, LigneCommande> > panierServeur;
    
    @PostConstruct
    @Override
    public void init() {
        panierServeur = new HashMap();
    }

    @Override
    public HashMap<Tablee, HashMap<Integer, LigneCommande>> getPanierServeur() {
        return panierServeur;
    }

    @Override
    public void setPanierServeur(HashMap<Tablee, HashMap<Integer, LigneCommande>> panierServeur) {
        this.panierServeur = panierServeur;
    }
    
    @Override
    public HashMap < Tablee, HashMap<Integer, LigneCommande> > updatePanier(Tablee t, HashMap<Integer, LigneCommande> panier){
        this.panierServeur.put(t, panier);
        return this.panierServeur;
    } 
    
}
