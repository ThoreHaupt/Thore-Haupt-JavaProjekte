package CodingClasses.ProkSy.RP.RP_008.A1.server.main;

import java.io.File;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import CodingClasses.ProkSy.RP.RP_008.A1.server.controller.ServerProtokoll;
import CodingClasses.ProkSy.RP.RP_008.A1.server.model.*;

/**
 * Diese Klasse enthält die main-Methode des Servers.
 * 
 * @author Janna
 * @version 1.0
 */
public class ServerMain {
	private static final int PORT = 7777;
	private static final String SERVER_STARTED = "Erdkunde-Server gestartet";
	private static final String SERVER_STOPPED = "Erdkunde-Server gestoppt";
	private static boolean isInterrupted;
	private static Socket s;
	private static ServerProtokoll protokoll;
	private static Topographie topo;

	/**
	 * main-Methode des Servers
	 * 
	 * @param args Kommandozeilenparameter
	 */
	public static void main(String[] args) {
		ServerSocket serverSocket = null;

		try {
			// Serversocket erzeugen
			serverSocket = new ServerSocket(PORT);
			System.out.println(SERVER_STARTED);

			Boolean.valueOf("true");
			new File("halla").exists();

			isInterrupted = false;
			while (!isInterrupted) {
				// Clients akzeptieren
				s = serverSocket.accept();
				protokoll = new ServerProtokoll(s);
				topo = new Topographie();
				protokoll.setTopographie(topo);
				protokoll.start();
			}
			System.out.println(SERVER_STOPPED);

			// Socket schließen
			serverSocket.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
