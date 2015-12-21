package entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import javax.persistence.Column;
import javax.persistence.Embedded;
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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private String nomProduit;
    private Float prixHT;
    @Column(length=5000)
    private String descritption;
    private String image;
    @Column(length=5000)
    private String historique;
    
    @ManyToOne
    private Categorie categorie;
    
    @ManyToOne
    private Type type;
    
    @ManyToOne
    private Famille famille;
    
    @ManyToOne
    private Tva tva;

    
    @OneToMany(mappedBy = "produit")
    private Collection<LigneCommande> ligneCommandes;
    
    @Embedded
    private QualiteNutritive qualiteNutritive;

    
// 2°/ CONSTRUCTOR--------------------------------------------------------------
    public Produit() {
        ligneCommandes = new ArrayList();
    }

    public Produit(String nomProduit, Float prixHT, String descritption, String image, String historique) {
        this();
        this.nomProduit = nomProduit;
        this.prixHT = prixHT;
        this.descritption = descritption;
        this.image = image;
        this.historique = historique;
    }

    public Produit(String nomProduit, Float prixHT, String descritption, String image, String historique, QualiteNutritive qualiteNutritive) {
        this();
        this.nomProduit = nomProduit;
        this.prixHT = prixHT;
        this.descritption = descritption;
        this.image = image;
        this.historique = historique;
        this.qualiteNutritive = qualiteNutritive;
    }

    public Produit(String nomProduit, Float prixHT, String descritption, String image, String historique, Categorie categorie, Type type, Tva tva, QualiteNutritive qualiteNutritive) {
        this();
        this.nomProduit = nomProduit;
        this.prixHT = prixHT;
        this.descritption = descritption;
        this.image = image;
        this.historique = historique;
        this.categorie = categorie;
        this.type = type;
        this.tva = tva;
        this.qualiteNutritive = qualiteNutritive;
    }

    public Produit(String nomProduit, Float prixHT, String descritption, String image, String historique, Categorie categorie, Type type, Famille famille, Tva tva, QualiteNutritive qualiteNutritive) {
        this();
        this.nomProduit = nomProduit;
        this.prixHT = prixHT;
        this.descritption = descritption;
        this.image = image;
        this.historique = historique;
        this.categorie = categorie;
        this.type = type;
        this.tva = tva;
        this.qualiteNutritive = qualiteNutritive;
        this.famille = famille;
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


    public Collection<LigneCommande> getLignecommandes() {
        return ligneCommandes;
    }

    public void setLignecommandes(Collection<LigneCommande> lignecommandes) {
        this.ligneCommandes = ligneCommandes;
    }

    public QualiteNutritive getQualiteNutritive() {
        return qualiteNutritive;
    }

    public void setQualiteNutritive(QualiteNutritive qualiteNutritive) {
        this.qualiteNutritive = qualiteNutritive;
    }

    public Famille getFamille() {
        return famille;
    }

    public void setFamille(Famille famille) {
        this.famille = famille;
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
        return "produit : "+nomProduit+"{"+id+"}";
    }
    
}
