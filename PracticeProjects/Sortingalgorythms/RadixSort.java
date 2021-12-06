package PracticeProjects.Sortingalgorythms;

import java.util.HashMap;

import PracticeProjects.Filemanager;
import PracticeProjects.Progressbart;

public class RadixSort {
    
    /**
     * randixsort algorithm for stringsorting O(n, maxlength(n))
     * 
     * @param Inputarray           given Array with Strings to sort
     * @param elementorderfilepath needed to define the sortingpriority of each
     *                             charakter. Each char in the Inputarray has to be
     *                             defined here
     * 
     * @return sorted Array of the Strings
     */
    public static String[] radixsortAlgorythm(String[] Inputarray, String elementorderfilepath) {

        String[] outputarray = new String[Inputarray.length]; // second array for sorting algorithm
        HashMap<Character, Integer> counterrefference = new HashMap<Character, Integer>(); // referenceMap to map a
                                                                                           // charakter to its
                                                                                           // sortingvalue
        String[] orderinfo = Filemanager.getallLinesFromFile(elementorderfilepath); // load reference map values from
                                                                                    // the file
        int[] counterarray = new int[orderinfo.length]; // initialize counter array, lenght = amount of different values
                                                        // Elements are differenciated by
        long starttime = System.nanoTime();

        // fill counterrefferenceHashMap
        {
            for (int i = 0; i < orderinfo.length; i++) {
                for (char charakter : orderinfo[i].toCharArray()) {
                    // System.out.println("matched charakter:" + charakter + " with Index:" + i);
                    counterrefference.put(charakter, i);
                }
            }
        }
        System.out.println("load counterreferenceHashMap" + (starttime - System.nanoTime()));

        // add _ to front of each String, so that they all have the same lenght -->
        // O(max(n), n),
        {
            int max = 0;
            String add = orderinfo[0];
            // finding the longest String in Inputarray
            for (int i = 0; i < Inputarray.length; i++) {
                Inputarray[i].strip();
                if (Inputarray[i].length() > max) {
                    max = Inputarray[i].length();
                }
            }
            /*
             * int[] lengthdistribution = new int[max];
             * for (int i = 0; i < Inputarray.length; i++) {
             * lengthdistribution[Inputarray[i].length() - 1]++;
             * }
             * for (int i = 0; i < lengthdistribution.length; i++) {
             * System.out.println("Index:" + i + "--> Value: " + lengthdistribution[i]);
             * }
             */

            System.out.println("max(length(inputarray)) =  " + max);
            // adding the first charakter from orderinfofile to fill all strings to same
            // lenght
            for (int i = 0; i < Inputarray.length; i++) {
                add = "";
                for (int c = 0; c < max - Inputarray[i].length(); c++) {
                    add += orderinfo[0];
                }
                Inputarray[i] = Inputarray[i] + add;
            }
        }

        Progressbart progressbar = new Progressbart("PracticeProjects/Textfiles/Console.txt",
                "Radixsort Progress:");

        // the actual radixsort sorting algorythm
        for (int m = Inputarray[0].length() - 1; m >= 0; m--) {
            outputarray = new String[Inputarray.length];

            for (int i = 0; i < Inputarray.length; i++) {
                try {
                    counterarray[counterrefference.get(Inputarray[i].charAt(m))]++;
                } catch (java.lang.NullPointerException e) {
                    System.out.println("Error" + e + " in line 204, A character was not in reference HashMap, word:"
                            + Inputarray[i] + " Character:" + Inputarray[i].charAt(m));
                    System.out.println(counterrefference.get(Inputarray[i].charAt(m)));
                }
            }

            // Indexe in counterarray berechnen
            for (int i = 1; i < counterarray.length; i++) {
                counterarray[i] += counterarray[i - 1];
            }

            // Elemente dem outputArray an Index ablegen, der in counterrefference gegeben
            // wurde
            for (int index = Inputarray.length - 1; index >= 0; index--) {
                if (Inputarray[index] == null)
                    continue;
                outputarray[--counterarray[counterrefference.get(Inputarray[index].charAt(m))]] = Inputarray[index];
            }

            // Stringoperations.printStringArray(outputarray);
            Inputarray = outputarray; // just setting the reference doesnt work somehow, peopably because the
                                      // Inputarray is a parameter, but I dont know
                                      // if you only set the reference Inputarray sets some Indexes to null -->
                                      // causes errors
            // Stringoperations.printStringArray(Inputarray);

            counterarray = new int[orderinfo.length];
            progressbar.update(100.0 / (Inputarray[0].length() - 1));
        }

        // removeing the temporary charakters
        {
            for (int i = 0; i < outputarray.length; i++) {
                outputarray[i] = outputarray[i].split(orderinfo[0])[0];
            }
        }
        return outputarray;
    }

}
