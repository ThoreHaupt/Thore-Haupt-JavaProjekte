package AIFB.NachProgrammierprüfung;

import org.junit.jupiter.api.Assertions;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class A5 {
    public static int quadraticSum(int n) {
        /* Hier die " Summe der Quadrate " REKURSIV berechnen und zurü ckgeben . */
        if (n == 1) {
            return 1;
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
    }
}
