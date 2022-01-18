package RP.W8.A6;

public class Veschbr {
    private double preis;
    private String name;

    public Veschbr(String name, double preis){
        this.name = name;
        this.preis = preis;
    }

    /**
     * @return the preis
     */
    public double getPreis() {
        return preis;
    }

    /**
     * @param preis the preis to set
     */
    public void setPreis(double preis) {
        this.preis = preis;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    public String toString(){
        return name + ", Preis: " + preis;
    }
}
