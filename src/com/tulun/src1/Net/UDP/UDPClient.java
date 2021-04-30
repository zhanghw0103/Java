package com.tulun.src1.Net.UDP;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.util.Scanner;

public class UDPClient {
    private DatagramPacket packet;
    private DatagramPacket sendpacket;
    private DatagramSocket socket;
    private Scanner scanner;
    private final int port = 5676;
    private final String IP="127.0.0.1";

    public UDPClient() {
        try {
            socket = new DatagramSocket();
            byte[] bytes = new byte[1024];
            packet = new DatagramPacket(bytes, 0, bytes.length);
            scanner = new Scanner(System.in);
        } catch (SocketException e) {
            e.printStackTrace();
        }
    }

    public void startUDPClient() {
        try {
            String s=scanner.nextLine();
            if(s.equals(new String("exit"))){
                System.out.println("客服端关闭");
                socket.close();
                socket=null;
                return;
            }
            byte[]bytes=s.getBytes();
            sendpacket=new DatagramPacket(bytes, 0, bytes.length, InetAddress.getByName(IP),port);
            socket.send(sendpacket);
            socket.receive(packet);
            String string=new String(packet.getData(),0,packet.getLength());
            System.out.println("接收服务器信息为：" + string);

        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if (socket != null) {
//                socket.close();
//                socket=null;
                startUDPClient();
            }
        }
    }

    public static void main(String[] args) {
        new UDPClient().startUDPClient();
    }

}
