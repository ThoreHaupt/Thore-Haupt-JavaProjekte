package AIFB.Programmierprüfung.Korrektur;

public class Aufgabe2 {
	public static void main(String[] args) {
		String matrikelnummer = "2355648";
		int letzteVierZiffernInt = 0;
		int anzahlNichtNullZiffern = 0;

		for (int i = matrikelnummer.length() - 1; i >= 0 && anzahlNichtNullZiffern < 4; i--) {
			char a = matrikelnummer.charAt(i);
			if (a != '0') {
				letzteVierZiffernInt = letzteVierZiffernInt * 10 + (a - '0');
				anzahlNichtNullZiffern++;
			}
		}

		letzteVierZiffernInt *= (int) Math.pow(10, 4 - anzahlNichtNullZiffern);

		for (int i = letzteVierZiffernInt; i >= letzteVierZiffernInt / 2; i -= 2) {
			System.out.println(i);
		}
	}
}
