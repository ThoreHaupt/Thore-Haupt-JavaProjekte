package CodingClasses.ProkSy.RP.RP_00.A2;

import java.util.*;

/**
 * Demonstriert eine Liste der Klasse DoublyLinkedList
 *
 * @version 1.0
 * @author Hans Wiwi
 */
public class DoublyLinkedListDemo {
	private static DoublyLinkedList liste;
	private static boolean running;
	private static String entry;

	/**
	 * Main-Methode der Klasse DoubyLinkedListDemo
	 * 
	 * @param args Kommandozeilenparamenter
	 */
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);

		running = true;
		liste = new DoublyLinkedList();
		liste.add(new int[] { 1, 2, 3, 4, 6, 7, 8 });
		System.out.println("Neue int-Liste erstellt.");

		while (running) {
			System.out.println("Was wollen Sie tun? (a)dd - (d)elete - (p)rint - (r)everse - (m)irror - (c)lose? ");
			entry = scan.next();

			switch (entry) {
				case "a":
					System.out.println("Welcher Wert soll hinzugefügt werden? ");
					liste.add(scan.nextInt());
					System.out.println("Listenausgabe: " + liste);
					break;
				case "d":
					liste.delete(liste.getHeadElement()); // Lösche erstes Element
					System.out.println("Listenausgabe: " + liste);
					break;
				case "p":
					System.out.println("Listenausgabe: " + liste);
					break;
				case "r":
					System.out.println("Listenausgabe: " + liste.toReverseString());
					break;
				case "m":
					liste.reverseList();
					System.out.println("Listenausgabe: " + liste);
					break;
				case "i":
					System.out.println("Index:");
					int index = scan.nextInt();
					if (index >= liste.getSize()) {
						System.out.println("Index out of Bounds try again.");
						break;
					}
					System.out.println("Value");
					Integer val = scan.nextInt();
					liste.insert(val, index);
					break;
				case "c":
					running = false;
					break;
				default:
					System.out.println("Bitte nur gültige Werte angeben.");
			}
		}
	}
}
