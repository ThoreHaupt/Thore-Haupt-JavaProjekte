package AIFB.Prog1Java.Uebungen.SS18Altklausur;

public class testLoader extends b {
    static {
        System.out.println("a");
    }

    public static void main(String[] args) {
        System.out.println("start");
        new testLoader();
        new b();
    }
}

class b {
    static {
        System.out.println("b");
    }

    b() {
        System.out.println("b obj");
    }
}
