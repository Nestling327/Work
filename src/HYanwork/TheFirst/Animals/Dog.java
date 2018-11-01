package HYanwork.TheFirst.Animals;

public class Dog extends Animal{
    @Override
    void speak() {
        System.out.println("汪汪汪~哈~");
    }

    @Override
    void eat() {
        System.out.println("狗吃肉ing");
    }
}
