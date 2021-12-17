package RP;

public class Test {
    public static void main(String[] args) {
        
        int a = 1;
        int b = 2;
        int c = 3;
        
        int[] feld = {a,b,c};

        for (int i = 0; i < feld.length; i++) {
            System.out.println("Index " + i + " = " + feld[i]);
        }

        System.out.println("Variablen ändern und neu ausdrucken");
        a = 4;
        b = 5;
        c = 6;

        for (int i = 0; i < feld.length; i++) {
            System.out.println("Index " + i + " = " + feld[i]);
        }
    }
}
