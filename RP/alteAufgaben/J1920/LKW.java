package RP.alteAufgaben.J1920;

public class LKW {
    
    public static void main(String[] args) {
        LKW auto = new LKW();
        auto.geschwindigkeit = 120;
        auto.beschleunigen(380);
        //System.out.println(auto.geschwindigkeit);
    }
    
    
    int geschwindigkeit;
    public void beschleunigen(int delta){
        geschwindigkeit += delta;
    }
}
