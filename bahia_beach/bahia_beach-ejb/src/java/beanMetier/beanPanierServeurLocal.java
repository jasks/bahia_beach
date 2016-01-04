/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beanMetier;

import entities.LigneCommande;
import entities.Tablee;
import java.util.HashMap;
import javax.ejb.Local;

/**
 *
 * @author kevin
 */
@Local
public interface beanPanierServeurLocal {

    public void init();

    public HashMap<Tablee, HashMap<Integer, LigneCommande>> getPanierServeur();

    public void setPanierServeur(HashMap<Tablee, HashMap<Integer, LigneCommande>> panierServeur);

    public HashMap<Tablee, HashMap<Integer, LigneCommande>> updatePanier(Tablee t, HashMap<Integer, LigneCommande> panier);

    
}
