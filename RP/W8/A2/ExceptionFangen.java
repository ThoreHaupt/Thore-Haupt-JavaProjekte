package RP.W8.A2;

import java.util.Scanner;

public class ExceptionFangen {
    public static void main(String[] args) {
        String[] feld = {"43","252","drei","3.5"};
        Scanner scanner = new Scanner(System.in);
        for (int i = 0; i < feld.length; i++) {
            System.out.println("index angeben");
            int index = scanner.nextInt();
            try {
                int integer = Integer.parseInt(feld[index]);
                System.out.println(integer);
            } catch (NumberFormatException e) {
                System.out.println("unzulässiger Datentyp unter diesem Index gespeichert!");
            } catch (IndexOutOfBoundsException e2){
                System.out.println("Dieser Index ist außerhalb des Feldes.");
            }
            
        }
        
    }
}
