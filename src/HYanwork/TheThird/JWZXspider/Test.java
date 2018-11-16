package HYanwork.TheThird.JWZXspider;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Test {
    private static String httpRequest(String url){
        StringBuffer buffer = null;
        BufferedReader bufferedReader =null;
        InputStream inputStream =null;
        InputStreamReader inputStreamReader = null;
        HttpURLConnection httpcon = null;
        try {
            URL url1 = new URL(url);
            httpcon = (HttpURLConnection) url1.openConnection();
            httpcon.setDoInput(true);
            httpcon.setRequestMethod("GET");
            inputStream = httpcon.getInputStream();
            inputStreamReader = new InputStreamReader(inputStream);
            bufferedReader = new BufferedReader(inputStreamReader);
            buffer = new StringBuffer();
            String str = null;
            while ((str = bufferedReader.readLine())!=null){
                buffer.append(str);
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            try {
                bufferedReader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                inputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                inputStreamReader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            httpcon.disconnect();
            return buffer.toString();
        }
    }
    private static String htmlFiter(String html){
        StringBuffer buffer = new StringBuffer();
        String str1 = "";
        String str2 = "";
        Pattern p = Pattern.compile("(.*)<div id=\\\"kbTabs-bj\\\" aria-labelledby=\\\"ui-id-3\\\" class=\\\"ui-tabs-panel ui-widget-contui-corner-bottom\" role=\"tabpanel\" aria-hidden=\"false\" style=\"display: block;\">(.*?)(</div>)(.*)");
        Matcher m = p.matcher(html);
        if (m.matches()){
            str1 = m.group(3);
            p = Pattern.compile("(.*)(<h3>)(.*?)(</h3>)(.*)");
            m = p.matcher(str1);
            if (m.matches()){
                str2 = m.group(3);
            }
        }
    }
}
