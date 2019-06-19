package com.sorin.simplecart.websocket.user;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

/**
 * userServerManager
 *
 * @author LSD
 * @date 2019/06/19
 **/
public class UserServerManager {

    private static final Logger logger = LoggerFactory.getLogger(UserServerManager.class);

    private static Collection<UserServer> servers =
            Collections.synchronizedCollection(new ArrayList<>());

    public static void broadCast(String msg) {
        for (UserServer server : servers) {
            try {
                server.sendMessage(msg);
            } catch (Exception e) {
                logger.error("userServer send msg error:", e);
            }
        }
    }

    public static void add(UserServer server) {
        logger.info("有新连接加入！ 当前总连接数是：" + servers.size());
        servers.add(server);
    }

    public static void remove(UserServer server) {
        logger.info("有连接退出！ 当前总连接数是：" + servers.size());
        servers.remove(server);
    }

    public static int getTotal() {
        return servers.size();
    }

}
