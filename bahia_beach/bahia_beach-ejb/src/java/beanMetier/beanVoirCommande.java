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
import java.util.Collection;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

@Stateless
public class beanVoirCommande implements beanVoirCommandeLocal {

    @PersistenceContext(unitName = "RestaurantPU")
    private EntityManager em;

    //    public List<Serveur> getLeServeur() {
//        Serveur s01=em.find(Serveur.class, 3L);
//         List<Serveur> liste = new ArrayList();
//         liste.add(s01);
//
//         return liste;
//    }     
    @Override
    public Serveur getLeServeur(String code) {
        String req = "select s from Serveur s where s.code= :valeur";
        Query qr = em.createQuery(req);
        qr.setParameter("valeur", code);

        return (Serveur) qr.getSingleResult();
    }

    @Override
    public List<Commande> getLesCommandesEncours(String codeServeur) {
        String req = "select c from Commande c where c.serveur.code= :valeur and c.etat< :etat";
        Query qr = em.createQuery(req);
        qr.setParameter("valeur", codeServeur);
        qr.setParameter("etat", 2);
        return qr.getResultList();
    }

    @Override
    public List<LigneCommande> getAllLigneCommande(String numCommande) {
        String req = "select lc from LigneCommande lc where lc.commande.numero= :numero";
        Query qr = em.createQuery(req);
        qr.setParameter("numero", numCommande);
        return qr.getResultList();
    }

    @Override
    public void mettreAjourCommande(String numCommande) {
        String req = "update Commande c set c.etat= :num where c.numero= :numCommande ";
        Query qr = em.createQuery(req);
        qr.setParameter("num", 2);
        qr.setParameter("numCommande", numCommande);
        qr.executeUpdate();
    }

    @Override
    public Commande getCommandeByNum(String numCommande) {
        String req = "select c from Commande c where c.numero= :numCommande ";
        Query qr = em.createQuery(req);
        qr.setParameter("numCommande", numCommande);
        return (Commande) qr.getSingleResult();
    }
}
