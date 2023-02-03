package CodingClasses.ProkSy.RP.RP_01.A3;

/**
 * Klasse modelliert ein Grillgut als �berbegriff von Bratwurst und Grillk�se.
 * 
 * @author Lukas Struppek
 * @version 1.0
 *
 */
public class Grillgut extends Proviant {
	private String bezeichnung;

	/**
	 * Methode liefert Bezeichnung des Grittlgutes zur�ck.
	 * 
	 * @return Bezeichnung des Grillgutes.
	 */
	public String getHerkunft() {
		return bezeichnung;
	}

	public String toString() {
		return "Grillgut der Art " + bezeichnung;
	}

	/**
	 * Konstruktor legt Bezeichnung eines Gutes fest.
	 * 
	 * @param bezeichnung Name des Gutes
	 */
	public Grillgut(String bezeichnung) {
		this.bezeichnung = bezeichnung;
	}

}
