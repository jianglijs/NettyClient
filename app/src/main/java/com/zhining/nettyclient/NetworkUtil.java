package com.zhining.nettyclient;

import android.content.Context;
import android.net.wifi.WifiManager;
import android.text.format.Formatter;

import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketAddress;

/**
 * @author：Jiangli
 * @date：2021/09/11 8:47
 */
public class NetworkUtil {
    public static boolean ping(String ipAddress, int timeOut) {
        try {
            boolean status = InetAddress.getByName(ipAddress).isReachable(timeOut); // 当返回值是true时，说明host是可用的，false则不可。
            return status;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }


    public static boolean isNetworkOnline(String ip) {
        Runtime runtime = Runtime.getRuntime();
        try {
            Process ipProcess = runtime.exec("ping -c 1 " + ip);
            int exitValue = ipProcess.waitFor();
            return (exitValue == 0);
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * 判断主机端口
     *
     * @param hostName 主机名称
     * @param port     端口
     * @param timeout  超时设置，单位毫秒
     * @return boolean - true/false
     */
    public static boolean isHostPortAlive(String hostName, int port, int timeout) {
        boolean isAlive = false;
        SocketAddress socketAddress = new InetSocketAddress(hostName, port);
        Socket socket = new Socket();
        try {
            socket.connect(socketAddress, timeout);
            socket.close();
            isAlive = true;
        } catch (IOException exception) {

        }
        return isAlive;
    }


    public static String getLocalIp(Context context) {
        WifiManager wm = (WifiManager) context.getApplicationContext().getSystemService(Context.WIFI_SERVICE);
        String ip = Formatter.formatIpAddress(wm.getConnectionInfo().getIpAddress());
        return ip;
    }

}
