package ProkSy.RP.RP_01.A3;

/**
 * Diese Klasse modelliert einen Grillk�se und ist eine Unterklasse von
 * Grillgut. Jedes Objekt besitzt einen Namen, welcher die genaue Speise
 * festlegt.
 * 
 * @author Lukas Struppek
 * @version 1.0
 *
 */
public class Grillkaese extends Grillgut {
	/**
	 * Konstruktor legt Bezeichnung des Grillk�ses fest.
	 * 
	 * @param bezeichnung Name des Grillk�ses.
	 */
	public Grillkaese(String bezeichnung) {
		super(bezeichnung);
	}

}