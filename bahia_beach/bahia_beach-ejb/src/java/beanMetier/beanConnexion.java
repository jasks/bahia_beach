package beanMetier;

import entities.Cuisinier;
import entities.Serveur;
import entities.Tablee;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.Query;

@Stateless
public class beanConnexion implements beanConnexionLocal {
    private EntityManager em;

    @Override
    public String verifCode(String code) {
        switch (code.substring(0, 1)) {
            case "C":
                Cuisinier cuisinier = selectCodeCuisinier(code);
                if (cuisinier != null) {
                    return "/WEB-INF/cuisine.jsp";
                } else {
                    return "/WEB-INF/connexion.jsp";
                }
            case "S":
                Serveur serveur = selectCodeServeur(code);
                if (serveur != null) {
                    return "/WEB-INF/voirCommande.jsp";
                } else {
                    return "/WEB-INF/connexion.jsp";
                }
            case "T":
                Tablee tablee = selectNumTablee(code);
                if (tablee != null) {
                    return "/WEB-INF/carte.jsp";
                } else {
                    return "/WEB-INF/connexion.jsp";
                }
            default:
                return "/WEB-INF/connexion.jsp";
        }
    }

    @Override
    public Cuisinier selectCodeCuisinier(String code) {
        String req = "select c from Cuisinier c where code = :code";
        Query qr = em.createQuery(req);
        qr.setParameter("code", code);
        return (Cuisinier) qr.getParameterValue(req);
    }

    @Override
    public Serveur selectCodeServeur(String code) {
        String req = "select s from Serveur s where code = :code";
        Query qr = em.createQuery(req);
        qr.setParameter("code", code);
        return (Serveur) qr.getParameterValue(req);
    }

    @Override
    public Tablee selectNumTablee(String code) {
        String req = "select t from Tablee t where num = :code";
        Query qr = em.createQuery(req);
        qr.setParameter("code", code);
        return (Tablee) qr.getParameterValue(req);
    }
}
