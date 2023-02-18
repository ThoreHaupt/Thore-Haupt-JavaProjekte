package AIFB;

public class test {

    // Option A
    static void m(Integer i, Object o, Object o2) {
        System.out.println("a");
    }

    // Option B
    static void m(double d, Character o, Object o2) {
        System.out.println("b");
    }

    static void m1(Integer i) {
        System.out.println("Integer m1 -> A");
    }

    static void m1(double i) {
        System.out.println("double m1 -> B");
    }

    static void m2(Object c) {
        System.out.println("Object m2 -> A");
    }

    static void m2(Character c) {
        System.out.println("Character m2 -> B");
    }

    static void m3(double d) {
        System.out.println("hallo");
    }

    static void m4(Integer d) {
        System.out.println("hallo");
    }

    public static void main(String[] args) {
        int i = 0;
        Boolean b = Boolean.valueOf(false);
        Integer h = Integer.valueOf(6);
        double d = h;
        //m(i, '?', b);
        m1(i);
        m2('?');
        m3(h);

    }
}
