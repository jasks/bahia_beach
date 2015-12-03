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
    
    //select un type
    @Override
    public Type selectType(Long id) {
        Type t = em.find(Type.class, id);
        return t;
    }
    
    //liste des type de produit
    @Override
     public List<Type> selectAllType(){
        String req = "select t from Type t";
        Query qr = em.createQuery(req);
        return qr.getResultList();

    }
     
     //liste des type de produit selon le type
    @Override
     public List<Produit> selectProduitByType(Type t){
        String req = "select t.produits from Type t "
                + "where t = :type";
        Query qr = em.createQuery(req);
        qr.setParameter("type", t);
        return qr.getResultList();
    }
     


    
    //liste des produits en entier
    
   
    @Override
    public List<Produit> selectAllproduit(){
        String req = "select p from Produit p";
        Query qr = em.createQuery(req);
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
    
    @Override
        public Produit selectProduit(Long id) {
        Produit p = em.find(Produit.class, id);
        return p;
    }

}
