package testStuff.Tut9;


public class LamasMitHueten {
    public static void main(String[] args) {
        Carl lama1 = new Carl();
        lama1.getKopfbedeckung();
        lama1.getName();
        System.out.println(lama1.kopfbedeckung);

        System.out.println("");
        Paul lama2 = new Carl();
        lama2.getKopfbedeckung();                         // Strickmütze --> Carl
        lama2.getName();                                  // Paul        --> Paul
        System.out.println(lama2.kopfbedeckung);          // Damenhut    --> Über Paul .kopfbedeckung zugreifen
        System.out.println(((Carl) lama2).kopfbedeckung); // Strickmütze --> Über Cals .kopfbedeckung
    }
}
