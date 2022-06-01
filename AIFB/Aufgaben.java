package AIFB;

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
        var w6 = new String("hallo") == "ha" + "llo";
        var w7 = 1.5d * ((20 % a == 0) ? a : (b++));
        var w8 = 4 * (a += 3) / (int) d;
        var w9 = a;
        var w10 = Math.pow(Math.E, a / (d + 1) - 2) * b + b;

        System.out.println("hallo1234567" == new String("hallo1234567"));

        System.out.println(w1);
        System.out.println(w2);
        System.out.println(w3);
        System.out.println(w4);
        System.out.println("w5 " + w5);
        System.out.println(w6);
        System.out.println(w7);
        System.out.println(w10);

        System.out.println(Rechnen.rest(5.3, 2));
    }
}

class Rechnen {

    /**
     * teilt a durch b genau
     * 
     * @param a Divident
     * @param b Divisor
     * @return Quotient
     */
    public static double teile(int a, int b) {
        return (double) ((double) a / (double) b);
    }

    /*
     * public static double teile(int a, int b) {
     * return ((double) a / b);
     * }
     * 
     */

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

    /*
     * public static double rest(double a, int b) {
     * return a - ((int) a / b) * b;
     * }
     */

    public static int meineMethode(int argument1, int argument2) {
        return argument1 + argument2;
    }

}
