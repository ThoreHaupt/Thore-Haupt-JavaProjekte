package AIFB;

public class test2 {
    public static void main(String[] args) {
        test2 t = new test2();

        Runnable r = () -> {
            System.out.println("hallo");
        };
    }

    private int i = 0;
}

interface test3 {
    default void bau() {

    }
}
