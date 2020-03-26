package com.xiaoxin.demo.util;

import javax.swing.*;
import java.io.IOException;
import java.net.BindException;
import java.net.ServerSocket;

/**
 * @author fuqiangxin
 * @Title: PortUtil
 * @Description: 判断是否启动redis
 * @date 2020/3/2515:20
 */
public class PortUtil {
    public static boolean testPort(int port) {
        try {
            ServerSocket serverSocket = new ServerSocket(port);
            serverSocket.close();
            return false;
        } catch (BindException e) {
            return true;
        } catch (IOException e) {
            return true;
        }
    }

    public static void checkPort(int port, String server, boolean shutdown) {
        if (!testPort(port)) {
            String message = "";
            if (shutdown) {
                message = String.format("在端口%d未检查得到%s启动%n", port, server);
                JOptionPane.showMessageDialog(null, message);
                System.exit(1);
            } else {
                message = String.format("在端口%d未检查得到%s启动%n，是否继续？", port, server);
                if (JOptionPane.OK_OPTION != JOptionPane.showConfirmDialog(null, message)) {
                    System.exit(1);
                }
            }
        }
    }
}
