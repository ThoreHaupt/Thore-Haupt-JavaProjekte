package ProkSy.ProkSyAltKlausurVorbereitung;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class K14A4 {
    public static final int port = 4444;
    public static PrintWriter toClient;
    public static BufferedReader fromClient;
    public static int playerchoice;
    public static boolean isRunning;

    public static void main(String[] args) {
        ServerSocket serverSocket = null;
        Socket s = null;

        try {
            serverSocket = new ServerSocket(port);
            s = serverSocket.accept();
            System.out.println("Verbindung aufgebaut");
            serverSocket.close();
            play(s);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void play(Socket s) {
        try {
            toClient = new PrintWriter(s.getOutputStream(), true);
            fromClient = new BufferedReader(new InputStreamReader(s.getInputStream()));
        } catch (Exception e) {
            System.out.println("didnt work lol");
        }
        isRunning = true;

        while (isRunning) {
            try {
                System.out.println("waiting for client input");
                playerchoice = Integer.parseInt(fromClient.readLine());
                System.out.println(playerchoice);
                if (playerchoice == (int) (Math.random() * 11)) {
                    toClient.println("u won lol");
                    isRunning = false;
                    s.close();
                } else {
                    toClient.println("u lost bitch noob get good lol");
                }
            } catch (NumberFormatException e) {
                toClient.println("only numbers u honk");

            } catch (IOException e) {
                isRunning = false;
                System.out.println("Error cringe");
            }
        }
    }
}
