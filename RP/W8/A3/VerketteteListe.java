package RP.W8.A3;

import java.util.NoSuchElementException;

import javax.print.attribute.Size2DSyntax;

public class VerketteteListe {
	static class ListenElement {
		private Object element;
		private ListenElement next;

		public ListenElement(Object o) {
			element = o;
			next = null;
		}
	}

	private ListenElement head;
	public int length = 0;

	public VerketteteListe() {
		head = null;
	}

	public VerketteteListe(Object o) {
		head = new ListenElement(o);
	}

	public ListenElement add(Object o) {
		length++;
		if (head != null) {
			ListenElement e = head;
			while (e.next != null) {
				e = e.next;
			}
			e.next = new ListenElement(o);
			return e.next;
		} else {
			head = new ListenElement(o);
			return head;

		}

	}

	public void vertauschen() {
		ListenElement help = head;
		ListenElement tail = null;

		while (help.next.next != null) {

			help = help.next;
		}
		tail = help.next;
		tail.next = head.next;
		help.next = head;
		head.next = null;

	}

	public ListenElement getPredElementfromValue(Object value) {
		ListenElement e = head;
		if (head.element.equals(value))
			return null;
		if (e.next == null) {
			throw new NoSuchElementException(value.toString() + " is not en Element of this List");
		}
		while (!e.next.element.equals(value)) {
			if (e.next == null) {
				throw new NoSuchElementException(value.toString() + " is not en Element of this List");
			}
			e = e.next;
		}
		return e;
	}

	public ListenElement insert(Object o, ListenElement pred) {
		ListenElement newEl = new ListenElement(o);

		if (pred == null) {
			newEl.next = head;
			head = newEl;
		} else {
			newEl.next = pred.next;
			pred.next = newEl;
		}

		return newEl;
	}

	public void delete(Object o) throws NoSuchElementException {
		ListenElement e = getPredElementfromValue(o);
		delete(e);
	}

	public void delete(ListenElement pred) {
		if (pred == null) {
			head = head.next;
		} else {
			try {
				pred.next = pred.next.next;
			} catch (NullPointerException e) {
				pred.next = null;
			}

		}
		length--;
	}

	public String toString() {
		ListenElement e = head;
		if (e == null) {
			return "null";
		}
		String outputString = "";
		while (e.next != null) {
			outputString += e.element.toString() + " | ";
			e = e.next;
		}
		return outputString + e.element.toString();
	}

	public ListenElement getHeadElement() {
		return head;
	}

	public void clear() {
		head = null;
		length = 0;
	}
}
