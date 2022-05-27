package AIFB;

import testStuff.testPolymorphie.A;

public class Aufgaben {

    @SuppressWarnings("unused")
    public static void main(String[] args) {

        int a = 10;
        int b = -5;
        double d = 4.5;

        var w1 = true || false;
        var w2 = a ^ b; // geben Sie nur den Datentyp von w2 an // evtl zu schwer und aus dem Scope,
                        // aber könnte man auch machen damit man es mal gesehen hat
        var w3 = a-- / b * 2 != 4 ^ a >= b;
        var w4 = Integer.valueOf(129).equals(Integer.valueOf(129));
        var w5 = 2 * (("hallo" == "ha" + "llo") ? (--a) : (b++));
        var w6 = 1.5d * ((20 % a == 0) ? a : (b++));
        var w7 = 4 * (a += 3) / (int) d;
        var w8 = a;
    }
}

class Rechnen {

    /**
     * teilt a durch b
     * 
     * @param a Divident
     * @param b Divisor
     * @return Quotient
     */
    public static double teile(int a, int b) {
        return (double) ((double) a / (double) b);
    }

    /**
     * berechnet den Rest beim Teilen von a durch die ganze Zahl b
     * 
     * @param a
     * @param b
     * @return
     */
    public static double rest(double a, int b) {
        return a - ((int) a / (double) b) * b;
    }

}
