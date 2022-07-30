package AIFB.ListenAufgabeVorlage;

public class Liste {

    Element head;
    int size;

    // Klasse Element mit Variablen: next und value
    class Element {
        int value;
        Element next;

        Element(int value) {
            this.next = null;
            this.value = value;
        }
    }

    /**
     * gibt den Wert eines Elementes bei einem gegebenen Index zurück.
     * @return
     */
    public int get(int index) {

        return 0;
    }

    /**
     * Diese Methode fügt ein neues Element dem Ende der Liste hinzu.
     */
    public void add(int value) {

    }

    /**
     * Diese Methode soll ein Element bei einem gegebenen Index entfernen.
     */
    public void remove(int index) {

    }

    /**
     * Diese Methode druckt die gesammte Liste aus.
     * @author Thore Haupt
     * @return
     */
    public String toString() {
        String outputString = "(";
        Element current = head;

        while (current != null && current.next != null) {
            outputString += current.value + ", ";
            current = current.next;
        }
        if (current != null) {
            outputString += current.value;
        }
        outputString += ")";
        return outputString;
    }
}
