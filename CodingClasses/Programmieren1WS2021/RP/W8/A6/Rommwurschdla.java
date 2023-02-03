package RP.W8.A6;

public class Rommwurschdla{
    public static void main(String[] args) {
        Schwab_O_Mat sMat = Schwab_O_Mat.getInstance();
        
        int gutscheineAmount = 5;
        Guadschai[] gutscheine = new Guadschai[gutscheineAmount];
        for (int i = 0; i < gutscheineAmount; i++) {
            gutscheine[i] = new Guadschai();
        }

        int kaesspaetzleAmount = 12;
        Kaesspaetzle[] Käsespätzle = new Kaesspaetzle[kaesspaetzleAmount];
        for (int i = 0; i < Käsespätzle.length; i++) {
            Käsespätzle[i] = new Kaesspaetzle(MirZaehlad.runden(Math.random()*4 + 1, 2));
        }


        int mauldaschaAmount = 16;
        Mauldascha[] mauldascha = new Mauldascha[mauldaschaAmount];
        for (int i = 0; i < mauldascha.length; i++) {
            mauldascha[i] = new Mauldascha("Mauldascha", MirZaehlad.runden(Math.random()*4 + 10, 2));
        }

        Veschbr[] Speisen = new Veschbr[kaesspaetzleAmount + mauldaschaAmount];
        for (int i = 0; i < kaesspaetzleAmount; i++) {
            Speisen[i] = Käsespätzle[i];
        }
        for (int i = 0; i < mauldaschaAmount; i++) {
            Speisen[kaesspaetzleAmount + i] = mauldascha[i];
        }

        for (int i = 0; i < Speisen.length; i++) {
            System.out.println(Speisen[i]);
        }
        sMat.reduzieren(gutscheine[4], Speisen);

        for (int i = 0; i < Speisen.length; i++) {
            System.out.println(Speisen[i]);
        }

    }
}