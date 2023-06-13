package AIFB.Prog1Java.Prog1_2223.RandomFiles;
public class Buchhandlung{

    Buch buch1;
    public Buchhandlung(Buch buch1){
        this.buch1 = buch1;
    }

    public static void main(String[] args){
        Buchhandlung bh = new Buchhandlung(new Buch());
    }

}

class Buch{
    String author;
}