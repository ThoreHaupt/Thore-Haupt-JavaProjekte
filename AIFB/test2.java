package AIFB;

public class test2 implements test3 {
    public static void main(String[] args) {
        test2 t = new test2();
        t.bau();
    }

    private int i = 0;
}

interface test3 {
    default void bau() {

    }
}
