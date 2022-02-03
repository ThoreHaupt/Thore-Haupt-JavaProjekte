package testStuff;

public class test31 {
    public static void main(String[] args) {
        short b = 3;
        short c = 3;
        int d = b + c;
        innerClass ic = new innerClass();
        System.out.println(ic.a);

        byte[] a = { 'A' + 62 };
        System.out.print(a[0]);
        m1(2, 2);
    }

    public static void m1(short x, double y) {
        System.out.println("a");
    }

    public static void m1(double x, int y) {
        System.out.println("b");
    }

    private static class innerClass {
        private int a = 4;
    }
}
