package ProkSy.RP.RP_002.P2_2;

import java.util.TreeSet;

public class SortiererMain {
    public static void main(String[] args) {
        TreeSet<Punkt> t1 = new TreeSet<Punkt>();
        TreeSet<Punkt> t2 = new TreeSet<Punkt>(new PunktComperator());

        for (int i = 0; i < 5; i++) {
            Punkt p = new Punkt((Math.random() * 100 + 1), (Math.random() * 100 + 1));
            t1.add(p);
            t2.add(p);
        }
        System.out.println(t1);
        System.out.println(t2);
    }
}
