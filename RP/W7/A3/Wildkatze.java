package RP.W7.A3;

public class Wildkatze extends Katze {
    @Override
    public void gibLaut() {
        System.out.println(this + ": Wildkatze, faucht");
    }

    public String getAbstammung() {
        String upper = super.getAbstammung();
        return "Wildkatze --> " + upper;
    }
}
