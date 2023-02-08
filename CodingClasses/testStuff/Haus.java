package CodingClasses.testStuff;

public class Haus extends Gebäude {

    static int anzahl = 0;
    static {
        System.out.println(anzahl);
    }

    String farbe = new String();
    String nameBesitzer = "";
    int hausnummer;
    int nummer = ++anzahl;

    public Haus() {
        this(0);
    }

    public Haus(int i) {
        this.hausnummer = i;
    }

    public static void main(String[] args) {
        /* Haus haus1 = new Haus();
        String b = haus1.farbe;
        System.out.println(haus1.farbe == haus1.nameBesitzer);
        System.out.println(haus1.farbe == b);
        haus1.farbe += "a";
        System.out.println(haus1.farbe == b); */

    }

}

class Gebäude {
    static {
        System.out.println("hallo2");
    }
}