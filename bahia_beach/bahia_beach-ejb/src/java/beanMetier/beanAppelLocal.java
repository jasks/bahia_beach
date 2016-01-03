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
 * @author kevin
 */
@Local
public interface beanAppelLocal {
    
    public void addAppel(Tablee t);

    public void responseAppel(Tablee t);

    public List<Tablee> afficherAppelTable(Serveur s);

    public int getNombreAppel(Serveur s);
}
