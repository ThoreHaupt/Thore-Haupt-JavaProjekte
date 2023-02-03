package RP.W8.A6;

import java.util.ArrayList;

public final class Schwab_O_Mat {
    private static Schwab_O_Mat schwabOmat = new Schwab_O_Mat();
    private Schwab_O_Mat(){}
    public static Schwab_O_Mat getInstance(){
        return schwabOmat;
    }
    public void reduzieren(Guadschai s, Veschbr[] v){
        ArrayList<Veschbr> list = new ArrayList<Veschbr>();
        for (int i = 0; i < v.length; i++) {
            if(v[i].getClass().getInterfaces().length > 0 && v[i].getClass().getInterfaces()[0].getSimpleName() == "DesKoMerGinstigerMacha"){
                list.add( v[i]);
            } 
        }
        reduzieren(s, (Mauldascha[]) list.toArray());
    }

    public void reduzieren(Guadschai s, Mauldascha[] v){
        for (int i = 0; i < v.length; i++) {
            v[i].reduzieren(s);
        }
    }

    /* public static void main(String[] args) {
        Mauldascha m = new Mauldascha("ha", 0.442);
        System.out.println(m.getClass().getInterfaces()[0].getSimpleName());
    } */
}
