package CodingClasses.ProkSy.RP.RP_006.RP_A004.Baeckerei.src.edu.kit.aifb.proksy.baeckerei;

/**
 * @author Lukas Struppek
 * 
 *         Die Klasse ist eine Unterklasse von Thread und simuliert einen
 *         Kunden. Dieser wird einer Bäckerei zugeordnet und kauft bei dieser
 *         Brote, entspricht also einem Verbraucher.
 *
 */
public class Kunde extends Thread {
	private Baeckerei b;

	/**
	 * Konstruktor legt Bezeichnung des Kunden fest und weist eine Bäckerei zu.
	 * 
	 * @param name Bezeichnung des Threads
	 * @param b    Zugewiesene Bäckerei
	 */
	public Kunde(String name, Baeckerei b) {
		super(name);
		this.b = b;
	}

	/**
	 * Der Kunde kauft 10 Brote pro Durchlauf. Nach jedem gekauften Brot wird eine
	 * Pause von zufälliger Länge eingelegt, bis der nächste Kauf erfolgt
	 */
	@Override
	public void run() {
		for (int i = 0; i < 10; i++) {
			b.kaufeBrot(this.getName());
			try {
				sleep((int) (Math.random() * 200));
			} catch (InterruptedException ie) {
			}
		}
	}
}
