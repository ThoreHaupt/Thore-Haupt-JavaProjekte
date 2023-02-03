package CodingClasses.testStuff.Tut9;

class Steve extends NoWait {
    static {
        System.out.println(" Steve statischer Initialisierer ");
    }

    public Steve() {
        this(2);
        System.out.println(" Steve Konstruktor 1");
    }

    public Steve(int i) {
        super();
        if (i == 5) {
            System.out.println("5");
        }
        System.out.println(" Steve Konstruktor 2");
    }
}
