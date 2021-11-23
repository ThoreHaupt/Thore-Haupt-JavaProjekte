package PracticeProjects;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

/**some random sorting method */
public class Sort {

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
     * @return                  true, if s1 > s2 --> s1 comes later 
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

    /**
     * Insertsort for Strings O(n!) Sortingorder: order of Charakters in ASKI
     * (captalisation is ignored) the randixsortalgorithm is way faster
     * 
     * @param inputarray Array of the Strings to be sorted
     * 
     */
    public static String[] StringInsertSort(String[] inputarray) {
        
        String[] outputarray = new String[inputarray.length];
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


        templist.add(inputarray[0]);
        int c = 0;
        int tempsize;
        long E9time = System.nanoTime();
        for (int i = 1; i < inputarray.length; i++) {
            c = 0;
            tempsize = templist.size() - 1;
            while (firstStringbool(inputarray[i], templist.get(c), counterrefference)) {
                if (c == tempsize) {
                    break;
                }
                c++;
            }
            templist.add(c, inputarray[i]);
            
            // System.out.println("added word at index:" + c + " word: " + inputarray[i]);
        }
        Filemanager.println(String.format("%-32s%32f","Insertsort average sortingtime:" , (System.nanoTime() - E9time) * Math.pow(10, -6) / (double) inputarray.length));
        
        for (int i = 0; i < templist.size(); i++) {
            outputarray[i] = templist.get(i);
        }

        return outputarray;
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

        int c = 0;
        long E9time = System.nanoTime();
        for (int i = 1; i < templist.size() ; i++) {
            c = i - 1;
            while (firstStringbool(templist.get(i), templist.get(c), counterrefference)) {
                
                if (c == 0) {
                    break;
                }
                c--;
            }
            templist.add(c + 1, templist.get(i));
            templist.remove(i + 1);
            // System.out.println("added word at index:" + c + " word: " + inputarray[i]);
        }
        Filemanager.println(String.format("%-32s%32f", "Insertsort average sortingtime:",
                (System.nanoTime() - E9time) * Math.pow(10, -6) / (double) inputarray.length));

        return templist.toArray(new String[templist.size()]);
    }

    /**
     * randixsort algorithm for stringsorting O(n, maxlength(n)) sortorder by
     * default from /Textfiles/CharakterOrder
     * 
     * @param Inputarray given Array with Strings to sort
     * 
     * @return sorted Array of the Strings
     */
    public static String[] radixsort(String[] Inputarray){
        return radixsortAlgorythm(Inputarray, "PracticeProjects/Textfiles/CharacterOrder.txt");
    }

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
    public static String[] radixsort(String[] Inputarray, String elementorderfilepath) {
        
        return radixsortAlgorythm(Inputarray, elementorderfilepath);
    }

    /**
     * randixsort algorithm for stringsorting O(n, maxlength(n))
     * 
     * @param Inputarray           given Array with Strings to sort
     * @param elementorderfilepath needed to define the sortingpriority of each
     *                             charakter. Each char in the Inputarray has to be
     *                             defined here
     * @param randixcappoff        the percentage of shortest Strings that is sorted
     *                             by the radix algorithm, the rest is sorted by
     *                             Insertsort This decreases rundtime since radix
     *                             has a proportional runtime to the length of the
     *                             longest element
     * 
     * @return sorted Array of the Strings
     */
    public static String[] radixInsertSort(String[] Inputarray){
        return radixInsertSortAlgorythm(Inputarray, "PracticeProjects/Textfiles/CharacterOrder.txt", .9d); 
    }
    
    /**
     * randixsort algorithm for stringsorting O(n, maxlength(n))
     * 
     * @param Inputarray           given Array with Strings to sort
     * @param elementorderfilepath needed to define the sortingpriority of each
     *                             charakter. Each char in the Inputarray has to be
     *                             defined here
     * @param randixcappoff        the percentage of shortest Strings that is sorted
     *                             by the radix algorithm, the rest is sorted by
     *                             Insertsort This decreases rundtime since radix
     *                             has a proportional runtime to the length of the
     *                             longest element
     * 
     * @return sorted Array of the Strings
     */
    public static String[] radixInsertSort(String[] Inputarray, String path, double capoff) {
        return radixInsertSortAlgorythm(Inputarray, path, capoff);
    }

