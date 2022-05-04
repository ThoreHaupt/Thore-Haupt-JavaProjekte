package ProkSy.RP.RP_01.A3;

/**
 * @author Lukas Struppek
 * @version 1.0
 *
 */
public class Bier extends Proviant {
	private String brauerei;

	/**
	 * Konstruktor legt Bezeichnung der Brauerei fest.
	 * 
	 * @param brauerei Name der Brauerei des Bieres.
	 */
	public Bier(String brauerei) {
		this.brauerei = brauerei;
	}

	/**
	 * Methode liefert Namen der Brauerei zur�ck.
	 * 
	 * @return Name der Brauerei,
	 */
	public String getBrauererei() {
		return brauerei;
	}

	public String toString() {
		return "Bier von " + brauerei;
	}
}