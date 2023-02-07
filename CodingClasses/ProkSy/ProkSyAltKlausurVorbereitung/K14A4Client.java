package CodingClasses.ProkSy.ProkSyAltKlausurVorbereitung;

import java.io.*;
import java.net.*;
import java.util.Scanner;

public class K14A4Client {
    static String result = "";
    static boolean running = true;
    static Scanner scanner = null;

    public static void main(String[] args) {

        try {
            Socket s = new Socket("localhost", 4444);
            BufferedReader in = new BufferedReader(new InputStreamReader(s.getInputStream()));
            PrintWriter out = new PrintWriter(s.getOutputStream(), true);
            scanner = new Scanner(System.in);
            while (running) {
                System.out.println("gimme a number");
                String output = scanner.nextLine();
                System.out.println(output);
                out.println(output);
                if ((result = in.readLine()).equals("u won lol")) {
                    running = false;
                    s.close();
                }
                System.out.println(result);
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (scanner != null)
                scanner.close();
        }
    }
}
