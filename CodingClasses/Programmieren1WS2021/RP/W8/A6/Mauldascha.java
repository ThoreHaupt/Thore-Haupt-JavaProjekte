package RP.W8.A6;

public class Mauldascha extends Veschbr implements DesKoMerGinstigerMacha{
    private boolean isReduziert = false;

    public Mauldascha(String name, double preis){
        super(name, preis);
    }

    public String toString(){
        return super.toString() + (isReduziert?" (reduziert) ":"") ;
    }

    @Override
    public void reduzieren(Guadschai var) {
        double newPreis = (super.getPreis() * (1-var.getRabatt()));
        if(!isReduziert) super.setPreis(newPreis);
    }
}
