package RP.W6.A3;

public class IterateTriangle {
    public static void main(String[] args) {
        druckeDreieckRekursiv (5);
    }
    
    public static String druckeDreieckRekursiv (int a ) {
        if( a == 1 ) {
            System . out . println ("*") ;
            return "* ";
        }else {
            String s = "* " + druckeDreieckRekursiv(a - 1);
            System . out . println ( s ) ;
            return s ;
        }   
    }
}
