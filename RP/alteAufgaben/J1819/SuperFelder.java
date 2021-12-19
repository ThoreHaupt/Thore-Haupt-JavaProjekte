package RP.alteAufgaben.J1819;

public class SuperFelder {
    public static void main(String[] args) {
        SuperFelder s = new SuperFelder();
        double[] feld = {1.0,2.0,5.42323};
        s.druckeFeld(feld);
    }
    
    public void druckeFeld(double[] inputfeld){
        String outputString = "(";
        for (int i = 0; i < inputfeld.length; i++) {
            if(i<inputfeld.length -1 ){ 
                outputString += inputfeld[i] + " - ";
            }else{
                outputString += inputfeld[i];
            }
        } 
        outputString += ")";
        System.out.println(outputString);
    }
}
