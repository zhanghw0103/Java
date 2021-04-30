package com.tulun.src1.Net.BIO;


import java.io.*;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class BIOClient {
    private Socket socket;
    private Scanner scanner;
    private final int port = 5676;
    private final String IP = "127.0.0.1";
    private BufferedWriter writer;
    private BufferedReader reader;
    private OutputStream outputStream;
    private InputStream inputStream;
    private volatile boolean flag=true;

    public BIOClient() {
        scanner = new Scanner(System.in);
        try {
            socket = new Socket(IP, port);
        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void startBIOClient() {
        try {
            boolean close = socket.isClosed();
            if (!close) {
                outputStream = socket.getOutputStream();
                inputStream = socket.getInputStream();
                writer = new BufferedWriter(new OutputStreamWriter(outputStream));
                reader = new BufferedReader(new InputStreamReader(inputStream));
                new Thread(new SendThread()).start();
                while (flag) {
                    String str = reader.readLine();
                    System.out.println("接收服务器消息为：" + str);


                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (socket != null) {
                    socket.close();
                    socket = null;
                }
                if (reader != null) {
                    reader.close();
                    reader = null;
                }
                if (writer != null) {
                    writer.close();
                    writer = null;
                }
                if (outputStream != null) {
                    outputStream.close();
                    outputStream = null;
                }
                if (inputStream != null) {
                    inputStream.close();
                    inputStream = null;
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    class SendThread implements Runnable {

        @Override
        public void run() {
            try {
                while (true) {
                    System.out.println("请输入");
                    String str1 = scanner.nextLine();
                    writer.write(str1 + "\n");
                    writer.flush();
                    if (str1.equals("exit")) {
                        System.out.println("客户端关闭");
                        flag=false;
                        break;
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }


    public static void main(String[] args) {
        new BIOClient().startBIOClient();
    }
}
