/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beanMetier;

import entities.Menu;
import entities.Produit;
import entities.Type;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author cdi418
 */
@Local
public interface beanCarteLocal {

    public List<Produit> selectAllproduit();

    public List<Menu> selectAllMenu();

    public List<Menu> selectMenuByName(String nom);

    public List<Type> selectAllType();
    
    public Type selectType(Long id);

    public List<Produit> selectProduitByType(Type t);

    public Produit selectProduit(Long id);

    public List<Produit> selectProduitByMenu(String nom);

    public Menu selectMenu(Long id);

    
}
