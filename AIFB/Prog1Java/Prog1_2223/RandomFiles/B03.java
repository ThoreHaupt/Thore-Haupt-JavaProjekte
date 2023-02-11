package AIFB.Prog1Java.Prog1_2223.RandomFiles;

public class B03 {
    public static void main(String[] args) {
        System.out.println(methode2(6, 3));
        System.out.println(rekursiv2(6, 3));

        System.out.println(stringRekursion("hallo"));
    }

    public static int methode1(int i) {
        System.out.println(i);
        if (i > 0) {
            return methode1(i - 1);
        }
        return 0;
    }

    public static int iterativ1(int i) {
        int sum = 0;
        for (; i > 0; i--) {
            System.out.println(i);
            sum += i;
        }
        return sum;
    }

    public static int methode2(int a, int b) {
        int erg = 1;
        while (a >= b) {
            erg *= a * (a - 2);
            a--;
        }
        return erg;
    }

    public static int rekursiv2(int a, int b) {
        if (a > b) {
            return rekursiv2(a, b + (a - b) / 2 + 1) * rekursiv2(b + (a - b) / 2, b);
        } else {
            return a * (a - 2);
        }
    }

    public static int rek3(int a, int b) {
        if (a < b) {
            return a * rek3(a + 1, b);
        } else {
            return b;
        }
    }

    public static String stringRekursion(String s) {
        if (s.length() > 0)
            return s + stringRekursion(s.substring(0, s.length() - 1));
        else
            return s;
    }

}
