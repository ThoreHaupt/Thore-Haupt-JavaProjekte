package CodingClasses.ProkSy.RP.RP_006.RP_A003;

public class AlarmImDarm {

	public static void main(String[] args) {
		Toilette t = new Toilette();
		Saugglocke g = new Saugglocke();
		Maschinenbauer b1 = new Maschinenbauer("Machi 1", t, g);
		Maschinenbauer b2 = new Maschinenbauer("Machi 2", t, g);
		Informatiker i1 = new Informatiker("Infi 1", t, g);
		Informatiker i2 = new Informatiker("Infi 2", t, g);

		b1.start();
		b2.start();
		i1.start();
		i2.start();
	}
}
