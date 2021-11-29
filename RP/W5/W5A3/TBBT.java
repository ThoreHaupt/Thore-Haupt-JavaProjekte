package RP.W5.W5A3;

public class TBBT {
    public static void main(String[] args) {
        Sheldon sheldon = new Sheldon();
        System.out.println("Sheldon: " + sheldon.sagt1);
        Lenard.sagt = "Das ist die Definition eines Klugscheißers!";
        System.out.println("Leanard: " + Lenard.sagt);
        sheldon.sagt1 = sheldon.sagt2;
        System.out.println("Sheldon: " + sheldon.sagt1);
        System.out.println("Penny: " + Penny.augenroll);
    }
}
