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

    beanCuisineLocal beanCuisine = lookupbeanCuisineLocal();

    private static Set<Session> peers = new HashSet<Session>();

    @OnOpen
    public void open(Session session) {
        System.out.println("Je suis dans open");
        System.out.println(session);
        peers.add(session);
    }

    @OnClose
    public void close(Session session) {
        peers.remove(session);
    }

    @OnError
    public void onError(Throwable error) {
        Logger.getLogger(EndPoint.class.getName()).log(Level.SEVERE, null, error);
    }

    @OnMessage
    public void onMessage(String message) {
        System.out.println("Je suis dans onMessage");
        System.out.println("Message :" + message);
        try (JsonReader reader = Json.createReader(new StringReader(message))) {
            JsonObject jsonMessage = reader.readObject();
            System.out.println("Je suis dans try");
            System.out.println(jsonMessage.getString("action"));
            if ("add".equals(jsonMessage.getString("action"))) {
                Commande c = beanCuisine.add(jsonMessage.getString("id"));
                System.out.println("test:" + c);
                add(c);
            }
        }
    }

    public void add(Commande c) {
        for (LigneCommande lc : c.getLigneCommandes()) {
            JsonObject addMessage = createAddMessage(lc);
            sendToAllConnectedSessions(addMessage);
        }
    }

    private beanCuisineLocal lookupbeanCuisineLocal() {
        try {
            Context c = new InitialContext();
            return (beanCuisineLocal) c.lookup("java:global/bahia_beach/bahia_beach-ejb/beanCuisine!beanMetier.beanCuisineLocal");
        } catch (NamingException ne) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", ne);
            throw new RuntimeException(ne);
        }
    }

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

    private void sendToSession(Session session, JsonObject message) {
        try {
            System.err.println("Dans catch SentToSession" + message);
            session.getBasicRemote().sendText(message.toString());
        } catch (IOException ex) {
            peers.remove(session);
        }
    }

    private void sendToAllConnectedSessions(JsonObject message) {
        for (Session session : peers) {
            sendToSession(session, message);
        }
    }
}
