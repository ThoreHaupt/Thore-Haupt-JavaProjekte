package AIFB.Prog1Java.Prog1_2223.Rechnerpraktika.RP9.Aufgabe1;

import java.util.NoSuchElementException;
import java.util.Scanner;

/**
 * Demonstriere eine Liste der Klasse ListeL
 *
 * @version 1.0
 * @author Hans Wiwi
 */
public class VerketteteListeDemo2 {
	private static Warteschlange liste;
	private static boolean running;
	private static String entry;

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);

		running = true;
		liste = new Warteschlange();
		System.out.println("Neue int-Liste erstellt.");
		int length = 0;

		while (running) {
			System.out.println("Was wollen Sie tun? (a)dd [ -Element ] - (d)elete [ -Element ] - (p)rint - (c)lose? ");
			entry = scan.nextLine();

			switch (entry.split(" ")[0]) {
				case "a":
					liste.addFirst(new Student());
					System.out.println("Listenausgabe: " + liste);
					length++;
					break;
				case "d":

					try {
						if (entry.split(" ")[1].length() > 1) {
							liste.delete(liste.getHeadElement());
						} else {

						}
						//liste.clear();
					} catch (NoSuchElementException e1) {
						System.out.println("Dieses Element ist nicht Teil der Liste.");
					} catch (IndexOutOfBoundsException e2) {
						System.out.println("Bitte geben sie das zu Löschende Element an");
						break;
					}

					System.out.println("Listenausgabe: " + liste);
					break;
				case "p":
					System.out.println("Listenausgabe: " + liste);
					break;
				case "c":
					running = false;
					break;
				default:
					System.out.println("Bitte nur gültige Werte angeben.");
			}
		}
		System.out.println(length);
		scan.close();
	}
}
