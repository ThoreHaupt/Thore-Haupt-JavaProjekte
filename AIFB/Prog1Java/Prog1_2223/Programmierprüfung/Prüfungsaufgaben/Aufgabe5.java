package AIFB.Prog1Java.Prog1_2223.Programmierprüfung.Prüfungsaufgaben;

public class Aufgabe5 {
    public static int quersumme(int i) {
        if (i < 10) {
            return i;
        } else {
            return i % 10 + quersumme(i / 10);
        }
    }

    public static void main(String[] args) {
        System.out.println(quersumme(2406824));
    }
}
