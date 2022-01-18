package RP.W8.A6;

public class Guadschai {
    private final double RABATT;
    private boolean eingeloest;

    public Guadschai() {
        RABATT = Math.random() * 10 + 1;
    }

    public double getRabatt() {
        if (!eingeloest) {
            return MirZaehlad.runden(RABATT, 2);
        } else
            return 0;
    }

    public void einloesen() {
        eingeloest = true;
    }
}
