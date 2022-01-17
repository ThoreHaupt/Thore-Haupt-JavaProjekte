package RP.W7.A3;

public class Katze extends Säugetier{
    public String getAbstammung() {
        String upper = super.getAbstammung();
        return "Katze --> " + upper;
    }
    
}
