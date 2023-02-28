package AIFB.Prog1Java.Prog1_2223.Rechnerpraktika.RP9.Aufgabe1;

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
        private Student student; // Inhalt des Listenelements
        private Wartende next; // Verweis auf Nachfolger
        private Wartende pred; // Verweis auf Vorgänger

        public Wartende(Student o) {
            student = o;
            next = null;
            pred = null;
        }

        public Student getStudent() {
            return student;
        }
    }

    private Wartende head; // Referenz auf Anfang der Liste
    private Wartende tail; // Referenz auf Ende der Liste

    public Warteschlange() {
        head = null;
    }

    public Warteschlange(Student o) {
        head = new Wartende(o);
        tail = head;
    }

    /* VORLAGE FÜR EINFACH VERKETTETE LISTE */
    public Wartende addFirst(Student o) { // am Anfang einfügen
        Wartende newEl = new Wartende(o);

        if (head == null) { // Liste ist noch leer
            head = newEl;
            tail = head;
        } else {
            newEl.next = head;
            head.pred = newEl;
            head = newEl;
        }
        return newEl;
    }

    /* VORLAGE FÜR EINFACH VERKETTETE LISTE */
    public Wartende insert(Student o, Wartende pred) {
        // nach pred (Vorgänger) einfügen
        Wartende newEl = new Wartende(o);
        if (pred == null) { // am Anfang einf ügen
            return addFirst(o);
        } else { // nach pred ( Vorgänger ) einfügen
            newEl.next = pred.next;
            newEl.pred = pred;
            pred.next = newEl;
            if (newEl.next != null) {
                newEl.next.pred = newEl;
            } else {
                tail = newEl;
            }
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

        /* HIER CODE EINFÜGEN !!! */
        Wartende out = tail;
        tail = tail.pred;
        if (tail != null) {
            tail.next = null;
        } else {
            head = null;
        }
        out.pred = null;
        return out;
    }

    /* VORLAGE FÜR EINFACH VERKETTETE LISTE */
    public void delete(Wartende delete) {
        // Element nach pred (Vorgänger) löschen
        if (head == null) {
            // Liste leer , tue nichts
            return;
        }
        if (delete.pred != null) { // delete left side löschen
            delete.pred.next = delete.next;
        } else {
            head = delete.next;
        }
        if (delete.next != null) {
            delete.next.pred = delete.pred;
        } else {
            tail = delete.pred;
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

        /* HIER CODE EINFÜGEN !!! */
        deleteLast();
    }

    /**
     * Tauscht den Platz von zwei Studierenden in der Warteschlange
     * 
     * @param student  Der neue Studierende
     * @param wartende Der bereits Wartende
     */
    public void studiTauschen(Student student, Wartende wartende) {

        /* HIER CODE EINFÜGEN !!! */
        Wartende pred = wartende.pred;
        delete(wartende);
        System.out.println("Nach zu Tauschenden Löschen: " + toString());
        insert(student, pred);
        System.out.println("Nach Ersatz einfügen: " + toString());
        System.out.println("Student(in) mit der Matrikelnummer " + wartende.student.getMatrikelNr()
                + " hat den Platz in der Warteschlange mit der Studentin mit der Matrikelnummer "
                + student.getMatrikelNr() + " getauscht.");

    }

    public boolean istPalindrom(Wartende start, Wartende end) {
        if (start == end) {
            return true;
        } else if ((start != null && end == null) || (start == null && end != null)) {
            return false;
        } else {
            return istPalindrom(start.next, end.pred);
        }
    }
}
