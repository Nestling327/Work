package HYanwork.TheFirst.Animals;

public class Cat extends Animal{

    @Override
    void speak() {
        System.out.println("喵~喵");
    }

    @Override
    void eat() {
        System.out.println("猫吃竹鼠ing");
    }
}
