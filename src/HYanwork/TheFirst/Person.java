package HYanwork.TheFirst;

public class Person {
    public String name;
    public String gender;
    public int facevalue;
    public int height;
    public String mind;
    public Person(String name,String gender,int facevalue,int height,String mind){
        this.name = name;
        this.gender = gender;
        this.facevalue = facevalue;
        this.height = height;
        this.mind = mind;
    }
    @Override
    public String toString() {
        return "\"name\":" + name +", \n"+ "\"gender\":" + gender + ", \n"+"\"facevalue\":" + facevalue
                +", \n" +"\"height\":" + height+", \n"+"\"mind\":"+mind;
    }
}
