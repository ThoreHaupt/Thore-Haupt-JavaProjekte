package ProkSy.RP.RP_002.P2_2;

public class Punkt implements Comparable<Punkt> {
    double x;
    double y;

    public Punkt(double x, double y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public int compareTo(Punkt o) {
        if (o == null) {
            throw new NullPointerException();
        }
        return (x < o.x) ? -1 : 1;
    }

    @Override
    public String toString() {
        return "(" + this.x + ", " + this.y + ")";
    }

}
