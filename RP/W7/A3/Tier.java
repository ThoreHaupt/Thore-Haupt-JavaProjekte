package RP.W7.A3;

public class Tier {
    public static void main(String[] args) {
        Tier t = new Tier();
        t.getAbstammung();
    }
    
    public String getAbstammung() {
        String upper = "Object";
        return "Tier --> " + upper;
    }

    public void fressen(){
        System.out.println(this + ": Tier, fressen");
    }

    public void gibLaut(){
        System.out.println(this + ": Tier, gibtLaut");
    }

    
}
