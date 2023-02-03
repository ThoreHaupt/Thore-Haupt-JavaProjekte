package CodingClasses.ProkSy.RP.RP_008.A2;

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
