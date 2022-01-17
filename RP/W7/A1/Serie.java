package RP.W7.A1;

import java.io.OutputStream;

public class Serie {
    String name;
    int anzahlCast;
    int castZaehler = 0;
    Charakter[] cast;

    public Serie(String name, int anzahlCast){
        this.name = name;
        this.anzahlCast = anzahlCast;
        cast = new Charakter[anzahlCast];
    }

    public void add(Charakter character){
        cast[castZaehler] = character;
        castZaehler++;
    }

    @Override
    public String toString(){
        return name + ": " + generateCastListe();
    }

    private String generateCastListe() {
        String outputString = "";
        for (int i = 0; i < cast.length; i++) {
            if(cast[i] != null) outputString += cast[i];
            if (i < cast.length - 1) outputString += " | ";
        }
        
        return outputString;
    }
}
