package CodingClasses.ProkSy.RP.RP_006.RP_A003;

public class Saugglocke {
	public void abflussReinigen(String name) {
		synchronized (this) {
			System.out.println(name + "\t hat sich die Saugglocke gesichert");
			try {
				Thread.sleep((int) (Math.random() * 1000));
			} catch (InterruptedException ie) {
			}
			System.out.println(name + "\t hat den Abfluss erfolgreich gereinigt");
		}
	}
}
