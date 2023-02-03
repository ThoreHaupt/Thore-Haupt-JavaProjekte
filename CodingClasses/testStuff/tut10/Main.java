package CodingClasses.testStuff.tut10;

public class Main {
    int d;

    public static void main(String[] args) {
        Main m = new Main(2);
        int i = 96;
        char d = (char) i;
        int i2 = d = 97;
        System.out.println(d);
        System.out.println(i2);

        int[] a = new int[] { 'a', 'b', 'c' };
        int[] b = new int[3];
        b = a;

    }

    Main(int i) {
        d = i;
    }

    void myMethod(int a, String b) {
        " ".length();
        return;
    }

    public String toJadenCase(String phrase) {
        // TODO put your code below this comment
        if (phrase == null || phrase.equals(""))
            return null;
        String[] words = phrase.split(" ");
        for (int i = 0; i < words.length; i++) {
            // words[i] = +words[i].substring(1, words[i].length());
        }

        return String.join(" ", words);
    }

    void myMethod(String a, int b) {
        /*
         * byte by = 2;
         * char c = by;
         * short sh = c;
         * int in = c;
         * int i2 = 2L;
         */
        Object ba = 123f;
        int i = 3;
        Float f = (float) i;
        String s = 3 + " hallp";
        System.out.println(s);
        Math.ceil(123d);
        // String.valueOf(c)
    }

}