/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beanMetier;

import entities.Tablee;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author cdi418
 */
@Local
public interface beanClientLocal {

    public Tablee selectTable(Long id);

    public List<Tablee> tableAttribueByServeur();
    
}
