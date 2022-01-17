package RP.W7;

public class Feld {
    public int nummer;
    public String sorte;
    private boolean istGeerntet = false;
    private int ernteMenge;
    
    
    /**
     * @return the istGeerntet
     */
    public boolean isIstGeerntet() {
        return istGeerntet;
    }



    /**
     * @return the ernteMenge
     */
    public int getErnteMenge() {
        return ernteMenge;
    }

    public String erntern(){
        istGeerntet = true;
        int hilfevar = ernteMenge;
        ernteMenge = 0;
        return sorte + ": " + hilfevar;
    }


    /**
     * @param nummer
     * @param sorte
     * @param ernteMenge
     */
    public Feld(int nummer, String sorte, int ernteMenge) {
        this.nummer = nummer;
        this.sorte = sorte;
        this.ernteMenge = ernteMenge;
    }

    @Override
    public String toString(){
        String outputString = "";
        if (istGeerntet){
            outputString += (sorte + ": " + ernteMenge);
        }else{
            outputString = "Dieses Feld wurde bereits geerntet";
        }
        return outputString;

    }


    
}
