package RP.W7.A3;

public class myClass extends abstractClass {
    public void foo() {

    }

    public static void main(String[] args) {
        myClass c = new myClass();
        Luchs l = new Luchs();

        c.getAb();
        l.getAb();
    }

    public void getAb() {
        // System.out.println(this.getClass().getSuperclass()
        // .getSuperclass().getSuperclass().getSuperclass().getSuperclass().getSimpleName());
        // System.out.println(this.getClass().getName());
        Class<?> stufe = this.getClass();
        String output = "";
        while (stufe.getSuperclass().getSuperclass() != null) {
            output += stufe.getSimpleName() + " --> ";
            stufe = stufe.getSuperclass();
        }
        output += stufe.getSuperclass().getSimpleName();
        System.out.println(output);
    }
}
