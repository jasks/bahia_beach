package beanMetier;

import entities.Menu;
import entities.Produit;
import entities.Type;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;


@Stateless
public class beanCarte implements beanCarteLocal {

    @PersistenceContext(unitName = "RestaurantPU")
    private EntityManager em;
    
    //liste des produits en entier
    
    
    @Override
    public List<Produit> selectAllproduit(){
        String req = "select p from Produit p";
        Query qr = em.createQuery(req);
        return qr.getResultList();
    }
    
    //liste des produits par type
        
    
    @Override
    public List<Produit> selectProduitByType(Type t){
        
        Type type = t;
        String req = "select p from Produit p "
                + "where p.type = :type";
        Query qr = em.createQuery(req);
        qr.setParameter("type", t);
        return qr.getResultList();
    }
    
    
    
    @Override
    public List<Menu> selectAllMenu(){
        String req = "select m from Menu m";
        Query qr = em.createQuery(req);
        return qr.getResultList();
    }
    
    
    @Override
    public List<Menu> selectMenuByName(String nom){
        String req = "select m from Menu m"
                + "where m.nom = :nom";
        Query qr = em.createQuery(req);
        qr.setParameter("nom", nom);
        return qr.getResultList();
    }

}
