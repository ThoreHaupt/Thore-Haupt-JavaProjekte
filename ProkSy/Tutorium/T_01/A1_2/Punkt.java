package ProkSy.Tutorium.T_01.A1_2;

import java.util.Arrays;

import Commons.CalulationTools.SupportingCalculations;

public class Punkt implements Comparable<Punkt> {
    public double x;
    public double y;

    public Punkt(double x, double y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public boolean equals(Object p2) {
        return (p2 instanceof Punkt) ? (x == ((Punkt) p2).x) && (y == ((Punkt) p2).y) : false;

        /*
         * if (p2 instanceof Punkt) {
         * return (x == p2.x) && (y == p2.y);
         * }
         */
    }

    public static void main(String[] args) {
        Punkt p = new Punkt(1, 2);
        Punkt d = new Punkt(1, 3);
        Punkt[] l = new Punkt[6];
        for (int i = 0; i < l.length; i++) {
            l[i] = new Punkt((Math.random() * 100 + 1), (Math.random() * 100 + 1));
        }

        System.out.println(Arrays.toString(l));
        Arrays.sort(l);
        System.out.println(Arrays.toString(l));
    }

    public double calcualteDistance() {
        return Math.sqrt(x * x + y * y);
    }

    public int compareTo(Punkt o) {

        if (o == null) {
            throw new NullPointerException();
        }

        if (this.getClass() != o.getClass()) {
            throw new ClassCastException();
        }

        if (o == this) {
            return 0;
        }

        double t_l = this.calcualteDistance();
        double o_l = ((Punkt) o).calcualteDistance();

        if (t_l > o_l)
            return 1;

        if (t_l < o_l)
            return -1;

        return 0;
    }

    @Override
    public String toString() {
        return "Punkt: (" + SupportingCalculations.round(x, 2) + ":" + SupportingCalculations.round(y, 2) + ")["
                + SupportingCalculations.round(this.calcualteDistance(), 3) + "]";
    }

    /*
     * @Override
     * public int compareTo(Punkt o) {
     * 
     * return 0;
     * }
     */
}
