package AIFB.ListenAufgabeVorlage;

import java.util.Scanner;

import AIFB.ArrayListen.ArrayListe;

public class TestListe {

    public static void main(String[] args) {

        boolean interrupted = false;
        ArrayListe liste = new ArrayListe();

        Scanner scanner = new Scanner(System.in);

        for (int i = 0; i < 30; i += 2) {
            liste.add(i);
        }

        System.out.println("Hier können Sie die Liste bearbeiten.");
        System.out.println("end [beendet das Programm]");
        System.out.println("add wert ; rem(ove) index ; get index ; print [druckt die ganze Liste]");
        System.out.println("Die momentanige Liste : " + liste.toString());

        while (!interrupted) {
            System.out.print(">>");
            String input = scanner.nextLine();
            String[] inputArray = input.split(" ");

            switch (inputArray[0]) {
                case "add":
                    if (inputArray.length == 1) {
                        System.out.println("Nicht das richtige Format. add value");
                    } else {
                        liste.add(Integer.parseInt(inputArray[1]));
                    }
                    break;
                case "rem":
                    if (inputArray.length == 1) {
                        System.out.println("Nicht das richtige Format. rem index");
                    } else {
                        try {
                            liste.remove(Integer.parseInt(inputArray[1]));
                        } catch (IndexOutOfBoundsException e) {
                            System.out.println("Dieser Index ist nicht in der Liste");
                        }
                    }
                    break;
                case "get":
                    if (inputArray.length == 1) {
                        System.out.println("Nicht das richtige Format. get index");
                    } else {
                        try {
                            System.out.println(liste.get(Integer.parseInt(inputArray[1])));
                        } catch (IndexOutOfBoundsException e) {
                            System.out.println("Dieser Index ist nicht in der Liste");
                        }
                    }
                    break;
                case "print":
                    System.out.println(liste.toString());
                    break;
                case "end":
                    interrupted = true;
                    System.out.println("Das Programm wurde beended. Die Liste war:");
                    System.out.println(liste.toString());
                    break;
                default:
                    System.out.println("Dieser Befehl existiert nicht.");
                    break;
            }
        }

        scanner.close();
    }
}
