package AIFB.Prog1Java.Prog1_2223.Rechnerpraktika.RP9.DavidLösung2023;

/**
 * Diese Klassen testet die Klasse Warteschlange
 * 
 * @author Prog1-Team
 * @version 1.0
 */
public class Studiverwaltung {

    /**
     * main-Methode des Programms
     * 
     * @param args Kommandozeilenparameter
     */
    public static void main(String[] args) {
        Warteschlange warteschlange = new Warteschlange();

        // Studierende stellen sich in der Warteschlange an

        Warteschlange.Wartende tauschPartner = null;

        for (int i = 0; i < 10; i++) {
            Student studi = new Student();
            studi.setMatrikelNr(1000000 + i);

            System.out.println("Student(in) mit der Matrikelnummer " + studi.getMatrikelNr()
                    + " hat sich in der Warteschlange angestellt.");

            Warteschlange.Wartende wartende = warteschlange.addFirst(studi);

            if (i == 5)
                tauschPartner = wartende;
            System.out.println(warteschlange.toString());
        }

        // Studierende tauschen
        Student newStudi = new Student();
        newStudi.setMatrikelNr(9999999);
        warteschlange.studiTauschen(newStudi, tauschPartner);

        System.out.println(warteschlange.toString());

        // Studierende werden aufgerufen

        for (int i = 0; i < 10; i++) {
            warteschlange.studiAufrufen();
            System.out.println(warteschlange.toString());
        }
    }
}
