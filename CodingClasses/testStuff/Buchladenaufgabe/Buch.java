package CodingClasses.testStuff.Buchladenaufgabe;

public class Buch {
    public String titel;
    public Author author;
    public String genre;
    public double preis;

    /**
     * @param titel
     * @param author
     * @param genre
     * @param preis
     */
    public Buch(String titel, Author author, String genre, double preis) {
        this.titel = titel;
        this.author = author;
        this.genre = genre;
        this.preis = preis;
    }

}
