package AIFB.ListenAufgabeVorlage;

public class Liste {

    Element head;
    int size = 0;

    class Element {
        Element next;
        int value;
    }

    /**
     * gibt den Wert eines Elementes bei einem gegebenen Index zurück.
     * @return
     */
    public int get(int index) {

        try {
            //Code der eine Exception wirft...
        } catch (NullPointerException e) {
            //TODO: handle exception
        } catch (IndexOutOfBoundsException e) {
            //TODO: handle exception
        } catch (RuntimeException e) {
            //TODO: handle exception
        } finally {
            // Was auf jeden Fall noch gemacht werden muss -> bsp. Scanner schließen
        }

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
