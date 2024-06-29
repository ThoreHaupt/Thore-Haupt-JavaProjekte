package CodingClasses.testStuff;

import java.util.LinkedList;

public class Baum {
    Baum links;
    Baum rechts;
    int wert;

    public Baum(int wert) {
        this.wert = wert;
    }

    @Override
    public String toString() {
        String s = "";

        s += " " + wert;
        if (links != null)
            s += links.toString();

        if (rechts != null) {
            s += rechts.toString();
        }

        return s;
    }

    public void insert(int wert) {
        if (wert <= this.wert) {
            if (links != null)
                links.insert(wert);
            else
                links = new Baum(wert);
        } else {
            if (rechts != null)
                rechts.insert(wert);
            else
                rechts = new Baum(wert);
        }
    }

    static Baum tiefensuche(Baum baum, int wert) {
        if (baum == null) {
            return null;
        }
        if (baum.wert == wert) {
            return baum;
        }
        Baum result = tiefensuche(baum.links, wert);
        if (result != null) {
            return result;
        }
        result = tiefensuche(baum.rechts, wert);
        return result;
    }

    static Baum breitenSuche(Baum baum, int wert) {
        LinkedList<Baum> liste = new LinkedList<Baum>();
        liste.add(baum);
        while (!liste.isEmpty()) {
            Baum aktuell = liste.pop();
            if (aktuell.wert == wert)
                return aktuell;
            else {
                if (aktuell.links != null)
                    liste.add(aktuell.links);
                if (aktuell.rechts != null)
                    liste.add(aktuell.rechts);
            }
        }
        return null;
    }

    public static void main(String[] args) {
        Baum b = new Baum(100);
        for (int i = 0; i < 30; i++) {
            b.insert((int) (Math.random() * 100));
        }
        System.out.println(b.toString());
        Baum gesucht = breitenSuche(b, 40);
        if (gesucht != null)
            System.out.println(gesucht.wert);
    }

}
