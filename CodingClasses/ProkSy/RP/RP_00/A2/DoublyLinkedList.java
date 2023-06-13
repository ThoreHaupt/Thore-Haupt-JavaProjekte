package CodingClasses.ProkSy.RP.RP_00.A2;

/**
 * Implementiert eine doppelt verkettete Liste
 * 
 * @version 1.0
 * @author Hans Wiwi
 */

public class DoublyLinkedList {

	int size = 0;

	/**
	 * Innere Klasse
	 * Repräsentiert ein Listenelement der Liste
	 * 
	 * @author Jonas Lehner
	 *
	 */
	static class ListElement {
		private Object element;
		private ListElement next;
		private ListElement previous;

		/**
		 * Konstruktor für ein ListElement, das eine Referenz auf das übergebene Objekt
		 * enthält
		 * 
		 * @param o Objekt, auf das das ListElement referenzieren soll
		 */
		public ListElement(Object o) {
			element = o;
			next = null;
			previous = null;
		}
	}

	private ListElement head;
	private ListElement tail;

	/**
	 * Konstruktor für eine leere doppelt verkettete Liste
	 */
	public DoublyLinkedList() {
		head = null;
		tail = null;
	}

	/**
	 * Konstruktor für eine doppelt verkettete Liste, die das übergebene Objekt
	 * enthält
	 * 
	 * @param o Objekt, das in die Liste aufgenommen werden soll
	 */
	public DoublyLinkedList(Object o) {
		head = new ListElement(o);
		tail = head;
	}

	public void add(int[] arr) {
		for (int i = 0; i < arr.length; i++) {
			add(Integer.valueOf(arr[i]));
		}
	}

	/**
	 * Fügt ein Objekt am Anfang der Liste ein.
	 * 
	 * @param o Objekt, das eingefügt werden soll
	 * @return Liefert das ListElement zurück, das die Referenz auf das eingefügte
	 *         Objekt enthält
	 */
	public ListElement add(Object o) {
		return insert(o, null);
	}

	/**
	 * Fügt ein Objekt nach dem übergebenen ListElement ein.
	 * 
	 * @param o    Objekt, das eingefügt werden soll
	 * @param pred ListElement, nach dem das Objekt eingefügt werden soll
	 * @return Liefert das ListElement zurück, das die Referenz auf das eingefügte
	 *         Objekt enthält
	 */
	public ListElement insert(Object o, ListElement pred) {
		size++;
		// Diese Methode soll ein neues Element nach dem übergebenen Element pred
		// hinzufügen
		// Falls pred eine null-Referenz ist, soll das neue Element an den Anfang der
		// Liste gesetzt werden
		ListElement newEl = new ListElement(o);
		if (pred == null) {
			if (head != null) {
				head.previous = newEl;
			} else {
				tail = newEl;
			}
			newEl.next = head;
			head = newEl;

			return newEl;
		}
		if (pred.next != null) {
			newEl.previous = pred;
			newEl.next = pred.next;
			pred.next.previous = newEl;
			pred.next = newEl;
			return newEl;
		} else {
			pred.next = newEl;
			newEl.previous = pred;
			tail = newEl;
			return newEl;
		}

	}

	public ListElement insert(Object o, int index) {
		ListElement pred = getElementByIndex(index);
		return insert(o, pred);
	}

	/**
	 * Löscht das übergebene ListElement aus der Liste
	 * 
	 * @param element ListElement, das gelöscht werden soll
	 */
	public void delete(ListElement element) {
		size--;
		// Diese Methode soll das ListElement element löschen
		if (element.previous != null) {
			element.previous.next = element.next;
		} else {
			head = element.next;
			if (head != null)
				head.previous = null;
		}
		if (element.next != null) {
			element.next.previous = element.previous;
		} else {
			tail = element.previous;
			if (tail != null)
				tail.next = null;
		}

	}

	/**
	 * Gibt die Liste in ursprünglicher Reihenfolge aus
	 * 
	 * @return String des Listeninhalts in ursprünglicher Reihenfolge
	 */
	public String toString() {
		String s = "(";
		ListElement help = head;

		while (help != null && help.next != null) {
			s = s + help.element + ", ";
			help = help.next;
		}

		if (help != null) {
			s = s + help.element;
		}

		return s + ")";
	}

	/**
	 * Gibt die Liste in umgekehrter Reihenfolge aus
	 * 
	 * @return String des Listeninhalts in umgekehrter Reihenfolge
	 */
	public String toReverseString() {
		// Diese Methode soll die Liste RÜCKWÄRTS als String zurückgeben
		ListElement help = tail;
		String output = "(";
		if (help == null)
			return "( )";
		while (help.previous != null) {
			output += help.element.toString() + ", ";
			help = help.previous;
		}
		output += help.element.toString() + ")";
		return output;
	}

	/**
	 * Dreht die Liste um.
	 */
	public void reverseList() {
		ListElement help = head;
		ListElement help2;
		while (help != null) {
			help2 = help.next;
			help.next = help.previous;
			help.previous = help2;
			help = help.previous;
		}
		help2 = head;
		head = tail;
		tail = help2;
	}

	public ListElement getHeadElement() {
		return head;
	}

	public ListElement getTailElement() {
		return tail;
	}

	public ListElement getElementByIndex(int index) {
		ListElement current = head;
		for (int i = 0; i < index; i++) {
			if (current == null) {
				throw new IndexOutOfBoundsException(index);
			}
			current = current.next;
		}
		return current;
	}

	public int getSize() {
		return size;
	}
}
