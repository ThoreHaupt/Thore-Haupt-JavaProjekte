package RP.W7.A3;

public class Hund extends Säugetier{
    public String getAbstammung() {
        String upper = super.getAbstammung();
        return "Hund --> " + upper;
    }
}
