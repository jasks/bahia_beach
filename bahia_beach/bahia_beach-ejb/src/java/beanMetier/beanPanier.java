package beanMetier;

import entities.Commande;
import entities.Commentaire;
import entities.LigneCommande;
import entities.Menu;
import entities.Produit;
import entities.Serveur;
import entities.Tablee;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ejb.Stateful;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateful
public class beanPanier implements beanPanierLocal {

    @EJB
    private beanCarteLocal beanCarte;
    @PersistenceContext(unitName = "RestaurantPU")
    private EntityManager em;

    private HashMap<Integer, LigneCommande> panier;

    @PostConstruct
    @Override
    public void init() {
        panier = new HashMap();
    }

    @Override
    public void add(Long id) {

        Produit p = beanCarte.selectProduit(id);
        LigneCommande lc = new LigneCommande(p);
        panier.put(lc.getIdentifiant(), lc);

    }

    @Override
    public Menu creerMenu(Long idMenu, Long idPlat, Long idEntree) {
        Menu m = beanCarte.selectMenu(idMenu);
        m.getProduits().add(beanCarte.selectProduit(idPlat));
        m.getProduits().add(beanCarte.selectProduit(idEntree));
        return m;
    }

    @Override
    public void addMenu(Long idMenu, Long idPlat, Long idEntree) {
        Menu m = creerMenu(idMenu, idPlat, idEntree);
        LigneCommande lc = new LigneCommande(m);
        panier.put(lc.getIdentifiant(), lc);
    }

    @Override
    public void delete(int id) {
        panier.remove(id);
    }

    @Override
    public void clearPanier() {
        panier.clear();
    }

    @Override
    public Collection<LigneCommande> getListe() {
        return panier.values();
    }

    @Override
    public boolean isEmpty() {
        return panier.isEmpty();
    }

    @Override
    public Float getTotalHT() {
        Float total = 0.0F;

        for (LigneCommande lc : getListe()) {
            if (lc.getProduit() != null) {
                total += lc.getProduit().getPrixHT();
            }
            if (lc.getMenu() != null) {
                total += lc.getMenu().getPrix();
            }
        }
        return total;
    }

    @Override
    public void ajoutCommentaire(int id, String contenu) {
        Commentaire c = new Commentaire(contenu);
        panier.get(id).setCommentaire(c);
    }

    @Override
    public HashMap<Integer, LigneCommande> getPanier() {
        return panier;
    }

    @Override
    public Commande validerPanier(Serveur s, Tablee t) {
        Commande c = new Commande();
        c.setLigneCommandes(panier.values());
        c.setNumero("CMD" + c.getId() + 1000);
        c.setEtat(1);
        Date date = new Date();
        c.setDate(date);
        c.setServeur(s);
        c.setTable(t);
        for (LigneCommande lc : c.getLigneCommandes()) {
            lc.setEtat(1);
            if (lc.getMenu() != null) {
                em.merge(lc.getMenu());
            }
            em.persist(lc);
        }
        em.persist(c);
        return c;
    }

    @Override
    public void persist(Object object) {
        em.persist(object);
    }

    @Override
    public Menu creerMenu(Long idMenu, String nomMenu, Float prixMenu, Long idPlat, Long idEntree) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
