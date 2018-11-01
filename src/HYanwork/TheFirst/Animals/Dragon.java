package HYanwork.TheFirst.Animals;

public class Dragon extends Animal{
    @Override
    void speak() {
        System.out.println("嗷呜（恶龙咆哮！）");
    }

    @Override
    void eat() {
        System.out.println("龙集天地之精华，系万物之灵气也");
    }
}
