package CodingClasses.ProkSy.RP.RP_008.A3;

import java.net.MalformedURLException;

import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.util.stream.Stream;

public class Imobilienmarkler {

    public static void main(String[] args) {
        new Imobilienmarkler();
    }

    public Imobilienmarkler() {
        try {
            LocateRegistry.createRegistry(1099);
            WohnungObject wohnung1 = new WohnungObject("lol 1", 420, 1, 0.5);
            WohnungObject wohnung2 = new WohnungObject("lol 2", 420, 1, 0.5);
            WohnungObject wohnung3 = new WohnungObject("lol 3", 420, 1, 0.5);
            WohnungObject[] wohungen = { wohnung1, wohnung2, wohnung3 };

            Stream.iterate(0, x -> ++x).forEach(i -> {
                try {
                    Naming.rebind("//localhost:1099/wohnung" + i, wohungen[i]);
                } catch (RemoteException | MalformedURLException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            });

            System.out.println("Der Immomarkler wurde gestartet");
        } catch (RemoteException e) {

        }
    }
}
