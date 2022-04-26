package testStuff.interfacetoabstract;

public class myClass extends abstractClassA {
    public static void main(String[] args) {
        myMethodB();
        myClass c = new myClass();
        System.out.println(interfacea.b);
    }

    public void myMethodA() {
        System.out.println("ratatata");
    }
}
