package CodingClasses.ProkSy.RP.RP_002.P2_3.trainingstagebuch.controller;

import java.util.*;

import CodingClasses.ProkSy.RP.RP_002.P2_3.trainingstagebuch.model.Krafttraining;
import CodingClasses.ProkSy.RP.RP_002.P2_3.trainingstagebuch.model.Lauftraining;
import CodingClasses.ProkSy.RP.RP_002.P2_3.trainingstagebuch.model.Trainingseinheit;

/**
 * Klasse Trainingsverwaltung
 * 
 * @author Jonas Lehner
 * @version V1.0
 * 
 */
public class TrainingsverwaltungSet {

	/**
	 * Main-Methode der Klasse Trainingsverwaltung
	 * 
	 * @param args
	 *             Kommandozeilenparameter
	 */
	public static void main(String[] args) {

		// Instantiieren Sie hier die ben�tigten drei Mengen
		TreeSet<Lauftraining> lauftrainingseinheiten = new TreeSet<Lauftraining>();
		TreeSet<Trainingseinheit> trainingseinheiten = new TreeSet<Trainingseinheit>();
		TreeSet<Krafttraining> krafttrainingseinheiten = new TreeSet<Krafttraining>();

		lauftrainingseinheiten.add(new Lauftraining(new Date(1460271436000L),
				8.889, 3600));
		lauftrainingseinheiten.add(new Lauftraining(new Date(1460098016000L),
				8.889, 3400));
		lauftrainingseinheiten.add(new Lauftraining(new Date(1460069016000L),
				8.889, 3280));
		lauftrainingseinheiten.add(new Lauftraining(new Date(1460862216000L),
				8.889, 3300));
		lauftrainingseinheiten.add(new Lauftraining(new Date(1460923416000L),
				8.889, 3250));

		krafttrainingseinheiten.add(new Krafttraining(new Date(1461186216000L),
				3));
		krafttrainingseinheiten.add(new Krafttraining(new Date(1460685816000L),
				5));
		krafttrainingseinheiten.add(new Krafttraining(new Date(1460268216000L),
				6));

		System.out
				.println("=================== Absolvierte Lauf-Trainingseinheiten ======================");
		System.out
				.println("==============================================================================");
		// Hier sollen alle gespeicherten Lauf-Trainingseinheiten ausgegeben
		// werden

		for (Lauftraining l : lauftrainingseinheiten) {
			System.out.println(l.toString());
			trainingseinheiten.add(l);
		}
		System.out.println("\n");

		System.out
				.println("=================== Absolvierte Kraft-Trainingseinheiten =====================");
		System.out
				.println("==============================================================================");
		for (Krafttraining k : krafttrainingseinheiten) {
			System.out.println(k.toString());
			trainingseinheiten.add(k);
		}
		System.out.println("\n");

		System.out
				.println("================== Absolvierte Trainingseinheiten (gesamt) ===================");
		System.out
				.println("==============================================================================");

		for (Lauftraining l : lauftrainingseinheiten) {
			System.out.println(l.toString());
		}
		// Hier sollen alle gespeicherten Trainingseinheiten (aus der Menge
		// trainingseinheiten) ausgegeben
		// werden

	}

}
