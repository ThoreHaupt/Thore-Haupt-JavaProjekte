package TryingNewThings;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class Stringoperations {
    

    public static void main(String[] args) {
        findNewChars("TryingNewThings/Textfiles/ListEnglishAndGermanWords", "TryingNewThings/Textfiles/CharacterOrder.txt");
    }
    /** prints a String[] */
    public static void printStringArray(String[] array) {
        
        for (int i = 0; i < array.length; i++) {
            System.out.println("Index: " + i + "--> Wert: " + array[i]);
        }
    }

    /**
     * creates a list of random German Words, takes the amount of words as an
     * Argument
     * @param length    amount of words
     */
    public static String[] createRandomStringArray(int length) {
       
        String[] list = new String[length];
        int[] indices = new int[length];
        Random randomNumberGenerator = new Random();
        for (int i = 0; i < length; i++){
            indices[i] = (int) (randomNumberGenerator.nextDouble() * 654140);
        }
        list = Filemanager.getLinesFromFile("TryingNewThings/Textfiles/ListEnglishAndGermanWords", indices);
        System.out.println("generated an Array of length: " + length);
        shuffleArray(list);
        return list;
    }

    /**
     * takes Array with multiword Strings(seperated by ' ') and returns an Array
     * with only 1 Word per Index
     * 
     * @param inputArray    array of words
     */
    public static String[] splitStringsIntoWordArray(String[] inputArray){
        
        ArrayList<String> returntemp = new ArrayList<String>();
        String[] tempSubString; 
        for (int i = 0; i < inputArray.length; i++){
            tempSubString = inputArray[i].strip().split(" ", -1);
            for (int c = 0; c < tempSubString.length; c++){
                returntemp.add(tempSubString[c]);
            }

        }
        String[] outputarray = new String[returntemp.size()];
        for (int i = 0; i < returntemp.size(); i++){
            outputarray[i] = returntemp.get(i);
        }
        return outputarray;
    }

    /** converts an ArrayList<String> Type to an String[] */
    public static String[] convertArrayListtoArray(ArrayList<String> inputArrayList){
        
        String[] output = new String[inputArrayList.size()];
        for(int i = 0; i < inputArrayList.size(); i++){
            output[i] = inputArrayList.get(i);
        }
        return output;
    }

    /**
     * moves Elements from an ArrayList to an Array
     * @param inputArrayList    ArrayList to be converted
     * @param direction         >= 0 --> same order, <0 inverses order of elements
     * @return                  Array
     */
    public static String[] convertArrayListtoArray(ArrayList<String> inputArrayList, int direction) {

        String[] output = new String[inputArrayList.size()];
        if (direction >= 0){
            for (int i = 0; i < inputArrayList.size(); i++) {
                output[i] = inputArrayList.get(i);
            }
        }else{
            int size = inputArrayList.size();
            for (int i = 0; i < inputArrayList.size(); i++) {
                output[i] = inputArrayList.get(size - i - 1);
            }
        }
        
        return output;
    }

    /** removes dots and comma */
    public static String[] removeSemantics(String[] inputArray){
        
        ArrayList<String> outputList = new ArrayList<String>();
        String string;
        for(int i = 0; i < inputArray.length; i++){
            string = inputArray[i].replaceAll(",", " ").replaceAll(".", " ").strip();
            if(!string.equals("")) outputList.add(string);
        }
        
        return inputArray;
    }

    static void shuffleArray(String[] ar) {
        // If running on Java 6 or older, use `new Random()` on RHS here
        Random rnd = ThreadLocalRandom.current();
        for (int i = ar.length - 1; i > 0; i--) {
            int index = rnd.nextInt(i + 1);
            // Simple swap
            String a = ar[index];
            ar[index] = ar[i];
            ar[i] = a;
        }
    }

    public static void findNewChars(String path, String reference){
        
        HashMap<Character, Integer> counterrefference = new HashMap<Character, Integer>();
        {
            String[] orderinfo = Filemanager.getallLinesFromFile(reference);
            for (int i = 0; i < orderinfo.length; i++) {
                for (char charakter : orderinfo[i].toCharArray()) {
                    // System.out.println("matched charakter:" + charakter + " with Index:" + i);
                    counterrefference.put(charakter, i);
                }
            }
        }

        String[] array = Filemanager.getallLinesFromFile(path);
        ArrayList<String> output = new ArrayList<String>();
        for (int i = 0; i < array.length; i++) {
            for (char c : array[i].toCharArray()) {
                if (null == counterrefference.get(c)){
                    output.add("" + c);
                }
            }
        }


        Filemanager.createFile("TryingNewThings/Textfiles/missingChars.txt", true);
        Filemanager.writeToFile("TryingNewThings/Textfiles/missingChars.txt",output.toArray(new String[output.size()]) , true);

    }

}
