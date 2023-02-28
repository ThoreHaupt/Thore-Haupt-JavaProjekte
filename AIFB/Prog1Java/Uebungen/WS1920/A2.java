package AIFB.Prog1Java.Uebungen.WS1920;

public class A2 {
    static void m(Integer i, Object o, Long lo) {
        System.out.println("0");
    }

    static void m(double i, char o, float lo) {
        System.out.println("1");
    }

    static void m(float i, char o, double lo) {
        System.out.println("2");
    }

    static void m(short i, int o, long lo) {
        System.out.println("3");
    }

    public static void main(String[] args) {
        short s = 3;
        int i = 3;
        float f = 2.3f;
        Long lo = Long.valueOf(23);
        Integer inte = Integer.valueOf(234);
        m(i, true, lo);
        // m(f, '?', f);
        m(s + s, 4, 4l);
        m(s, i, lo);
        //m(67, "Mal", inte);
    }
}
