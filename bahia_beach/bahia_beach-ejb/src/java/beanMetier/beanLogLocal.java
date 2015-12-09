/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beanMetier;

import entities.Cuisinier;
import entities.Serveur;
import javax.ejb.Local;
import javax.persistence.NoResultException;

/**
 *
 * @author cdi418
 */
@Local
public interface beanLogLocal {

    public Serveur connexionServeur(String code) throws Exception;

    public Cuisinier connexionCuisinier(String code) throws Exception;
    
}
