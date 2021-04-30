package com.tulun.src1.Net.BIO;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;


public class ServerHandler implements Runnable {
    private Socket clientSocket;
    private BufferedWriter writer;
    private BufferedReader reader;
    private OutputStream outputStream;
    private InputStream inputStream;
    private Scanner scanner;


    public ServerHandler(Socket clientSocket) throws IOException {
        scanner = new Scanner(System.in);
        this.clientSocket = clientSocket;
        try {
            outputStream = clientSocket.getOutputStream();//负责发送消息
            inputStream = clientSocket.getInputStream();// 负责接收消息
            //字节转字符的过程
            //文件传输
//            BufferedOutputStream outputStream1 = new BufferedOutputStream(outputStream);
            writer = new BufferedWriter(new OutputStreamWriter(outputStream));
            reader = new BufferedReader(new InputStreamReader(inputStream));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        try {
            while (true) {
                String str = null;
                str = reader.readLine();
                System.out.println("接收客户端消息为：" + str);
                if (str.equals("exit")) {
                    System.out.println("客户端关闭");
                    break;
                }
                System.out.println("请输入回复");
                String str1 = scanner.nextLine();
                writer.write(str1 + "\n");
                writer.flush();
            }

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
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
}
