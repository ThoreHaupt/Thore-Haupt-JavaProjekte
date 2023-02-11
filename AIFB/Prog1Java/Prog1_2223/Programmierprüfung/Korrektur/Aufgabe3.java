package AIFB.Prog1Java.Prog1_2223.Programmierprüfung.Korrektur;

public class Aufgabe3 {
	public static void main(String[] args) {
		int[] matrikelnummerFeld = { 2, 3, 5, 5, 6, 4, 8 };

		print(matrikelnummerFeld);

		reverse(matrikelnummerFeld);

		print(matrikelnummerFeld);
	}

	public static void reverse(int[] array) {

		int[] temp = new int[array.length];

		for (int i = 0, j = array.length - 1; i < array.length; i++, j--) {
			temp[j] = array[i];
		}

		for (int i = 0; i < array.length; i++) {
			array[i] = temp[i];
		}
	}

	public static void print(int[] array) {
		for (int i : array) {
			System.out.print(i);
		}
		System.out.println();
	}
}
