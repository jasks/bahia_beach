/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beanMetier;

import entities.Commande;
import entities.LigneCommande;
import entities.Produit;
import entities.Serveur;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author cdi415
 */
@Stateless
public class beanVoirCommande implements beanVoirCommandeLocal {

    @PersistenceContext(unitName = "bahia_beach-ejbPU")
    private EntityManager em;

    
    
    @Override
    public List<Serveur> getLeServeur() {
        Serveur s01=em.find(Serveur.class, 1L);
         List<Serveur> liste = new ArrayList();
         liste.add(s01);

         return liste;
    }
    
    
    
    @Override
    public Serveur getLeServeur(String code){
        String req="select s from Serveur s where s.code= :valeur";
        Query qr= em.createQuery(req);
        qr.setParameter("valeur", code);  
        return (Serveur) qr.getSingleResult();
    }
    
    @Override
    public List<Commande> getLesCommandesEncours(String code){
        String req="select c from Commande c join Serveur s on c.id=s.id"
                + " where s.code= :valeur and c.etat= :valeur1";
               
        Query qr = em.createQuery(req);
        qr.setParameter("valeur", code);
        qr.setParameter("valeur1", 1);
        return qr.getResultList();
    }
    
    @Override
    public List<Produit> getLesProduits(String code){
       String req = "select p from Produit p join LigneCommande l on p.id=l.id "
               + "join Commande c on l.id=c.id where c.code= : valeur" ;
       Query qr=em.createQuery(req);
       qr.setParameter("valeur",code);
       return qr.getResultList();
    }

    /*
     select SERVEUR.NOM, LIGNECOMMANDE.CUISSON, LIGNECOMMANDE.ETAT, COMMANDE.NUMERO, COMMANDE.DATE, COMMANDE.ETAT
     from SERVEUR JOIN COMMANDE on SERVEUR.ID = COMMANDE.ID
     JOIN LIGNECOMMANDE on COMMANDE.ID=LIGNECOMMANDE.ID  where SERVEUR.CODE = 'S3001'
    
     */
}
