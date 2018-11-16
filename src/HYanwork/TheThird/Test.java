package HYanwork.TheThird;

import java.io.*;

public class Test {
    public static void main(String[] args) throws Exception {
        Student chen = new Student("陈小雨",201801,19,"女");
        Student sun = new Student("孙大陆",201753,20,"男");
        Student li = new Student("李四",201912,18,"男");
        Student zhang = new Student("张三",201899,19,"男");
        Student gan = new Student("甘沐沐",201856,19,"女");
        Student zhou = new Student("周静",201703,17,"女");
        Class rocket = new Class(04);
        rocket.addstu(chen);
        rocket.addstu(sun);
        rocket.addstu(li);
        rocket.addstu(zhang);
        rocket.addstu(gan);
        rocket.addstu(zhou);
        rocket.deletstu("李四");
        rocket.addstu(li);
        rocket.deletstu(li.getStudentID());
        rocket.addstu(li);
        rocket.agesort();
        rocket.stuidsort();
        rocket.reverstuidsort();
        //SerializeClass(rocket);
        DeserializeClass();
    }
    private static void SerializeClass(Object object) throws IOException {
        FileOutputStream fos = new FileOutputStream(new File("G:/序列化储存/Student.txt"));
        ObjectOutputStream oo = new ObjectOutputStream(fos);
        oo.writeObject(object);
        System.out.println("序列化完成");
        oo.close();
        fos.close();
    }
    private static void DeserializeClass() throws Exception, IOException{
        FileInputStream fis = new FileInputStream(new File("G:/序列化储存/Student.txt"));
        ObjectInputStream ois =new ObjectInputStream(fis);
        Class rocket2 = (Class)ois.readObject();
        System.out.println("序列化反相成功");
        rocket2.stuidsort();
        ois.close();
        fis.close();
    }
}
