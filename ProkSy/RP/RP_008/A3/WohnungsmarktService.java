package ProkSy.RP.RP_008.A3;

import java.rmi.*;

public interface WohnungsmarktService extends Remote {
    public String getWohnung() throws RemoteException;
}
