package RP.W7;

public class Charakter {
    private String name;
    private Schauspieler spieler;

    public Charakter(String name, Serie serie, Schauspieler Schauspieler){
        this.name = name;
        this.spieler = Schauspieler;
        serie.add(this);
    }

    //@Override
    public String toString(){
        return name + "-" + spieler.toString();
    }
}
