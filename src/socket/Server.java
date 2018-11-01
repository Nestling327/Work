package socket;

import thread.Receive;
import thread.Send;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class Server {
    public static void main(String[] args) {

            try {
                ServerSocket ss = new ServerSocket(8888);
                System.out.println("现在正在监听窗口...");
                Socket s = ss.accept();
                new Receive(s).start();
                new Send(s).start();
            } catch (IOException e) {
                e.printStackTrace();
            }

    }
}
