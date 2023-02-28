package AIFB.Prog1Java.Uebungen.WS1920;

import com.formdev.flatlaf.util.SystemInfo;

public class A2b {
    final int f;

    static void m(String s) {

    }

    A2b(int i) {
        f = i;
    }

    A2b() {
        f = 3;
    }

    public static void main(String[] args) {
        int i = 0;
        Ober o1 = new Ober();
        Ober o2 = new Unter();
        Unter u = new Unter();
        System.out.println(o1.v);
        System.out.println(o2.v);
        o1.kochen(o2);
        o2.kochen(o1);
        u.kochen(u);
        m(i + "");
        int[] arr = new int[3];
        //System.out.println(arr[-1]);
    }

}

class Ober {
    public long v = 8;

    public int d32df = 3;

    public void kochen(Ober o) {
        System.out.println("T" + o.v + this.v);
    }

    public void kochen(Unter u) {
        this.kochen(new Ober());
    }
}

class Unter extends Ober {
    public long v = 2;

    public void kochen(Ober o) {
        System.out.print("D");
        super.kochen(o);
    }

    public void kochen(Unter u) {
        System.out.println("D" + u.v + this.v + super.v);
    }

    public Unter() {
        super();
    }
}

interface i {
    void m(double d);
}