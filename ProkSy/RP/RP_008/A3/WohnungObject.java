package ProkSy.RP.RP_008.A3;

import java.rmi.*;
import java.rmi.server.RMIClientSocketFactory;
import java.rmi.server.RMIServerSocketFactory;
import java.rmi.server.UnicastRemoteObject;

public class WohnungObject extends UnicastRemoteObject implements WohnungsmarktService {

    String name;
    int preis;
    int zimmerzahl;
    double fläche;

    /**
     * @param port
     * @param csf
     * @param ssf
     * @param name
     * @param price
     * @param zimmerzahl
     * @param fläche
     * @throws RemoteException
     */
    public WohnungObject(String name, int price, int zimmerzahl, double fläche) throws RemoteException {
        super();
        this.name = name;
        this.preis = price;
        this.zimmerzahl = zimmerzahl;
        this.fläche = fläche;
    }

    @Override
    public String getWohnung() throws RemoteException {
        return name + " : \nHat " + zimmerzahl + " Zimmer auf einer \nFläche von " + fläche + " m2. Der Preis ist "
                + preis + " Euro";
    }

}
