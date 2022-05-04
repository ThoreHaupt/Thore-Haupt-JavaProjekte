package ProkSy.RP.RP_01.A2;

public class WerBinIch_Class<T> {
    public WerBinIch_Class(T var) {
        dasBinIch(var);
    }

    public void dasBinIch(T var) {
        System.out.println("Ich bin vom Datentyp " + var.getClass());
    }
}
