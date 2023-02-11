package AIFB.Prog1Java.Altklausuren.SS18Altklausur;

public class A2 {

    public static void main(String[] args) {
        byte by = 4;
        short sh = 8;
        long lo = 16;
        char ch = 'c';
        float fl = 2.0f;
        A a = new A();
        B b = new B();
        Integer iW = Integer.valueOf(40);
        //a.  methode ( lo , ( char) fl , ch ) ;
        a.methode(sh, ch, lo);
        a.methode(a, iW, by);
        // b . methode ( lo , by−fl , sh ) ;
        b.methode(by, sh, by);
        b.methode(lo, sh + sh, lo);
    }

    static class A {

        void methode(int a, long b, char c) {
            System.out.println("A1");
        }

        void methode(int a, char b, double c) {
            System.out.println("A2");
        }

        void methode(Object a, Integer b, short c) {
            System.out.println("A3");
        }
    }

    static class B extends A {

        void methode(int a, short b, int c) {
            System.out.println("B1");
        }

        void methode(long a, int b, double c) {
            System.out.println("B2");
            ;
        }
    }
}