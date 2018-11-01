package HYanwork.TheFirst;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.regex.Pattern;

public class splitString {
    public static void main(String[] args) {
        String str ="acbdw,1269547,AASIDX,AIUydjs,12sjaA,3819247,ausSHSzio,IUFISsi";
        String[] strs = str.split(",");
        for (String a:strs){
            System.out.println(a);
        }
        isallnum(strs[2]);
        isallChar(strs[2]);
        isallchar(strs[0]);
        transchar(strs[0]);
        sortnum(strs[1]);
        sortchar(strs[0]);
    }
    public static boolean isallnum(String str){
        Pattern pattern = Pattern.compile("[0-9]*");
        if (pattern.matcher(str).matches())     {
            System.out.println(str+"全为数字!");
            return true;
        }else {
            System.out.println(str + "不全为数字");
            return false;
        }
    }
    public static void isallChar(String str){
        Pattern pattern = Pattern.compile("[A-Z]*");
        if (pattern.matcher(str).matches())     {
            System.out.println(str+"全为大写字母!");
        }else {
            System.out.println(str + "不全为大写字母");
        }
    }
    public static void isallchar(String str){
        Pattern pattern = Pattern.compile("[a-z]*");
        if (pattern.matcher(str).matches())     {
            System.out.println(str+"全为小写字母!");
        }else {
            System.out.println(str + "不全为小写字母");
        }
    }
    public static void transchar(String str){
        Pattern pattern = Pattern.compile("[A-z]*");
        if (pattern.matcher(str).matches())     {
            System.out.println("将字符串:"+str+"转化为全大写"+str.toUpperCase());
        }else {
            System.out.println(str + "不全为字母");
        }
    }
    public static void sortnum(String str){
        if (isallnum(str)){
            char[] c=str.toCharArray();
            int[] nums=new int[c.length];
            for(int i=0;i<c.length;i++){
                nums[i]=c[i]-'0';
            }
            Arrays.sort(nums);
            System.out.println("将字符串"+str+"排序后");
            for (int i = 0;i<nums.length;i++){
                System.out.print(nums[i]);
            }
            System.out.println();
        }
    }
    public static void sortchar(String str){
        Pattern pattern = Pattern.compile("[A-z]*");
        if (pattern.matcher(str).matches()){
            System.out.println(str+"全为字母");
            char[] c = str.toCharArray();
            Arrays.sort(c);
            System.out.print("排序后为");
            System.out.println(c);
        }
    }

}
