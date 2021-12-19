package RP.alteAufgaben.J1920;

public class A3 {
    public static void main(String[] args) {
        for (int i = 51; i < 74; i++) {
            if(i%2==1)System.out.println(i);
        }
        A3 a = new A3();
        char[] b = a.drehen(new char[]{'a','b','c'});
        for (char c : b) {
            System.out.println(c);
        }
    }

    public char[] drehen(char[] feld){
        char[] neuesFeld = new char[feld.length];
        for (int i = 0; i < feld.length; i++) {
            neuesFeld[feld.length-i-1] = feld[i];
        }
        return neuesFeld;
    }   
}

