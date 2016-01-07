package sousController;

import beanMetier.beanClientLocal;
import beanMetier.beanLog;
import beanMetier.beanLogLocal;
import entities.Tablee;
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

public class Client implements ControllerInterface, Serializable {

    beanLogLocal beanLog = lookupbeanLogLocal();
    beanClientLocal beanClient = lookupbeanClientLocal();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response, HttpServlet servlet) {

        HttpSession session = request.getSession();
        String action = request.getParameter("action");
        String code = request.getParameter("code");

        if ("initTable".equalsIgnoreCase(action)) {
            List<Tablee> lt = beanClient.tableAttribueByServeur();
            request.setAttribute("tab", lt);
            return "/WEB-INF/client/afficherTable.jsp";
        }

        if ("accederTable".equalsIgnoreCase(action)) {
            Long id = Long.parseLong(request.getParameter("id"));
            Tablee t = beanClient.selectTable(id);
            session.setAttribute("table", t);
            request.setAttribute("msg", t.getNum() + " mis en session");
            System.out.println(t);
            return "/WEB-INF/client/interfaceClient.jsp";
        }

        if ("deconnexion".equalsIgnoreCase(action)) {
            try {
                beanLog.logout(code);
                beanLog.setActif((Tablee) session.getAttribute("table"), 0);
                beanLog.viderTable((Tablee) session.getAttribute("table"));
                session.setAttribute("table", null);
                request.setAttribute("msg", "vous avez été deconnecté");
                return "/WEB-INF/log.jsp";
            } catch (Exception ex) {
                request.setAttribute("msg", ex);
                return "/WEB-INF/logout.jsp";
            }
        }

        return "/WEB-INF/index.jsp";
    }

    private beanClientLocal lookupbeanClientLocal() {
        try {
            Context c = new InitialContext();
            return (beanClientLocal) c.lookup("java:global/bahia_beach/bahia_beach-ejb/beanClient!beanMetier.beanClientLocal");
        } catch (NamingException ne) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", ne);
            throw new RuntimeException(ne);
        }
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
}
