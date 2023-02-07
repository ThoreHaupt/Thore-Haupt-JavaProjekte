package RP.W6.A2;

public class Teig {
    private boolean geknetet = false;

    public Teig(){
        
    }

    public boolean isGeknetet() {
        return geknetet;
    }

    public void setGeknetet(boolean geknetet) {
        this.geknetet = geknetet;
    }

    public void zutatenhinzufügen(Zutat z1){
        System.out.println("zutat hinzugefügt: " + z1.getName() + "( " + z1.getWeightGramm() +")");
    }

    public void kneten(){
        geknetet = true;
        System.out.println("Teig geknetet");
    }
}
