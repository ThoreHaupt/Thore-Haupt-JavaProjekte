package AIFB.Prog1Java.Prog1_2223.Rechnerpraktika.RP9.DavidLösung2023;

/**
 * Die Warteschlange bei der Studierendenverwaltung.
 * 
 * @version 1.0
 * @author Prog1-Team
 */
public class Warteschlange {

    /**
     * Die Klasse Wartende
     * 
     * @version 1.0
     * @author Prog1-Team
     */
    static class Wartende {
        private Student student = new Student(); // Inhalt des Listenelements
        private Wartende next; // Verweis auf Nachfolger
        private Wartende pred;

        public Wartende(Student o) {
            student = o;
            next = null;
            pred = null;
        }
    }

    private Wartende head; // Referenz auf Anfang der Liste
    private Wartende tail;

    public Warteschlange() {
        head = null;
    }

    public Warteschlange(Student o) {
        head = new Wartende(o);
        tail = head;
    }

    /* VORLAGE FÜR EINFACH VERKETTETE LISTE */
    public Wartende addFirst(Student o) { // am Anfang einfügen
        // haben wir in die InsertMethode kopiert, also können wir einfach insert(o,null) aufrufen.
        // das heißt, es gibt keinen doppelten Code. addFirst ist ein spezialFall von insert, also am besten hier Insert aufrufen.
        return insert(o, null);
    }

    /* VORLAGE FÜR EINFACH VERKETTETE LISTE */
    public Wartende insert(Student o, Wartende pred) {
        // nach pred (Vorgänger) einfügen
        Wartende newEl = new Wartende(o);
        if (pred == null) { // am Anfang einfügen
            /* 
            Hier muss noch der Fall abgefangen werden, dass die Liste leer ist. -> nur dann tail auf head setzen, sonst nicht.
            Sonst ist das einfügen des Elementes an erster stelle dazu, dass Tail auf head zeigt und damit nicht auf das richtige Element
            Wie du es hattest:
            
            newEl.next = head;
            tail = head;
            head = newEl; */

            // Das, was bei dir in der AddFirst Methode stand:
            if (head == null) { // Liste ist noch leer
                head = newEl;
                tail = head;
            } else {
                newEl.next = head;
                head.pred = newEl;
                head = newEl;
            }

        } else { // nach pred ( Vorgänger ) einfügen

            // tail = newEl; Wir fügen nicht umbedingt am Ende ein, sondern nach pred. Deshalb hier nicht tail auf newEl setzten.

            newEl.next = pred.next;
            pred.next = newEl;

            // Dies hier ist noch wichtig, da die beiden zeilen oben nur die Verbindungen in die Vorwärds Richtung setzten
            // der Vorgänger vom neuen Element ist natürlich pred
            newEl.pred = pred;
            // die Referenz auf NewEl beim neuen Nachfolger muss dann natürlich auch noch gesetzt werden. (wenn der Nachfolger != null ist)
            if (newEl.next != null)
                newEl.next.pred = newEl;
            else
                tail = newEl;

        }
        return newEl;
    }

    /**
     * Löscht den Wartenden am Ende der Liste
     * 
     * @return Der gelöschte Wartende
     */
    public Wartende deleteLast() { // %%%
        // Element am Ende der Liste löschen
        Wartende last = tail;
        delete(tail);
        return last;
    }

    /* VORLAGE FÜR EINFACH VERKETTETE LISTE */
    public void delete(Wartende w) {

        if (w.next != null && w.pred != null) {
            w.pred.next = w.next;
            w.next.pred = w.pred;

        } else if (w.next == null && w.pred == null) {
            tail = null;
            head = null;

        } else if (w.next != null && w.pred == null) {
            head = w.next;
            w.next.pred = null;

        } else {
            tail = w.pred;
            w.pred.next = null;
        }

    }

    public String toString() {
        String s = "(";
        Wartende help = head;
        while ((help != null) && (help.next != null)) {
            s = s + help.student.getMatrikelNr() + ", ";
            help = help.next;
        }
        if (help != null) {
            s = s + help.student.getMatrikelNr();
        }
        return s + ")";
    }

    public Wartende getHeadElement() {
        return head;
    }

    public Wartende getTailElement() {
        return tail;
    }

    /**
     * Ein Studierender wird aufgerufen und verlässt somit die Warteschlange
     */
    public void studiAufrufen() {

        System.out.println(tail.student.getMatrikelNr());
        deleteLast();
    }

    /**
     * Tauscht den Platz von zwei Studierenden in der Warteschlange
     * 
     * @param student  Der neue Studierende
     * @param wartende Der bereits Wartende
     */
    public void studiTauschen(Student student, Wartende wartende) {

        insert(student, wartende);
        delete(wartende);

        System.out.println("Student(in) mit der Matrikelnummer " + wartende.student.getMatrikelNr()
                + " hat den Platz in der Warteschlange mit der Studentin mit der Matrikelnummer "
                + student.getMatrikelNr() + " getauscht.");

    }
}
