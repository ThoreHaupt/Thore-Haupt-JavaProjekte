package CodingClasses.ProkSy.RP.RP_007;

import java.util.Arrays;

public class test {
    public static void main(String[] args) {
        String[] inp = { "hall", "hallo" };
        Arrays.stream(inp).mapToInt(String::length).forEach(s -> System.out.println(s));
        rumRechner((a, b) -> a * a + b * b);
    }

    static double rumRechner(Rechner r) {
        return r.rechne(1, 2);
    }

    interface Rechner {
        double rechne(double a, double b);
    }
}
