package RP.W3;

import java.util.Scanner;

public class W3A5 {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        int input = scanner.nextInt();

        scanner.close();

        String[] words = { "Null", "Eins", "Zwei", "Drei", "Vier", "Fünf", "Sechs", "Sieben", "Acht", "Neun", "Zehn" };

        /* int i = 1;
        while (input / i > 0) {
            i *= 10;
            System.out.print(words[input % i / (i / 10)] + " ");
        }
        System.out.println(""); */

        int c = 1;
        int inputReverse = 0;
        String vorwärds = "";
        String rückwärds = "";

        while (input / c > 0) {
            c *= 10;
            int zahl = input % c / (c / 10);

            String wort = switch (zahl) {
                case 0:
                    yield ("Null ");
                case 1:
                    yield ("Eins ");
                case 2:
                    yield ("Zwei ");
                case 3:
                    yield ("Drei ");
                case 4:
                    yield ("Vier ");
                case 5:
                    yield ("Fünf ");
                case 6:
                    yield ("Sechs ");
                case 7:
                    yield ("Sieben ");
                case 8:
                    yield ("Acht ");
                case 9:
                    yield ("Neun ");
                default:
                    yield ("wtf");
            };

            rückwärds += wort + " ";
            vorwärds = wort + " " + vorwärds;
        }

        System.out.println("Rückwärds: " + rückwärds);
        System.out.println("Vorwärds: " + vorwärds);

        //System.out.println("Vorwärds");
        c = (int) Math.pow(10, (int) Math.log10(input) + 1);
        /* System.out.println((int) Math.log10(input) + 1);
        System.out.println(c);
        System.out.println(input / c); */
        while (c >= 10) {
            int zahl = input % c / (c / 10);
            c /= 10;
            String wort = switch (zahl) {
                case 0:
                    yield ("Null ");
                case 1:
                    yield ("Eins ");
                case 2:
                    yield ("Zwei ");
                case 3:
                    yield ("Drei ");
                case 4:
                    yield ("Vier ");
                case 5:
                    yield ("Fünf ");
                case 6:
                    yield ("Sechs ");
                case 7:
                    yield ("Sieben ");
                case 8:
                    yield ("Acht ");
                case 9:
                    yield ("Neun ");
                default:
                    yield ("wtf");
            };

            System.out.print(wort);
        }

        System.out.println("\n\nVersion 2 :");
        // ohne String speichern und mit einmal
        int stellen = (int) Math.log10(input) + 1;

        System.out.print("Rückwärds: ");
        int current = input;
        int reverseInput = 0;
        for (int r = 0; r < 2; r++) {
            c = (int) Math.pow(10, stellen + 1);
            reverseInput = 0;
            for (int i = 0; i < stellen; i++) {
                int zahl = current % c / (c / 10);
                c /= 10;
                // revere Input erweitern
                reverseInput += zahl * (int) Math.pow(10, i);

                String wort = switch (zahl) {
                    case 0:
                        yield ("Null ");
                    case 1:
                        yield ("Eins ");
                    case 2:
                        yield ("Zwei ");
                    case 3:
                        yield ("Drei ");
                    case 4:
                        yield ("Vier ");
                    case 5:
                        yield ("Fünf ");
                    case 6:
                        yield ("Sechs ");
                    case 7:
                        yield ("Sieben ");
                    case 8:
                        yield ("Acht ");
                    case 9:
                        yield ("Neun ");
                    default:
                        yield ("wtf");
                };
                System.out.print(wort);
            }
            current = reverseInput;
            System.out.print("\nVorwärds: ");
        }
    }

}
