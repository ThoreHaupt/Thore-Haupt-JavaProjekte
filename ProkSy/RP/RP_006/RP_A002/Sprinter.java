package ProkSy.RP.RP_006.RP_A002;

import Commons.CalulationTools.SupportingCalculations;

public class Sprinter extends Thread {

    int nummer = 0;

    public Sprinter(int nummer) {
        this.nummer = nummer;
    }

    public void run() {
        System.out.println("Sprinter" + nummer + " gestartet.");
        double d = SupportingCalculations.round(Math.random() * 3, 2);
        System.out.println("Sprinter" + nummer + "läuft " + d + " Sekunden");
        try {
            sleep((long) d * 1000);
        } catch (InterruptedException e) {

        }
        System.out.println("Sprinter" + nummer + "erreicht sein Ziel");
        System.out.println("Stab an den nächsten übergeben");
    }
}
