package testStuff.testPolymorphie;

public class B extends A {
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
}
