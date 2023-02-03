package RP.W7.A3;

public class Tier {
    public static void main(String[] args) {
        Tier t = new Tier();

        System.out.println(t.getAbstammung());
        t.getAb();
    }

    public String getAbstammung() {
        String upper = "Object";
        return "Tier" + " --> " + upper;
    }

    public void fressen() {
        System.out.println(this + ": Tier, fressen");
    }

    public void gibLaut() {
        System.out.println(this + ": Tier, gibtLaut");
    }

    public void getAb() {
        //System.out.println(this.getClass().getSuperclass()
        //.getSuperclass().getSuperclass().getSuperclass().getSuperclass().getSimpleName());
        //System.out.println(this.getClass().getName());
        Class<?> stufe = this.getClass();
        String output = "";
        output += stufe.getSimpleName() + " --> ";
        while (stufe.getSuperclass().getSuperclass() != null) {
            stufe = stufe.getSuperclass();
            output += stufe.getSimpleName() + " --> ";
        }
        output += stufe.getSuperclass().getSimpleName();
        System.out.println(output);
    }

}
