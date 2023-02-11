package AIFB.Prog1Java.Altklausuren.SS18Altklausur;

public class Rec {
    public static void main(String[] args) {
        System.out.println(m(3, 5));
        System.out.println(m(2, 7));
        System.out.println(m(3, 4));
    }

    static int m(int a, int b) {
        if (a * b == 4 || a == 1) {
            return a * b;
        } else {
            int i = m(a - 1, b - 2) - 3;
            System.out.println("m =" + i);
            return i;
        }
    }
}
