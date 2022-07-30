package ProkSy.RP.RP_008.A4.server.controller;

import java.rmi.*;

public interface Erdkunde extends Remote {

    String getResult(boolean bundesland, String eingabe) throws RemoteException;

}
