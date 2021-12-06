package PracticeProjects.Sortingalgorythms;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

import PracticeProjects.Filemanager;
import PracticeProjects.Progressbart;

public class MergeSortArrayList{
    
    /**
     * Mergesort Algorithm
     * 
     * @param Inputarray           Array to be sorted
     * @param elementorderfilepath filepath of char order map
     * @return sorted Array (Inputarray)
     */
    public static String[] MergeSort(String[] Inputarray, String elementorderfilepath) {

        ArrayList<String> sortlist = new ArrayList<String>(Arrays.asList(Inputarray)); // Arraylistsorted on, this
                                                                                       // algorithm might be slow, but
                                                                                       // it only uses this Arralist to
                                                                                       // do all operations on the
                                                                                       // Stringorder
        ArrayList<Integer> chunklist = new ArrayList<Integer>(); // list of chunks sorted
        int[] chunk = new int[] { 0, 0, 0 };
        Progressbart progressbar = new Progressbart("PracticeProjects/Textfiles/Console.txt", "Mergesort Progress:");
        // first index of first chunk, first Index of second chunk, index after second
        // chunk
        HashMap<Character, Integer> counterrefference = new HashMap<Character, Integer>(); // The order of characters
                                                                                           // sorted by
        {
            String[] orderinfo = Filemanager.getallLinesFromFile(elementorderfilepath);
            for (int i = 0; i < orderinfo.length; i++) {
                for (char charakter : orderinfo[i].toCharArray()) {
                    // System.out.println("matched charakter:" + charakter + " with Index:" + i);
                    counterrefference.put(charakter, i);
                }
            }
        }

        while (chunk[2] < Inputarray.length) {
            if ((chunklist.size() < 2
                    || !((int) chunklist.get(chunklist.size() - 1) == (int) chunklist.get(chunklist.size() - 2)))) {
                chunklist.add(1);
                chunk[2] += 1;
            }
            if (chunklist.size() > 1)
                sortlist = Common.mergeArrayListe(sortlist, counterrefference, true, chunklist, chunk);
            progressbar.update(100.0 / (Inputarray.length));
        }
        sortlist = Common.mergeArrayListe(sortlist, counterrefference, false, chunklist, chunk);

        return sortlist.toArray(Inputarray);

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
        ArrayList<String> sortlist = new ArrayList<String>(Arrays.asList(Array2));
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
            if (Common.firstStringbool(Array2[i], Array1[A1Index], counterrefference)) {
                continue;
            } else {
                sortlist.add((i + A1Index), Array1[A1Index]);
                if (A1Index < Array1.length - 1) {
                    A1Index++;
                } else
                    break;
            }
        }
        String[] returnArray = new String[Array1.length + Array2.length];
        Filemanager.println(
                String.format("%-32s%32f", "sortmerge Arrays", (System.nanoTime() - starttime) * Math.pow(10, -6)));
        return sortlist.toArray(returnArray);

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
        while (i < IndexBoarder3) { // interates over 2nd listpart
            if (Common.firstStringbool(sortliste.get(i), sortliste.get(c), referencemap)) {
                sortliste.add(c, sortliste.get(i));
                sortliste.remove(i + 1); // remove old element i, but it is moved bc of the insert (--> +1)
                i++; // take next character from index
                c++; // bc was moved to the right through the insert
            } else {
                c++; // compare to next character
            }
            if (c > i)
                break;
        }
        return sortliste;

    }

    /**
     * this mill merge two sorted chunk of an ArrayList<String> to one sorted chunk
     * on the same ArrayList
     * 
     * @param sortliste     ArrayList with Elements
     * @param IndexBoarder1 first index of first chunk
     * @param IndexBoarder2 first index of second chunk
     * @param IndexBoarder3 first index after second chunk
     * @param referencemap  reference HashMap with char, int key, value pairs to
     *                      find the priority of each char
     * @return
     */
    public static ArrayList<String> mergeSortedArrayListRegions(ArrayList<String> sortliste, int IndexBoarder1,
            int IndexBoarder2,
            int IndexBoarder3, HashMap<Character, Integer> referencemap) {

        int i = IndexBoarder1;
        while (i < IndexBoarder2) {
            if (!Common.firstStringbool(sortliste.get(i), sortliste.get(IndexBoarder2), referencemap)) {
                sortliste.add((i), sortliste.get(IndexBoarder2));
                IndexBoarder2++;
                i++;
                sortliste.remove(IndexBoarder2);
            } else {
                i++;
            }
        }
        return sortliste;

    }

}