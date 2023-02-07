package CodingClasses.ProkSy.Tutorium.T_01.A1;

public class Schrank extends Möbel implements Entsorgbar, Bewegbar {
    public double calcualteVolume() {
        return breite * höhe * tiefe;
    }

}
