package CodingClasses.ProkSy.RP.RP_01.A1;

public class Metzger {
    private String name;
    private Wursttypen wursttyp;

    public Metzger(String name, Wursttypen wursttyp) {
        this.name = name;
        this.wursttyp = wursttyp;
    }

    @Override
    public String toString() {
        return "Die Spezialität von Metzker" + this.name + " ist " + wursttyp;
    }
}
