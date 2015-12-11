/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beanMetier;

import entities.Commande;

import entities.LigneCommande;
import entities.Produit;
import entities.Serveur;
import java.util.ArrayList;

import entities.Produit;
import entities.Serveur;
import java.util.Collection;

import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author cdi418
 */
@Local
public interface beanVoirCommandeLocal {


    public List<Commande> getLesCommandesEncours(String codeServeur);

    public Serveur getLeServeur(String code);

    public Serveur getLeServeur(Long id);

    public List<LigneCommande> getLesLignes(String numCommande);






    
}
