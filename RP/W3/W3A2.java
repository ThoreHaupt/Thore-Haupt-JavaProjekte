package RP.W3;

import java.util.Scanner;

public class W3A2{

    public static void main(String[] args){
        Scanner scanner = new Scanner(System.in);

        System.out.println("bitte oberes Limit eingeben: ");
        int limitupper = scanner.nextInt();
        System.out.println("bitte unteres Limit eingeben: ");
        int limitlower = scanner.nextInt();

        for (int i = 0; i<100; i++){
            System.out.println("");
        }

        System.out.println("raten sie eine Zahl! ");
        int guess = scanner.nextInt();

        if (guess > limitupper || guess < limitlower){
            System.out.println("Haha sie haben verloren");
        }
        if (guess < limitupper || guess > limitlower){
            System.out.println("gz das war richtig");
        }
        scanner.close();
    }
}