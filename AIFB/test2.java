package AIFB;

public class test2 {
    private int i = 0;
}

class test3 extends test2 {
    public static void main(String[] args) {
        test3 t = new test3();
        System.out.println(t.geti());
    }

    public int geti() {
        return i;
    }
}
