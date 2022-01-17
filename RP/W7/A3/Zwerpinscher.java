package RP.W7.A3;

public class Zwerpinscher extends Hund {
    public String getAbstammung() {
        String upper = super.getAbstammung();
        return "Zwerpinscher --> " + upper;
    }
}
