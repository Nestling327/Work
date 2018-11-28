package HYanwork.TheThird.JWZXspider;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Test {
    //返回网业源码
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
    //同时进行过滤和数据库插入
    private static String htmlFiter(String html,Connection con) throws SQLException {
        String str_new = "INSERT INTO classtable VALUES(?,?,?,?,?)";
        String str_upd = "UPDATE classtable set time=?";
        String str_select = "SELECT time FROM classtable";
        ResultSet rs = null;
        PreparedStatement p_seclct = con.prepareStatement(str_select);
        PreparedStatement p_new = con.prepareStatement(str_new);
        PreparedStatement p_upd = con.prepareStatement(str_upd);
        StringBuffer buffer = new StringBuffer();
        String str1 = "";
        String str2 = "";
        Pattern p = Pattern.compile("(.*)(<div class=\"printTable\">)(.*?)(</div>)(.*)");
        Matcher m = p.matcher(html);
        if (m.matches()){
            str1 = m.group(3);
            p = Pattern.compile("(.*)(<tbody>)(.*?)(</tbody>)(.*)");
            m = p.matcher(str1);
            if (m.matches()){//<td rowspan="2" align="center">理论</td>
                str2 = m.group(3);
                System.out.println(str2);
                p = Pattern.compile("<tr.*?>(.*?)</tr>");//筛查每列
                m = p.matcher(str2);
                List<String> list = new ArrayList<>();
                String location =null;
                int i=0,j=0;
                Pattern p2 = Pattern.compile("<td.*?>(.*?)</td>");//筛查每行
                Matcher m2 = null;
                String str3 =null;
                StringBuffer list_select =new StringBuffer();
                while (m.find()){
                    str1 = m.group(1);
                    m2 = p2.matcher(str1);
                    while (m2.find()){
                        str3 = m2.group(1);
                        if (str3.contains("href")||str3==""||str3.equals("")){
                            continue;
                            }
                        i++;
                        list.add(str3);
                        }
                    System.out.print(i);
                        if (i==8) {//当一行含有8列时，表示是船新的课程
                            buffer.append(list.get(0)+"\n");
                            buffer.append(list.get(1)+"\n");
                            buffer.append(list.get(5)+"\n");
                            buffer.append(list.get(6)+"\n");
                            buffer.append(list.get(7)+"\n");
                            location =list.get(7);
                            i=0;
                            p_new.setString(1,list.get(0));
                            p_new.setString(2,list.get(1));
                            p_new.setString(3,list.get(5));
                            p_new.setString(4,list.get(6));
                            p_new.setString(5,list.get(7));
                            list.clear();
                        }
                    System.out.println(location);
                    if (i==3){//当一行只有3列时，即表示该课程有不同的行课时间，需要更新数据库time
                            buffer.append(list.get(1)+"\n");
                            rs = p_seclct.executeQuery();
                            while (rs.next()){
                                list_select.append(rs.getString(1));
                            }
                            list_select.append(list.get(1));
                            p_upd.setString(1,list_select.toString());
                            i=0;
                            list.clear();
                        }
                    }
                }
            }
        return buffer.toString();
    }
    public static String getClassInfo(Connection con) throws SQLException {
        String html = httpRequest("http://jwzx.cqupt.edu.cn/kebiao/kb_stu.php?xh=2017211856");
        String result = htmlFiter(html,con);
        return result;
    }
    public static void main(String[] args) throws SQLException {
        String info = getClassInfo(DButil.getCon());
        System.out.println(info);
    }
}