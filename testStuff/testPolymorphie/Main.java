package testStuff.testPolymorphie;

public class Main {
    public static void main(String[] args) {
        Object aObj = (Object) (new int[] {});

        A a1 = new A();
        A a2 = new B();
        B b = new B();

        a2.foo(a1);
        a1.foo(a2);
        b.foo(a2);
        a2.foo(a2);
        a1.foo(b);
        b.foo(23);
    }
}
