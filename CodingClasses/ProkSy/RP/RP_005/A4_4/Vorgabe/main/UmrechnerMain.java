package CodingClasses.ProkSy.RP.RP_005.A4_4.Vorgabe.main;

import java.util.*;

import CodingClasses.ProkSy.RP.RP_005.A4_4.Vorgabe.controller.UmrechnerController;
import CodingClasses.ProkSy.RP.RP_005.A4_4.Vorgabe.model.UnitOfLength;
import CodingClasses.ProkSy.RP.RP_005.A4_4.Vorgabe.view.UmrechnerView;

/**
 * Diese KLasse enthält die main-Methode, aus der heraus das anzuzeigende
 * Fenster und der Controller erzeugt wird und die beiden miteinander bekannt
 * gemacht werden
 * 
 * @author Jonas Lehner
 * 
 */
public class UmrechnerMain {
	private static TreeSet<UnitOfLength> units;
	private static UmrechnerController controller;
	private static UmrechnerView view;

	/**
	 * Main-Methode der Klasse
	 * 
	 * @param args Kommandozeilenparameter
	 */
	public static void main(String[] args) {
		// Erzeuge eine Menge des Typs UnitOfLength
		units = new TreeSet<UnitOfLength>();
		// Fülle die Menge mit einigen Einheiten
		units.add(new UnitOfLength("Millimeter", 1));
		units.add(new UnitOfLength("Zentimeter", 10));
		units.add(new UnitOfLength("Meter", 1000));
		units.add(new UnitOfLength("Kilometer", 1000000));
		units.add(new UnitOfLength("Zoll", 25.4));
		units.add(new UnitOfLength("Fuß", 304.8));
		units.add(new UnitOfLength("Meile", 1609000));
		units.add(new UnitOfLength("Seemeile", 1852000));
		units.add(new UnitOfLength("Yard", 914));

		// Erzeuge einen Controller des Typs UmrechnerController
		controller = new UmrechnerController();

		// Erzeuge ein Fenster des Typs UmrechnerView
		view = new UmrechnerView(units);

		// Macht beide miteinander bekannt
		view.setController(controller);
		controller.setView(view);

		// Macht das Fenster sichtbar
		view.setVisible(true);

	}

}
