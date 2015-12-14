package beanMetier;

import entities.Cuisinier;
import entities.Serveur;
import entities.Tablee;
import javax.ejb.Local;

@Local
public interface beanConnexionLocal {
    String verifCode(String code);
    public Cuisinier selectCodeCuisinier(String code);
    public Serveur selectCodeServeur(String code);
    public Tablee selectNumTablee(String code);
}
