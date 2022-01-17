package RP.W7.A3;

public class Hauskatze extends Katze {
    @Override
    public void gibLaut() {
        System.out.println(this + ": Hauskatze, Miaut");
    }

    public String getAbstammung() {
        String upper = super.getAbstammung();
        return "Hauskatze --> " + upper;
    }
}
