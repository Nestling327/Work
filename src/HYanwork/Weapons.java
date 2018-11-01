package HYanwork;

public class Weapons {
    private Rifle rifle = null;
    private Sniper sniper = null;
    private Gun gun = null;
    public class Rifle{
        private int cost = 3000;
        public Rifle(){
            System.out.println("步枪将会花费你"+cost+"块");
        }
        public void butit(){
            System.out.println("哒哒哒...");
        }
    }
    public class Sniper{
        private int cost = 5000;
        public Sniper(){
            System.out.println("狙击枪将会花费你"+cost+"块");
        }
        public void buyit(){
            System.out.println("bang！");
        }
    }
    public class Gun{
        private int cost = 1000;
        public Gun(){
            System.out.println("手枪将会花费你"+cost+"块");
        }
        public void buyit(){
            System.out.println("piu piu piu");
        }
    }

    public Weapons.Gun getGun() {
        if (gun == null){
            gun = new Gun();
        }
        return gun;
    }

    public Weapons.Rifle getRifle() {
        if (rifle == null){
            rifle = new Rifle();
        }
        return rifle;
    }

    public Weapons.Sniper getSniper() {
        if (sniper == null){
            sniper = new Sniper();
        }
        return sniper;
    }

    public static void main(String[] args) {
        Weapons wp = new Weapons();
        Gun gun = wp.getGun();
        Rifle rifle = wp.getRifle();
        Sniper sniper = wp.getSniper();
        gun.buyit();
        rifle.butit();
        sniper.buyit();
    }
}
