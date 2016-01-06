package sousController;

import beanMetier.beanAppelLocal;
import beanMetier.beanCarteLocal;
import beanMetier.beanLogLocal;
import entities.Cuisinier;
import entities.Menu;
import entities.Serveur;
import entities.Tablee;
import entities.Type;
import java.io.Serializable;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class Log implements ControllerInterface, Serializable {
    beanCarteLocal beanCarte = lookupbeanCarteLocal();
    beanAppelLocal beanAppel = lookupbeanAppelLocal();
    beanLogLocal beanLog = lookupbeanLogLocal();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response, HttpServlet servlet) {
        HttpSession session = request.getSession();

        String action = request.getParameter("action");
        String code = request.getParameter("code");

        if ("log".equalsIgnoreCase(action)) {
            return "/WEB-INF/log.jsp";
        }

        if ("verifierCode".equalsIgnoreCase(action)) {
            if (code.substring(0, 1).equalsIgnoreCase("S")) {
                Serveur s = new Serveur();

                try {
                    s = beanLog.connexionServeur(code);
                    beanLog.setActif(s, 1);
                    session.setAttribute("auth", s);
//                    request.setAttribute("nombre", beanAppel.getNombreAppel(s));
                    request.setAttribute("msg", "Bonjour "+s.getNom() + " " + s.getPrenom() + ": vous etes bien un "+s.getClass());
                    return "/WEB-INF/serveur/interfaceServeur.jsp";
                } catch (Exception ex) {
                    request.setAttribute("msg", ex);
                    return "/WEB-INF/log.jsp";
                }

            } else if (code.substring(0, 1).equalsIgnoreCase("C")) {
                Cuisinier c = new Cuisinier();

                try {
                    c = beanLog.connexionCuisinier(code);
                    beanLog.setActif(c, 1);
                    session.setAttribute("auth", c);
                    request.setAttribute("msg", "Bonjour " + c.getNom() + " " + c.getPrenom() + ": vous etes bien un " + c.getClass());
                } catch (Exception ex) {
                    request.setAttribute("msg", ex);
                    return "/WEB-INF/log.jsp";
                }

                return "/WEB-INF/log.jsp";
            } else if (code.substring(0, 1).equalsIgnoreCase("T")) {
                Tablee t = new Tablee();
                try {
                    t = beanLog.connexionTablee(code);
                    beanLog.setActif(t, 1);
                    session.setAttribute("auth", t);
                    request.setAttribute("msg", "Bonjour " + t.getNum() + ": vous etes bien sur la table " + t.getClass());
                    List<Type> lt = beanCarte.selectAllType();
                    request.setAttribute("types", lt);

                    List<Menu> lm = beanCarte.selectAllMenu();
                    request.setAttribute("menus", lm);
                    return "/WEB-INF/client/carte.jsp";
                } catch (Exception ex) {
                    request.setAttribute("msg", ex);
                    return "/WEB-INF/log.jsp";
                }
            } else {
                try {
                    request.setAttribute("msg", "code invalide");
                    return "/WEB-INF/log.jsp";
                } catch (StringIndexOutOfBoundsException ex) {
                    request.setAttribute("msg", ex);
                    return "/WEB-INF/log.jsp";
                }

            }

        }

        return "/WEB-INF/log.jsp";
    }

    private beanLogLocal lookupbeanLogLocal() {
        try {
            Context c = new InitialContext();
            return (beanLogLocal) c.lookup("java:global/bahia_beach/bahia_beach-ejb/beanLog!beanMetier.beanLogLocal");
        } catch (NamingException ne) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", ne);
            throw new RuntimeException(ne);
        }
    }

    private beanAppelLocal lookupbeanAppelLocal() {
        try {
            Context c = new InitialContext();
            return (beanAppelLocal) c.lookup("java:global/bahia_beach/bahia_beach-ejb/beanAppel!beanMetier.beanAppelLocal");
        } catch (NamingException ne) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", ne);
            throw new RuntimeException(ne);
        }
    }

    private beanCarteLocal lookupbeanCarteLocal() {
        try {
            Context c = new InitialContext();
            return (beanCarteLocal) c.lookup("java:global/bahia_beach/bahia_beach-ejb/beanCarte!beanMetier.beanCarteLocal");
        } catch (NamingException ne) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", ne);
            throw new RuntimeException(ne);
        }
    }


}
