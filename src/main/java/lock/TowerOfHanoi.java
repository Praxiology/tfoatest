package lock;

//汉罗塔的设计
import java.util.Scanner;

public class TowerOfHanoi {
    static int count=1;//用来记录生成步骤的条数
    public static void main(String[] args) {
        // TODO Auto-generated method stub
        Scanner s=new Scanner(System.in);
        int n=5;
        move(n,"A","B","C");
    }
    /**
     * 将n个盘子从A移到B，C作为辅助的柱子
     * @param n 原来有多少个盘
     * @param A 原来放置盘子的柱子
     * @param B B柱子
     * @param C C柱子
     */
    public static void move(int n,String A,String B,String C) {
        if(n==1){
            System.out.println("第"+count+++"步:"+"将第1个盘从"+A+"移到"+B);
        }
        else{
            move(n-1,A,C,B);
            System.out.println("第"+count+++"步:"+"将第"+n+"个盘从"+A+"移到"+B);
            move(n-1,C,B,A);
        }
    };
}
