package RP.W6.A2;

public class Weihnachtsbaeckerei {
    
    public static void main(String[] args) {
        Zutat mehl = new Zutat("Mehl", 200);
        Zutat zucker = new Zutat("Zucker", 100);
        Zutat eier = new Zutat("Eier", 150);
        
        Teig teig = new Teig();
        teig.zutatenhinzufügen(mehl);
        teig.zutatenhinzufügen(zucker);
        teig.zutatenhinzufügen(eier);

        teig.kneten();
        if (Ofen.backen(teig, 180)){
            System.out.println("Hmm lecker");
        };
    }
    
    
}
