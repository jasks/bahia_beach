package entities;

import java.io.Serializable;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;


@Entity
public class Cuisinier implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nom;
    private String prenom;
    private String code;
    private int actif;
    
    @Embedded
    private Coordonnee coordonnee;

    public Cuisinier() {
    }

    public Cuisinier(String nom, String prenom, String code) {
        this.nom = nom;
        this.prenom = prenom;
        this.code = code;
    }

    public Cuisinier(String nom, String prenom, String code, Coordonnee coordonnee) {
        this.nom = nom;
        this.prenom = prenom;
        this.code = code;
        this.coordonnee = coordonnee;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
        if (!(object instanceof Cuisinier)) {
            return false;
        }
        Cuisinier other = (Cuisinier) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "nom: "+nom + " " + prenom + " {"+id+"}";
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

    public int getActif() {
        return actif;
    }

    public void setActif(int actif) {
        this.actif = actif;
    }
    
    
    
}
