package RP.W8.A3;

import java.util.NoSuchElementException;

public class VerketteteListe {
	static class ListenElement {
		private Object element; 
		private ListenElement next; 

		public ListenElement(Object o) {
			element = o;
			next    = null;
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
		if(head != null){
			ListenElement e = head;
			while (e.next != null) {
				e = e.next;
			}
			e.next = new ListenElement(o);
			return e.next;
		}else{
			head = new ListenElement(o);
			return head;

		}

	}

	public ListenElement getElementfromValue(Object value) {
		ListenElement e = head;
		
		while(!e.next.element.equals(value)){
			
			e = e.next;
			if (e.next == null){
				throw new NoSuchElementException(value.toString() + " is not en Element of this List");
			}
		}
		return e;
	}

	public ListenElement insert(Object o, ListenElement pred) {
		ListenElement newEl = new ListenElement(o);

		if (pred == null) {
			newEl.next = head;
			head	   = newEl;
		} else {
			newEl.next = pred.next;
			pred.next  = newEl;
		}

		return newEl;
	}

	public void delete(Object o) {
		ListenElement e;
		try {
			e = getElementfromValue(o);
		} catch (NoSuchElementException er) {
			
		}
		pred.next = pred.next.next;
	}

	public String toString() {
		ListenElement e = head;
		String outputString = "";
		while (e.next != null) {
			outputString += e.element.toString() + " | ";
			e = e.next;
		}
		return outputString + e.element.toString();
	}

	public ListenElement getHeadElement()
	{
		return head;
	}
}

