package testStuff;

import java.text.DecimalFormat;

public class FormattsTest {
    public static void main(String[] args) {
        double d = 1231123.12312;
        DecimalFormat f1 = new DecimalFormat("Wert = 000,000,000.00000000");
        DecimalFormat f2 = new DecimalFormat("Wert = ###,###,###.########");
        System.out.println(f1.format(d));
        System.out.println(f2.format(d));
    }
}
