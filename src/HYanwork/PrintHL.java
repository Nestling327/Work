package HYanwork;

public class PrintHL {

        static void printheart(){
            float sum;
            for (float y = (float) 1.5; y>-1.5;y-=0.1){
                for (float x=(float) -1.5;x<1.5;x+=0.05){
                    sum = (float) Math.pow((x*x+y*y-1),3) - x*x*y*y*y;
                    if (sum<=0){
                        System.out.print("*");
                    }else {
                        System.out.print(" ");
                    }
                }
                System.out.println();
            }
        }

        static void printL(int k){
            for (int x=0;x<2*k-1;x++){
                if (x<k){
                    for (int y=0;y<k-x-1;y++){
                        System.out.print(" ");
                    }
                    for (int y=0;y<2*x+1;y++){
                        System.out.print("*");
                    }
                    System.out.println();
                }else {
                    for (int y=0;y<x-k+1;y++){
                        System.out.print(" ");
                    }
                    for (int y=0;y<(2*k-x-1)*2-1;y++){
                        System.out.print("*");
                    }
                    System.out.println();
                }
            }
        }

    public static void main(String[] args) {
        printheart();
        printL(5);
    }
}
