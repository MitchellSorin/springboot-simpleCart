package com.sorin.simplecart.websocket;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;

/**
 * userServer
 *
 * @author LSD
 * @date 2019/06/19
 **/
@ServerEndpoint("/wsTest")
@Component
public class WsServer {

    private static final Logger logger = LoggerFactory.getLogger(WsServer.class);
    private Session session;

    @OnOpen
    public void onOpen(Session session) {
        this.session = session;
        WsServerManager.add(this);
    }

    @OnClose
    public void onClose() {
        WsServerManager.remove(this);
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
