package AIFB.Prog1Java.Prog1_2223.KlausurKorrektur;

import javax.sql.rowset.spi.SyncProvider;

public class LinkedList {
    class Element {
        int val;
        Element next;
        Element prev;

        public Element(int val) {
            this.val = val;
        }
    }

    Element head;
    Element tail;

    public void add(int i) {
        Element newEl = new Element(i);

        if (head == null) { // Liste ist noch leer
            head = newEl;
            tail = head;
        } else {
            newEl.next = head;
            head.prev = newEl;
            head = newEl;
        }
    }

    public String toString() {
        String s = "(";
        Element help = head;
        while ((help != null) && (help.next != null)) {
            s = s + help.val + ", ";
            help = help.next;
        }
        if (help != null) {
            s = s + help.val;
        }
        return s + ")";
    }

    //(start != null && end == null) || (start == null && end != null)

    public static boolean istPalindrom(Element start, Element end) {
        if (start.val == end.val && (start.next == end || start.next.next == end)) {
            return true;
        } else if (start.val != end.val) {
            return false;
        } else {
            return istPalindrom(start.next, end.prev);
        }
    }

    public static void main(String[] args) {
        System.out.println((int) ('B'));
        LinkedList l = new LinkedList();
        l.add(3);
        l.add(2);
        l.add(1);
        l.add(2);
        l.add(3);
        System.out.println(l.toString());
        System.out.println(istPalindrom(l.head, l.tail));
        LinkedList l2 = new LinkedList();
        l2.add(3);
        l2.add(2);
        l2.add(2);
        l2.add(3);
        System.out.println(l2.toString());
        System.out.println(istPalindrom(l2.head, l2.tail));
    }

}
