package thread;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;

public class Receive extends Thread{
    private Socket s = null;
    public Receive(Socket s){
        this.s = s;
    }
    @Override
    public void run() {
        try {
            InputStream is = s.getInputStream();
            DataInputStream dis = new DataInputStream(is);
            while (true){
                String msg = dis.readUTF();
                System.out.println("接受到消息："+msg);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
