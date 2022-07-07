package ProkSy.RP.RP_006.RP_A002;

public class Sportplatz {
    public static void main(String[] args) {
        int l = 5;
        Sprinter[] arr = new Sprinter[l];
        for (int i = 0; i < l; i++) {
            arr[i] = new Sprinter(i + 1);
        }
        for (int i = 0; i < arr.length; i++) {
            arr[i].start();
            try {
                arr[i].join();
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }
}
