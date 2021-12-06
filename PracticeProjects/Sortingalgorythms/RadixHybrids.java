package PracticeProjects.Sortingalgorythms;

import java.util.ArrayList;
import java.util.HashMap;

import PracticeProjects.Filemanager;
import PracticeProjects.Stringoperations;

public class RadixHybrids {
    
    /**
     * randixsort algorithm for stringsorting O(n * maxlength(n))
     * 
     * @param Inputarray           given Array with Strings to sort
     * @param elementorderfilepath needed to define the sortingpriority of each
     *                             charakter. Each char in the Inputarray has to be
     *                             defined here
     * @param randixcappoff        the percentage of shortest Strings that is sorted
     *                             by the radix algorithm, the rest is sorted by
     *                             Insertsort and then merged to the Array. This
     *                             decreases rundtime since radix
     *                             has a proportional runtime to the length of the
     *                             longest element
     * 
     * @return sorted Array of the Strings
     */
    public static String[] radixInsertMergeSortAlgorythm(String[] Inputarray, String elementorderfilepath,
            double randixcappoff) {

        long starttime = System.nanoTime();
        // second array for sorting algorithm
        HashMap<Character, Integer> counterrefference = new HashMap<Character, Integer>(); // referenceMap to map a
                                                                                           // character to its
                                                                                           // sortingvalue
        String[] orderinfo = Filemanager.getallLinesFromFile(elementorderfilepath); // load reference map values from
                                                                                    // the file
        int[] counterarray = new int[orderinfo.length]; // initialize counter array, lenght = amount of different values
                                                        // Elements are differenciated by

        // fill counterrefferenceHashMap
        {
            for (int i = 0; i < orderinfo.length; i++) {
                for (char charakter : orderinfo[i].toCharArray()) {
                    // System.out.println("matched charakter:" + charakter + " with Index:" + i);
                    counterrefference.put(charakter, i);
                }
            }
        }

        // add _ to front of each String, so that they all have the same lenght -->
        // split values to randix algorythm and insert sort
        // O(max(n), n),

        Filemanager.printtp("load counterreferenceHashMap", (System.nanoTime() - starttime) * Math.pow(10, -6));
        long E2time = System.nanoTime();

        int max = 0;
        // finding the longest String in Inputarray
        for (int i = 0; i < Inputarray.length; i++) {
            Inputarray[i].strip();
            if (Inputarray[i].length() > max) {
                max = Inputarray[i].length();
            }
        }

        Filemanager.println(String.format("%-32s%32f", "find max:", (System.nanoTime() - E2time) * Math.pow(10, -6)));
        long E3time = System.nanoTime();

        int[] lengthdistribution = new int[max]; // value of element represents the amount of Elements in Inputqarray
                                                 // with that length
        int covered = 0; // runnervariable, which stores the max amount of values to be sorted by randix
        int distributionindex = 0;

        // fills the lengthdistribution
        for (int i = 0; i < Inputarray.length; i++) {
            lengthdistribution[Inputarray[i].length() - 1]++;
        }

        Filemanager.printtp("lengthdistribution", (System.nanoTime() - E3time) * Math.pow(10, -6));
        long E4time = System.nanoTime();

        // calculates covered and the capoffpoint for length
        while (((double) covered / Inputarray.length) < randixcappoff) {
            covered += lengthdistribution[distributionindex];
            distributionindex++;
        }

        String[] randixInputArray = new String[covered];
        String[] InsertInputArray = new String[Inputarray.length - covered];
        int filledcounter = 0;
        for (int i = 0; i < Inputarray.length; i++) {
            if ((Inputarray[i].length()) <= distributionindex) {
                randixInputArray[filledcounter] = Inputarray[i];
                filledcounter++;
            } else {
                InsertInputArray[i - filledcounter] = Inputarray[i];
            }
        }

        Filemanager.printtp("split into both Arrays ", (System.nanoTime() - E4time) * Math.pow(10, -6));
        long E5time = System.nanoTime();

        // adding the first charakter from orderinfofile to fill all strings to same
        // lenght

        String[] addchars = new String[distributionindex + 1];
        String adds = orderinfo[0];
        addchars[0] = "";
        for (int i = 0; i < addchars.length; i++) {
            addchars[i] = adds;
        }
        for (int i = 2; i < addchars.length; i++) {
            addchars[i] += adds;
            adds += orderinfo[0];
        }
        for (int i = 0; i < randixInputArray.length; i++) {
            randixInputArray[i] += addchars[distributionindex - randixInputArray[i].length()];
        }

        Filemanager.printtp("radix characteradding ", (System.nanoTime() - E5time) * Math.pow(10, -6));
        long E6time = System.nanoTime();

        // radixsort sorting algorythm
        String[] randixoutputarray = new String[Inputarray.length];

        if (randixInputArray.length > 0) {
            for (int m = randixInputArray[0].length() - 1; m >= 0; m--) {
                randixoutputarray = new String[randixInputArray.length];

                for (int i = 0; i < randixInputArray.length; i++) {
                    try {
                        counterarray[counterrefference.get(randixInputArray[i].charAt(m))]++;
                    } catch (java.lang.NullPointerException e) {
                        Filemanager
                                .println("Error" + e + " in line 204, A character was not in reference HashMap, word:"
                                        + randixInputArray[i] + " Character: " + randixInputArray[i].charAt(m));
                        Filemanager.println(counterrefference.get(randixInputArray[i].charAt(m)));
                    }
                }

                // Indexe in counterarray berechnen
                for (int i = 1; i < counterarray.length; i++) {
                    counterarray[i] += counterarray[i - 1];
                }

                // Elemente dem outputArray an Index ablegen, der in counterrefference gegeben
                // wurde
                for (int index = randixInputArray.length - 1; index >= 0; index--) {
                    if (randixInputArray[index] == null)
                        continue;
                    try {
                        randixoutputarray[--counterarray[counterrefference
                                .get(randixInputArray[index].charAt(m))]] = randixInputArray[index];
                    } catch (java.lang.NullPointerException e) {
                        Filemanager.println("Error" + e + ": \n A character was not in reference HashMap, word: "
                                + randixInputArray[index] + " Character: " + randixInputArray[index].charAt(m));
                        Filemanager.println(counterrefference.get(randixInputArray[index].charAt(m)));
                    }

                }

                // Stringoperations.printStringArray(outputarray);
                randixInputArray = randixoutputarray; // just setting the reference doesnt work somehow, peopably
                                                      // because the
                // Inputarray is a parameter, but I dont know
                // if you only set the reference Inputarray sets some Indexes to null -->
                // causes errors
                // Stringoperations.printStringArray(Inputarray);

                counterarray = new int[orderinfo.length];
            }
        }

        Filemanager.printtp("radix sort", (System.nanoTime() - E6time) * Math.pow(10, -6));
        long E7time = System.nanoTime();

        // removeing the temporary charakters
        if (randixInputArray.length > 0) {
            for (int i = 0; i < randixoutputarray.length; i++) {
                randixoutputarray[i] = randixoutputarray[i].replace(orderinfo[0], " ").strip();
            }
        }

        Filemanager.printtp("removing temp chars", (System.nanoTime() - E7time) * Math.pow(10, -6));
        long E8time = System.nanoTime();

        // Merge the other long Elements
        String[] outputarray;
        if (InsertInputArray.length >= 1) {
            outputarray = MergeSortArrayList.mergeSortedArrays(MergeSortLinked.MergeSortLinkedAlgorythm(InsertInputArray,
                    elementorderfilepath), randixoutputarray, elementorderfilepath);
        } else {
            outputarray = randixoutputarray;
        }

        Filemanager.printtp("MergeSort:", (System.nanoTime() - E8time) * Math.pow(10, -6));
        Filemanager.printtp("radixinsertmerch sort complete: ", (System.nanoTime() - starttime) * Math.pow(10, -6));
        return outputarray;
    }

