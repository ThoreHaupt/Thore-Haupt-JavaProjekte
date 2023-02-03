package RP.W6.A2;

public class Ofen {
    
    public static boolean backen(Teig teig, int backtemperatur){
        return (teig.isGeknetet() && backtemperatur <= 200)? true: false;
    }
}
