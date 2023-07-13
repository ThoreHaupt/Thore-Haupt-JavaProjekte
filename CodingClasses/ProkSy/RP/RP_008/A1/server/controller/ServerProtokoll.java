package CodingClasses.ProkSy.RP.RP_008.A1.server.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

import CodingClasses.ProkSy.RP.RP_008.A1.server.model.Topographie;

public class ServerProtokoll extends Thread {

    BufferedReader inp;
    PrintWriter out;

    Socket s;

    boolean interrupted = false;
    boolean isBundesland = true;

    Topographie topo = null;

    public ServerProtokoll(Socket s) {
        try {
            this.s = s;
            out = new PrintWriter(s.getOutputStream(), true);
            inp = new BufferedReader(new InputStreamReader(s.getInputStream()));
        } catch (IOException e) {
            System.out.println(e);
        }

    }

    public void setTopographie(Topographie topo) {
        this.topo = topo;
    }

    public String topographie(String key, boolean isBundesland) {
        if (isBundesland) {
            return topo.getLand(key);
        } else {
            return topo.getStadt(key);
        }
    }

    public void run() {
        System.out.println("connected to new client and started thread");
        transact();
    }

    public void transact() {
        while (!interrupted) {
            try {
                while (!inp.ready()) {
                    Thread.sleep(1);
                }
                String request = inp.readLine();
                System.out.println("Request: " + request);
                if (!request.contains(";")) {
                    continue;
                }
                String[] arr = request.split(";");
                if (arr.length < 2)
                    continue;
                this.isBundesland = arr[0].equals("1");
                String result = topographie(arr[1], isBundesland);
                out.println(result);

            } catch (IOException e) {
                e.printStackTrace();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
