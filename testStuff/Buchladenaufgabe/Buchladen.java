package testStuff.Buchladenaufgabe;

public class Buchladen {
    private Buch[] Buecher;

    public Buch buchFinden(String buchname) {

        for (Buch buch : Buecher) {
            if (buch.titel.equals(buchname)) {
                return buch;
            }
        }
        return null;
    }

    public void buchKaufen(Kunde kunde, Buch buch) {

    }
}
