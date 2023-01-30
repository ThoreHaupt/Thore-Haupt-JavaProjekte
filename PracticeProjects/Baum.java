package PracticeProjects;

public class Baum {
    Baum links;
    Baum rechts;
    int wert;

    public Baum(int wert) {
        this.wert = wert;
    }

    public static boolean suche(Baum baum) {
        System.out.println(baum.wert);
        if (baum.wert == 0)
            return true;
        if (baum.links != null) {
            if (suche(baum.links)) {
                return true;
            }
        }
        if (baum.rechts != null) {
            if (suche(baum.rechts)) {
                return true;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        Baum b1 = new Baum(12);
        b1.links = new Baum(123);
        b1.rechts = new Baum(23);
        b1.links.rechts = new Baum(0);
        b1.links.rechts.links = new Baum(123);
        System.out.println(suche(b1));
    }
}
