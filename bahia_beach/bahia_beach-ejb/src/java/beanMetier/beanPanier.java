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
    private beanServeurLocal beanServeur;

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
    public void bidon() {
        System.out.println("-------------- -salut l'ami- ----------------");
    }

    @Override
    public void add(Long id) {

        Produit p = beanCarte.selectProduit(id);
        LigneCommande lc = new LigneCommande(p);
        lc.setCuisson(0);
        panier.put(lc.getIdentifiant(), lc);
    }
    
        
   
    @Override
    public Menu creerMenu(Menu m, Long idPlat, Long idEntree) {
        LigneCommande lc01 = new LigneCommande(beanCarte.selectProduit(idPlat));
        LigneCommande lc02 = new LigneCommande(beanCarte.selectProduit(idEntree));
        m.getLigneCommandes().add(lc01);
        m.getLigneCommandes().add(lc02);
      
        return m;
    }

    
    @Override
        public void addMenu(Menu m, Long idPlat, Long idEntree){
            m = creerMenu(m, idPlat, idEntree);
            for (LigneCommande lc : m.getLigneCommandes()) {
            lc.setCuisson(0);
        }
            LigneCommande lc = new LigneCommande(m);
            lc.setCuisson(0);
            panier.put(lc.getIdentifiant(), lc);
    }

    @Override
    public void delete(int id) {
        panier.remove(id);
    }

    
    @Override
    public Collection<LigneCommande> clearPanier() {
        panier.clear();
        return panier.values();
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
    public void cuissonViande(int id, int cuisson) {
        panier.get(id).setCuisson(cuisson);
    }
    
    @Override
    public void cuissonViandeMenu(int idMenu, int idLc, int cuisson) {
        for (LigneCommande lc : panier.get(idMenu).getMenu().getLigneCommandes()) {
            if(lc.getProduit().getCategorie().getNomCategorie().equalsIgnoreCase("Viande")){
            lc.setCuisson(cuisson);
            }
        }
    }

    @Override
    public void ajoutCommentaire(int id, String contenu) {
        Commentaire c = new Commentaire(contenu);
        panier.get(id).setCommentaire(c);
    }
    
    @Override
    public void modifierCommentaire(int id, String contenu) {
        Commentaire c = new Commentaire(contenu);
        panier.get(id).setCommentaire(c);
    }
    
    @Override
    public void isCommentaire(int id) {
        if(panier.get(id).getCommentaire().getContenu().trim().equalsIgnoreCase("")) {
            supprimerCommentaire(id);
        }
    }
    
    @Override
    public void supprimerCommentaire(int id) {
        panier.get(id).getCommentaire().setContenu("");
        System.out.println("Commentaire Supprimer test");
        System.out.println("Commentaire Supprimer" + panier.get(id).getCommentaire().getContenu());
    }

    @Override
    public HashMap<Integer, LigneCommande> getPanier() {
        return panier;
    }

    @Override
    public void setPanier(HashMap<Integer, LigneCommande> panier) {
        this.panier = panier;
    }
    
    
    @Override
       public int getNombreProduit() {
        return this.getListe().size();
    }

    @Override
    public Commande validerPanier(Serveur s, Tablee t) {
        Commande c = new Commande();
        for (LigneCommande lc : panier.values()) {
            lc.setCommande(c);
            lc.setEtat(1);
            if(lc.getCommentaire()==null) {
                Commentaire com = new Commentaire("");
                lc.setCommentaire(com);
            }
            em.persist(lc.getCommentaire());
            if (lc.getMenu() != null) {
                em.merge(lc.getMenu());
            }
            em.persist(lc);
        }
        c.setNumero("CMD" + c.getId() + 1000);
        c.setEtat(1);
        Date date = new Date();
        c.setDate(date);
        c.setServeur(s);
        c.setTable(t);
        em.persist(c);
        return c;
    }


    @Override
    public void persist(Object object) {
        em.persist(object);
    }
}
