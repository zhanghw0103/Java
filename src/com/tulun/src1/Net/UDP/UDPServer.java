package com.tulun.src1.Net.UDP;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;
import java.util.Scanner;

public class UDPServer {
    private DatagramPacket packet;
    private DatagramPacket sendpacket;
    private DatagramSocket socket;
    private Scanner scanner;
    private final int port = 5676;

    public UDPServer() {
        try {
            socket = new DatagramSocket(port);
            byte[] bytes = new byte[1024];
            packet = new DatagramPacket(bytes, 0, bytes.length);
            scanner = new Scanner(System.in);
        } catch (SocketException e) {
            e.printStackTrace();
        }
    }

    public void startUDPServer() {
        try {
            socket.receive(packet);
            String string=new String(packet.getData(),0,packet.getLength());
            System.out.println("接收客户端信息为：" + string);
            String s=scanner.nextLine();
            byte[]bytes=s.getBytes();
            sendpacket=new DatagramPacket(bytes, 0, bytes.length,packet.getAddress(),packet.getPort());
            socket.send(sendpacket);
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if (socket != null) {
//                socket.close();
//                socket=null;
                startUDPServer();
            }
        }
    }

    public static void main(String[] args) {
        new UDPServer().startUDPServer();
    }
}
