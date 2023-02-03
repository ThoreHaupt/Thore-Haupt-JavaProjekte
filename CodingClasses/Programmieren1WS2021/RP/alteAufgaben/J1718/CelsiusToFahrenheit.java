package RP.alteAufgaben.J1718;

public class CelsiusToFahrenheit{
    public static void main(String[] args) {
        CelsiusToFahrenheit c = new CelsiusToFahrenheit();
        double[] feld = {32.1,24.0};
        feld = c.converter(feld);
        for (double d : feld) {
            System.out.println(d);
        }
    }

    public double[] converter(double[] inputfeld){
        double[] outputFeld = new double[inputfeld.length];
        for (int i = 0; i < inputfeld.length; i++) {
            outputFeld[i] = 32.0 + inputfeld[i] * 1.8;
        }
        return outputFeld;
    }
}