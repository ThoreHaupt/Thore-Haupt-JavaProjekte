package ProkSy.RP.RP_005.A4_4.Vorgabe.controller;

import java.awt.event.*;
import java.text.*;
import java.util.*;

import javax.swing.JOptionPane;
import javax.swing.JTextField;

import ProkSy.RP.RP_005.A4_4.Vorgabe.view.UmrechnerView;

/**
 * Die Klasse UmrechnerController kümmert sich um die Verarbeitung von
 * Benutzereingaben im zugehörigen View
 * 
 * @author Jonas Lehner
 * 
 */
public class UmrechnerController {

	private UmrechnerView view; // Referenz auf das zugehörige View

	/**
	 * Erzeugt ein neues Objekt der inneren Klasse UnitListener und gibt es zurück
	 * 
	 * @return neues UnitListener-Objekt
	 */
	public UnitListener createUnitListener() {
		return new UnitListener();
	}

	/**
	 * Erzeugt ein neues Objekt der inneren Klasse CalculateListener und gibt es
	 * zurück
	 * 
	 * @return neues CalculateListener-Objekt
	 */
	public CalculateListener createCalculateListener() {
		return new CalculateListener();
	}

	/**
	 * Setzt die vom Controller zu verwaltende View
	 * 
	 * @param view zu verwaltende View
	 */
	public void setView(UmrechnerView view) {
		this.view = view;
		// Hier können weitere Initialisierungen vorgenommen werden
		// --> Hier müssen Sie evtl. eigenen Code hinzufgen <--
	}

	/**
	 * Diese innere Klasse implementiert das ActionListener-Interface, kann also
	 * verwendet werden, um Benutzerinteraktion mit dem GUI zu verarbeiten. Sie soll
	 * sich um Ereignisse des unitButtons kümmern
	 * 
	 * @author Jonas Lehner
	 * 
	 */
	class UnitListener implements ActionListener {

		/*
		 * Diese Methode wird aufgerufen, wenn ein Button, dem eine Instanz dieser
		 * Klasse als Listener hinzugefügt wurde, betätigt wird
		 * 
		 * (non-Javadoc)
		 * 
		 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent
		 * )
		 */
		@Override
		public void actionPerformed(ActionEvent e) {
			// Hier ist (analog zu Aufgabe p11) auf das Betätigen des unitButtons zu
			// reagieren
			// Im Gegensatz zu Aufgabe p11 können Sie hier aber nicht direkt auf die
			// Elemente des
			// View zugreifen.
			// Verwenden Sie statt dessen die Instanzvariable view, die eine Referenz auf
			// das View
			// enthält
			// --> Hier müssen Sie eigenen Code hinzufügen <--
		}
	}

	/**
	 * Diese innere Klasse implementiert das ActionListener-Interface, kann also
	 * verwendet werden, um Benutzerinteraktion mit dem GUI zu verarbeiten. Sie soll
	 * sich um Ereignisse des calculateButtons kümmern
	 * 
	 * @author Jonas Lehner
	 * 
	 */
	class CalculateListener implements ActionListener {

		/*
		 * Diese Methode wird aufgerufen, wenn ein Button, dem eine Instanz dieser
		 * Klasse als Listener hinzugefügt wurde, betätigt wird
		 * 
		 * (non-Javadoc)
		 * 
		 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent
		 * )
		 */
		@Override
		public void actionPerformed(ActionEvent e) {
			// Hier ist (analog zu Aufgabe p11) auf das Betätigen des calculateButtons
			// zu reagieren
			// Im Gegensatz zu Aufgabe p11 können Sie hier aber nicht direkt auf die
			// Elemente des View zugreifen.
			// Verwenden Sie statt dessen die Instanzvariable view, die eine Referenz auf
			// das View enthält
			// --> Hier müssen Sie eigenen Code hinzufügen <--
		}
	}

}
