package testStuff.Tut9;

public class Alan extends Steve {
    static NoWait alan = new NoWait();
    public Steve murmeltier = new Steve(5);

    public Alan() {
        System.out.println(" Alan Konstruktor ");
    }

    public static void main(String[] args) {
        new Alan(); 
    }
}