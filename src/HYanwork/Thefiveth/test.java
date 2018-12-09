package HYanwork.Thefiveth;

import HYanwork.TheThird.JWZXspider.DButil;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import javax.xml.transform.Result;
import java.sql.*;

public class test {
    /*
    bj: "04911702"
csrq: "20001121"
mz: "汉族                "
nj: "2017"
rxrq: "201709"
xb: "女"
xh: "2017211688"
xjzt: "在校"
xm: "赵宁静"
xmEn: "Zhao Ning Jing "
xz: 4
yxh: "04"
yxm: "计算机科学与技术学院"
yxmen: "School of Computer Science and Technology "
zyh: "0491"
zym: "计算机科学与技术专业卓越工程师班"
zymEn: "Computer Science and Technology"
     */
    public static void main(String[] args) throws SQLException {
        Connection conn = DButil.getCon();
        String str_new = "INSERT INTO STUINFO VALUES(?,?,?)";
        String str_select = "SELECT chinese,math,english FROM GRADE WHERE name = ?";
        PreparedStatement p_select = conn.prepareStatement(str_select);
        PreparedStatement p_new = conn.prepareStatement(str_new);
        String xm;
        String yxm;
        String zym;
        int chinese;
        int math;
        int english;
        ResultSet rs;
        String url = "http://jwzx.cqupt.edu.cn/data/json_StudentSearch.php";
        String param ="searchKey=04911702";
        JsonParser parser = new JsonParser();
        JsonObject object = (JsonObject)parser.parse(HttpPostRequest.doPost(url,param));
        JsonArray array = object.getAsJsonArray("returnData");
        for (int i=0;i<array.size();i++){
            System.out.println("------------");
            JsonObject subObject = array.get(i).getAsJsonObject();
            xm = subObject.get("xm").getAsString();
            yxm = subObject.get("yxm").getAsString();
            zym = subObject.get("zym").getAsString();
            p_new.setString(1,xm);
            p_new.setString(2,yxm);
            p_new.setString(3,zym);
            p_select.setString(1,xm);
            p_new.executeQuery();
            rs = p_select.executeQuery();
            while (rs.next()){
                chinese = rs.getInt(1);
                math = rs.getInt(2);
                english = rs.getInt(3);
                System.out.println(xm+"语文："+chinese+"数学"+math+"英语"+english);
            }
        }

    }
}
