package RP.W7.A3;

public class Wellensittich extends Vogel {
    public String getAbstammung() {
        String upper = super.getAbstammung();
        return "Wellensittich --> " + upper;
    }
}
