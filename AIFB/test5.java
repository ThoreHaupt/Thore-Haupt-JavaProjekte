package AIFB;

import org.w3c.dom.html.HTMLTableColElement;

public class test5 {
    static void m(int i, short s, int b) {

    }

    static void m(int i, long b, char c) {

    }

    public static void main(String[] args) {
        int i = 0;
        byte b = 0;
        char c = 'g';
        //m(i, b, c);
        test5 f = new b();
        //f.g();
    }
}

class b extends test5 {
    void g() {

    }
}

interface inter {
    static int i = 0;
    int a = 0;
    final int b = 0;

    static void methode1() {
        System.out.println("hallo du noooooob");
    }

    default void methode2() {
        System.out.println("looser");
    }

    abstract void methode3();

}