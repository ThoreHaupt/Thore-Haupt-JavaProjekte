package RP.W7.A3;

public class Luchs extends Wildkatze {
    public String getAbstammung() {
        String upper = super.getAbstammung();
        return "Luchs --> " + upper;
    }
}
