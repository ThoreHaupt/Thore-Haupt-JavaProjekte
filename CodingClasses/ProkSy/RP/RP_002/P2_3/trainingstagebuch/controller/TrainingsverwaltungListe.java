package CodingClasses.ProkSy.RP.RP_002.P2_3.trainingstagebuch.controller;

import java.util.*;

import CodingClasses.ProkSy.RP.RP_002.P2_3.trainingstagebuch.model.Lauftraining;

/**
 * @author Jonas Lehner
 * @version V1.0
 * 
 */
public class TrainingsverwaltungListe {

	private static LinkedList<Lauftraining> lauftrainingseinheiten;
	private static boolean running;

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);

		lauftrainingseinheiten = new LinkedList<Lauftraining>();
		lauftrainingseinheiten.add(new Lauftraining(new Date(1460271436000L),
				8.889, 3600));
		lauftrainingseinheiten.add(new Lauftraining(new Date(1460098016000L),
				8.889, 3400));
		lauftrainingseinheiten.add(new Lauftraining(new Date(1460069016000L),
				8.889, 3280));
		lauftrainingseinheiten.add(new Lauftraining(new Date(1460862216000L),
				8.889, 3300));
		running = true;
		while (running) {
			System.out
					.println("================== Absolvierte Trainingseinheiten (gesamt) ===================");
			System.out
					.println("==============================================================================");
			// Inhalt der Liste ausgeben
			for (Lauftraining l : lauftrainingseinheiten) {
				System.out.println(l);
			}
			System.out.println();
			System.out
					.println("Welchen Eintrag m�chten Sie l�schen? Bitte Nummer eingeben. (Zum Beenden -1 eingeben)");
			String[] eintragString = scan.nextLine().split(" ");

			// Hier bitte die im �bungsblatt beschriebene Logik mit
			// entsprechender Fehlerbehandlung implementieren

			switch (eintragString[0]) {
				case "d":
					int i = Integer.parseInt(eintragString[1]);
					if (i < lauftrainingseinheiten.size() && i > -1)
						lauftrainingseinheiten.remove(i);
					else {
						if (i == -1) {
							running = false;
						}
						System.err.println(
								"bitte gebe einen Wert zwischen 0 und " + lauftrainingseinheiten.size() + " ein.");
					}
					break;
				case "c":
					running = false;
					break;
				default:
					System.out.println("Bitte geben einen der erlaubten Befehle ein, wie (d)elete oder (c)lose");
					break;
			}
		}
	}
}
