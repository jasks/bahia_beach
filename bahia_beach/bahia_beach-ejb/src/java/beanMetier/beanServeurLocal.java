/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beanMetier;

import entities.Serveur;
import entities.Tablee;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author cdi418
 */
@Local
public interface beanServeurLocal {

    public List<Tablee> afficherTable();

    public List<Tablee> afficherTableLibre();

    public void attribuerTable(Serveur s, Long id);

    
}
