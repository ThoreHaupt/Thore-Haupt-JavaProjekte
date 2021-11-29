package RP.W5.W5A4;

import java.util.HashMap;
import java.util.Scanner;

public class Telefonbuch {
    static HashMap<String, Entry> entriesName = new HashMap<String, Entry>();
    static HashMap<Integer, Entry> entriesID = new HashMap<Integer, Entry>();
    static boolean run = true;
    static Scanner scanner;
    
    public static void main(String[] args) {
        init();
        while(run)runtime();
    }
    public static void init(){
        scanner = new Scanner(System.in);
    }

    public static void runtime(){
        System.out.print("Telefonbuch comand >..");
        String[] input = scanner.nextLine().split(" ");
        
        switch (input[0]){
            case "shutdown" -> shutdown();
            case "add" -> add(input);
            case "read" -> read(input);
            case "printbook" -> printTelephonebook();
            default -> System.out.println("This comand doesnt make any sense dude. Please repeat! \n");
        }

    }

    public static void shutdown(){
        run = false;
        scanner.close();
        System.out.println("Shutdown programm");
    }
    
    
    public static void add(String[] input){
        if (input.length > 3){
            try {
                new Entry(input[1], input[2], Integer.parseInt(input[3]));
            } catch (NumberFormatException e) {
                System.out.println("This telephonenumber format is invalid!");
            }
            
        }
        
    }

    public static void read(String[] input) {
        
        if (input.length > 2) {
            if (entriesName.containsKey((input[1] + " "  + input[2]))){
                System.out.println(entriesName.get(input[1] + " " + input[2]));
            }
            else{
                System.out.println("This person doesnt exist.");
            }
            
        }else{
            System.out.println("This comand does not have the right parameters");
        }

    }

    public static void printTelephonebook(){
        System.out.println("Whole telephonebook: ");
        for (String key : entriesName.keySet()) {
            System.out.println(entriesName.get(key));
        }
    }
}
