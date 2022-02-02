package testStuff.tut10;

import testStuff.testPolymorphie.B;

public class Main {
    int d;

    public static void main(String[] args) {
        Main m = new Main(2);
        int i = 96;
        char d = (char) i;
        int i2 = d = 97;
        System.out.println(d);
        System.out.println(i2);

        int[] a = new int[] { 'a', 'b', 'c' };
        int[] b = new int[3];
        b = a;

    }

    Main(int i) {
        d = i;
    }

    void myMethod(int a, String b) {
        return;
    }

    void myMethod(String a, int b) {
    }

}