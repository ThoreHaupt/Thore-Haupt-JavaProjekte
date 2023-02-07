package RP.W5.W5A4;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

public class Telefonbuch {
    static HashMap<String, Entry> entriesName = new HashMap<String, Entry>();
    static HashMap<Integer, Entry> entriesID = new HashMap<Integer, Entry>();
    static boolean run = true;
    static Scanner scanner;
    static String loadsetting;
    static String path = "RP/W5/W5A4/telbook.txt";

    public static void main(String[] args) {
        init();
        while (run)
            runtime();
    }

    public static void init() {
        scanner = new Scanner(System.in);
        loadsetting = PracticeProjects.Filemanager.returnLineFromFile(path, 1);
        if (loadsetting.equals("++load Participants")){
            load(path);
        }
    }

    public static void runtime() {
        System.out.print("Telefonbuch comand >..");
        String[] input = scanner.nextLine().split(" ");

        switch (input[0]) {
            case "shutdown":
                shutdown();
                break;
            case "add":
                add(input);
                break;
            case "read":
                read(input);
                break;
            case "printbook":
                printTelephonebook();
                break;
            case "help":
                help();
                break;
            case "setLoad":
                setLoadsetting(input);
                break;
            default:
                System.out.println("This comand doesnt make any sense dude. Please repeat! \n");
        }

    }

    public static void shutdown() {
        run = false;
        scanner.close();
        save(path);
        System.out.println("Shutdown programm");
    }

    public static void add(String[] input) {
        if (input.length > 3) {
            try {
                new Entry(input[1], input[2], Integer.parseInt(input[3]));
                System.out.println("added "+ input[1] + input[2]);
            } catch (NumberFormatException e) {
                System.out.println("This telephonenumber format is invalid!");
            }

        }else{System.out.println("This format doesnt match the parameters for add");}

    }

    public static void read(String[] input) {

        if (input.length > 2) {
            if (entriesName.containsKey((input[1] + " " + input[2]))) {
                System.out.println(entriesName.get(input[1] + " " + input[2]));
            } else {
                System.out.println("This person doesnt exist.");
            }

        } else {
            System.out.println("This comand does not have the right parameters");
        }

    }

    public static void printTelephonebook() {
        System.out.println("Whole telephonebook: ");
        for (String key : entriesName.keySet()) {
            System.out.println(entriesName.get(key));
        }
    }

    public static void help() {
        String[][] help = new String[][] { 
            { "help", "", "prints all comands and parameters" },
            { "add", "Vorname Nachname Telfonnummer", "adds a new Person to the Book" },
            {"read", "Vorname Nachname", "returns information concerning this Person"}
        };
        String format = "%-" + 32 + "s%-" + 64 + "s%-" + 48 + "s";
        for (String[] strings : help) {
            System.out.println(String.format(format, strings[0], strings[1], strings[2]));
        }
    }

    public static void load(String path){
        if (PracticeProjects.Filemanager.getfilelength(path) <= 1) return;
        String[] loadedParticipants = PracticeProjects.Filemanager.getLinesFromFile(path, 2, PracticeProjects.Filemanager.getfilelength(path));
        for (int i = 0; i < loadedParticipants.length; i++) {
            new Entry(loadedParticipants[1], loadedParticipants[2], Integer.parseInt(loadedParticipants[3]));
        }
    }

    public static void save(String path){
        String[] file = PracticeProjects.Filemanager.getallLinesFromFile(path);
        List<String> filelist = (Arrays.asList(file));
        for (String key : entriesName.keySet()) {
            String entry = entriesName.get(key).toString().replace(":", "").replace(" ", "_");
            if (!filelist.contains(entry)){
                PracticeProjects.Filemanager.writeToFile(path, entry, true);
            }
        }
    }

    public static void setLoadsetting(String input[]){
        if (input.length > 1){
            if (input[1].equals("true")){
                PracticeProjects.Filemanager.writeToFileLine(path, 1, "++load Participants");
                System.out.println("changed setting to true");
            }else if (input[1].equals("false")){
                PracticeProjects.Filemanager.writeToFileLine(path, 1, "--load Participants");
                System.out.println("changed setting to false");
            }
        }else{
            System.out.println("Wrong parameter for setLoadsetting, use \"true\" or \"false\"");
        }
    }
}
