package servlet;

import beanMetier.beanCuisineLocal;
import entities.Commande;
import entities.LigneCommande;
import java.io.IOException;
import java.io.StringReader;
import java.util.HashSet;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.bean.ApplicationScoped;
import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonReader;
import javax.json.spi.JsonProvider;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

@ApplicationScoped
@ServerEndpoint("/cuisine")
public class EndPoint {
    
//BEAN METIER-------------------------------------------------------------------
    beanCuisineLocal beanCuisine = lookupbeanCuisineLocal();
    
    private beanCuisineLocal lookupbeanCuisineLocal() {
        try {
            Context c = new InitialContext();
            return (beanCuisineLocal) c.lookup("java:global/bahia_beach/bahia_beach-ejb/beanCuisine!beanMetier.beanCuisineLocal");
        } catch (NamingException ne) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", ne);
            throw new RuntimeException(ne);
        }
    }

//SET DES SESSIONS CONNECTER----------------------------------------------------
    private static Set<Session> peers = new HashSet<Session>();
   
//METHODE DE ENDPOINT-----------------------------------------------------------
    @OnOpen
    public void open(Session session) {
        //Lorsque le navigateur se connecte à une jsp avec le script js qui à
        //l'URI du websocket, la session est enregister dans le set de l'EndPoint
        peers.add(session);
    }

    @OnClose
    public void close(Session session) {
        //Lorsque le navigateur se deconnecte à une jsp avec le script js qui à
        //l'URI du websocket, la session est retirer dans le set de l'EndPoint
        peers.remove(session);
    }

    @OnError
    public void onError(Throwable error) {
        Logger.getLogger(EndPoint.class.getName()).log(Level.SEVERE, null, error);
    }

    @OnMessage
    public void onMessage(String message) {
        //C'est ici que le JSON(JavaScript Object Notation) est envoyé et traité
        System.out.println("Je suis dans onMessage");
        System.out.println("Message :" + message);
        try (JsonReader reader = Json.createReader(new StringReader(message))) {
            JsonObject jsonMessage = reader.readObject();
            System.out.println("Je suis dans try");
            System.out.println(jsonMessage.getString("action"));
            if ("add".equals(jsonMessage.getString("action"))) {
                Commande c = beanCuisine.add(jsonMessage.getString("id"));
                add(c);
            }
            if ("toggle".equals(jsonMessage.getString("action"))) {
                LigneCommande lc = beanCuisine.toggle(jsonMessage.getString("id"));
                System.out.println("test:" + lc);
                toggle(lc);
            }
        }
    }
    
//METHODE DE TRAITEMENR---------------------------------------------------------
    //Methode pour rajouter les plats d'une commande
    public void add(Commande c) {
        for (LigneCommande lc : c.getLigneCommandes()) {
            JsonObject addMessage = createAddMessage(lc);
            sendToAllConnectedSessions(addMessage);
        }
    }
    
    //Methode pour changer l'état d'un plat
    public void toggle(LigneCommande lc) {
        JsonProvider provider = JsonProvider.provider();
        if (lc != null) {
            if (1 == lc.getEtat()) {
                lc.setEtat(2);
            } else {
                lc.setEtat(3);
            }
            JsonObject updateDevMessage = provider.createObjectBuilder()
                    .add("action", "toggle")
                    .add("id", lc.getId())
                    .add("status", lc.getEtat())
                    .build();
            sendToAllConnectedSessions(updateDevMessage);
        }
    }

//METHODE DE RENVOIE------------------------------------------------------------
    //Methode de traitement de l'objet java en JSON
    private JsonObject createAddMessage(LigneCommande lc) {
        JsonProvider provider = JsonProvider.provider();
        JsonObject addMessage = provider.createObjectBuilder()
                .add("action", "add")
                .add("id", lc.getId())
                .add("produit", lc.getProduit().getNomProduit())
                .add("idProduit", lc.getProduit().getId())
                .add("status", lc.getEtat())
                .build();
        return addMessage;
    }
    
    //Methode pour envoyé le JSON à une session
    private void sendToSession(Session session, JsonObject message) {
        try {
            System.err.println("Dans catch SentToSession" + message);
            session.getBasicRemote().sendText(message.toString());
        } catch (IOException ex) {
            peers.remove(session);
        }
    }
    
    //Methode pour envoyé le JSON à toutes les sessions connectés
    private void sendToAllConnectedSessions(JsonObject message) {
        for (Session session : peers) {
            sendToSession(session, message);
        }
    }

    
}
