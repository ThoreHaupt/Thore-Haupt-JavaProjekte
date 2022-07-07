package ProkSy.RP.RP_006.RP_A003;

public class Informatiker extends Thread {
	private Toilette toilette;
	private Saugglocke saugglocke;

	public Informatiker(String name, Toilette toilette, Saugglocke saugglocke) {
		super(name);
		this.toilette = toilette;
		this.saugglocke = saugglocke;
	}

	public void erleichtern() {

		synchronized (toilette) {
			saugglocke.abflussReinigen(this.getName());
			toilette.benutzen(this.getName());
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
