package AIFB.Prog1Java.Uebungen.SS18Altklausur;

import javax.imageio.ImageReadParam;

public class Falafel extends Yufka {
    private Yufka lecker = new Yufka("Mit Reis");

    public static void main(String[] args) {
        System.out.println("- Vegetanischer Yufka -");
        new Falafel();
        System.out.println("-Einmal mit allem-");
        new Brot("Knoblauch");

        int[][] f = new int[3][];
        f[0] = new int[4];
        m2(f[1]);
        f[1][0] = 3;
        Integer i = Integer.valueOf(2);
        i.equals(i);
    }

    static void m2(int[] f2) {
        f2 = new int[3];
    }
}

class Brot extends Doener {
    private Imbiss kross = new Imbiss();

    public Brot(String s) {
        super(s);
        System.out.println("Brot: " + s);
        new Doener(s);
    }
}

class Imbiss {
    static Doener frisch = new Doener("Mit allem");

    public Imbiss(String s) {
        System.out.println("Imbiss: " + s);
    }

    public Imbiss() {
        System.out.println("Imbiss");
    }
}

class Doener {
    public Doener(String s) {
        System.out.println("Doener r : " + s);
    }
}

class Yufka extends Imbiss {
    public static Imbiss frisch = new Imbiss("Falafel");
    protected Imbiss scharf = new Imbiss("Tomate");

    public Yufka(String s) {
        super(s);
    }

    public Yufka() {
        super("Falafel");
    }
}
