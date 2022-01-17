package RP.W7;

public class Glück {
    public static void wetterglück(Bauer bauer){
        bauer.felder[(int) (Math.random() *4)].erntern();
    }

}
