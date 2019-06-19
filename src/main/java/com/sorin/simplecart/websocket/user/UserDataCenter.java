package com.sorin.simplecart.websocket.user;

import com.sorin.simplecart.bean.User;
import com.sorin.simplecart.service.api.UserServcie;
import com.sorin.simplecart.utils.Page4Navigator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;

/**
 * userDataCenter
 *
 * @author LSD
 * @date 2019/06/19
 **/
@WebServlet(name = "UserDataCenter", urlPatterns = "/UserDataCenter", loadOnStartup = 1)
public class UserDataCenter extends HttpServlet implements Runnable {
    private static final Logger logger = LoggerFactory.getLogger(UserDataCenter.class);

    @Autowired
    private UserServcie userServcie;

    @Override
    public void init(ServletConfig config) throws ServletException {
        startUp();
    }

    private void startUp() {
        new Thread(this).start();
    }

    @Override
    public void run() {
        while (true) {
            try {
                Thread.sleep(3000);
            } catch (Exception e) {
                logger.error("UserDataCenter--run--error:", e);
            }
            Page4Navigator<User> users = userServcie.select(0, 0, "", "", "", "");
            long userNum = users.getTotalElements();
            UserServerManager.broadCast("当前系统用户总数" + userNum);
        }
    }
}
