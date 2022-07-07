package ProkSy.RP.RP_008.A1.client.main;

import ProkSy.RP.RP_008.A1.client.controller.*;
import ProkSy.RP.RP_008.A1.client.view.*;

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
	private static ClientController clientController;

	/**
	 * main-Methode der Klasse
	 * 
	 * @param args Kommandozeilenargumente
	 */
	public static void main(String[] args) {
		frame = new ClientFrame();
		viewController = new ViewController();
		clientController = new ClientController();

		// hier werden Frame und ViewController miteinander bekannt gemacht
		frame.setViewController(viewController);
		viewController.setView(frame);

		// hier werden ClientController und ViewController miteinander bekannt gemacht
		viewController.setClientController(clientController);
		clientController.setViewController(viewController);

	}

}
