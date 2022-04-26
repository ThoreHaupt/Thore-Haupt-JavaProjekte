package PracticeProjects.Sortingalgorythms;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;

import PracticeProjects.Filemanager;
import PracticeProjects.Progressbart;

public class InsertSorts {

    /**
     * Insertsort for Strings O(n!) Sortingorder: order of Charakters in ASKI
     * (captalisation is ignored) the randixsortalgorithm is way faster
     * 
     * @param inputarray Array of the Strings to be sorted
     * 
     */
    public static String[] StringInsertSort(String[] inputarray) {

        ArrayList<String> templist = new ArrayList<String>();
        String[] orderinfo = Filemanager.getallLinesFromFile("PracticeProjects/Textfiles/CharacterOrder.txt");

        HashMap<Character, Integer> counterrefference = new HashMap<Character, Integer>();
        {
            for (int i = 0; i < orderinfo.length; i++) {
                for (char charakter : orderinfo[i].toCharArray()) {
                    // System.out.println("matched charakter:" + charakter + " with Index:" + i);
                    counterrefference.put(charakter, i);
                }
            }
        }
        // progressbar in console.txt file
        Progressbart progressbar = new Progressbart("PracticeProjects/Textfiles/Console.txt", "Insertsort I Progress:");

        templist.add(inputarray[0]);
        int c = 0;
        int tempsize;
        long E9time = System.nanoTime();
        for (int i = 1; i < inputarray.length; i++) {
            c = 0;
            tempsize = templist.size() - 1;
            while (Common.firstStringbool(inputarray[i], templist.get(c), counterrefference)) {
                if (c == tempsize) {
                    break;
                }
                c++;
            }
            templist.add(c, inputarray[i]);

            // System.out.println("added word at index:" + c + " word: " + inputarray[i]);
            progressbar.update(100.0 / (inputarray.length));
        }
        Filemanager.println(String.format("%-32s%32f", "Insertsort average sortingtime:",
                (System.nanoTime() - E9time) * Math.pow(10, -6) / (double) inputarray.length));
        Collections.reverse(templist);
        inputarray = templist.toArray(new String[templist.size()]);
        return inputarray;
    }

    /**
     * Insertsort for Strings O(n!) Sortingorder: order of Charakters in ASKI
     * (captalisation is ignored) the randixsortalgorithm is way faster
     * 
     * @param inputarray Array of the Strings to be sorted
     * 
     */
    public static String[] StringInsertSortII(String[] inputarray) {

        ArrayList<String> templist = new ArrayList<String>(Arrays.asList(inputarray));
        HashMap<Character, Integer> counterrefference = new HashMap<Character, Integer>();
        {
            String[] orderinfo = Filemanager.getallLinesFromFile("PracticeProjects/Textfiles/CharacterOrder.txt");
            for (int i = 0; i < orderinfo.length; i++) {
                for (char charakter : orderinfo[i].toCharArray()) {
                    // System.out.println("matched charakter:" + charakter + " with Index:" + i);
                    counterrefference.put(charakter, i);
                }
            }
        }

        Progressbart progressbar = new Progressbart("PracticeProjects/Textfiles/Console.txt",
                "Insertsort II Progress:");

        int c = 0;
        long E9time = System.nanoTime();
        for (int i = 1; i < templist.size(); i++) {
            c = i - 1;
            while (Common.firstStringbool(templist.get(i), templist.get(c), counterrefference)) {

                if (c == 0) {
                    break;
                }
                c--;
            }
            templist.add(c + 1, templist.get(i));
            templist.remove(i + 1);
            progressbar.update(100.0 / (inputarray.length));
        }
        Filemanager.println(String.format("%-32s%32f", "Element Insert average sortingtime:",
                (System.nanoTime() - E9time) * Math.pow(10, -6) / (double) inputarray.length));

        Collections.reverse(templist);
        return templist.toArray(new String[templist.size()]);
    }

}
