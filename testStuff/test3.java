package testStuff;

public class test3 {
    static {
        System.out.println("hahha");
        test3 t = new test3() {
            int q = 2314;
        };
    }

    public test3(int i) {

    }

    public test3() {

    }

    public class thing {
        static {
            method1();
        }

        private void method2() {

        }
    }

    public static void method1() {

    }

    public static void main(String[] args) {
        /*
         * double d3 = Float.valueOf(0.32f).longValue();
         * // Double d4 = (Double) 1234;
         * Double d4 = (double) 234.3f;
         * double f = Float.valueOf(123).floatValue();
         */
        int i = 5;
        Object I = i;
        System.out.println((I instanceof Integer));
        test3 b = new test3();
        thing a = b.new thing();
        short sh = 10;
        m1(2);

    }

    public static void m1(Integer i) {

    }
}
