package AIFB.ListenAufgabe;

public class Liste {
    Element head;
    int size = 0;

    class Element {
        int value;
        Element next;

        Element(int value) {
            this.next = null;
            this.value = value;
        }
    }

    public void add(int value) {
        Element current = head;
        if (head == null) {
            head = new Element(value);
            size++;
            return;
        }
        while (current.next != null) {
            current = current.next;
        }
        current.next = new Element(value);
        size++;
    }

    public int get(int index) {
        Element current = head;
        if (index < 0 || size <= index) {
            throw new IndexOutOfBoundsException();
        }
        for (int i = 0; i < index; i++) {
            current = current.next;
        }
        return current.value;
    }

    public void remove(int index) {
        Element current = head;
        // checkt, ob das Element in der Länge der Liste ist.
        if (index < 0 || size <= index) {
            throw new IndexOutOfBoundsException();
        }

        // wenn es sich um den 0. Index handelt, dann muss head und nicht etwas nach head verändert werden.
        if (index == 0 && head != null) {
            head = head.next;
            size--;
            return;
        }

        // gehe index - 1 mal forwärts. Da der Index nicht null ist, handelt es sich hier immer mindestens um 0.
        for (int i = 0; i < index - 1; i++) {
            current = current.next;
        }

        // lösche das Element von der Liste
        current.next = current.next.next;
        size--;
    }

    public String print() {
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
