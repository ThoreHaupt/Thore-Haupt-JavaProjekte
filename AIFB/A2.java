package AIFB;

import java.util.Scanner;

public class A2 {
    public static void main(String[] args) {
        System.out.println(meineMethode(1, 2));
        float f = 2.5f;
        Float F = 123.4f;
        int c = (int) (float) F;
        System.out.println(c);
        Scanner meinScanner = new Scanner(System.in);

        System.out.println("Bitte geben Sie eine Kombination von Zahlen und Buchstaben ein");
        String input = meinScanner.next();
        for (int i = 0; i < input.length(); i++) {
            if (Character.isAlphabetic(input.charAt(i))) {
                System.out.println("Zeichen an Stelle " + i + " ist ein Buchstabe");
            }
            if (Character.isDigit(input.charAt(i))) {
                System.out.println("Zeichen an Stelle " + i + " ist eine Zahl");
            }
        }

        meinScanner.close();

    }

    public static int meineMethode(int a, int b) {
        return 5 * a / b;
    }
}
