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
import javax.persistence.NoResultException;

/**
 *
 * @author cdi418
 */
@Local
public interface beanServeurLocal {

    public List<Tablee> afficherTable();

    public List<Tablee> afficherTableLibre();

    public List<Tablee> attribuerTable(Serveur s, Long id);

    public List<Tablee> afficherTableAttribue(Serveur s);

    public Tablee validerNomTable(String num) throws NoResultException;

    public Serveur getServeur(Long id);

    public Tablee getTablee(Long id);
}
