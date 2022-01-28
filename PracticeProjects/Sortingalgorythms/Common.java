package PracticeProjects.Sortingalgorythms;

import java.util.ArrayList;
import java.util.HashMap;
//import java.util.Set;

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
            // System.out.println(s1Array[i] + ": " + (int) s1Array[i] + "| " + s2Array[i] +
            // ": " + (int) s2Array[i]);
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
            // System.out.println(s1Array[i] + ": " + (int) s1Array[i] + "| " + s2Array[i] +
            // ": " + (int) s2Array[i]);
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
     * 
     * @param s1           first String
     * @param s2           second String
     * @param referencemap reference HashMap.
     * @return true, if s1 < s2 --> s1 comes before s2
     */
    public static boolean firstStringbool(String s1, String s2, HashMap<Character, Integer> referencemap) {
        int i = 0;
        while (true) {
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

    /**
     * merges presorted Array chunks.
     * 
     * @param sortlist     ArrayList of elements ordered in at least 2 chunks (-->
     *                     otherwise nothing will really happpen)
     * @param referencemap A HashMap containing the char --> int value pair to
     *                     define the order in which characters are supoosed to be
     *                     sortet
     * @param qualsOnly    if true it merges unequal chunks as well, this will
     *                     always result in one chunk up to the index of chunk[2]
     *                     parameter
     * @param chunklist    An Arraylists that has stored each chunk as an int
     *                     (length of the chunk)
     * @param chunk        The boarders of the last two chunks, {first index first
     *                     chunk, first Index second chunk, index of last element of
     *                     chunk 2 + 1} Only have to pass in chunk[3], which has to
     *                     describe the first index not merged in any chunk so far
     * 
     * @return (not really nessesary) ArrayList with merged Chunks
     */
    public static ArrayList<String> mergeArrayListe(ArrayList<String> sortlist,
            HashMap<Character, Integer> referencemap, boolean equalsOnly,
            ArrayList<Integer> chunklist, int[] chunk) {

        chunk[0] = chunk[2] - chunklist.get(chunklist.size() - 1) - chunklist.get(chunklist.size() - 2);
        chunk[1] = chunk[2] - chunklist.get(chunklist.size() - 1);
        while ((chunklist.size() >= 2
                && ((int) chunklist.get(chunklist.size() - 1) == (int) chunklist.get(chunklist.size() - 2)))
                || !equalsOnly) {
            MergeSortArrayList.mergeSortedArrayListRegionsII(sortlist, chunk[0], chunk[1], chunk[2], referencemap);

            chunklist.add(chunk[2] - chunk[0]);
            chunklist.remove(chunklist.size() - 2);
            chunklist.remove(chunklist.size() - 2);
            chunk[1] = chunk[0];
            chunk[0] = chunk[0]
                    - ((chunklist.size() < 2) ? chunklist.size() : (int) chunklist.get((int) chunklist.size() - 2));
            if (chunk[2] == sortlist.size() && chunklist.size() == 1)
                break;
        }

        return sortlist;

    }

    /*
     * public static <T> boolean firstStringbool(T value, T value2, HashMap<String,
     * Integer> referenceMap) {
     * if (value instanceof Set<?>) {
     * Set<?> set = (Set<?>) value;
     * if (set.stream().allMatch(String.class::isInstance)) {
     * return firstStringbool(value, value2, referenceMap);
     * }
     * }
     * else{
     * return false;
     * }
     * return false;
     * }
     */

    public static double log(double base, double value) {
        return (Math.log(value) / Math.log(base));
    }
}
