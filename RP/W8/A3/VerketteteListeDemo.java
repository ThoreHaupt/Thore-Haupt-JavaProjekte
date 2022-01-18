package RP.W8.A3;

import java.util.Scanner;

import javax.security.auth.kerberos.DelegationPermission;
import javax.swing.*;


/**
 * Demonstriere eine Liste der Klasse ListeL
 *
 * @version 1.0
 * @author Hans Wiwi
 */
public class VerketteteListeDemo {
	private static VerketteteListe liste;
	private static boolean running;
	private static String entry;

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		
		running = true;
		liste   = new VerketteteListe();
		System.out.println("Neue int-Liste erstellt.");
		int length = 0;

		while (running) {
			System.out.println("Was wollen Sie tun? (a)dd - (d)elete [ -Element ] - (p)rint - (c)lose? ");
			entry = scan.next();

			switch (entry.split(" ")[0]) {
				case "a":
				System.out.println("Welcher Wert soll hinzugef�gt werden? ");
				liste.add(scan.next());
				System.out.println("Listenausgabe: " + liste);
				length++;
				break;
				case "d":
					liste.delete(entry.split(" ")[1]);
					System.out.println("Listenausgabe: " + liste);
                break;
                case "p":
			    System.out.println("Listenausgabe: " + liste);
                break;
                case "c":
			    running = false;
                break;
				default:
				System.out.println("Bitte nur g�ltige Werte angeben.");
			}
		}
	}
}

