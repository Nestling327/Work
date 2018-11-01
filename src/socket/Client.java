package socket;

import thread.Receive;
import thread.Send;

import java.io.*;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class Client {
    private static String getlocalip(){
        String ip = null;
        try {
            InetAddress inet = InetAddress.getLocalHost();
            ip = inet.getHostAddress();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
        return ip;
    }
    public static void main(String[] args) {
        try {
            Socket s = new Socket(getlocalip(),8888);
            new Receive(s).start();
            new Send(s).start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
