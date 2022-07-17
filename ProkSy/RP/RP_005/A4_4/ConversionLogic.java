package ProkSy.RP.RP_005.A4_4;

import java.util.HashMap;
import java.util.TreeSet;

public class ConversionLogic {

    static String[] units = { "m", "cm", "mm" };

    // static HashMap<String, Double> conversionFactors = new HashMap<String,
    // Double>();

    Umrechner fenster;

    double[][] lookUpTable = { { 1, 0.01, 0.001 }, { 100, 1, 0.1 }, { 1000, 10, 1 } };

    int indeces1 = 0;
    int indeces2 = 0;

    double value;

    public ConversionLogic() {
        TreeSet<Integer> arr = new TreeSet<>();
    }

    public String[] getImplementedUnits() {
        return units;
    }

    public void setValue(double value) {
        this.value = value;
    }

    public void setConversionIndeces(int selectedIndex1, int selectedIndex2) {
        indeces1 = selectedIndex1;
        indeces2 = selectedIndex2;
    }

    public void calculate(boolean isLeftInput) {

        double result = (!isLeftInput ? lookUpTable[indeces1][indeces2] : lookUpTable[indeces2][indeces1]) * value;
        if (isLeftInput)
            fenster.setRightField(result);
        else
            fenster.setLeftField(result);
    }

    public void setFrame(Umrechner umrechner) {
        fenster = umrechner;
    }

}
