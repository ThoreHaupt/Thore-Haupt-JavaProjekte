package Projects.Programmierprüfung.Korrektur.Alex;

public class Aufgabe4 {

	public static void main(String[] args) {
		// Feld deklarieren und initialisieren mit den Ziffern der Matrikelnummer!
		int[] feld = { 2, 4, 7, 7, 4, 4, 9 };

		/*
		 * Die Methode 'createArray' aufrufen mit dem oben definierten Feld!
		 * Anschließend das Ergebnis mit der unten definierten Methode 'print' auf der
		 * Konsole ausgeben!
		 */

		createArray(feld);
		print(createArray(feld));

	}

	public static int[][] createArray(int[] array) {

		/* Hier das neue zwei-dimensionale int-Feld erzeugen und entsprechend mit Werten ausfüllen.
		Anschließend soll das Feld von der Methode zurückgegeben werden. */
		int[][] c = new int[array.length][array.length];
		for (int i = 0; i < array.length; i++) {
			for (int j = i; j < array.length; j++) {
				c[j][i] = array[i];

			}
		}
		return c;

	}

	/**
	 * Gibt ein zwei-dimensionales int-Feld auf der Konsole aus.
	 * @param array Das zwei-dimensionale int-Feld.
	 */
	public static void print(int[][] array) {
		for (int[] a : array) {
			for (int b : a) {
				System.out.print(b);
			}
			System.out.println();
		}
	}
}