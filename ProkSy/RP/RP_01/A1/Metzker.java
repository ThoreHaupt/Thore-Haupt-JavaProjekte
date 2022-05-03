package ProkSy.RP.RP_01.A1;

public class Metzker {
    private String name;
    private Wursttypen wursttyp;

    public Metzker(String name, Wursttypen wursttyp) {
        this.name = name;
        this.wursttyp = wursttyp;
    }

    @Override
    public String toString() {
        return "Die Spezialität von Metzker" + this.name + " ist " + wursttyp;
    }
}
