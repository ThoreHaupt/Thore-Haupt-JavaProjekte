package RP.W7.A3;

public class Zebra extends Säugetier {
    public String getAbstammung() {
        String upper = super.getAbstammung();
        return "Zebra --> " + upper;
    }
}
