package CodingClasses.ProkSy.RP.RP_008.A2;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;

/**
 *
 * @author Niklas K�hl
 */
public class EchoServer {
    private EchoObject eo;

    public static void main(String[] args) {
        new EchoServer();
    }

    public EchoServer() {
        try {
            //entfernte Objekt erstellen
            this.eo = new EchoObject();
            //create Registry 
            LocateRegistry.createRegistry(1099);
            // das entfernte Object bei der Registry anmelden
            Naming.rebind("rmi://localhost:1099/Echo", this.eo);
            System.out.println("Der Server ist aktiv");
        } catch (RemoteException e) {
            System.out.println("a");
            e.printStackTrace();
        } catch (MalformedURLException e) {
            System.out.println("b");
            e.printStackTrace();
        }
    }

    public EchoObject getEchoObject() {
        EchoObject result;
        result = eo;
        return result;
    }
}
