package AIFB.Prog1Java.Prog1_2223.Rechnerpraktika.RP_o_10;

public class Inception extends Kino {
    static Kino kino = new Kino(" Inception ");

    public Inception() {
        super(" Inception ");
    }

    public static void main(String[] args) {
        System.out.println(" -> Beginn der main - Methode ");
        System.out.println(" -> new Traum ( main ):");
        new Traum(" main ");
        System.out.println(" -> new Inception ():");
        new Inception();
        System.out.println(" -> Ende der main - Methode ");
    }
}

class Haus {
    static Kampf klassenKampf = new Kampf(" Haus (KV)");
    Kampf instanzKampf = new Kampf(" Haus (IV)");

    public Haus(String von) {
        System.out.println(" Haus von " + von);
    }
}

class Kino extends Haus {
    static Kampf klassenKampf = new Kampf(" Kino (KV)");
    Kampf instanzKampf = new Kampf(" Kino (IV)");

    public Kino(String von) {
        this(von, 3);
        System.out.println(" Kino von " + von);
    }

    public Kino(String von, int i) {
        super(von);
    }
}

class Traum extends Kampf {
    public Traum(String von) {
        System.out.println(" Traum von " + von);
    }
}

class Kampf {
    public Kampf() {
        System.out.println(" Kampf ");
    }

    public Kampf(String von) {
        System.out.println(" Kampf von " + von);
    }
}
