package PracticeProjects.Sortingalgorythms;

import java.util.HashMap;

public class Common {
    
    /**
     * finds the first String in alphabetical order
     * 
     * @param s1 String 1
     * @param s2 String 2
     * 
     * @return returns string sorted first in the alphabet
     */
    public static String[] firstString(String s1, String s2) {
        
        // returns the alphabetically higher String
        char[] s1Array = s1.toLowerCase().toCharArray();
        char[] s2Array = s2.toLowerCase().toCharArray();
        int i = 0;
        String[] returnstring = new String[2];
        do {
            //System.out.println(s1Array[i] + ": " + (int) s1Array[i] + "| " + s2Array[i] + ": " + (int) s2Array[i]);
            if ((int) s2Array[i] < (int) s1Array[i]) {
                returnstring[0] = s2;
                returnstring[1] = s1;
            }
            if ((int) s1Array[i] < (int) s2Array[i]) {
                returnstring[0] = s1;
                returnstring[1] = s2;
            }
            if ((i + 1) == Math.min(s1Array.length, s2Array.length)) {
                if (s1Array.length < s2Array.length) {
                    returnstring[0] = s1;
                    returnstring[1] = s2;
                    ;
                } else {
                    returnstring[0] = s2;
                    returnstring[1] = s1;
                }
                break;
            } else {
                i++;
            }
        } while (s1Array[i - 1] == s2Array[i - 1]);
        return returnstring;
    }

    /**
     * returns true if the first string s1 is alphabetically sorted before s2
     * 
     * @param s1 String 1
     * @param s2 String 2
     * 
     */
    public static boolean firstStringbool(String s1, String s2) {
        
        // returns the alphabetically higher String
        char[] s1Array = s1.strip().toLowerCase().toCharArray();
        char[] s2Array = s2.strip().toLowerCase().toCharArray();
        int i = 0;
        boolean returnbool = false;
        do {
            //System.out.println(s1Array[i] + ": " + (int) s1Array[i] + "| " + s2Array[i] + ": " + (int) s2Array[i]);
            if ((int) s2Array[i] < (int) s1Array[i]) {
                returnbool = false;
                break;
            }
            if ((int) s1Array[i] < (int) s2Array[i]) {
                returnbool = true;
                break;
            }
            if ((i + 1) == Math.min(s1Array.length, s2Array.length)) {
                if (s1Array.length < s2Array.length) {
                    returnbool = true;
                    break;
                } else {
                    returnbool = false;
                    break;
                }
            } else {
                i++;
            }
        } while (s1Array[i - 1] == s2Array[i - 1]);
        return returnbool;
    }

    /**
     * compares two Strings by their order on a rederence HashMap.
     * @param s1                first String    
     * @param s2                second String
     * @param referencemap      reference HashMap. 
     * @return                  true, if s1 < s2 --> s1 comes before s2 
     */
    public static boolean firstStringbool(String s1, String s2, HashMap<Character, Integer> referencemap){
        int i = 0;
        while(true){
            boolean check = referencemap.get(s2.charAt(i)) > referencemap.get(s1.charAt(i));
            if (check) {
                return true;
            }
            boolean check2 = (referencemap.get(s1.charAt(i)) > referencemap.get(s2.charAt(i)));
            if (check2) {
                return false;
            }
            if ((i + 1) == Math.min(s1.length(), s2.length())) {
                if (s1.length() <= s2.length()) {
                    return true;
                } else {
                    return false;
                }
            } else {
                i++;
            }
        } 
    }

}
