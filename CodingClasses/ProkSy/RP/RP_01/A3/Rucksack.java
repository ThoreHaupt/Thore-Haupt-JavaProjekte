package CodingClasses.ProkSy.RP.RP_01.A3;

/**
 * Klasse modelliert jeweils einen Rucksack, der Platz f�r eine Einheit einer
 * Unterklasse von Proviant besitzt. F�r die Aufgabe muss nur diese Klasse inkl.
 * Methoden angepasst werden.
 * 
 * Klasse enth�lt zugleich die main-Methode.
 * 
 * @author Lukas Struppek
 * @version 1.0
 *
 */
public class Rucksack {

	Proviant inhalt = null;

	public boolean isEmpty() {
		return (inhalt == null);
	}

	public Proviant getInhalt() {
		return inhalt;
	}

	public <G extends Proviant> void packen(G inhalt) {
		this.inhalt = inhalt;
	}

	public Proviant verzehren() {
		Proviant result = inhalt;
		inhalt = null;
		return result;
	}

	public static String wasIstDrin(Rucksack rucksack) {
		if (rucksack.getInhalt() instanceof Grillgut) {
			return rucksack.getInhalt().getClass().getSimpleName();
		} else
			return " kein Grillgut im Rucksack";
	}

	public static void main(String[] unwichtigeArgumente) {
		Rucksack rucksack1 = new Rucksack();
		rucksack1.packen(new Bier("Wiwi-Bräu"));
		System.out.println("Inhalt von Rucksack 1 vernichtet: " + rucksack1.verzehren());
		if (rucksack1.isEmpty())
			System.out.println("Rucksack 1 ist leer und kann neu befüllt werden");
		rucksack1.packen(new Bier("Maurersekt"));
		System.out.println("Inhalt von Rucksack 1: " + rucksack1.getInhalt() + "\n");

		Rucksack rucksack2 = new Rucksack();
		rucksack2.packen(new Grillkaese("Schafskäse"));
		System.out.println("Inhalt von Rucksack 2: " + Rucksack.wasIstDrin(rucksack2));
		rucksack2.packen(new Bratwurst("Schlesische Landbratwurst"));
		System.out.println("Inhalt von Rucksack 2 vernichtet: " + rucksack2.verzehren() + "\n");

		Rucksack rucksack3 = new Rucksack();
		rucksack3.packen(new Bratwurst("Waadtländer Bratwurst"));
		System.out.println("Inhalt von Rucksack 3: " + Rucksack.wasIstDrin(rucksack3));
	}
}