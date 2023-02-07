package CodingClasses.ProkSy.RP.RP_008.A4.client.main;

import java.rmi.*;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import CodingClasses.ProkSy.RP.RP_008.A4.client.controller.*;
import CodingClasses.ProkSy.RP.RP_008.A4.client.view.*;
import CodingClasses.ProkSy.RP.RP_008.A4.server.controller.*;

import java.io.*;

/**
 * Diese Klasse enthält die main-Methode des Client
 * 
 * @author Janna Ulrich
 * @version 1.0
 *
 */
public class ClientMain {

	private static ClientFrame frame;
	private static ViewController viewController;

	/**
	 * main-Methode der Klasse
	 * 
	 * @param args Kommandozeilenargumente
	 */
	public static void main(String[] args) {
		frame = new ClientFrame();
		viewController = new ViewController();

		// hier werden Frame und ViewController miteinander bekannt gemacht
		frame.setViewController(viewController);
		viewController.setView(frame);

	}

}
