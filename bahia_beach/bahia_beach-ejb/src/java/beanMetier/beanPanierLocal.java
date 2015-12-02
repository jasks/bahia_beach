/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beanMetier;

import entities.LigneCommande;
import java.util.Collection;
import javax.ejb.Local;


@Local
public interface beanPanierLocal {

    public void init();

    public void add(Long id);

    public void delete(Long id);

    public void clearPanier();

    public Collection<LigneCommande> getListe();

    public boolean isEmpty();

    public Float getTotalHT();
    
}
