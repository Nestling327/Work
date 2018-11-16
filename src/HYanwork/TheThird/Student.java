package HYanwork.TheThird;

import java.io.Serializable;

public class Student implements Serializable {
    private static final long serialVersionID = 5809782578272943999L;
    private String name;
    private int clas;
    private long studentID;
    private int years;
    private String sex;
    public Student(String name,long studentID,int years,String sex){
        this.name = name;
        this.studentID = studentID;
        this.years = years;
        this.sex = sex;
    }
    public int getYears() {
        return years;
    }

    public String getName() {
        return name;
    }

    public int getClas() {
        return clas;
    }

    public String getSex() {
        return sex;
    }

    public long getStudentID() {
        return studentID;
    }

    public void setClas(int clas) {
        this.clas = clas;
    }

    public String toString() {
        return "Person [name=" + name + ", age=" + years +"，stuid="+studentID+ "]";
    }
}
