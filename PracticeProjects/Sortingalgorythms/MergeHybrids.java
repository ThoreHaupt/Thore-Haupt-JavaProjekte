package PracticeProjects.Sortingalgorythms;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

import PracticeProjects.Filemanager;
import PracticeProjects.Progressbart;

public class MergeHybrids {
    
    /**
     * Mergesort Algorithm with Insert Elements. Firstlayer sorting done with an
     * Insertsort Algorithm, then chunks are merged.
     * 
     * @param Inputarray           Array to be sorted
     * @param elementorderfilepath filepath of char order map
     * @param seperatorval         desired layers of merging.
     * @return Sorted Array (Inputarray)
     */
    public static String[] mergeInsertSort(String[] Inputarray, String elementorderfilepath, int seperatorval) {

        ArrayList<String> sortlist = new ArrayList<String>(Arrays.asList(Inputarray));
        // loads referencemap to get order of charakters when comaring
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
        // Progressbar:
        Progressbart progressbar = new Progressbart("PracticeProjects/Textfiles/Console.txt", "Mergesort Progress:");

        // init chunk variables
        int smalestchuncsize = (Inputarray.length / (int) Math.pow(2, seperatorval));
        int lastindex = smalestchuncsize - 1;
        int currentchunksize;
        ArrayList<Integer> chunklist = new ArrayList<Integer>(); // chunkliste erstellt, Typ Integer
        while (lastindex < Inputarray.length) {
            int c = 0;
            currentchunksize = ((Inputarray.length - lastindex) > smalestchuncsize) ? smalestchuncsize
                    : Inputarray.length - lastindex; // wenn die kleinste chunksize größer ist als die restlichen
                                                     // Elemente auf der Arrayliste
                                                     // wird der momentanige Chunk auf den Rest gesetzt
            int lowerchunkboarder = lastindex - currentchunksize + 2;

            // insert Sort für den kleinsten Chunk
            for (int i = lowerchunkboarder; i < lastindex + currentchunksize - 1; i++) {
                c = i;
                while (Common.firstStringbool(sortlist.get(i), sortlist.get(c), counterrefference)) {
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

            chunklist.add(currentchunksize); // Hier wird ein Element der chunkliste hinzugefügt
            if (chunklist.size() >= 2) {
                // gleich große chunks zusammenfügen, bis die letzten beiden chunks aus
                // chunkliste nicht mehr gleich groß sind.
                while (chunklist.get(chunklist.size() - 1).equals(chunklist.get(chunklist.size() - 2))) { // Hier
                                                                                                          // ist
                                                                                                          // die
                                                                                                          // besagte
                                                                                                          // while
                                                                                                          // Schleife,
                                                                                                          // Ohne
                                                                                                          // (int)
                                                                                                          // gibt
                                                                                                          // dieses
                                                                                                          // Statement
                                                                                                          // false
                                                                                                          // zurück,
                                                                                                          // sobald
                                                                                                          // die
                                                                                                          // größe
                                                                                                          // der
                                                                                                          // Chunks
                                                                                                          // einen
                                                                                                          // bestimmten
                                                                                                          // Wert
                                                                                                          // überschreitet.
                    sortlist = MergeSortArrayList.mergeSortedArrayListRegionsII(sortlist,
                            lastindex + 1 - 2 * chunklist.get(chunklist.size() - 1),
                            lastindex + 1 - chunklist.get(chunklist.size() - 1), lastindex + 1, counterrefference);
                    chunklist.add(2 * chunklist.get(chunklist.size() - 1));
                    chunklist.remove(chunklist.size() - 2);
                    chunklist.remove(chunklist.size() - 2);
                    if (chunklist.size() == 1) {
                        break;
                    }
                }
            }
            // Am Ende die letzten chunks zusammentuen
            if ((Inputarray.length - lastindex < smalestchuncsize)) {
                while ((int) chunklist.get(chunklist.size() - 1) != Inputarray.length) {
                    sortlist = MergeSortArrayList.mergeSortedArrayListRegionsII(sortlist,
                            lastindex + 1 - chunklist.get(chunklist.size() - 1) - chunklist.get(chunklist.size() - 2),
                            lastindex + 1 - chunklist.get(chunklist.size() - 1), lastindex + 1, counterrefference);

                    chunklist.add(chunklist.get(chunklist.size() - 1) + chunklist.get(chunklist.size() - 2));
                    chunklist.remove(chunklist.size() - 2);
                    chunklist.remove(chunklist.size() - 2);
                    if (chunklist.size() == 1) {
                        break;
                    }
                }
            }
            progressbar.update((100.0 / Inputarray.length) * currentchunksize);
            lastindex += currentchunksize;
        }

        String[] returnArray = new String[Inputarray.length];
        return sortlist.toArray(returnArray);

    }

}
