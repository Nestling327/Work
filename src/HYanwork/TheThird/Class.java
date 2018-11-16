package HYanwork.TheThird;

import java.io.Serializable;
import java.util.*;

public class Class implements Serializable {
    private static final long serialVersionID = 5809782578272943988L;
    private int classnum;
    private List<Student> list = new ArrayList<>();
    public Class(int classnum){
        this.classnum = classnum;
    }
    public boolean addstu(Student student){
        student.setClas(classnum);
        System.out.println("加入新同学："+student);
        return list.add(student);
    }
    public boolean deletstu(String name){
        for (Student stu :list){
            if (stu.getName().equals(name)){
                System.out.println("找到了"+name+"现在将其移出"+this.classnum);
                list.remove(stu);
                stu.setClas(0);
                return true;
            }
        }
        System.out.println("在"+this.classnum+"班没有找到"+name);
        return false;
        /*Iterator iterator = list.iterator();
        while (iterator.hasNext()){
            Student student = (Student) iterator.next();
            if (student.getName().equals(name)){
                list.remove(student);
                return true;
            }
        }
        return false;*/
    }
    public boolean deletstu(long stuid){
        for (Student stu :list){
            if (stu.getStudentID()==stuid){
                System.out.println("找到了"+stu.getStudentID()+stu.getName()+"现在将其移出"+this.classnum);
                list.remove(stu);
                stu.setClas(0);
                return true;
            }
        }
        System.out.println("在"+this.classnum+"班没有找到"+stuid);
        return false;
    }
    public void show(){
        System.out.println("在"+this.classnum+"班有"+this.list.size()+"个同学:");
        for (Student stu :list) {
            System.out.println(stu.getName() + " " + stu.getSex() + " " + stu.getStudentID());
        }
    }
    class StuidComparator implements Comparator<Student>{

        @Override
        public int compare(Student o1, Student o2) {
            return (int) (o1.getStudentID() - o2.getStudentID());
        }
    }
    public void stuidsort(){
        System.out.println("学号升序");
        Collections.sort(list,new StuidComparator());
        System.out.println(list);
    }
    public void reverstuidsort(){
        Collections.sort(list,new StuidComparator());
        System.out.println("学号反序");
        for (int i=list.size();i>0;i--){
            System.out.println(list.get(i-1));
        }
    }
    class AgeComparator implements Comparator<Student>{

        @Override
        public int compare(Student o1, Student o2) {
            return (o1.getYears() - o2.getYears());
        }
    }
    public void agesort(){
        System.out.println("年龄排序");
        Collections.sort(list,new AgeComparator());
        System.out.println(list);
    }
}
