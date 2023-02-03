package CodingClasses.ProkSy.RP.RP_005.A4_4.Vorgabe.view;

import java.awt.*;
import java.util.*;

import javax.swing.*;
import javax.swing.border.*;

import CodingClasses.ProkSy.RP.RP_005.A4_4.Vorgabe.controller.UmrechnerController;
import CodingClasses.ProkSy.RP.RP_005.A4_4.Vorgabe.model.*;

/**
 * Diese Klasse repräsentiert ein Fenster, mit dem Längeneinheiten ineinander
 * umgerechnet werden können
 * 
 * @author ABC
 * 
 */
@SuppressWarnings("serial")
public class UmrechnerView extends JFrame {

	private UmrechnerController controller;

	// An dieser Stelle müssen die Variablen der für das Fenster notwendigen
	// Komponenten deklariert
	// werden
	// --> Hier müssen Sie eigenen Code hinzufügen <--

	/**
	 * Konstruktor der Klasse UmrechnerView
	 * 
	 * @param units Set von Einheiten-Objekten (UnitOfLength), die berücksichtigt
	 *              werden sollen
	 */
	public UmrechnerView(TreeSet<UnitOfLength> units) {
		// Fenster aufbauen
		// --> Hier müssen Sie eigenen Code hinzufügen <--

	}

	/**
	 * Setzt den für die View zuständigen Controller
	 * 
	 * @param controller zu setzender Controller
	 */
	public void setController(UmrechnerController controller) {
		this.controller = controller;
		// unitButton.addActionListener(this.controller.createUnitListener());
		// calculateButton.addActionListener(this.controller.createCalculateListener());
	}

	// Implementieren Sie hier Getter-Methoden, um aus dem Controller auf Objekte
	// der View zugreifen
	// zu können
	// --> Hier müssen Sie eigenen Code hinzufügen <--

}
