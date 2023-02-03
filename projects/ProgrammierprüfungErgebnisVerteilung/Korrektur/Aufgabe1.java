package Projects.ProgrammierprüfungErgebnisVerteilung.Korrektur;

public class Aufgabe1 {
    public static void main(String[] args) {
        /* Hier Variablen deklarieren und mit der letzten Ziffer der Matrikelnummer initialisieren! */
        byte a = 0;
        char b = 0;
        float c = 0;
        double d = 0;

        /* Hier die unten definierte Methode 'check' jeweils für alle Variablen aufrufen. */
        check(a);
        check(b);
        check(c);
        check(d);

    }

    /* Sie dürfen nur die Lücken der folgenden zwei Methoden ausfüllen und keine neuen Methoden definieren! */

    public static void check(byte d) {
        System.out.println("Das übergebene Argument ist KEINE Gleikommazahl.");
    }

    public static void check(double c) {
        System.out.println("Das übergebene Argument ist eine Gleikommazahl.");
    }
}