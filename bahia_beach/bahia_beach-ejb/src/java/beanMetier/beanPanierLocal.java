/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beanMetier;

import entities.Commande;
import entities.Commentaire;
import entities.LigneCommande;
import entities.Menu;
import entities.Serveur;
import entities.Tablee;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import javax.ejb.Local;


@Local
public interface beanPanierLocal {

    public void init();

    public void add(Long id);

    public void delete(int id);

    public Collection<LigneCommande> clearPanier();

    public Collection<LigneCommande> getListe();

    public boolean isEmpty();

    public Float getTotalHT();

    public void ajoutCommentaire(int id, String contenu);

    public HashMap<Integer, LigneCommande> getPanier();

    public Commande validerPanier(Serveur s, Tablee t);

    public void persist(Object object);

    public Menu creerMenu(Menu m, Long idPlat, Long idEntree);

    public void addMenu(Menu m, Long idPlat, Long idEntree);

    public void supprimerCommentaire(int id);

    public void isCommentaire(int id);

    public void modifierCommentaire(int id, String contenu);

    public void cuissonViande(int id, int cuisson);

    public void cuissonViandeMenu(int idMenu, int idLc, int cuisson);

    public int getNombreProduit();

    public void setPanier(HashMap<Integer, LigneCommande> panier);

    public void bidon();

    public List<LigneCommande> afficherLigneEnCour(Tablee t, int etat);
}
