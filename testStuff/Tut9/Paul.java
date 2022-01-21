package testStuff.Tut9;

class Paul {
    public String kopfbedeckung = " Damenhut ";

    public void getKopfbedeckung() {
        System.out.println(kopfbedeckung + " kommt aus PAul");
        System.out.println(this.getClass().getSimpleName());
        
    }

    public static void getName() {
        System.out.println(" Paul " + " kommt aus Paul");
    }
}