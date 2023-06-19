package RP.W7.A3;

public class Hauskatze extends Katze {

    public Hauskatze() {
        this("s");
        System.out.println("hallo");
        System.out.println("hallo");
    }

    public Hauskatze(String s) {
        super();
        Double d = new Double(1);
        long l = 12;

    }

    @Override
    public void gibLaut() {
        System.out.println(this + ": Hauskatze, Miaut");
    }

    public String getAbstammung() {
        String upper = super.getAbstammung();
        return "Hauskatze --> " + upper;
    }
}
