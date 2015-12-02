/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beanMetier;

import entities.Commande;
import entities.LigneCommande;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author cdi415
 */
@Local
public interface beanVoirCommandeLocal {

    public List<Commande> voirLesCommandesEnCours();

    public List<LigneCommande> voirLesLignesDeCommandes();
    
}
