package ProkSy.RP.RP_008.A1.client.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class ClientController {

    ViewController viewController;

    BufferedReader inp;
    PrintWriter out;

    Socket s;
    String serverIP = "localhost";
    int serverport = 7777;

    public ClientController() {
        try {
            s = new Socket(serverIP, serverport);
            out = new PrintWriter(s.getOutputStream(), true);
            inp = new BufferedReader(new InputStreamReader(s.getInputStream()));
        } catch (IOException e) {
            System.out.println(e);
        }
    }

    public String exchangeMessage(String eingabe, boolean b) {
        out.println((b ? "1" : "0") + ";" + eingabe);
        System.out.println(b ? "1" : "0" + ";" + eingabe);
        try {
            while (!inp.ready()) {
                Thread.sleep(1);
            }
            return inp.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "";
    }

    public void setViewController(ViewController viewController) {
        this.viewController = viewController;
    }

    public void closeStreams() {
        try {
            s.close();
            out.close();
            inp.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
