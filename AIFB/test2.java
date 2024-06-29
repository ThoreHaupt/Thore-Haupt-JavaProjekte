package AIFB;

import java.util.Objects;

public class test2 {
    public static void main(String[] args) {
        test2 t = new test2();

        Runnable r = () -> {
            System.out.println("hallo");
        };

        Objects.equals(new Object(), "");
        Object o1 = new Object();
        o1.equals(o1);
    }

    private int i = 0;
}

interface test3 {
    default void bau() {

    }
}
