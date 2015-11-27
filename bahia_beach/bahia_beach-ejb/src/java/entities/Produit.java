package entities;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class Produit implements Serializable {
    private static final long serialVersionUID = 1L;
// 1°/ ATTRIBUTS----------------------------------------------------------------
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    
    private String nomProduit;
    private Float prixHT;
    private String descritption;
    private String image;
    private String qualiteNutritive;
    private String historique;
    
    @ManyToOne
    private Categorie categorie;
    
    @ManyToOne
    private Type type;
    
    @ManyToOne
    private Tva tva;
    
    @ManyToMany
    private Collection<Menu> menus;
    
    @OneToMany(mappedBy = "produit")
    private Collection<LigneCommande> lignecommandes;

    
// 2°/ CONSTRUCTOR--------------------------------------------------------------
    public Produit() {
    }

    public Produit(String nomProduit, Float prixHT, String descritption, String image, String qualiteNutritive, String historique) {
        this.nomProduit = nomProduit;
        this.prixHT = prixHT;
        this.descritption = descritption;
        this.image = image;
        this.qualiteNutritive = qualiteNutritive;
        this.historique = historique;
    }
    
// 3°/ GETTER AND SETTER--------------------------------------------------------
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNomProduit() {
        return nomProduit;
    }

    public void setNomProduit(String nomProduit) {
        this.nomProduit = nomProduit;
    }

    public Float getPrixHT() {
        return prixHT;
    }

    public void setPrixHT(Float prixHT) {
        this.prixHT = prixHT;
    }

    public String getDescritption() {
        return descritption;
    }

    public void setDescritption(String descritption) {
        this.descritption = descritption;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getQualiteNutritive() {
        return qualiteNutritive;
    }

    public void setQualiteNutritive(String qualiteNutritive) {
        this.qualiteNutritive = qualiteNutritive;
    }

    public String getHistorique() {
        return historique;
    }

    public void setHistorique(String historique) {
        this.historique = historique;
    }

    public Categorie getCategorie() {
        return categorie;
    }

    public void setCategorie(Categorie categorie) {
        this.categorie = categorie;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public Tva getTva() {
        return tva;
    }

    public void setTva(Tva tva) {
        this.tva = tva;
    }

    public Collection<Menu> getMenus() {
        return menus;
    }

    public void setMenus(Collection<Menu> menus) {
        this.menus = menus;
    }

    public Collection<LigneCommande> getLignecommandes() {
        return lignecommandes;
    }

    public void setLignecommandes(Collection<LigneCommande> lignecommandes) {
        this.lignecommandes = lignecommandes;
    }
    
    
// 4°/ METHODE------------------------------------------------------------------       
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Produit)) {
            return false;
        }
        Produit other = (Produit) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Produit[ id=" + id + " ]";
    }
    
}
