package RP.W7.A3;

public class Tier {
    public static void main(String[] args) {
        Tier t = new Tier();
        t.getAbstammung();
    }
    
    public String getAbstammung() {
        String upper = "Object";
        return this.getClass().getSimpleName() + " --> " + upper;
    }

    public void fressen(){
        System.out.println(this + ": Tier, fressen");
    }

    public void gibLaut(){
        System.out.println(this + ": Tier, gibtLaut");
    }

    public void getAb() {
        //System.out.println(this.getClass().getSuperclass()
                //.getSuperclass().getSuperclass().getSuperclass().getSuperclass().getSimpleName());
        //System.out.println(this.getClass().getName());
        Class<?> stufe = this.getClass();
        String output = "";
        while(stufe.getSuperclass().getSuperclass() != null){
            output += stufe.getSimpleName() + " --> ";
            stufe = stufe.getSuperclass();
        }
        output += stufe.getSuperclass().getSimpleName();
        System.out.println(output);
    }
    
}
