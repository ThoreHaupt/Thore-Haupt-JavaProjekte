package CodingClasses.testStuff;

public class t123 {

    private void m(int i, int b) {
        System.out.println("1");
    };

    public void m(int i, double b) {
        System.out.println("2");
    };

    static void m2() {

    }

    public static void main(String[] args) {
        t1234 t = new t1234();
        t.doSth();
        t123 m = new t1234();
        t123.m2();
    }

}
