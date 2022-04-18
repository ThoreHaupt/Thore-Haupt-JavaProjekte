package testStuff;

public class test3 {
    static {
        System.out.println("hahha");
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

    }
}
