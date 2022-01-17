package RP.W7.A3;

public class Löwe extends Wildkatze {
    @Override
    public void gibLaut() {
        System.out.println(this + ": Löwe, brüllt");
    }

    @Override 
    public String toString(){
        return "Löwe";
    }

    public String getAbstammung() {
        String upper = super.getAbstammung();
        return "Löwe --> " + upper;
    }
}
