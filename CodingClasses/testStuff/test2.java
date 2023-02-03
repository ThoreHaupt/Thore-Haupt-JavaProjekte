package CodingClasses.testStuff;

public class test2 {
    public static void doSomething(int[] feld) {
        while (feld.length > 0)
            doSomethingOther(feld);
    }

    public static void doSomethingOther(int[] feld) {
        int[] args = new int[feld.length - 1];
        System.out.println(feld.length);
        for (int i = 0; i < args.length; i++) {
            args[i] = feld[i] - 3;
        }
        doSomething(args);
    }

    public static void main(String[] args) {
        doSomething(new int[] { 1, 2, 3 });
    }
}