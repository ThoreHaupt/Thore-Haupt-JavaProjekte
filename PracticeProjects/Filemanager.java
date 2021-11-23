package PracticeProjects;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * methods to use files more easily 
 * static
*/
public class Filemanager {

    /** 
     * returns the String of a line in that txt file.
     * 
     * @param path filepath, hast to be a .txt file
     * @param line line to be read in txt file, returns whole line as a string
     * @return the String of that line
     */
    public static String returnLineFromFile(String path, int line) {

        String returnString = "";
        int currentline = 1;

        try {
            File file = new File(path);
            Scanner filereader = new Scanner(file);

            while (filereader.hasNextLine() && currentline < line) {
                filereader.nextLine();
                currentline++;
            }

            if (filereader.hasNextLine()) {
                returnString = filereader.nextLine();
            } else {
                System.out.println("Error, file does not have this many lines. \n returned an empty String");
            }

            filereader.close();

        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
        return returnString;
    }

    /**
     * returns an array of the Strings. Strings are copied from the file lines with the given indices
     * @param path      filepath
     * @param lines     Array of the lineindexes to be read
     * @return
     */
    public static String[] getLinesFromFile(String path, int[] lines) {

        String[] returnlines = new String[lines.length];

        ArrayList<Integer> linesAL = new ArrayList<Integer>();
        ArrayList<Integer> linestemp = new ArrayList<Integer>();;
        ArrayList<int[]> multilines = new ArrayList<int[]>();;
        for (int line : lines) {
            linesAL.add(line);
        }
        linesAL.sort(null);
        int prevint = linesAL.get(0);
        linestemp.add(linesAL.get(0));
        for (int i = 1; i < linesAL.size(); i++) {
            if (prevint == linesAL.get(i)-1) {
                linestemp.add(linesAL.get(i));
                prevint = linesAL.get(i);
            }else{
                multilines.add(new int[]{linestemp.get(0),(linestemp.size())});
                linestemp.clear();
                linestemp.add(linesAL.get(i));
                prevint = linesAL.get(i);
            }
        }
        multilines.add(new int[] { linestemp.get(0), (linestemp.size()) });

        int currentline = 1;
        int currentindex = 0;
        int currentoutputline = 0;
        int line;
        try {
            File file = new File(path);
            Scanner filereader = new Scanner(file);
            while (currentindex < multilines.size()){
                line = multilines.get(currentindex)[0];
                
                while (filereader.hasNextLine() && currentline < line) {
                    filereader.nextLine();
                    currentline++;
                }

                for(int i = 0; i <multilines.get(currentindex)[1]; i++ ){
                    if (filereader.hasNextLine()) {
                        returnlines[currentoutputline] = filereader.nextLine();
                        currentoutputline++;
                        currentline++;
                    } else {
                        System.out.println("Error, file does not have this many lines. \n returned an empty String");
                    }
                }
                currentindex++;
            }
            filereader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
        
        return returnlines;
    }

    public static int getfilelength(String path) {
        int c = 0;
        File file = new File(path);
        try {
            Scanner filereader = new Scanner(file);
            while (filereader.hasNextLine()){
                c++;
                filereader.nextLine();
            }
            filereader.close();
        } catch (Exception e) {
            System.out.println("could give back the length of file, because it doesnt exist");
        }
        return c + 1;
    }

    /**
     * reads all lines between two boarder lines
     * @param path      filepath
     * @param line1     first line read
     * @param line2     last line read
     * @return          String Array of the lines read, each line one element
     */
    public static String[] getLinesFromFile(String path, int line1, int line2) {

        String[] returnlines = new String[line2-line1];
        
        int currentline = 1;
        try {
            File file = new File(path);
            Scanner filereader = new Scanner(file);
            
            while (filereader.hasNextLine() && currentline < line1) {
                filereader.nextLine();
                currentline++;
            }

            for (int i = 0; i <= (line2 - line1); i++) {
                if (filereader.hasNextLine()) {
                    returnlines[i] = filereader.nextLine();
                    currentline++;
                } else {
                    System.out.println("Error, file does not have this many lines. \n returned an empty String");
                }
            }
            filereader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }

        return returnlines;
    }

    /**
     * Read the whole file
     * 
     * @param path      filepath
     * @return          String[] of all lines in the file
     */
    public static String[] getallLinesFromFile(String path){
        ArrayList<String> returnlines = new ArrayList<String>();
        String line;
        try {
            File file = new File(path);
            Scanner filereader = new Scanner(file);
            while (filereader.hasNextLine()) {
                line = filereader.nextLine();
                if(!line.equals("")) returnlines.add(line);
            }
            filereader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
        String[] returnlinesArray = Stringoperations.convertArrayListtoArray(returnlines);

        return returnlinesArray;
    }

    /**
     * Read the whole file
     * 
     * @param path filepath
     * @return String[] of all lines in the file
     */
    public static String[] getallLinesFromFileExact(String path) {
        ArrayList<String> returnlines = new ArrayList<String>();
        String line;
        try {
            File file = new File(path);
            Scanner filereader = new Scanner(file);
            while (filereader.hasNextLine()) {
                line = filereader.nextLine();
                returnlines.add(line);
            }
            filereader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
        String[] returnlinesArray = Stringoperations.convertArrayListtoArray(returnlines);

        return returnlinesArray;
    }

    /**
     * creates a new file
     * @param path  filepath
     * @return      true, if creation successfull
     */
    public static boolean createFile(String path, boolean overwrite){
        try {
            File file = new File(path);
            if (file.createNewFile()) {
                System.out.println("File created: " + file.getName());
            } else {
                if(overwrite){
                    System.out.println("File already exists. deleteting and creating new");
                    deleteFile(path);
                    createFile(path, false);
                }else{
                    System.out.println("File already exists. Overwrite is off");
                }

                
            }
            
            return true;
        }catch(IOException e){
            System.out.println("An error occurred.");
            e.printStackTrace();
            return false;
        }
        
    }

    /**
     * Changes the specific line in a file.
     * @param path      filepath
     * @param line      index of the line to be changed (first line has index = 0)
     * @param input     String to add to file
     * @return          true, if sucessfull
     */
    public static boolean writeToFileLine(String path, int line, String input){
        
        String[] file = getallLinesFromFileExact(path);
        if(file.length >= line)file[line - 1] = input;
        writeToFile(path, file, true);

        return true;
    }

    /**
     * adds a String to the end of the file
     * 
     * @param path  filepath
     * @param input String to add to file
     * @return true, if sucessfull
     */
    public static boolean writeToFile(String path, String input, boolean newline) {
        try {
            FileWriter myWriter = new FileWriter(path, true);
            input += newline? "\n":"";
            myWriter.write(input);
            myWriter.close();
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }

        return true;
    }

    /**
     * Writes every Element of an Array to a .txt file
     * @param path       filename
     * @param inputarray Array of Strings which is to be added to the file.
     * @param newline    if true ? each element is on a new line : everything is in
     *                   the same line, unless there is \n as an element of the
     *                   inputarray
     * @return true, if successfull
     */
    public static boolean writeToFile(String path, String[] inputarray, boolean newline){
        try {
            FileWriter myWriter = new FileWriter(path);
            
            for(String string : inputarray){
                if (newline) string += "\n";
                myWriter.write(string);
            }
            myWriter.close();
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }

        return true;

    }

    /**
     *deletes the given file file
     * @param path      filepath of the to be deleted file
     * @return          true if successfull
     */
    public static boolean deleteFile(String path){
        File file = new File(path);
        boolean success = file.delete();
        return success;
    }

    public static void println(String string){
        writeToFile("PracticeProjects/Textfiles/Console.txt", string, true);
    }

    public static void print(String string) {
        writeToFile("PracticeProjects/Textfiles/Console.txt", string, false);
    }
    
    public static void println(char string) {
        writeToFile("PracticeProjects/Textfiles/Console.txt", "" + string, true);
    }
    
    public static void println(int string) {
        writeToFile("PracticeProjects/Textfiles/Console.txt", "" + string, true);
    }

    public static void println(double string) {
        writeToFile("PracticeProjects/Textfiles/Console.txt", "" + string, true);
    }

    public static void printtp(String string, double value){
        int valuevar = 32;
        int stringvar = 32;
        if (string.length() > 32){
            stringvar = string.length();
            valuevar = 64 - stringvar;
        }
        
        String format = "%-" + stringvar + "s%" + valuevar + "f";
        String printstring = String.format(format, string, value); 
        writeToFile("PracticeProjects/Textfiles/Console.txt", printstring, true);

        
    }

    
}