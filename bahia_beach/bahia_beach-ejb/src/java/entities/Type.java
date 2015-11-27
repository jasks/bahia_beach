package entities;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Type implements Serializable {
    private static final long serialVersionUID = 1L;
// 1°/ ATTRIBUTS----------------------------------------------------------------    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    
    private String nomType;
    
    @OneToMany(mappedBy = "type")
    Collection<Produit> produit;
// 2°/ CONSTRUCTOR--------------------------------------------------------------    
    public Type() {
    }

    public Type(String nomType) {
        this.nomType = nomType;
    }
    
// 3°/ GETTER AND SETTER--------------------------------------------------------    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNomType() {
        return nomType;
    }

    public void setNomType(String nomType) {
        this.nomType = nomType;
    }

    public Collection<Produit> getProduit() {
        return produit;
    }

    public void setProduit(Collection<Produit> produit) {
        this.produit = produit;
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
        if (!(object instanceof Type)) {
            return false;
        }
        Type other = (Type) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Type(id="+id+";nom="+nomType+")";
    }
    
}
