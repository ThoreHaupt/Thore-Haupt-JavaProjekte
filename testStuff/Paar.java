package testStuff;

public class Paar<T, S> {

    T links;
    S rechts;

    public Paar(T links, S rechts) {
        this.links = links;
        this.rechts = rechts;
    }

    public T getL() {
        return links;
    }

    public S getR() {
        return rechts;
    }

    @Override
    public String toString() {
        return "(l,r) = (" + links + "," + rechts + ")";
    }
}