    /**
     * randixsort algorithm for stringsorting O(n, maxlength(n)) using an InsertSort
     * for the longst Elements
     * 
     * @param Inputarray           given Array with Strings to sort
     * @param elementorderfilepath needed to define the sortingpriority of each
     *                             charakter. Each char in the Inputarray has to be
     *                             defined here
     * @param randixcappoff        the percentage of shortest Strings that is sorted
     *                             by the radix algorithm, the rest is sorted by
     *                             Insertsort
     *                             This decreases rundtime since radix has a
     *                             proportional runtime to the length of the longest
     *                             element
     * 
     * @return sorted Array of the Strings
     */
    public static String[] radixInsertSortAlgorythm(String[] Inputarray, String elementorderfilepath,
            double randixcappoff) {

        long starttime = System.nanoTime();
        // second array for sorting algorithm
        HashMap<Character, Integer> counterrefference = new HashMap<Character, Integer>(); // referenceMap to map a
                                                                                           // character to its
                                                                                           // sortingvalue
        String[] orderinfo = Filemanager.getallLinesFromFile(elementorderfilepath); // load reference map values from
                                                                                    // the file
        int[] counterarray = new int[orderinfo.length]; // initialize counter array, lenght = amount of different values
                                                        // Elements are differenciated by

        // fill counterrefferenceHashMap
        {
            for (int i = 0; i < orderinfo.length; i++) {
                for (char charakter : orderinfo[i].toCharArray()) {
                    // System.out.println("matched charakter:" + charakter + " with Index:" + i);
                    counterrefference.put(charakter, i);
                }
            }
        }

        // add _ to front of each String, so that they all have the same lenght -->
        // split values to randix algorythm and insert sort
        // O(max(n), n),

        Filemanager.printtp("load counterreferenceHashMap", (System.nanoTime() - starttime) * Math.pow(10, -6));
        long E2time = System.nanoTime();

        int max = 0;
        // finding the longest String in Inputarray
        for (int i = 0; i < Inputarray.length; i++) {
            Inputarray[i].strip();
            if (Inputarray[i].length() > max) {
                max = Inputarray[i].length();
            }
        }

        Filemanager.printtp("find max:", (System.nanoTime() - E2time) * Math.pow(10, -6));
        long E3time = System.nanoTime();

        int[] lengthdistribution = new int[max]; // value of element represents the amount of Elements in Inputqarray
                                                 // with that length
        int covered = 0; // runnervariable, which stores the max amount of values to be sorted by randix
        int distributionindex = 0;

        // fills the lengthdistribution
        for (int i = 0; i < Inputarray.length; i++) {
            lengthdistribution[Inputarray[i].length() - 1]++;
        }

        Filemanager.printtp("lengthdistribution", (System.nanoTime() - E3time) * Math.pow(10, -6));
        long E4time = System.nanoTime();

        // calculates covered and the capoffpoint for length
        while (((double) covered / Inputarray.length) < randixcappoff) {
            covered += lengthdistribution[distributionindex];
            distributionindex++;
        }

        String[] randixInputArray = new String[covered];
        String[] InsertInputArray = new String[Inputarray.length - covered];
        int filledcounter = 0;
        for (int i = 0; i < Inputarray.length; i++) {
            if ((Inputarray[i].length()) <= distributionindex) {
                randixInputArray[filledcounter] = Inputarray[i];
                filledcounter++;
            } else {
                InsertInputArray[i - filledcounter] = Inputarray[i];
            }
        }

        Filemanager.printtp("split into both Arrays ", (System.nanoTime() - E4time) * Math.pow(10, -6));
        long E5time = System.nanoTime();

        // adding the first charakter from orderinfofile to fill all strings to same
        // lenght

        String[] addchars = new String[distributionindex + 1];
        String adds = orderinfo[0];
        addchars[0] = "";
        for (int i = 0; i < addchars.length; i++) {
            addchars[i] = adds;
        }
        for (int i = 2; i < addchars.length; i++) {
            addchars[i] += adds;
            adds += orderinfo[0];
        }
        for (int i = 0; i < randixInputArray.length; i++) {
            randixInputArray[i] += addchars[distributionindex - randixInputArray[i].length()];
        }

        Filemanager.printtp("radix characteradding ", (System.nanoTime() - E5time) * Math.pow(10, -6));
        long E6time = System.nanoTime();

        // radixsort sorting algorythm
        String[] randixoutputarray = new String[Inputarray.length];

        if (randixInputArray.length > 0)
            for (int m = randixInputArray[0].length() - 1; m >= 0; m--) {
                randixoutputarray = new String[randixInputArray.length];

                for (int i = 0; i < randixInputArray.length; i++) {
                    try {
                        counterarray[counterrefference.get(randixInputArray[i].charAt(m))]++;
                    } catch (java.lang.NullPointerException e) {
                        Filemanager
                                .println("Error" + e + " in line 204, A character was not in reference HashMap, word:"
                                        + randixInputArray[i] + " Character: " + randixInputArray[i].charAt(m));
                        Filemanager.println(counterrefference.get(randixInputArray[i].charAt(m)));
                    }
                }

                // Indexe in counterarray berechnen
                for (int i = 1; i < counterarray.length; i++) {
                    counterarray[i] += counterarray[i - 1];
                }

                // Elemente dem outputArray an Index ablegen, der in counterrefference gegeben
                // wurde
                for (int index = randixInputArray.length - 1; index >= 0; index--) {
                    if (randixInputArray[index] == null)
                        continue;
                    try {
                        randixoutputarray[--counterarray[counterrefference
                                .get(randixInputArray[index].charAt(m))]] = randixInputArray[index];
                    } catch (java.lang.NullPointerException e) {
                        Filemanager.println("Error" + e + ": \n A character was not in reference HashMap, word: "
                                + randixInputArray[index] + " Character: " + randixInputArray[index].charAt(m));
                        Filemanager.println(counterrefference.get(randixInputArray[index].charAt(m)));
                    }

                }

                // Stringoperations.printStringArray(outputarray);
                randixInputArray = randixoutputarray; // just setting the reference doesnt work somehow, peopably
                                                      // because the
                // Inputarray is a parameter, but I dont know
                // if you only set the reference Inputarray sets some Indexes to null -->
                // causes errors
                // Stringoperations.printStringArray(Inputarray);

                counterarray = new int[orderinfo.length];
            }

        Filemanager.printtp("radix sort", (System.nanoTime() - E6time) * Math.pow(10, -6));
        long E7time = System.nanoTime();

        // removeing the temporary charakters
        if (randixInputArray.length > 0) {
            for (int i = 0; i < randixoutputarray.length; i++) {
                randixoutputarray[i] = randixoutputarray[i].replace(orderinfo[0], " ").strip();
            }
        }

        Filemanager.printtp("removing temp chars", (System.nanoTime() - E7time) * Math.pow(10, -6));
        long E8time = System.nanoTime();

        // InsertSort the other long Elements
        String[] outputarray;
        if (InsertInputArray.length >= 1) {
            ArrayList<String> insertlist = new ArrayList<String>(0);

            // move sorted randixoutputarray into an ArrayList to insertsort the longer
            // Elements, invers order for Insertalgorithm
            if (randixInputArray.length > 0)
                for (int i = randixoutputarray.length - 1; i >= 0; i--) {
                    insertlist.add(randixoutputarray[i]);
                }

            // if randixoutputarray is empty, there is no first Element to compare to, this
            // would return an error
            if (insertlist.size() == 0) {
                insertlist.add(InsertInputArray[0]);
            }

            Filemanager.printtp("copy to list(Insert Sort)", (System.nanoTime() - E8time) * Math.pow(10, -6));
            long E9time = System.nanoTime();

            // Insertsorting the long Elements into the Insertarray. Invers order Index 0 =
            // last
            int compareIndex;
            int InsertListLastIndex = insertlist.size() - 1; // last index of the Arraylist
            for (int i = 0; i < InsertInputArray.length; i++) {
                compareIndex = 0;
                while (Common.firstStringbool(InsertInputArray[i], insertlist.get(compareIndex++), counterrefference)) {
                    if (compareIndex == InsertListLastIndex) {
                        break;
                    }
                }
                insertlist.add(compareIndex, InsertInputArray[i]); // add the Element
                InsertListLastIndex++; // incrase last Index
            }
            Filemanager.printtp("average sortingtime: ",
                    (System.nanoTime() - E9time) * Math.pow(10, -6) / (double) InsertInputArray.length);

            outputarray = Stringoperations.convertArrayListtoArray(insertlist, -1);
        } else {
            outputarray = randixoutputarray;
        }

        Filemanager.printtp("InsertSort:", (System.nanoTime() - E8time) * Math.pow(10, -6));
        Filemanager.printtp("radixinsert sort complete: ", (System.nanoTime() - starttime) * Math.pow(10, -6));
        return outputarray;
    }

}
