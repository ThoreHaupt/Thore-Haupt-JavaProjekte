package ProkSy.RP.RP_006.RP_A003;

public class Toilette {

	public void benutzen(String name) {
		synchronized (this) {
			System.out.println(name + "\t hat die Toilette besetzt");
			try {
				Thread.sleep((int) (Math.random() * 1000));
			} catch (InterruptedException ie) {
			}
			System.out.println(name + "\t hat seinen Darm erfolgreich geleert");
		}
	}
}
