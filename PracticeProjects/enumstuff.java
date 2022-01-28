package PracticeProjects;

public class enumstuff {
    enum Wochentage {
        Montag(1, 2),
        Diensatag(1, 2),
        Mittwoch(1, 2),
        Donnerstag(1, 2),
        Freitag(1, 2),
        Samstag(1, 2),
        Sonntag(1, 2);

        private double Stunden;
        private double Sekunden;

        Wochentage(double Stunden, double Sekunden) {
            this.Stunden = Stunden;
            this.Sekunden = Sekunden;
        }

        public double Stunden() {
            return this.Stunden;
        }

        public double Sekunden() {
            return this.Sekunden;
        }

    }

    int[] liste = { 1, 2, 3, 4, 5 };

    public static void main(String[] args) {
        System.out.println(Wochentage.values());

        for (Wochentage Tag : Wochentage.values()) {
            System.out.println("" + Tag + ": " + Tag.Stunden());
        }

    }
}
