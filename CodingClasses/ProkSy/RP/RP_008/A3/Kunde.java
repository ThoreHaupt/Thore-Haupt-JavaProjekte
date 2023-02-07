package CodingClasses.ProkSy.RP.RP_008.A3;

import java.net.MalformedURLException;
import java.rmi.AccessException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.ArrayList;

public class Kunde {

    boolean interrupted = false;
    WohnungsmarktService w;
    ArrayList<WohnungsmarktService> wohungen;

    public static void main(String[] args) {
        new Kunde();
    }

    public Kunde() {
        try {
            w = (WohnungsmarktService) Naming.lookup("//localhost:1099/wohnung1");
            System.out.println(w.getWohnung());

        } catch (AccessException e) {
            e.printStackTrace();
        } catch (RemoteException e) {
            e.printStackTrace();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (NotBoundException e) {
            e.printStackTrace();
        }
    }

    public void uiInterface() {

    }
}
