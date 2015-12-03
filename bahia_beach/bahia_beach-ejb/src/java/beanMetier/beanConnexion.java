package beanMetier;

import javax.ejb.Stateless;

@Stateless
public class beanConnexion implements beanConnexionLocal {
    // méthode qui affiche l'interface qui correspond au code saisi
    void verifCode(String code) {
        switch(code.substring(0, 2)) {
            case "CU":
                afficherCu();
                break;
            case "CL":
                afficherCl();
                break;
            case "SE":
                afficherSe();
                break;
            default:
                afficherErreur();
                break;
        }
    }
    
    // afficher l'interface cuisinier
    void afficherCu() {
        
    }
    
    // afficher l'interface client
    void afficherCl() {
        
    }
    
    // afficher l'interface serveur
    void afficherSe() {
        
    }
    
    // afficher l'interface erreur
    void afficherErreur() {
        
    }
}
