package entities;

import java.io.Serializable;
import javax.persistence.Embeddable;

@Embeddable
public class Coordonnee implements Serializable {
    private static final long serialVersionUID = 1L;

    private String numTel;
    private String rue;
    private String ville;
    private String cp;

    public Coordonnee() {
    }

    public Coordonnee(String rue, String ville, String cp) {
        this.rue = rue;
        this.ville = ville;
        this.cp = cp;
    }

    public Coordonnee(String numTel, String rue, String ville, String cp) {
        this.numTel = numTel;
        this.rue = rue;
        this.ville = ville;
        this.cp = cp;
    }
    
    

    public String getNumTel() {
        return numTel;
    }

    public void setNumTel(String numTel) {
        this.numTel = numTel;
    }

    public String getRue() {
        return rue;
    }

    public void setRue(String rue) {
        this.rue = rue;
    }

    public String getVille() {
        return ville;
    }

    public void setVille(String ville) {
        this.ville = ville;
    }

    public String getCp() {
        return cp;
    }

    public void setCp(String cp) {
        this.cp = cp;
    }
    


    @Override
    public String toString() {
        return "coordonnee: "+ numTel + " / "+rue  + " / "+cp + " / "+ville ;
    }
    
}