    /**
     * randixsort algorithm for stringsorting O(n, maxlength(n))
     * 
     * @param Inputarray           given Array with Strings to sort
     * @param elementorderfilepath needed to define the sortingpriority of each
     *                             charakter. Each char in the Inputarray has to be
     *                             defined here
     * @param randixcappoff        the percentage of shortest Strings that is sorted
     *                             by the radix algorithm, the rest is sorted by
     *                             Insertsort This decreases rundtime since radix
     *                             has a proportional runtime to the length of the
     *                             longest element
     * 
     * @return sorted Array of the Strings
     */
    public static String[] radixMergeInsertSort(String[] Inputarray, String path, double capoff) {
        return radixInsertMergeSortAlgorythm(Inputarray, path, capoff);
    }

    /**
     * A comparisonsort merger Algorythm to merge two sorted Lists
     * 
     * @param Array1               smaller Array to be sorted into the List
     * @param Array2               Array sorted into
     * @param elementorderfilepath Order of Characters the
     * 
     * @return The merged Array
     */
    public static String[] mergeSortedArrays(String[] Array1, String[] Array2, String elementorderfilepath) {

        long starttime = System.nanoTime();
        ArrayList<String> sortlist = new ArrayList<String>(Arrays.asList(Array1));
        HashMap<Character, Integer> counterrefference = new HashMap<Character, Integer>();
        {
            String[] orderinfo = Filemanager.getallLinesFromFile(elementorderfilepath);
            for (int i = 0; i < orderinfo.length; i++) {
                for (char charakter : orderinfo[i].toCharArray()) {
                    // System.out.println("matched charakter:" + charakter + " with Index:" + i);
                    counterrefference.put(charakter, i);
                }
            }
        }

        int A1Index = 0;
        for (int i = 0; i < Array2.length; i++) {
            if (firstStringbool(Array2[i], Array1[A1Index], counterrefference)) {
                continue;
            } else {
                sortlist.add((i + A1Index), Array2[i]);
                A1Index++;
            }
        }
        String[] returnArray = new String[Array1.length + Array2.length];
        Filemanager.println(
                String.format("%-32s%32f", "sortmerge Arrays", (System.nanoTime() - starttime) * Math.pow(10, -6)));
        return sortlist.toArray(returnArray);

    }
    
    /**
     * this mill merge two sorted chunk of an ArrayList<String> to one sorted chunk on the same ArrayList
     * @param sortliste         ArrayList with Elements
     * @param IndexBoarder1     first index of first chunk
     * @param IndexBoarder2     first index of second chunk
     * @param IndexBoarder3     first index after second chunk
     * @param referencemap      reference HashMap with char, int key, value pairs to find the priority of each char
     * @return
     */
    public static ArrayList<String> mergeSortedArrayListRegions(ArrayList<String> sortliste, int IndexBoarder1, int IndexBoarder2, 
            int IndexBoarder3, HashMap<Character, Integer> referencemap ) {

        int i = IndexBoarder1;
        while (i < IndexBoarder2) {
            if (!firstStringbool(sortliste.get(i), sortliste.get(IndexBoarder2), referencemap)) {
                sortliste.add((i), sortliste.get(IndexBoarder2));
                IndexBoarder2++;
                i++;
                sortliste.remove(IndexBoarder2);
            }else{
                i++;
            }
        }
        return sortliste;

    }

    /**
     * this mill merge two sorted chunk of an ArrayList<String> to one sorted chunk
     * on the same ArrayList VersionII, which is a little cleaner than version 1 
     * 
     * @param sortliste     ArrayList with Elements
     * @param IndexBoarder1 first index of first chunk
     * @param IndexBoarder2 first index of second chunk
     * @param IndexBoarder3 first index after second chunk
     * @param referencemap  reference HashMap with char, int key, value pairs to
     *                      find the priority of each char
     * @return
     */
    public static ArrayList<String> mergeSortedArrayListRegionsII(ArrayList<String> sortliste, int IndexBoarder1,
            int IndexBoarder2, int IndexBoarder3, HashMap<Character, Integer> referencemap) {

        int c = IndexBoarder1;
        int i = IndexBoarder2;
        while (i < IndexBoarder3) {                                                         // interates over 2nd listpart
            if (firstStringbool(sortliste.get(i), sortliste.get(c), referencemap)) {
                sortliste.add(c, sortliste.get(i));
                sortliste.remove(i + 1);                                                    // remove old element i, but it is moved bc of the insert (--> +1)
                i++;                                                                        // take next character from index
                c++;                                                                        // bc was moved to the right through the insert
            } else {
                c++;                                                                        //compare to next character
            }
            if (c > i)break;
        }
        return sortliste;

    }
    
