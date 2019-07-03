package com.sorin.simplecart.websocket.user;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;

/**
 * userServer
 *
 * @author LSD
 * @date 2019/06/19
 **/
@ServerEndpoint("/ws/user/dataServer")
public class UserServer {

    private static final Logger logger = LoggerFactory.getLogger(UserServer.class);
    private Session session;

    @OnOpen
    public void onOpen(Session session) {
        this.session = session;
        UserServerManager.add(this);
    }

    @OnClose
    public void onClose() {
        UserServerManager.remove(this);
    }

    @OnError
    public void onError(Session session, Throwable error) {
        String id = session.getId();
        logger.error("UserServer connection error, sessionID:" + id + "  error:", error);
    }

    @OnMessage
    public void onMessage(String message, Session session) {
        String id = session.getId();
        logger.info("sessionID:" + id + ",client message:" + message);
    }

    public void sendMessage(String message) throws IOException {
        this.session.getBasicRemote().sendText(message);
    }
}
