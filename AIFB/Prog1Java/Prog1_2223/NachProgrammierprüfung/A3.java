package AIFB.Prog1Java.Prog1_2223.NachProgrammierprüfung;

public class A3 {
    public static void main(String[] args) {
        // Feld deklarieren und initialisieren mit den Ziffern der Matrikelnummer !
        int[] feld = { 2, 4, 0, 6, 8, 2, 4 };
        /*
        * Ausgabe des Feldes auf der Konsole mit der unten definierten Methode ’
        print ’.
        */
        print(feld);
        /*
        * Aufrufen der Methode ’divide ’ mit dem Feld und abspeichern des
        * zur ü ckgegebenen Feldes in einer neuen Variablen .
        */
        int[] feld2 = divide(feld);
        /*
        * Ausgabe des zurü ckgegebenen Feldes auf der Konsole mit der unten
        definierten
        * Methode ’print ’.
        */
        print(feld2);
    }

    public static int[] divide(int[] array) {
        /*
        * Hier ein neues ein - dimensionales Feld erstellen , welches die ungeraden
        Zahlen
        * und die Hä lften der geraden Zahlen von ’array ’ enth ält. Dieses neue Feld
        zum
        
        
        * Schluss zurü ckgeben .
        */

        int[] temp = new int[array.length];
        for (int i = 0; i < array.length; i++) {
            temp[i] = (array[i] % 2 == 0) ? array[i] / 2 : array[i];
        }
        return temp;
    }

    /**
    * Gibt ein ein - dimensionales int - Feld auf der Konsole aus.
    *
    * @param array Das ein - dimensionale int - Feld .
    */
    public static void print(int[] array) {
        for (int i : array) {
            System.out.print(i + " ");
        }
        System.out.println();
    }
}
