package RP.W8.A1;

public class Studiverwaltung {
    public static void main(String[] args) {
        Student Jan = new Student();
        Jan.setAlterInJahren(9);
        Jan.setMatrikelNr(123445678);
        Jan.setName("Jan");
        System.out.println(Jan.getAlterInJahren() + Jan.getName() + Jan.getMatrikelNr());
    }
}
