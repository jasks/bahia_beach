package beanMetier;

import entities.Cuisinier;
import entities.Serveur;
import entities.Tablee;
import javax.ejb.Local;
import javax.persistence.NoResultException;

@Local
public interface beanLogLocal {

    public Serveur connexionServeur(String code) throws Exception;

    public Cuisinier connexionCuisinier(String code) throws Exception;

    public void setActif(Serveur s, int etat);

    public void setActif(Cuisinier c, int etat);

    public Tablee connexionTablee(String code) throws Exception;

    public void setActif(Tablee t, int etat);

    public void viderTable(Tablee t);

    public Serveur logout(String code);
}