    /** 
     * merges presorted Array chunks. 
     * @param sortlist      ArrayList of elements ordered in at least 2 chunks (--> otherwise nothing will really happpen)
     * @param referencemap  A HashMap containing the char --> int value pair to define the order in which characters are supoosed to be sortet
     * @param qualsOnly     if true it merges unequal chunks as well, this will always result in one chunk up to the index of chunk[2] parameter
     * @param chunklist     An Arraylists that has stored each chunk as an int (length of the chunk)
     * @param chunk         The boarders of the last two chunks, {first index first chunk, first Index second chunk, index of last element of chunk 2 + 1} Only have to pass in chunk[3], which has to describe the first index not merged in any chunk so far
     * 
     * @return (not really nessesary) ArrayList with merged Chunks
     */
    public static ArrayList<String> mergeArrayListe(ArrayList<String> sortlist, HashMap<Character, Integer> referencemap, boolean equalsOnly, 
            ArrayList<Integer> chunklist, int[] chunk){
        
        chunk[0] = chunk[2] - chunklist.get(chunklist.size() - 1) - chunklist.get(chunklist.size() - 2);
        chunk[1] = chunk[2] - chunklist.get(chunklist.size() - 1);
        while((chunklist.size() >= 2 && ((int) chunklist.get(chunklist.size() - 1) == (int) chunklist.get(chunklist.size() - 2))) || !equalsOnly ){
            mergeSortedArrayListRegionsII(sortlist, chunk[0], chunk[1], chunk[2], referencemap);
            
            chunklist.add(chunk[2] - chunk[0]);
            chunklist.remove(chunklist.size() - 2);
            chunklist.remove(chunklist.size() - 2);
            chunk[1] = chunk[0]; 
            chunk[0] = chunk[0] - ((chunklist.size() < 2) ? chunklist.size() : (int) chunklist.get((int)chunklist.size() - 2));
            if (chunk[2] == sortlist.size() && chunklist.size() == 1)break;
        }

        return sortlist;

    }

    /**
     * adds up an ArrayList<Integer>
     * @param list
     * @return
     */
    public static int sumArrayList(ArrayList<Integer> list){
        int sum =0;
        for(int i = 0; i < list.size(); i++){
            sum += list.get(i);
        }
        return sum;
        
    }

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
        
        String[] outputarray = new String[Inputarray.length];                                       // second array for sorting algorithm
        HashMap<Character, Integer> counterrefference = new HashMap<Character, Integer>();          // referenceMap to map a charakter to its sortingvalue
        String[] orderinfo = Filemanager.getallLinesFromFile(elementorderfilepath);                 // load reference map values from the file
        int[] counterarray = new int[orderinfo.length];                                             // initialize counter array, lenght = amount of different values Elements are differenciated by
        long starttime = System.nanoTime();


        // fill counterrefferenceHashMap 
        {
            for (int i = 0; i < orderinfo.length; i++){
                for(char charakter : orderinfo[i].toCharArray()){
                    //System.out.println("matched charakter:" + charakter + " with Index:" + i);
                    counterrefference.put(charakter, i);
                }
            }   
        }
        System.out.println("load counterreferenceHashMap" + (starttime - System.nanoTime()));
        

