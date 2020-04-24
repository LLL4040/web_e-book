package backend.WebSocket;

import backend.WebSocket.Decoders.MessageDecoder;
import backend.WebSocket.Encoders.ChatMessageEncoder;
import backend.WebSocket.Encoders.InfoMessageEncoder;
import backend.WebSocket.Encoders.JoinMessageEncoder;
import backend.WebSocket.Encoders.UsersMessageEncoder;
import backend.WebSocket.Messages.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;

@Component
/* Websocket endpoint */
@ServerEndpoint(
        value = "/websocket",
        decoders = { MessageDecoder.class },
        encoders = { JoinMessageEncoder.class, ChatMessageEncoder.class,
                InfoMessageEncoder.class, UsersMessageEncoder.class }
)
/* There is a Endpoint instance per connetion */
public class Endpoint {
    private static final Logger hbaseLog = LoggerFactory.getLogger("HBase");

    @OnOpen
    public void openConnection(Session session) {
        hbaseLog.info("Connection opened.");
    }

    @OnMessage
    public void message(final Session session, Message msg) {
        hbaseLog.info("Received: " + msg.toString());

        if (msg instanceof JoinMessage) {
            /* Add the new user and notify everybody */
            JoinMessage jmsg = (JoinMessage) msg;
            session.getUserProperties().put("name", jmsg.getName());
            session.getUserProperties().put("active", true);
            hbaseLog.info("Received: " + jmsg.toString());
            sendAll(session, new InfoMessage(jmsg.getName() + " has joined the chat"));
            sendAll(session, new ChatMessage("Duke", jmsg.getName(), "Hi there!!"));
            sendAll(session, new UsersMessage(this.getUserList(session)));

        } else if (msg instanceof ChatMessage) {
            /* Forward the message to everybody */
            final ChatMessage cmsg = (ChatMessage) msg;
            hbaseLog.info("Received: " + cmsg.toString());
            sendAll(session, cmsg);
        }
    }

    @OnClose
    public void closedConnection(Session session) {
        /* Notify everybody */
        session.getUserProperties().put("active", false);
        if (session.getUserProperties().containsKey("name")) {
            String name = session.getUserProperties().get("name").toString();
            sendAll(session, new InfoMessage(name + " has left the chat"));
            sendAll(session, new UsersMessage(this.getUserList(session)));
        }
        hbaseLog.info("Connection closed.");
    }

    @OnError
    public void error(Session session, Throwable t) {
        hbaseLog.info("Connection error: " + t.toString());
    }

    /* Forward a message to all connected clients
     * The endpoint figures what encoder to use based on the message type */
    public synchronized void sendAll(Session session, Object msg) {
        try {
            for (Session s : session.getOpenSessions()) {
                if (s.isOpen()) {
                    s.getBasicRemote().sendObject(msg);
                    hbaseLog.info("Sent: " + msg.toString());
                }
            }
        } catch (IOException | EncodeException e) {
            hbaseLog.info(e.toString());
        }
    }

    /* Returns the list of users from the properties of all open sessions */
    public List<String> getUserList(Session session) {
        List<String> users = new ArrayList<>();
        //users.add("Duke");
        for (Session s : session.getOpenSessions()) {
            if (s.isOpen() && (boolean) s.getUserProperties().get("active")){
                users.add(s.getUserProperties().get("name").toString());
            }
        }
        return users;
    }
}
