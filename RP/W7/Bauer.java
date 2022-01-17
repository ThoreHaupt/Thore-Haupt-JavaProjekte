package RP.W7;

public class Bauer {
    Feld[] felder;
    public String name;

    public Bauer(String name){
        this.name = name;
        felder = new Feld[4];
    }

    @Override
    public String toString(){
        return this.name;
    }


}
