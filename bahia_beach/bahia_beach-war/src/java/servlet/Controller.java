package servlet;

import java.io.IOException;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import sousController.ControllerInterface;


public class Controller extends HttpServlet {
    
    
    
    //type de mon interface la valeur de la hashmap
    private HashMap<String, ControllerInterface> mp;

    //methode == @postconstruct pour le controller
    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        mp = new HashMap();
//        je recupere une enumeration des nom de parametre de mon fichier web.xml
        for(Enumeration<String> e = config.getInitParameterNames();
                e.hasMoreElements();
                ){
            String name = e.nextElement();
//            je recupere les valeurs de parametre de mon fichier web.xml via le nom
            String valeur = config.getInitParameter(name);
            try {
//            mais c'est sous forme de chaine de caractere, donc il faut instancier une class selon le nom de la class:
                ControllerInterface ctrl = (ControllerInterface) Class.forName(valeur).newInstance();
                mp.put(name, ctrl);
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
            } catch (InstantiationException ex) {
                Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IllegalAccessException ex) {
                Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        }
    }

    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");
        
        String section = request.getParameter("section");
        
        //page sur laquelle renvoie l'appli au debut
        String url = "/WEB-INF/mvc2/index.jsp";

        //code à faire
        
        if(section != null) {
            ControllerInterface ctrl = mp.get(section);
            url = ctrl.execute(request, response, this);
        }
        
        //le reste se passe ds sous controller  
        //indiquer les variable session application EJB... qu'on a besoin ds les sous-controller et plus ds le controller principal
        
        url = response.encodeRedirectURL(url);
        getServletContext().getRequestDispatcher(url).include(request, response);
     
        
        
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
