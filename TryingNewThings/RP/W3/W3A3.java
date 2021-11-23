package TryingNewThings.RP.W3;

import java.util.Scanner;

public class W3A3{

    public static void main(String[] args){

        Scanner scanner = new Scanner(System.in);

        int input = scanner.nextInt();

        scanner.close();
        
        int i = 0;
        int result = 1;
        while(i < input){
            i++; 
            result *= i; 
        }
        System.out.println(result);
        
        
        result = 1;
        i = 0;
        do{
            i++;
            result *= i;
        }while(i < input);
        System.out.println(result);
        
        
        result = 1;
        for(i = 1; i <= input; i++){
            result *= i;
        }
        System.out.println(result);

        
    }
}