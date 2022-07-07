package ProkSy.RP.RP_006.RP_A003;

public class Maschinenbauer extends Thread {
	private Toilette toilette;
	private Saugglocke saugglocke;

	public Maschinenbauer(String name, Toilette toilette, Saugglocke saugglocke) {
		super(name);
		this.toilette = toilette;
		this.saugglocke = saugglocke;
	}

	public void erleichtern() {
		synchronized (toilette) {
			toilette.benutzen(this.getName());
			saugglocke.abflussReinigen(this.getName());
		}
	}

	@Override
	public void run() {
		for (int i = 0; i < 3; i++) {
			erleichtern();
			try {
				Thread.sleep((long) (Math.random() * 1000));
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

}
