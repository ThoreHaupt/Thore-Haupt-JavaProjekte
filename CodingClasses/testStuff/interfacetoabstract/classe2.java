package CodingClasses.testStuff.interfacetoabstract;

import java.util.Comparator;

public class classe2 implements Comparator<classe2> {

    @Override
    public int compare(classe2 o1, classe2 o2) {
        return 0;
    }

    public static void main(String[] args) {
        classe c = new classe() {
            @Override
            void m2() {
                System.out.println("yalla");
            }
        };
    }

}
