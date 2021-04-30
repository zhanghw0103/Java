package com.tulun.src1.Net.BIO;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class BIOServer {
    private ServerSocket serverSocket;
    private Scanner scanner;
    private final int port = 5676;


    public BIOServer() {
        try {
            //将服务器绑定到特定端口上
            serverSocket = new ServerSocket(port);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void startTCPServer() {
        try {
            while (true) {
                Socket clientSocket = serverSocket.accept();//阻塞方法   阻塞到连接建立上为止
                //TCP 建立连接  三次握手   三次握手的终点
//            clientSocket请求连接的用户一一对应
                //后面讲使用该clientSocket进行和这个用户之间的通信
                new Thread(new ServerHandler(clientSocket)).start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (serverSocket != null) {
                    serverSocket.close();
                    serverSocket = null;
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


    public static void main(String[] args) {
        new BIOServer().startTCPServer();
    }
}
