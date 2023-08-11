package com.shawn.king.chat;

import java.io.IOException;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;

/**
 * @author Shawn
 * @date 2023/8/7 23:04
 * @description
 */

@ServerEndpoint("/chat")
public class ChatServlet {
    private static Set<Session> sessions = Collections.synchronizedSet(new HashSet<Session>());

    @OnOpen
    public void onOpen(Session session) {
        sessions.add(session);
        System.out.println("WebSocket connection opened: " + session.getId());
    }

    @OnMessage
    public void onMessage(String message, Session session) throws IOException {
        System.out.println("Message received from " + session.getId() + ": " + message);
        for (Session s : sessions) {
            s.getBasicRemote().sendText(message);
        }
    }

    @OnClose
    public void onClose(Session session) {
        sessions.remove(session);
        System.out.println("WebSocket connection closed: " + session.getId());
    }
}
