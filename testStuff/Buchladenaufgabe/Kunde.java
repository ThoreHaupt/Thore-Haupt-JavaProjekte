package testStuff.Buchladenaufgabe;

public class Kunde extends Person {
    private long kreditkarte;

    public Kunde() {
        super("");
    }

    public Kunde(String name) {
        super(name);
    }

    public void setName(String name) {
        super.name = name;
    }
}
