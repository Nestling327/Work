package socket;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

public class TestSocket {
    public static void main(String[] args) {
        try {
            InetAddress inet = InetAddress.getLocalHost();
            String ip = inet.getHostAddress();
            String ipRange = ip.substring(0,ip.lastIndexOf("."));
            System.out.println("本机地址："+ip);
            System.out.println("网段是："+ipRange);
            List<String> ips = new ArrayList<>();
            AtomicInteger number = new AtomicInteger();
            ThreadPoolExecutor threadpool = new ThreadPoolExecutor(10,15,60, TimeUnit.SECONDS,
                    new LinkedBlockingQueue<Runnable>());
            for (int i =0;i<255;i++){
                String testip = ipRange +"."+ (i+1);
                threadpool.execute(new Runnable() {
                    @Override
                    public void run() {
                        if (isReachable(testip)){
                            ips.add(testip);
                            synchronized (number){
                                System.out.println("已经完成"+number.incrementAndGet()+"个 ip测试");
                            }
                        }
                    }
                });
            }
            threadpool.shutdown();
            if(threadpool.awaitTermination(1,TimeUnit.HOURS)){
                System.out.println("如下ip地址可以连接");
                for (String theip : ips){
                    System.out.println(theip);
                }
                System.out.println("总共有："+ips.size()+"个地址");
            }
        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
    private static boolean isReachable(String ip){
        try{
            boolean reachable = false;
            Process p = Runtime.getRuntime().exec("ping "+ip);
            BufferedReader br = new BufferedReader(new InputStreamReader(p.getInputStream()));
            String line = null;
            StringBuilder sb = new StringBuilder();
            while ((line=br.readLine())!=null) {
                if (line.length() != 0) {
                    sb.append(line + "\r\n");
                }
            }
                reachable = sb.toString().contains("TTL");
                br.close();
                return reachable;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

}
