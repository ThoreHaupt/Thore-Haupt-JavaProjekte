package RP.W5;

import java.util.Scanner;

public class W5A1_5 {
    
    static int switches = 0;
    public static void main(String[] args) {
        sortierMich();
    }

    public static int getInput(String input, Scanner scanner){
        System.out.println(input);
        return scanner.nextInt();
    }

    public static void switchIntnumbers(int[] array, int i, int c){
        switches++;
        int h = array[i];
        array[i] = array[c];
        array[c] = h;
    }

    public static void sortierMich(){
        Scanner scanner = new Scanner(System.in);
        int anzahlzahlen = getInput("Wie viele Zahlen in der Liste? ", scanner);
        int[] array = new int[anzahlzahlen];
        for (int i = 0; i< anzahlzahlen; i++) {
            array[i] = getInput("nächste Zahl", scanner);
        }

        System.out.println("");

        long current = System.nanoTime();
        for (int i = 0; i < array.length - 1; i++) {
            for (int j = 0; j < array.length - 1; j++) {
                if (array[j] > array[j + 1]) {
                    switchIntnumbers(array, j, j + 1);
                    j++;
                }
            }
                
        }
        System.out.println("Time: " + (System.nanoTime() - current) * Math.pow(10, -6));
        System.out.println("täusche: " + switches );
        for (int i : array) {
            System.out.println(i);
        }
        //bester Fall: 0 mal tauschen
        //schlechtester Fall n^2 switches
    }
}
