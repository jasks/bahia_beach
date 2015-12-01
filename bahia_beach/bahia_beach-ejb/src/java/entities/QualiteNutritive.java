package entities;

import java.io.Serializable;
import javax.persistence.Embeddable;

@Embeddable
public class QualiteNutritive implements Serializable {
    private static final long serialVersionUID = 1L;
// 1°/ ATTRIBUTS----------------------------------------------------------------    

    private Integer calorie;
    private Integer matiereGrasse;
    private Integer acidesGras;
    private Integer glucide;
    private Integer sucre;
    private Integer proteine;
    private Integer sel;
    
// 2°/ CONSTRUCTOR--------------------------------------------------------------
    public QualiteNutritive() {
    }

    public QualiteNutritive(Integer calorie, Integer matiereGrasse, Integer acidesGras, Integer glucide, Integer sucre, Integer proteine, Integer sel) {
        this.calorie = calorie;
        this.matiereGrasse = matiereGrasse;
        this.acidesGras = acidesGras;
        this.glucide = glucide;
        this.sucre = sucre;
        this.proteine = proteine;
        this.sel = sel;
    }

// 3°/ GETTER AND SETTER--------------------------------------------------------
    public Integer getCalorie() {
        return calorie;
    }

    public void setCalorie(Integer calorie) {
        this.calorie = calorie;
    }

    public Integer getMatiereGrasse() {
        return matiereGrasse;
    }

    public void setMatiereGrasse(Integer matiereGrasse) {
        this.matiereGrasse = matiereGrasse;
    }

    public Integer getAcidesGras() {
        return acidesGras;
    }

    public void setAcidesGras(Integer acidesGras) {
        this.acidesGras = acidesGras;
    }

    public Integer getGlucide() {
        return glucide;
    }

    public void setGlucide(Integer glucide) {
        this.glucide = glucide;
    }

    public Integer getSucre() {
        return sucre;
    }

    public void setSucre(Integer sucre) {
        this.sucre = sucre;
    }

    public Integer getProteine() {
        return proteine;
    }

    public void setProteine(Integer proteine) {
        this.proteine = proteine;
    }

    public Integer getSel() {
        return sel;
    }

    public void setSel(Integer sel) {
        this.sel = sel;
    }
    
    
    
}
