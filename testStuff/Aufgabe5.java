package testStuff;

public class Aufgabe5 {
    public static void main(String[] args) {
        String[] feld = new String[3];
        int counter = 0;

        for (int i = 0, j = feld.length; i < feld.length; i++, --j) {
            if (i % 2 == 0) {
                feld[i] = "Gerade";
            } else {
                feld[j] = "Ungerade";
            }
        }
        while (counter <= feld.length - 1) {
            System.out.println(feld[counter].toString());
            counter++;
        }
    }
}
