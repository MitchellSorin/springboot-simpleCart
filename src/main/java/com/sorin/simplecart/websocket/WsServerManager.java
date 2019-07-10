package com.sorin.simplecart.websocket;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;

/**
 * userServerManager
 *
 * @author LSD
 * @date 2019/06/19
 **/
public class WsServerManager {

    private static final Logger logger = LoggerFactory.getLogger(WsServerManager.class);

    private static Collection<WsServer> servers =
            Collections.synchronizedCollection(new ArrayList<>());

    public static void broadCast(String msg) {
        for (WsServer server : servers) {
            try {
                server.sendMessage(msg);
            } catch (Exception e) {
                logger.error("userServer send msg error:", e);
            }
        }
    }

    public static void add(WsServer server) {
        servers.add(server);
        logger.info("有新连接加入！ 当前总连接数是：" + servers.size());
    }

    public static void remove(WsServer server) {
        servers.remove(server);
        logger.info("有连接退出！ 当前总连接数是：" + servers.size());
    }

    public static int getTotal() {
        return servers.size();
    }

}
