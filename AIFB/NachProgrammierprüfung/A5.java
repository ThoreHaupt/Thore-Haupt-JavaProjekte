package AIFB.NachProgrammierprüfung;

import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.junit.function.ThrowingRunnable;

public class A5 {
    public static int quadraticSum(int n) {
        /* Hier die " Summe der Quadrate " REKURSIV berechnen und zurü ckgeben . */
        if (n < 0)
            throw new IllegalArgumentException();
        if (n <= 0) {
            return 0;
        } else {
            return n * n + quadraticSum(n - 1);
        }
    }

    public static void main(String[] args) {
        /*
        * Hier die Methode ’ quadraticSum ’ mit den letzten 3 Ziffern Ihrer
        * Matrikelnummer aufrufen und das Ergebnis auf der Konsole ausgeben .
        */
        System.out.println(quadraticSum(15));
    }

    @Test
    public void test() {
        assertTrue(A5.quadraticSum(15) == 1240);
        ThrowingRunnable r = new ThrowingRunnable() {
            public void run() {
                A5.quadraticSum(-1);
            }
        };
        assertThrows(new IllegalArgumentException().getClass(), r);

    }
}
