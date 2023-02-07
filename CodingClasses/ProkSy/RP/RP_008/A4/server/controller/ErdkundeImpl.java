package CodingClasses.ProkSy.RP.RP_008.A4.server.controller;

import java.rmi.*;
import java.rmi.server.UnicastRemoteObject;

import CodingClasses.ProkSy.RP.RP_008.A4.server.model.Topographie;

public class ErdkundeImpl extends UnicastRemoteObject implements Erdkunde {

    Topographie topo = null;

    public ErdkundeImpl() throws RemoteException {
        super();
    }

    public void setTopographie(Topographie t) {
        topo = t;
    }

    @Override
    public String getResult(boolean bundesland, String eingabe) throws RemoteException {
        if (bundesland) {
            return topo.getStadt(eingabe);
        } else {
            return topo.getLand(eingabe);
        }
    }

}
