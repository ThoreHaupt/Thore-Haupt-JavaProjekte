package RP.W7.A3;

public class LöweDemo {
    public static void main(String[] args) {
        Löwe l = new Löwe();
        l.gibLaut();
        l.fressen();
        l.saeugen();
    }

    public String getAbstammung() {
        String output = this.getClass().getName() + " -> ";
        return output + getAbstammung();
    }
}
