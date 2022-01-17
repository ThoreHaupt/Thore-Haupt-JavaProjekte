package RP.W7;

public class Erntezeit {
    public static void main(String[] args) {
        Bauer b1 = new Bauer("Hans");
        Bauer b2 = new Bauer("Karl");

        String[] sorten = {"Katoffel", "Hopfen","mais", "Gerste", "Hafer", "Karotte", "Zuckerrüben", "Kohl"};
        for (int i = 0; i < sorten.length; i++) {
            if (i < 4){
                b1.felder[i] = new Feld(i, sorten[i], (int) (10*Math.random()));
            }
            if (i > 3){
                b2.felder[i-4] = new Feld(i-4, sorten[i], (int) (10 * Math.random()));
            }
        }

    }
}
