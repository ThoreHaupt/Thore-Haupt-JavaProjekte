package RP.W3;
import java.util.Scanner;

public class W4A1{

    public static void main(String[] args){

        Scanner scanner = new Scanner(System.in);

        int input = scanner.nextInt();
        System.out.println("Alle Sekunden: " + input);
        
        int jahre = input/(24*3600*365);
        int tage = input%(24*3600*365)/(3600*24);
        int seks = input%(24*365*3600*jahre + (24 * 3600 * tage));
        int stunden = seks/(3600);
        int minuten = seks%3600/60;
        int sekunden = seks%60;


        System.out.println("Jahre: " + jahre);
        System.out.println("Tage: " + tage);
        System.out.println("Stunden: " + stunden);
        System.out.println("Minuten: " + minuten);
        System.out.println("Sekunden: " + sekunden);

        scanner.close();

    }
}