package AIFB.Rechnerpraktiken.RP9.Aufgabe2.binaerBaum;

/**
 * Die Klasse Baum
 * 
 * @version 1.0
 * @author Prog1-Team
 */
public class Baum {

    /* HIER CODE EINFÜGEN !!! */

    int wert;
    Baum links;
    Baum rechts;

    public Baum(int x) {
        wert = x;
    }

    public Baum(int x, Baum links, Baum rechts) {
        wert = x;
        this.links = links;
        this.rechts = rechts;
    }

    /**
     * Fügt einen int-Wert nach dem Prinzip eines binären Suchbaums ein.
     * 
     * @param x int-Wert
     */
    public void insert(int x) {

        /* HIER CODE EINFÜGEN !!! */
        if (x < wert) {
            if (links != null) {
                links.insert(x);
            } else {
                links = new Baum(x);
            }
        } else {
            if (rechts != null) {
                rechts.insert(x);
            } else {
                rechts = new Baum(x);
            }
        }

    }

    public Baum baumSpiegeln() {
        return baumSpiegeln(this);
    }

    /**
     * Gibt einen neuen gespiegelten Baum zurück.
     * 
     * @param zuspiegeln Der zu spiegelnde Baum
     * @return Der neue gespiegelte Baum
     */
    public static Baum baumSpiegeln(Baum zuspiegeln) {

        /* HIER CODE EINFÜGEN !!! */
        return zuspiegeln != null
                ? new Baum(zuspiegeln.wert, baumSpiegeln(zuspiegeln.rechts), baumSpiegeln(zuspiegeln.links))
                : null;

    }

    /**
     * Überprüft ob zwei Bäume gespiegelt sind.
     * 
     * @param baum1 Der erste Baum
     * @param baum2 Der zweite Baum
     * @return true wenn Bäume gespiegelt sind, false andernfalls
     */
    public static boolean sindGespiegelt(Baum baum1, Baum baum2) {

        /* HIER CODE EINFÜGEN !!! */
        if (baum1 == null && baum2 == null)
            return true;
        if (baum1 != baum2 && (baum1 == null || baum2 == null))
            return false;
        return baum1.wert == baum2.wert && sindGespiegelt(baum1.links, baum2.rechts)
                && sindGespiegelt(baum2.links, baum1.rechts);

    }

    /**
     * Gibt an, ob der Baum symmetrisch ist.
     * 
     * @return true wenn symmetrisch, false andernfalls
     */
    public boolean istSymmetrisch() {

        /* HIER CODE EINFÜGEN !!! */
        return sindGespiegelt(rechts, links);

    }

    // Inorder-Traversierung (LWR)
    public String toString() {

        /* HIER CODE EINFÜGEN !!! */
        return print("in");

    }

    /**
     * Gibt einen String für die Baumstruktur bezüglich einer gegebenen
     * Traversierungsreihenfolge zurück.
     * 
     * @param order "pre", "in" oder "post"
     * @return String
     */
    public String print(String order) {

        /* HIER CODE EINFÜGEN !!! */
        if (order.equals("in"))
            return (links != null ? (links.print("in") + ", ")
                    : "") + wert + (rechts != null ? (", " + rechts.print("in")) : "");
        if (order.equals("pre"))
            return wert + (links != null ? ", "
                    + (links.print("pre"))
                    : "") + (rechts != null ? (", " + rechts.print("pre")) : "");
        if (order.equals("post"))
            return (links != null ? (links.print("post") + ", ")
                    : "") + (rechts != null ? (rechts.print("post") + ", ") : "") + wert;
        else
            return "";
    }

}
