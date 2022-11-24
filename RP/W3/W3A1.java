package RP.W3;

import java.util.Scanner;

public class W3A1 {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        int input = scanner.nextInt();
        System.out.println("Alle Sekunden: " + input);

        int jahre = input / (24 * 3600 * 365);
        int sekTagen = input % (24 * 3600 * 365);
        int tage = sekTagen / (3600 * 24);
        int seks = sekTagen % (3600 * 24);
        int stunden = seks / (3600);
        int secMinuten = seks % 3600;
        int minuten = secMinuten / 60;
        int sekunden = seks % 60;

        System.out.println("Jahre: " + jahre);
        System.out.println("Tage: " + tage);
        System.out.println("Stunden: " + stunden);
        System.out.println("Minuten: " + minuten);
        System.out.println("Sekunden: " + sekunden);

        scanner.close();

    }
}