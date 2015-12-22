package servlet;

import beanMetier.beanCuisineLocal;
import entities.Commande;
import entities.Commentaire;
import entities.LigneCommande;
import java.io.IOException;
import java.io.StringReader;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
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
        System.out.println("<<--------- Je suis dans onOpen --------->>");
        peers.add(session);
        List<LigneCommande> lc = beanCuisine.afficher();
        afficher(lc, session);
    }

    @OnClose
    public void close(Session session) {
        //Lorsque le navigateur se deconnecte à une jsp avec le script js qui à
        //l'URI du websocket, la session est retirer dans le set de l'EndPoint
        System.out.println("<<--------- Je suis dans onClose --------->>");
        peers.remove(session);
    }

    @OnError
    public void onError(Throwable error) {
        System.out.println("<<--------- Je suis dans onError --------->>");
        Logger.getLogger(EndPoint.class.getName()).log(Level.SEVERE, null, error);
    }

    @OnMessage
    public void onMessage(String message) {
        //C'est ici que le JSON(JavaScript Object Notation) est envoyé et traité
        System.out.println("<<--------- Je suis dans onMessage --------->>");
        System.out.println("Message :" + message);
        try (JsonReader reader = Json.createReader(new StringReader(message))) {
            JsonObject jsonMessage = reader.readObject();
            System.out.println("<<--------- Je suis dans Try --------->>");
            System.out.println("Action :" + jsonMessage.getString("action"));

            if ("add".equals(jsonMessage.getString("action"))) {
                System.out.println("<<--------- Je suis dans Add --------->>");
                System.out.println("Id :" + jsonMessage.getString("id"));
                Commande c = beanCuisine.add(jsonMessage.getString("id"));
                System.out.println("Commande :" + c);
                add(c);
            }

            if ("toggle".equals(jsonMessage.getString("action"))) {
                System.out.println("<<--------- Je suis dans Toggle --------->>");
                LigneCommande lc = beanCuisine.toggle(jsonMessage.getString("id") + "");
                int c = Integer.parseInt(jsonMessage.getString("c")+"");
                System.out.println("changement"+c);
                toggle(lc, c);
            }
        }
        System.out.println("<<--------- Je part de onMessage --------->>");
    }

//METHODE DE TRAITEMENT---------------------------------------------------------
    //Methode pour rajouter les plats d'une commande
    public void add(Commande c) {
        System.out.println("<<--------- Je suis dans add(Commande " + c + " --------->>");
        for (LigneCommande lc : c.getLigneCommandes()) {
            System.out.println("Ligne de Commande : " + lc);
            JsonObject addMessage = createAddMessage(lc);
            System.out.println("Message(add) : " + addMessage);
            sendToAllConnectedSessions(addMessage);
        }
        System.out.println("<<--------- Je pars de add(Commande " + c + " --------->>");
    }

    //Methode pour afficher les lignes de commandes en état 1,2 ou 3
    public void afficher(List<LigneCommande> llc, Session session) {
        System.out.println("<<--------- Je suis dans afficher(ListeCommande) --------->>");
        for (LigneCommande lc : llc) {
            System.out.println("Ligne de Commande : " + lc);
            JsonObject addMessage = createAddMessage(lc);
            System.out.println("Message(add) : " + addMessage);
            sendToSession(session, addMessage);
        }
        System.out.println("<<--------- Je pars de add(ListeCommande)--------->>");
    }

    //Methode pour changer l'état d'un plat
    public void toggle(LigneCommande lc, int c) {
        JsonProvider provider = JsonProvider.provider();
        if (lc != null) {
            switch (c) {
                case 1:
                    lc.setEtat(2);
                    break;
                case 2:
                    lc.setEtat(3);
                    break;
            }
            System.out.println("Je suis dant Toggle = "+lc.getEtat());
            beanCuisine.actualiser(lc);
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
        System.out.println("<<--------- Je suis dans createAddMessage(LigneCommande " + lc + " --------->>");
        if (lc.getCommentaire() == null) {
            Commentaire c = new Commentaire();
            lc.setCommentaire(c);
            c.setContenu("");
        }
        JsonProvider provider = JsonProvider.provider();
        JsonObject addMessage = provider.createObjectBuilder()
                .add("action", "add")
                .add("id", lc.getId())
                .add("commande", lc.getCommande().getNumero())
                .add("produit", lc.getProduit().getNomProduit())
                .add("status", lc.getEtat())
                .add("commentaire", lc.getCommentaire().getContenu())
                .add("date", lc.getCommande().getDate()+"")
                .add("cuisson", lc.getCuisson())
                .build();
        System.out.println("<<--------- Je pars de createAddMessage(LigneCommande " + addMessage + " --------->>");
        return addMessage;
    }

    //Methode pour envoyé le JSON à une session
    private void sendToSession(Session session, JsonObject message) {
        try {
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
