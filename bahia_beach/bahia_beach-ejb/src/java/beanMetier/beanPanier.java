package beanMetier;

import entities.Commande;
import entities.Commentaire;
import entities.LigneCommande;
import entities.Menu;
import entities.Produit;
import java.util.Collection;
import java.util.HashMap;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ejb.Stateful;

@Stateful
public class beanPanier implements beanPanierLocal {

    @EJB
    private beanCarteLocal beanCarte;

    private HashMap<Long, LigneCommande> panier;

    @PostConstruct
    @Override
    public void init() {
        panier = new HashMap();
    }

    @Override
    public void add(Long id) {
        Produit p = beanCarte.selectProduit(id);
        LigneCommande lc = new LigneCommande(p);
        panier.put(lc.getId(), lc);
    }

    @Override
    public Menu creerMenu(String nomMenu, Float prixMenu, Long idPlat, Long idEntree) {
        Menu m = new Menu();
        m.setNom(nomMenu);
        m.setPrix(prixMenu);
        m.getProduits().add(beanCarte.selectProduit(idPlat));
        m.getProduits().add(beanCarte.selectProduit(idEntree));
        return m;
    }
    

    @Override
        public void addMenu(String nomMenu, Float prixMenu, Long idPlat, Long idEntree){
            Menu m = creerMenu(nomMenu, prixMenu, idPlat, idEntree);
            LigneCommande lc = new LigneCommande(m);
            panier.put(lc.getIdentifiant(), lc);
    }
    
        
    @Override
    public void delete(int id){
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
        
        for(LigneCommande lc : getListe()){
            if(lc.getProduit() != null) {
            total += lc.getProduit().getPrixHT();
            }
            if(lc.getMenu() != null) {
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
    public Commande validerPanier() {
        Commande c = new Commande();
        c.setLigneCommandes(panier.values());
        return c;
    }
    
}
