package AIFB.Prog1Java.Prog1_2223.NachProgrammierprüfung;

public class A4 {
    public static void main(String[] args) {
        /*
        * Definieren Sie das zwei - dimensionale Matrikelnummer - Feld und geben Sie es
        mit
        * der unten definierten ’print ’- Methode auf der Konsole aus.
        */
        int[][] array = { { 2 }, { 4 }, { 0 }, { 6 }, { 8 }, { 2 }, { 4 } };
        print(array);
        /*
        * Erhalten Sie durch den Aufruf der ’reverse ’- Methode ein umgekehrtes Feld
        und
        * speichern Sie es in einer neuen Variablen . Geben Sie es auß erdem mit der
        * unten definierten ’print ’- Methode auf der Konsole aus .
        */
        int[][] arr2 = reverse(array);
        print(arr2);
        /*
        * Ä ndern Sie das Vorzeichen der ersten Ziffer in dem Matrikelnummer - Feld .
        * Anschlie ß end geben Sie das Matrikelnummer - Feld und das umgekehrte Feld
        * jeweils mit der unten definierten ’print ’- Methode auf der Konsole aus .
        */
        array[0][0] *= -1;
        print(array);
        print(arr2);
    }

    public static int[][] reverse(int[][] array) {
        /*
        * Erzeugen Sie hier ein neues zwei - dimensionales Feld , welches die Elemente
        von
        * ’array ’ in umgekehrter Reihenfolge flach kopiert hat . Die Methode soll
        dieses
        * Feld zurü ckgeben .
        */
        int[][] temp = new int[array.length][];
        for (int i = 0; i < array.length; i++) {
            temp[i] = array[array.length - 1 - i];
        }
        return temp;
    }

    /**
    * Gibt ein zwei - dimensionales int - Feld auf der Konsole aus.
    *
    * @param array Das zwei - dimensionale int - Feld .
    */
    public static void print(int[][] array) {
        for (int[] a : array) {
            for (int b : a) {
                System.out.print(b + " ");
            }
        }
        System.out.println();
    }
}
