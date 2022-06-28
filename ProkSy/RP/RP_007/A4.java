package ProkSy.RP.RP_007;

import java.util.Arrays;
import java.util.stream.Collectors;

public class A4 {
    interface Filmrolle {
        public void sagWas(String s);
    }

    public static void main(String[] args) {
        Filmrolle hodor = e -> System.out.println("Hodor");
        Filmrolle plapper = e -> System.out.println(e);
        Filmrolle yoda = e -> {
            int[] reinfolge = { 3, 0, 1, 2 };
            String[] wörter = e.split(" ");
            System.out.println(Arrays.stream(reinfolge).mapToObj(i -> wörter[i]).collect(Collectors.joining(" ")));
        };

        System.out.println(" Hodor :");
        hodor.sagWas("Hallo , wie geht ’s?");
        System.out.println("");
        System.out.println("");
        System.out.println(" Plapper :");
        plapper.sagWas("Was guckst du?");
        System.out.println("");
        System.out.println("");
        System.out.println(" Yoda :");
        yoda.sagWas("Der Himmel ist blau ");
    }
}
