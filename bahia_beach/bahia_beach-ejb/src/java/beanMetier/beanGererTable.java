package beanMetier;

import javax.ejb.Stateless;
import entities.Tablee;

@Stateless
public class beanGererTable implements beanGererTableLocal {
    // attribue une table à un client
    void attribuerTable(Tablee tablee) {
        tablee.setStatut(1);
    }
    
    // libère une table
    void libererTable(Tablee tablee) {
        tablee.setStatut(0);
    }
}
