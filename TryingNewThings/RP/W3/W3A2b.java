package TryingNewThings.RP.W3;
import java.util.Scanner;

public class W3A2b{

    public static void main(String[] args){
        Scanner scanner = new Scanner(System.in);

        System.out.println("bitte unteres Limit eingeben: ");
        int limitlower = scanner.nextInt();
        System.out.println("bitte oberes Limit eingeben: ");
        int limitupper = scanner.nextInt();
        System.out.println("bitte magische Zahl eingeben: ");
        int magicNum = scanner.nextInt();

        for (int i = 0; i<100; i++){
            System.out.println("");
        }

        System.out.println("Die magische Zahl liegt im Intervall: ["+limitlower+","+limitupper+"]");
        
        System.out.println("raten sie die magische Zahl! ");
        int guess = scanner.nextInt();

        if (guess != magicNum){
            if (magicNum < guess){System.out.println("Ihre Zahl war über der magischen Zahl");}
            if (magicNum > guess){System.out.println("Ihre Zahl war unter der magischen Zahl");}
            System.out.println("raten sie die magische Zahl, sie haben einen zweiten Versuch! ");
            guess = scanner.nextInt();
            
            if (guess != magicNum){
                System.out.println("hahahaha verloren");
            }else{
                System.out.println("gz das war richtig");
            }

        }else  {
            System.out.println("gz das war richtig");
        }

        scanner.close();
    }
}