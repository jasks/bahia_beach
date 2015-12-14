package entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;


@Entity
public class Serveur implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nom;
    private String prenom;
    private String code;
    
    @Embedded
    private Coordonnee coordonnee;
    
    @ManyToMany
    private Collection<Tablee> tables;
    
    @OneToMany(mappedBy = "serveur")
    private Collection<Commande> commandes;

    public Serveur() {
        this.tables = new ArrayList();
        this.commandes = new ArrayList();
    }

    public Serveur(String nom, String prenom, String code) {
        this();
        this.nom = nom;
        this.prenom = prenom;
        this.code = code;
    }

    public Serveur(String nom, String prenom, String code, Coordonnee coordonnee) {
        this();
        this.nom = nom;
        this.prenom = prenom;
        this.code = code;
        this.coordonnee = coordonnee;
    }

    public Serveur(String nom, String prenom, String code, Coordonnee coordonnee, Collection<Tablee> tables, Collection<Commande> commandes) {
        this.nom = nom;
        this.prenom = prenom;
        this.code = code;
        this.coordonnee = coordonnee;
        this.tables = tables;
        this.commandes = commandes;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Coordonnee getCoordonnee() {
        return coordonnee;
    }

    public void setCoordonnee(Coordonnee coordonnee) {
        this.coordonnee = coordonnee;
    }

    public Collection<Tablee> getTables() {
        return tables;
    }

    public void setTables(Collection<Tablee> tables) {
        this.tables = tables;
    }

    public Collection<Commande> getCommandes() {
        return commandes;
    }

    public void setCommandes(Collection<Commande> commandes) {
        this.commandes = commandes;
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
        if (!(object instanceof Serveur)) {
            return false;
        }
        Serveur other = (Serveur) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "nom: "+nom + " " + prenom + " {"+id+"}";
    }
    
}
