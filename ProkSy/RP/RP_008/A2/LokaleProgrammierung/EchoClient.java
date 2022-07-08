package ProkSy.RP.RP_008.A2.LokaleProgrammierung;

import java.io.*;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

/**
 *
 * @author Niklas K�hl
 */
public class EchoClient {
    public static final String EXIT = "exit";
    public static final String PROMPT_CLIENT = "EchoClient> ";
    public static final String PROMPT_SERVER = "EchoServer> ";

    public static void main(String[] args) {
        EchoService es = null;
        String request = null;
        String response = null;
        boolean isRunning = false;
        BufferedReader in = null;

        while (es == null) {
            try {
                // Referenz des Server-Objekts von Registry besorgen
                es = (EchoService) Naming.lookup("//172.23.219.33:7779/Echo");
            } catch (RemoteException e) {
                // e.printStackTrace();
            } catch (MalformedURLException e) {
                // e.printStackTrace();
            } catch (NotBoundException e) {
                // e.printStackTrace();
            }
            // print and wait until connected
            if (es == null) {
                try {
                    System.out.println("Could not connect to Server.");
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }

        try {
            isRunning = true;
            in = new BufferedReader(new InputStreamReader(System.in));
            System.out.print(PROMPT_CLIENT);
            request = in.readLine();
            while (isRunning) {
                if (request != null && !request.equals(EXIT)) {
                    es.request(request);
                    response = es.getResponse();
                    System.out.println("EchoServer> " + response);
                    System.out.print(PROMPT_CLIENT);
                    request = in.readLine();
                } else {
                    isRunning = false;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
