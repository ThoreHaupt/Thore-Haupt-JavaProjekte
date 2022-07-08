package ProkSy.RP.RP_008.A2.LokaleProgrammierung;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 *
 * @author Niklas K�hl
 */
public interface EchoService extends Remote {
    public String getResponse() throws RemoteException;

    public void request(String request) throws RemoteException;
}
