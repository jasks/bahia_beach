package entities;
import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;


@Entity
public class LigneCommande implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Integer etat;
    private Integer cuisson;
    private Integer prix;
    private static Integer staticIdentifiant = 1;
    private Integer identifiant;

    @OneToOne
    private Commande commande;
    
    @OneToOne
    private Commentaire commentaire;
    
    @ManyToOne
    private Produit produit;
    
    @ManyToOne
    private Menu menu;
    
    public LigneCommande() {
        this.identifiant = staticIdentifiant++;
    }

    public LigneCommande(Integer etat, Integer cuisson, Integer prix, Integer identifiant, Commande commande, Commentaire commentaire, Produit produit, Menu menu) {
        this.etat = etat;
        this.cuisson = cuisson;
        this.prix = prix;
        this.identifiant = identifiant;
        this.commande = commande;
        this.commentaire = commentaire;
        this.produit = produit;
        this.menu = menu;
    }
    
     public LigneCommande(Produit produit) {
         this();
         this.produit = produit;
    }
    
     public LigneCommande(Menu menu) {
         this();
         this.menu = menu;
    }

    public LigneCommande(Integer etat, Integer cuisson) {
        this();
        this.etat = etat;
        this.cuisson = cuisson;
    }

    public Integer getEtat() {
        return etat;
    }

    public void setEtat(Integer etat) {
        this.etat = etat;
    }

    public Integer getCuisson() {
        return cuisson;
    }

    public void setCuisson(Integer cuisson) {
        this.cuisson = cuisson;
    }
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Commande getCommande() {
        return commande;
    }

    public void setCommande(Commande commande) {
        this.commande = commande;
    }

    public Commentaire getCommentaire() {
        return commentaire;
    }

    public void setCommentaire(Commentaire commentaire) {
        this.commentaire = commentaire;
    }

    public Produit getProduit() {
        return produit;
    }

    public void setProduit(Produit produit) {
        this.produit = produit;
    }


    public Integer getIdentifiant() {
        return identifiant;
    }

    public Menu getMenu() {
        return menu;
    }

    public void setMenu(Menu menu) {
        this.menu = menu;
    }

    public Integer getPrix() {
        return prix;
    }

    public void setPrix(Integer prix) {
        this.prix = prix;
    }

    public String getNom() {
        String nom = "";
        if(getProduit() != null) {
            nom = getProduit().getNomProduit();
        }
        if(getMenu()!= null) {
            nom = getMenu().getNom();
        }
        return nom;
    }

    
    

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof LigneCommande)) {
            return false;
        }
        LigneCommande other = (LigneCommande) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

//    @Override
//    public String toString() {
//        return "n° ligne de commande : " + identifiant;
//    }

    @Override
    public String toString() {
        return "LigneCommande{" + "id=" + id + ", etat=" + etat + ", cuisson=" + cuisson + ", prix=" + prix + ", identifiant=" + identifiant + ", commande=" + commande + ", commentaire=" + commentaire + ", produit=" + produit + ", menu=" + menu + '}';
    }
    
    
}
