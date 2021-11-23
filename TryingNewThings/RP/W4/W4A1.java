package RP.W4;
import java.util.Random;
import java.util.Scanner;

public class W4A1 {
    
    public static void main(String[] args) {
        A45c();
    }

    public static int getInput(String nachricht, Scanner scanner){
        System.out.print(nachricht);
        int input = scanner.nextInt();
        return input;
    }

    public static String getInput(String nachricht, Scanner scanner, boolean wahr) {
        System.out.print(nachricht);
        String input = scanner.next();
        return input;
    }

    public static int A41(int n){
        return n*(n+1)/2;
    }
    
    public static int A41a(int n) {
        int o = 0;
        for (int i = 0; i <= n; i++) {
            o += i;
        }
        
        return o;
    }   

    public static void A41b(){
        Scanner scanner = new Scanner(System.in);
        int geld = getInput("Anzulegender Geldbetrag: ", scanner);
        System.out.println("dadada");
        int zinssatz = getInput("Zinzsatz in Prozent: ", scanner);
        System.out.println("");
        int laufzeit = getInput("Laufzeit: ", scanner);
        System.out.println("");
        A41bp(geld, zinssatz, laufzeit);
        scanner.close();
    }

    public static void A41bp(int Geld, int zinssatz100, int Laufzeit){
        double momentanGeld;
        for (int i = 1; i <= Laufzeit; i++) {
            momentanGeld = Geld + 0.01 * Geld * Math.pow(zinssatz100, i);
            double prozent = Geld * Math.pow(zinssatz100, i)/Geld;
            System.out.println("Wert nach " + i + "Jahr(en): " + momentanGeld + "(Wertzuwachs von " + prozent + "Prozent )");
        }
    }

    public static void printTriangle(){
        Scanner scanner = new Scanner(System.in);
        int len = getInput("Wie groß?", scanner);
        for (int i = 0; i <= len; i++) {
            for (int c = 0; c <= i; c++) {
                System.out.print("*");
            }
            System.out.println("");
        }
        scanner.close();
    }

    public static void A42a(){
        double[] doubleArray = new double[4];
        int[] intArray = new int[4];
        for (int i = 0; i < intArray.length; i++) {
            intArray[i] = (int) Math.random() * 10 - 1;
        }
        for (int i = 0; i < doubleArray.length; i++) {
            doubleArray[i] = Math.random() * 10 - 1;
        }
        for (int i = 0; i < doubleArray.length; i++) {
            System.out.println("("+ intArray[i] + " / " + doubleArray[i] + " )");
        }

    }

    public static void A42b(){
        int[][] Array = new int[][] { { 1, 2, 3 }, { 4, 5, 6 }, { 7, 8, 9 } };
        for (int i = 0; i < Array.length; i++) {
            for (int is : Array[i]) {
                System.out.print("" + is + " ");
            }
            System.out.println("");
        }
    }

    public static void A43a(){
        Scanner scanner = new Scanner(System.in);
        int namen_Anzahl = getInput("Wie viele Namen wollen Sie eingeben?", scanner);
        String[] Namen = new String[namen_Anzahl];
        for (int i = 0; i < namen_Anzahl; i++) {
            Namen[i] = getInput("Vorname: ", scanner, true);
        }
        while(true){
            int nummer = getInput("Wen wollen Sie auslesen?", scanner) + 1;
            if (nummer > namen_Anzahl)break;
            System.out.println(Namen[nummer]);
        }
        scanner.close();
    }
    
    public static void A43b() {
        Scanner scanner = new Scanner(System.in);
        int namen_Anzahl = getInput("Wie viele Namen wollen Sie eingeben?", scanner);
        String[] Namen = new String[namen_Anzahl];
        for (int i = 0; i < namen_Anzahl; i++) {
            Namen[i] = getInput("Vorname: ", scanner, true);
            Namen[i] += getInput("Nachname: ", scanner, true);
        }
        while (true) {
            int nummer = getInput("Wen wollen Sie auslesen?", scanner) + 1;
            if (nummer > namen_Anzahl)
                break;
            System.out.println(Namen[nummer]);
        }

        scanner.close();
    }
    
    public static void A44a(){
        /*
         * BLA 
         * Wert von i : 2 j: 0 
         * BLA 
         * Wert von i : 4 j: -1 
         * BLA 
         * Wert von i : 6 j: -2 
         * BLA
         * Wert von i : 8 j: -3 
         * BLA 
         * Wert von i : 10 j: -4
         * 
         */
    }

    public static void A44b(){
    



    }

    public static void A45b(){
        int[][] feld = new int[7][7];
        for (int i = 0; i < 7; i++) {
            for (int c = 0; c < 7; c++) {
                feld[i][c] = i + c +1;
            }
        }

        for (int i = 0; i < 7; i++) {
            for (int c = 0; c < 7; c++) {
                if (feld[i][c] < 9)
                    System.out.print("" + feld[i][c] + "   ");
                if (feld[i][c] >= 9)
                    System.out.print("" + feld[i][c] + "  ");
            }
            System.out.println("\n");
        }
    }

    public static void A45a(){
        for (int i = 1; i < 8; i++) {
            for (int c = i; c < 7 + i; c++) {
                if (c < 9)
                    System.out.print("" + c + "   ");
                if (c >= 9)
                    System.out.print("" + c + "  ");
            }
            System.out.println("\n");
        }
    }

    public static void A45c() {
        Scanner scanner = new Scanner(System.in);
        int größe = getInput("Wie groß soll das Feld?", scanner);
        int[][] feld = new int[größe][größe];
        for (int i = 0; i < größe; i++) {
            for (int c = 0; c < größe; c++) {
                feld[i][c] = i + c + 1;
            }
        }

        for (int i = 0; i < größe; i++) {
            for (int c = 0; c < größe; c++) {
                if (feld[i][c] < 9)
                    System.out.print("" + feld[i][c] + "   ");
                if (feld[i][c] >= 9 && feld[i][c] < 99)
                    System.out.print("" + feld[i][c] + "  ");
                if (feld[i][c] >= 99 && feld[i][c] < 999)
                    System.out.print("" + feld[i][c] + " ");
            }
            System.out.println("\n");
        }
        // keine, Felder sind heilig
    }

}
