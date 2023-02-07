package CodingClasses.ProkSy.RP.RP_008.A4.client.controller;

import java.awt.event.*;
import java.net.MalformedURLException;
import java.rmi.registry.LocateRegistry;
import java.text.*;
import java.util.*;

import CodingClasses.ProkSy.RP.RP_008.A4.client.view.*;
import CodingClasses.ProkSy.RP.RP_008.A4.server.controller.Erdkunde;

import java.rmi.*;
import java.rmi.registry.Registry;

/**
 * Diese Klasse enthält die ActionListener für das Frame.
 * 
 * @author Janna
 * @version 1.0
 */
public class ViewController {
	private ClientFrame frame;
	Erdkunde erdkunde = null;
	private boolean bundesland = true;

	public ViewController() {
		try {

			Registry r = LocateRegistry.getRegistry("localhost", 1705);
			erdkunde = (Erdkunde) r.lookup("rmi://localhost:1705/Erdkunde");
		} catch (RemoteException | NotBoundException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Methode, mit der der ViewController eine Instanz des Frames zugeordnet
	 * bekommt.
	 * 
	 * @param frame
	 */
	public void setView(ClientFrame frame) {
		this.frame = frame;
	}

	/**
	 * gibt einen neuen TauschListener zurück
	 * 
	 * @return new TauschListener()
	 */
	public TauschListener createTauschListener() {
		return new TauschListener();
	}

	/**
	 * gibt einen neuen StartListener zurück
	 * 
	 * @return new StartListener()
	 */
	public StartListener createStartListener() {
		return new StartListener();
	}

	/**
	 * innere Klasse, die den Listener für den Tausch Button repräsentiert
	 * 
	 * @author Janna
	 * @version 1.0
	 */
	class TauschListener implements ActionListener {
		/**
		 * Diese Methode wird aufgerufen, wenn der Tausch Button betätigt wird. Sie
		 * vertauscht die Zustände für die Editierbarkeit der beiden Textfelder.
		 */
		@Override
		public void actionPerformed(ActionEvent e) {
			if (frame.getBundesland().isEditable() == true) {
				frame.getBundesland().setEditable(false);
				frame.getHauptstadt().setEditable(true);
				frame.getHauptstadt().setText("");
				frame.getBundesland().setText("");
				bundesland = false;
			} else {
				frame.getBundesland().setEditable(true);
				frame.getHauptstadt().setEditable(false);
				frame.getHauptstadt().setText("");
				frame.getBundesland().setText("");
				bundesland = true;
			}
		}
	}

	/**
	 * Innere Klasse, die den ActionListener für den Start Button repräsentiert.
	 * 
	 * @author Janna
	 * @version 1.0
	 */
	class StartListener implements ActionListener {
		String eingabe = null;
		String ausgabe = null;

		// Diese innere Klasse soll fertig ausprogrammiert werden.

		public StartListener() {

		}

		/**
		 * Diese Methode wird aufgerufen, wenn der Start Button betätigt wird. Das Frame
		 * wird jetzt mit dem Server verbunden und bekommt den angefragten Wert
		 * geliefert.
		 */
		@Override
		public void actionPerformed(ActionEvent e) {
			eingabe = frame.getBundesland().isEditable() ? frame.getBundesland().getText()
					: frame.getHauptstadt().getText();

			if (frame.getBundesland().isEditable()) {
				bundesland = true;
				eingabe = frame.getBundesland().getText();
			} else {
				bundesland = false;
				eingabe = frame.getHauptstadt().getText();
			}

			try {
				ausgabe = erdkunde.getResult(bundesland, eingabe);
			} catch (RemoteException e1) {
				e1.printStackTrace();
			}
			if (bundesland) {
				frame.getHauptstadt().setText(ausgabe);
			} else {
				frame.getBundesland().setText(ausgabe);
			}

		}
	}

}
