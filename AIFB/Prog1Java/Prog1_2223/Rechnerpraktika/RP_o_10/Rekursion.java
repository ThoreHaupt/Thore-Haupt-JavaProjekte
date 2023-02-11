package AIFB.Prog1Java.Prog1_2223.Rechnerpraktika.RP_o_10;

public class Rekursion {
    public static int methode(int x, int y) {
        System.out.println(x + " // " + y);
        int z;
        if (x == 2) {
            z = 3;
            System.out.println(z);
            return z;
        }
        if (y % 2 == 0) {
            z = methode(x - 2, y + 1) + y;
        } else {
            z = methode(x - 2, y - 1) + 2;
        }
        System.out.println(z);
        return z;
    }

    public static void main(String[] args) {
        System.out.println(" methode (8 , 5) = "
                + methode(6, 9));
    }
}