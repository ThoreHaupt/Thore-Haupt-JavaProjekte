package RP.W6.A4;

public class Experimentauswertung {
    
    public static void gewichteterMesswert(Messung messung) {
        messung.messwertGewichtet = 0.0;
        for (int i = 0; i < messung.messreihe.length; i++) {
            messung.messwertGewichtet += (double) messung.messreihe[i] * messung.gewichtungsreihe[messung.messreihe.length - 1 - i];
            System.out.println((double) messung.messreihe[i]);
            System.out.println(messung.gewichtungsreihe[messung.messreihe.length - 1 - i]);
            System.out.println("calculation: " + i + " : " + ((double) messung.messreihe[i]
                    * messung.gewichtungsreihe[messung.messreihe.length - 1 - i]));
        }
    }
}
