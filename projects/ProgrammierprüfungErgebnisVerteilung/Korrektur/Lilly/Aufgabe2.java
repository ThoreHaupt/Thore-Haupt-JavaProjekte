package Projects.ProgrammierprüfungErgebnisVerteilung.Korrektur.Lilly;

public class Aufgabe2 {

	public static void main(String[] args) {
		int m = 2469985;
		int n = m % 10000;
		int a = n;
		for (int i = 0; i < a / 2; i++) {
			if (i % 2 == 0) {
				System.out.println(n - i - 1);
			}
		}

	}

}
