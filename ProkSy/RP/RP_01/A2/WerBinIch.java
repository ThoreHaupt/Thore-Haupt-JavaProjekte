package ProkSy.RP.RP_01.A2;

public class WerBinIch {
    public <T> WerBinIch(T var) {
        dasBinIch(var);
    }

    public <T> void dasBinIch(T var) {
        System.out.println("Ich bin vom Datentyp " + var.getClass());
    }
}
