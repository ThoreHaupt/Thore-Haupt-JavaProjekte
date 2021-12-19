package RP.alteAufgaben.J1819;

import java.util.Random;

public class Schatz<T extends Number, G> {
    private T code;
    private G geheimnis;

    /* public static void main(String[] args) {
        Schatz<Integer,Boolean> s = new Schatz<>(1234, false);
        System.out.println(s.verraten(1235)); 
    } */
    
    public Schatz(T code, G geheimnis){
        this.code = code;
        this.geheimnis = geheimnis;
    }

    public <D> String verraten(D code){
        if (code.equals(this.code)){
            return geheimnis.toString();
        }else{
            String s = "";
            for (int i = 0; i < 1000; i++) {
                s += (char) (int) (Math.random()*50 + 70);
            }
            return s;
        }
    }
    
}
