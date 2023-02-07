package CodingClasses.ProkSy.Tutorium.T_01.A1;

public interface Entsorgbar {
    public default void entsorgen() {
        System.out.println("hallo");
    }

    public static String terminabragen() {
        return "heute";
    }
}
