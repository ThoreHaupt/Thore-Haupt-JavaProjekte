package CodingClasses.testStuff.testPolymorphie;

public class B extends A {
    private int b = 3;

    static {
        System.out.println("Static B");
    }

    public B() {
        System.out.println("Konstruktor B");
    }

    public B(int a) {
        // super(b);
        System.out.println("B()");
    }

    void foo(A a) {
        System.out.println("Bfoo(a)");
        return;
    }

    void foo(B b) {
        System.out.println("Bfoo(b)");
        return;
    }

    void foo(int i) {
        System.out.println("B_i");
    }

    public static void main(String[] args) {
        B b = new B();
    }
}
