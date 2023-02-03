package RP.W7.A3;

public class Dobermann extends Hund {
    public String getAbstammung() {
        String upper = super.getAbstammung();
        return "Dobermann --> " + upper;
    }
}
