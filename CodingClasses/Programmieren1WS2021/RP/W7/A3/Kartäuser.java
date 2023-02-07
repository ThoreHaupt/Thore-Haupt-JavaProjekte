package RP.W7.A3;

public class Kartäuser extends Hauskatze {
    public String getAbstammung() {
        String upper = super.getAbstammung();
        return "Kartäuser --> " + upper;
    }
}
