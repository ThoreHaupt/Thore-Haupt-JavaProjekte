package AIFB.Prog1Java.Prog1_2223.Rechnerpraktika.RP9.Aufgabe2.binaerBaum;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Beliebig viele int-Werte " + "eingeben (Wert 0 fuer Abbruch):");

        int n = scanner.nextInt();
        Baum b = new Baum(n);

        n = scanner.nextInt();
        while (n != 0) {
            b.insert(n);
            n = scanner.nextInt();
        }

        System.out.println("Baum (pre): " + b.print("pre"));
        System.out.println("Baum (post): " + b.print("post"));
        System.out.println("Baum (in): " + b.print("in"));

        Baum x = b.baumSpiegeln();

        System.out.println("Gespiegelter Baum (in): " + x.print("in"));

        Baum z = new Baum(0, b, x);
        System.out.println("Zusammengesetzter Baum (in): " + z.print("in"));
        System.out.println("Ist symmetrisch: " + z.istSymmetrisch());

        scanner.close();
    }
}
