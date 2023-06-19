package AIFB;

public class threadTestLea {
    public static void main(String[] args) {
        Sprinter hannes = new Sprinter("hannes", null);
        Sprinter lea = new Sprinter("lea", hannes);
        Sprinter rosa = new Sprinter("rosa", lea);

        rosa.start();
        hannes.start();
        lea.start();
    }

}

class Sprinter extends Thread {
    Sprinter vor;
    double laufzeit = 3 + 3 * Math.random();

    public Sprinter(String name, Sprinter vor) {
        super(name);
        this.vor = vor;

    }

    @Override
    public void run() {
        try {
            if (vor != null) {
                vor.join();
                System.out.println(getName() + " ist dran");
            } else {
                System.out.println(getName() + " hat gestartet");
            }
            sleep((int) laufzeit * 10);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}