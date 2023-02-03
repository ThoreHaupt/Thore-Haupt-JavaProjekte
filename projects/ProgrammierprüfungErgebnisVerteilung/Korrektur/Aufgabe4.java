package Projects.ProgrammierprüfungErgebnisVerteilung.Korrektur;

public class Aufgabe4 {

	public static void main(String[] args) {

		int[] array = { 2, 4, 7, 7, 0, 5, 2 };
		int[][] zweidimmensionalesarray = createArray(array);
		print(zweidimmensionalesarray);
	}

	public static int[][] createArray(int[] array) {

		int[][] zweidimmensionalesarray = new int[array.length][];
		for (int i = 0; i < array.length; i++) {
			int[] a = new int[i + 1];

			System.arraycopy(array, 0, a, 0, i + 1);

			zweidimmensionalesarray[i] = a;
		}
		return zweidimmensionalesarray;
	}

	public static void print(int[][] array) {

		for (int[] a : array) {

			for (int b : a) {
				System.out.print(b);
			}

			System.out.println();
		}
	}
}