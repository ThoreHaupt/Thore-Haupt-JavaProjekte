package RP.W7.A3;

public class Säugetier extends Tier {
    public void saeugen(){
        System.out.println(this + ": Säugetier, saeugen");
    }

    public static void main(String[] args) {
        Säugetier s = new Säugetier();
        System.out.println(s.getAbstammung()); 
    }
    
    public String getAbstammung() {
        String upper = super.getAbstammung();
        return "Säugetier --> " + upper;
    }
}
