package RP.W8.A1;

public class Student {
    private String name; // Name des /der Studierenden
    private int alterInJahren; // Alter des/ der Studierenden in Jahren
    private int matrikelNr; // Matrikel - Nummer des/der Studierenden
    /**
     * @return the name
     */
    public String getName() {
        return name;
    }
    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }
    /**
     * @return the alterInJahren
     */
    public int getAlterInJahren() {
        return alterInJahren;
    }
    /**
     * @param alterInJahren the alterInJahren to set
     */
    public void setAlterInJahren(int alterInJahren) {
        this.alterInJahren = alterInJahren;
    }
    /**
     * @return the matrikelNr
     */
    public int getMatrikelNr() {
        return matrikelNr;
    }
    /**
     * @param matrikelNr the matrikelNr to set
     */
    public void setMatrikelNr(int matrikelNr) {
        this.matrikelNr = matrikelNr;
    }
}
