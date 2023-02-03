package CodingClasses.ProkSy.RP.RP_008.A4.server.main;

import java.rmi.*;
import java.rmi.registry.*;

import CodingClasses.ProkSy.RP.RP_008.A4.server.controller.*;
import CodingClasses.ProkSy.RP.RP_008.A4.server.model.*;

/**
 * Diese Klasse enthält die main-Methode des Servers.
 * 
 * @author Janna
 * @version 1.0
 */
public class ServerMain {

	/**
	 * main-Methode des Servers
	 * 
	 * @param args Kommandozeilenparameter
	 */
	public static void main(String[] args) {
		ErdkundeImpl e;
		Topographie t;

		try {
			e = new ErdkundeImpl();
			t = new Topographie();

			e.setTopographie(t);

			Registry registry = LocateRegistry.createRegistry(1705);
			registry.rebind("rmi://localhost:1705/Erdkunde", e);
		} catch (RemoteException re) {
			re.printStackTrace();
		}

		System.out.println("Der Server ist aktiv");
	}
}
