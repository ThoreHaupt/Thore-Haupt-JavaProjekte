package CodingClasses.ProkSy.RP.RP_008.A2;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

/**
 *
 * @author Niklas K�hl
 */
public class EchoObject extends UnicastRemoteObject implements EchoService {
    private String response;

    public EchoObject() throws RemoteException {
        super();
        response = "";
    }

    public String getResponse() throws RemoteException {
        String result = null;
        result = response;
        System.out.println("Aufruf von getResponse()");
        System.out.println("Client>> " + result);
        return result;
    }

    public void request(String request) throws RemoteException {
        response = request;
    }
}
