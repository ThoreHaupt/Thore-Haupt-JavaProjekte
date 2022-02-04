package testStuff.testPolymorphie;

import javax.management.RuntimeErrorException;

public class Main {
    public static void main(String[] args) {
        /*
         * Object aObj = (Object) (new int[] {});
         * 
         * A a1 = new A();
         * A a2 = new B();
         * B b = new B();
         * 
         * a2.foo(a1);
         * a1.foo(a2);
         * b.foo(a2);
         * a2.foo(a2);
         * a1.foo(b);
         * b.foo(23);
         */

        B b = new B();
        method(Long.valueOf(2), Long.valueOf(3), 23f);
        throw new RuntimeException("e");
    }

    public static void method(double d, long l, Float f) {

    }
}