        // add _ to front of each String, so that they all have the same lenght --> O(max(n), n), 
        {
            int max = 0;
            String add = orderinfo[0];
            //finding the longest String in Inputarray
            for (int i = 0; i < Inputarray.length; i++) {
                Inputarray[i].strip();
                if (Inputarray[i].length() > max) {
                    max = Inputarray[i].length();
                }
            }
            /*
            int[] lengthdistribution = new int[max];
            for (int i = 0; i < Inputarray.length; i++) {
                lengthdistribution[Inputarray[i].length() - 1]++;
            }
            for (int i = 0; i < lengthdistribution.length; i++) {
                System.out.println("Index:" + i + "--> Value: " + lengthdistribution[i]);
            }
            */
            
            System.out.println("max(length(inputarray)) =  " + max);
            //adding the first charakter from orderinfofile to fill all strings to same lenght
            for (int i = 0; i < Inputarray.length; i++) {
                add = "";
                for (int c = 0; c < max - Inputarray[i].length(); c++) {
                    add += orderinfo[0];
                }
                Inputarray[i] = Inputarray[i] + add;
            }
        }

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
                if(Inputarray[index] == null) continue;
                outputarray[--counterarray[counterrefference.get(Inputarray[index].charAt(m))]] = Inputarray[index];
            }

            //Stringoperations.printStringArray(outputarray);
            Inputarray = outputarray;                               // just setting the reference doesnt work somehow, peopably because the Inputarray is a parameter, but I dont know
                                                                    // if you only set the reference Inputarray sets some Indexes to null -->
                                                                    // causes errors
            //Stringoperations.printStringArray(Inputarray);                
            
            counterarray = new int[orderinfo.length];
        }
        
        // removeing the temporary charakters
        {
            for (int i = 0; i< outputarray.length; i++) {
                outputarray[i] = outputarray[i].split(orderinfo[0])[0];
            }
        }
        return outputarray;
    }

    /**
     * randixsort algorithm for stringsorting O(n, maxlength(n)) using an InsertSort for the longst Elements
     * 
     * @param Inputarray           given Array with Strings to sort
     * @param elementorderfilepath needed to define the sortingpriority of each
     *                             charakter. Each char in the Inputarray has to be
     *                             defined here
     * @param randixcappoff        the percentage of shortest Strings that is sorted by the radix algorithm, the rest is sorted by Insertsort
     *                             This decreases rundtime since radix has a proportional runtime to the length of the longest element
     * 
     * @return sorted Array of the Strings
     */
    public static String[] radixInsertSortAlgorythm(String[] Inputarray, String elementorderfilepath, double randixcappoff) {

        long starttime = System.nanoTime();
                                        // second array for sorting algorithm
        HashMap<Character, Integer> counterrefference = new HashMap<Character, Integer>();      // referenceMap to map a
                                                                                                // character to its
                                                                                                // sortingvalue
        String[] orderinfo = Filemanager.getallLinesFromFile(elementorderfilepath);             // load reference map values from
                                                                                                // the file
        int[] counterarray = new int[orderinfo.length];                                         // initialize counter array, lenght = amount of different values
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
        
        Filemanager.printtp( "load counterreferenceHashMap" , (System.nanoTime() - starttime) * Math.pow(10, -6));
        long E2time = System.nanoTime();

        int max = 0;
        // finding the longest String in Inputarray
        for (int i = 0; i < Inputarray.length; i++) {
            Inputarray[i].strip();
            if (Inputarray[i].length() > max) {
                max = Inputarray[i].length();
            }
        }

        Filemanager.printtp( "find max:" , (System.nanoTime() - E2time) * Math.pow(10, -6));
        long E3time = System.nanoTime();

        int[] lengthdistribution = new int[max];    // value of element represents the amount of Elements in Inputqarray with that length
        int covered = 0;                            // runnervariable, which stores the max amount of values to be sorted by randix
        int distributionindex = 0;

        //fills the lengthdistribution 
        for (int i = 0; i < Inputarray.length; i++) {
            lengthdistribution[Inputarray[i].length() - 1]++;
        }

        Filemanager.printtp( "lengthdistribution" , (System.nanoTime() - E3time) * Math.pow(10, -6));
        long E4time = System.nanoTime();

        //calculates covered and the capoffpoint for length
        while (((double)covered/Inputarray.length) < randixcappoff){
            covered += lengthdistribution[distributionindex];
            distributionindex++;
        }

        String[] randixInputArray = new String[covered];
        String[] InsertInputArray = new String[Inputarray.length - covered];
        int filledcounter = 0;
        for (int i = 0; i < Inputarray.length; i++) {
            if ((Inputarray[i].length()) <= distributionindex){
                randixInputArray[filledcounter] = Inputarray[i];
                filledcounter++;
            }else{
                InsertInputArray[i - filledcounter] = Inputarray[i];
            }
        }

        Filemanager.printtp( "split into both Arrays " , (System.nanoTime() - E4time) * Math.pow(10, -6));
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
        
        Filemanager.printtp( "radix characteradding ", (System.nanoTime() - E5time) * Math.pow(10, -6));
        long E6time = System.nanoTime();

        //radixsort sorting algorythm
        String[] randixoutputarray = new String[Inputarray.length];

        if(randixInputArray.length>0)
        for (int m = randixInputArray[0].length() - 1; m >= 0; m--) {
            randixoutputarray = new String[randixInputArray.length];

            for (int i = 0; i < randixInputArray.length; i++) {
                try {
                    counterarray[counterrefference.get(randixInputArray[i].charAt(m))]++;
                } catch (java.lang.NullPointerException e) {
                    Filemanager.println("Error" + e + " in line 204, A character was not in reference HashMap, word:"
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
                    randixoutputarray[--counterarray[counterrefference.get(randixInputArray[index].charAt(m))]] = randixInputArray[index];
                } catch (java.lang.NullPointerException e) {
                    Filemanager.println("Error" + e + ": \n A character was not in reference HashMap, word: "
                            + randixInputArray[index] + " Character: " + randixInputArray[index].charAt(m));
                    Filemanager.println(counterrefference.get(randixInputArray[index].charAt(m)));
                }
                
            }

            // Stringoperations.printStringArray(outputarray);
            randixInputArray = randixoutputarray; // just setting the reference doesnt work somehow, peopably because the
                                      // Inputarray is a parameter, but I dont know
                                      // if you only set the reference Inputarray sets some Indexes to null -->
                                      // causes errors
            // Stringoperations.printStringArray(Inputarray);

            counterarray = new int[orderinfo.length];
        }

        Filemanager.printtp( "radix sort" , (System.nanoTime() - E6time) * Math.pow(10, -6));
        long E7time = System.nanoTime();

        //removeing the temporary charakters
        if (randixInputArray.length > 0){
            for (int i = 0; i < randixoutputarray.length; i++) {
                randixoutputarray[i] = randixoutputarray[i].replace(orderinfo[0], " ").strip();
            }
        }

        Filemanager.printtp( "removing temp chars" , (System.nanoTime() - E7time) * Math.pow(10, -6));
        long E8time = System.nanoTime();
        

        // InsertSort the other long Elements
        String[] outputarray;
        if(InsertInputArray.length >= 1){
            ArrayList<String> insertlist = new ArrayList<String>(0);

            //move sorted randixoutputarray into an ArrayList to insertsort the longer Elements, invers order for Insertalgorithm
            if (randixInputArray.length > 0) for (int i = randixoutputarray.length - 1; i >= 0; i--) {
                insertlist.add(randixoutputarray[i]);
            }

            // if randixoutputarray is empty, there is no first Element to compare to, this would return an error
            if (insertlist.size() == 0){
                insertlist.add(InsertInputArray[0]);
            }

            Filemanager.printtp( "copy to list(Insert Sort)" , (System.nanoTime() - E8time) * Math.pow(10, -6));
            long E9time = System.nanoTime();
            
            // Insertsorting the long Elements into the Insertarray. Invers order Index 0 = last  
            int compareIndex;                                   
            int InsertListLastIndex = insertlist.size() - 1;     //last index of the Arraylist  
            for (int i = 0; i < InsertInputArray.length; i++) {  
                compareIndex = 0;
                while (firstStringbool(InsertInputArray[i], insertlist.get(compareIndex++), counterrefference)) {
                    if (compareIndex == InsertListLastIndex) {
                        break;
                    }
                }
                insertlist.add(compareIndex, InsertInputArray[i]);  //add the Element 
                InsertListLastIndex++;                              //incrase last Index
            }
            Filemanager.printtp( "average sortingtime: ", (System.nanoTime() - E9time) * Math.pow(10, -6)/ (double)InsertInputArray.length);
            
            outputarray = Stringoperations.convertArrayListtoArray(insertlist, -1);
        }
        else {
            outputarray = randixoutputarray;
        }

        Filemanager.printtp(  "InsertSort:" ,(System.nanoTime() - E8time) * Math.pow(10, -6));
        Filemanager.printtp(  "radixinsert sort complete: " , (System.nanoTime() - starttime)*Math.pow(10,-6));
        return outputarray;
    }

    /**
     * randixsort algorithm for stringsorting O(n * maxlength(n))
     * 
     * @param Inputarray           given Array with Strings to sort
     * @param elementorderfilepath needed to define the sortingpriority of each
     *                             charakter. Each char in the Inputarray has to be
     *                             defined here
     * @param randixcappoff        the percentage of shortest Strings that is sorted
     *                             by the radix algorithm, the rest is sorted by
     *                             Insertsort and then merged to the Array. This decreases rundtime since radix
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

        Filemanager.printtp( "load counterreferenceHashMap", (System.nanoTime() - starttime) * Math.pow(10, -6));
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

        Filemanager.printtp( "split into both Arrays ", (System.nanoTime() - E4time) * Math.pow(10, -6));
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

        Filemanager.printtp( "radix characteradding ", (System.nanoTime() - E5time) * Math.pow(10, -6));
        long E6time = System.nanoTime();

        // radixsort sorting algorythm
        String[] randixoutputarray = new String[Inputarray.length];

        if (randixInputArray.length > 0){
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

        Filemanager.printtp( "radix sort", (System.nanoTime() - E6time) * Math.pow(10, -6));
        long E7time = System.nanoTime();

        // removeing the temporary charakters
        if (randixInputArray.length > 0) {
            for (int i = 0; i < randixoutputarray.length; i++) {
                randixoutputarray[i] = randixoutputarray[i].replace(orderinfo[0], " ").strip();
            }
        }

        Filemanager.printtp( "removing temp chars", (System.nanoTime() - E7time) * Math.pow(10, -6));
        long E8time = System.nanoTime();

        // InsertSort the other long Elements
        String[] outputarray;
        if (InsertInputArray.length >= 1) {
            outputarray = mergeSortedArrays(StringInsertSort(InsertInputArray), randixoutputarray , elementorderfilepath); 
        } else {
            outputarray = randixoutputarray;
        }

        Filemanager.printtp( "InsertSort:", (System.nanoTime() - E8time) * Math.pow(10, -6));
        Filemanager.printtp( "radixinsert sort complete: ", (System.nanoTime() - starttime) * Math.pow(10, -6));
        return outputarray;
    }

    /**
     * Mergesort Algorithm with Insert Elements. Firstlayer sorting done with an Insertsort Algorithm, then chunks are merged.
     * 
     * @param Inputarray            Array to be sorted
     * @param elementorderfilepath  filepath of char order map
     * @param seperatorval          desired layers of merging.
     * @return Sorted Array (Inputarray)
     */
    public static String[] mergeInsertSort(String[] Inputarray, String elementorderfilepath, int seperatorval){

        long starttime = System.nanoTime();
        ArrayList<String> sortlist = new ArrayList<String>(Arrays.asList(Inputarray));
        //loads referencemap to get order of charakters when comaring
        HashMap<Character, Integer> counterrefference = new HashMap<Character, Integer>();
        {
            String[] orderinfo = Filemanager.getallLinesFromFile(elementorderfilepath);
            for (int i = 0; i < orderinfo.length; i++) {
                for (char charakter : orderinfo[i].toCharArray()) {
                    // System.out.println("matched charakter:" + charakter + " with Index:" + i);
                    counterrefference.put(charakter, i);
                }
            }
        }

        int smalestchuncsize = (Inputarray.length / (int) Math.pow(2, seperatorval));
        int lastindex = smalestchuncsize - 1;
        int currentchunksize;
        ArrayList<Integer> chunklist = new ArrayList<Integer>();
        while(lastindex < Inputarray.length){
            int c = 0;
            currentchunksize = ((Inputarray.length - lastindex) > smalestchuncsize) ?  smalestchuncsize : Inputarray.length - lastindex;
            int lowerchunkboarder = lastindex - currentchunksize + 2;
            for (int i = lowerchunkboarder; i < lastindex + currentchunksize - 1; i++) {
                c = i;
                while (firstStringbool(sortlist.get(i), sortlist.get(c), counterrefference)) {
                    if (c == lowerchunkboarder - 1) {
                        c--;
                        break;
                    }
                    c--;
                }
                sortlist.add(c + 1, sortlist.get(i));
                sortlist.remove(i + 1);
                // System.out.println("added word at index:" + c + " word: " + inputarray[i]);
            }

            chunklist.add(currentchunksize);
            if (chunklist.size() >= 2){
                while ((int)chunklist.get(chunklist.size() - 1) == (int) chunklist.get(chunklist.size() - 2)) {
                    sortlist = mergeSortedArrayListRegionsII(sortlist, 
                            lastindex + 1  - 2 * chunklist.get(chunklist.size() - 1),
                            lastindex + 1 - chunklist.get(chunklist.size() - 1), 
                            lastindex + 1, 
                            counterrefference);
                    chunklist.add(2 * chunklist.get(chunklist.size() - 1));
                    chunklist.remove(chunklist.size() - 2);
                    chunklist.remove(chunklist.size() - 2);
                    if(chunklist.size() == 1) {
                        System.out.println("liste 1 lang, break");
                        break;}
                }
            } 
            if((Inputarray.length - lastindex < smalestchuncsize)) {
                while ((int) chunklist.get(chunklist.size() - 1) != Inputarray.length) {
                    sortlist = mergeSortedArrayListRegionsII(sortlist,
                            lastindex + 1 - chunklist.get(chunklist.size() - 1) - chunklist.get(chunklist.size() - 2),
                            lastindex + 1 - chunklist.get(chunklist.size() - 1), 
                            lastindex + 1, 
                            counterrefference);

                    chunklist.add(chunklist.get(chunklist.size() - 1) + chunklist.get(chunklist.size() - 2));
                    chunklist.remove(chunklist.size() - 2);
                    chunklist.remove(chunklist.size() - 2);
                    if (chunklist.size() == 1) {
                        break;
                    }
                }
            }
            
            lastindex += currentchunksize;
            System.out.println("Mergesort progress: ~" + (double) lastindex/Inputarray.length);
        }

        String[] returnArray = new String[Inputarray.length];
        Filemanager.println("Mergesort:");
        Filemanager.printtp("mergesort executiontime: (milliseconds) ",  (System.nanoTime() - starttime) * Math.pow(10, -6));
        return sortlist.toArray(returnArray);

    }

    /**
     * Mergesort Algorithm
     * @param Inputarray                Array to be sorted
     * @param elementorderfilepath      filepath of char order map
     * @return                          sorted Array (Inputarray)
     */
    public static String[] MergeSort(String[] Inputarray, String elementorderfilepath) {

        long starttime = System.nanoTime();
        ArrayList<String> sortlist = new ArrayList<String>(Arrays.asList(Inputarray));               //Arraylistsorted on, this algorithm might be slow, but it only uses this Arralist to do all operations on the Stringorder
        ArrayList<Integer> chunklist = new ArrayList<Integer>();                                     // list of chunks sorted
        int[] chunk = new int[]{0,0,0};                                                              // first index of first chunk, first Index of second chunk, index after second chunk
        HashMap<Character, Integer> counterrefference = new HashMap<Character, Integer>();           // The order of characters sorted by
        {
            String[] orderinfo = Filemanager.getallLinesFromFile(elementorderfilepath);
            for (int i = 0; i < orderinfo.length; i++) {
                for (char charakter : orderinfo[i].toCharArray()) {
                    // System.out.println("matched charakter:" + charakter + " with Index:" + i);
                    counterrefference.put(charakter, i);
                }
            }
        }

        while (chunk[2] < Inputarray.length){                                                                                      
            if ((chunklist.size() < 2 || !((int) chunklist.get(chunklist.size() - 1) ==  (int) chunklist.get(chunklist.size() - 2)))){
                chunklist.add(1);
                chunk[2] +=1;
            }
            if (chunklist.size() > 1) sortlist = mergeArrayListe(sortlist, counterrefference, true, chunklist, chunk);
        }
        sortlist = mergeArrayListe(sortlist, counterrefference, false, chunklist, chunk);

        Filemanager.printtp("mergesort executiontime: (milliseconds) ",
                (System.nanoTime() - starttime) * Math.pow(10, -6));
        return sortlist.toArray(Inputarray);

    }
}
