/**
 * Diese Klassen repräsentiert eine(n) Studierende(n)
 * 
 * @author Prog1-Team
 * @version 1.0
 */
public class Student {
    private String name; // Name des/der Studierenden
    private int alterInJahren; // Alter des/der Studierenden in Jahren
    private int matrikelNr; // Matrikel-Nummer des/der Studierenden

    /**
     * Getter-Methode zum Auslesen des Namens
     * 
     * @return Name des/der Studierenden
     */
    public String getName() {
        return name;
    }

    /**
     * Setter-Methode zum Setzen des Namens
     * 
     * @param name zu setzender Name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Getter-Methode zum Auslesen des Alters
     * 
     * @return Alter des/der Studierenden in Jahren
     */
    public int getAlterInJahren() {
        return alterInJahren;
    }

    /**
     * Setter-Methode zum Setzen des Alters
     * 
     * @param alter zu setzendes Alter
     */
    public void setAlterInJahren(int alter) {
        this.alterInJahren = alter;
    }

    /**
     * Getter-Methode zum Auslesen der Matrikel-Nummer
     * 
     * @return Matrikel-Nummer des/der Studierenden
     */
    public int getMatrikelNr() {
        return matrikelNr;
    }

    /**
     * Setter-Methode zum Setzen der Matrikel-Nummer
     * 
     * @param matrikelNr zu setzende Matrikel-Nummer
     */
    public void setMatrikelNr(int matrikelNr) {
        this.matrikelNr = matrikelNr;
    }
}
