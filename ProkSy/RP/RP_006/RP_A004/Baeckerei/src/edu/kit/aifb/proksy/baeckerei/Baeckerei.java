package ProkSy.RP.RP_006.RP_A004.Baeckerei.src.edu.kit.aifb.proksy.baeckerei;

/**
 * @author Lukas Struppek
 * 
 *         Die Klasse simuliert eine Bäckerei, welche eine begrenzten Lagerraum
 *         für Brote besitzt.
 *
 */
public class Baeckerei {
	private int anzahlBrote = 0;
	private int maxBrote = 10;

	public void kaufeBrot(String threadName) {
		synchronized (this) {
			while (anzahlBrote == 0) {
				/* Thread.yield(); */
				try {
					wait(5);
				} catch (InterruptedException e) {
					System.out.println("not synced");
				}
			}

			anzahlBrote = anzahlBrote - 1;
		}
		System.out.println("Bestand Brote: " + anzahlBrote + " - Vorgang: Verkauf an " + threadName);
	}

	public void backeBrot(String threadName) {
		synchronized (this) {
			while (maxBrote == anzahlBrote) {
				/* Thread.yield(); */
				try {
					wait(5);
				} catch (InterruptedException e) {
					System.out.println("not synced");
				}
			}

			anzahlBrote = anzahlBrote + 1;
		}
		System.out.println("Bestand Brote: " + anzahlBrote + " - Vorgang: Backen von " + threadName);
	}
}
