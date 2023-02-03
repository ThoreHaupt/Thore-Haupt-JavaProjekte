package CodingClasses.testStuff;

public class generic {

    public static void main(String[] args) {
        Paar<? extends Subklasse, ?> t1 = new Paar(new Subklasse(), new test3());
        System.out.println(method1(t1));
    }

    public static String method1(Paar<? extends Subklasse, ?> inp) {
        return inp.toString();
    }

}
