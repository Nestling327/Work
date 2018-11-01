package thread;

import java.util.LinkedList;
import java.util.Random;

public class PJpassword {
    public static void main(String[] args) {
        String password ="";
        for (int i=0;i<3;i++){
            int seed = new Random().nextInt(10);
            password += seed;
        }
        LinkedList<String> li = new LinkedList<>();
        System.out.println("password: \n"+password);
        Thread t1 = new Thread() {
            @Override
            public void run() {
                String testpassword="";
                for (int i=0;i<10;i++){
                    for (int j=0;j<10;j++){
                        for (int k=0;k<10;k++){
                            testpassword = testpassword+i+j+k;
                            li.offer(testpassword);
                        }
                    }
                }
            }
        };
        String finalPassword = password;
        Thread t2 = new Thread(){
            @Override
            public void run() {
                while (true){
                    if (li.isEmpty()){
                        try {
                            Thread.sleep(100);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    String s = li.removeFirst();
                    System.out.println(s);
                    if (s.equals(finalPassword))
                        break;

                }
            }
        };
        t1.start();
        t2.start();
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("通过穷举法得password为：" + password);

    }
}
