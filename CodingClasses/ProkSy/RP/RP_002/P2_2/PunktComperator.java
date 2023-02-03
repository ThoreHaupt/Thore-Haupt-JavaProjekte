package CodingClasses.ProkSy.RP.RP_002.P2_2;

import java.util.Comparator;

public class PunktComperator implements Comparator<Punkt> {

    @Override
    public int compare(Punkt o1, Punkt o2) {
        return (o1.y < o2.y) ? -1 : 1;
    }

}
