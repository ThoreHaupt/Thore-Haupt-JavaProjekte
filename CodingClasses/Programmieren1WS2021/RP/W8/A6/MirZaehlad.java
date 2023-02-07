package RP.W8.A6;

public final class MirZaehlad {

    private MirZaehlad(){};
    public static double runden(double wert, int stellen){
        return (Math.round(wert*Math.pow(10,stellen))/Math.pow(10,stellen));
    }
}
