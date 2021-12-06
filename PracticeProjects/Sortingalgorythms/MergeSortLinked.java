package PracticeProjects.Sortingalgorythms;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import PracticeProjects.Filemanager;
import PracticeProjects.Progressbart;
import PracticeProjects.Sort;
import PracticeProjects.TLinkedList;
import PracticeProjects.TNode;

public class MergeSortLinked{


    static long sumMergeTimetogetArrayIndex = 0;


    //@SuppressWarnings("unchecked")
    public static String[] MergeSortLinkedAlgorythm(String[] Inputarray, String elementorderfilepath) {

        TLinkedList<String> sortlist = new TLinkedList<String>(Arrays.asList(Inputarray)); // Arraylistsorted on, this
                                                                                           // algorithm might be slow,
                                                                                           // but it only uses this
                                                                                           // Arralist to do all
                                                                                           // operations on the
                                                                                           // Stringorder
        ArrayList<Integer> chunklist = new ArrayList<Integer>(); // list of chunks sorted
        ArrayList<TNode<String>> chunklistNodes = new ArrayList<TNode<String>>();
        int[] chunk = new int[] { 0, 0, 0 };
        ArrayList<TNode<String>> chunkNodes = new ArrayList<TNode<String>>(); // got to supress that unchecked warning
        chunkNodes.add(sortlist.getFisTNode()); 
        chunkNodes.add(sortlist.getFisTNode());
        chunkNodes.add(sortlist.getFisTNode());
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
            if ((chunklist.size() < 2 || !(chunklist.get(chunklist.size() - 1).equals(chunklist.get(chunklist.size() - 2))))) {     // add new Chunks of size 1
                chunklist.add(1);
                chunklistNodes.add(chunkNodes.get(2));
                chunk[2] += 1;
                chunkNodes.set(2, chunkNodes.get(2).getNextNode());
            }
            if (chunklist.size() > 1 && (chunklist.get(chunklist.size() - 1).equals(chunklist.get(chunklist.size() - 2))))
                sortlist = mergeLinkedListe(sortlist, counterrefference, true, chunklist, chunklistNodes, chunk, chunkNodes);
            progressbar.update(100.0 / (Inputarray.length));
        }
        sortlist = mergeLinkedListe(sortlist, counterrefference, false, chunklist, chunklistNodes, chunk, chunkNodes);
        Filemanager.printtp("Sum Time to get init Nodes", sumMergeTimetogetArrayIndex * Math.pow(10, -6));
        
        sortlist.getNodeIndex(sortlist.size() - 1);
        sortlist.toArray(Inputarray);
        return Inputarray;

    }

    /**
     * merges presorted Array chunks.
     * 
     * @param sortlist     TLinkedList of elements ordered in at least 2 chunks (-->
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
    public static TLinkedList<String> mergeLinkedListe(TLinkedList<String> sortlist,
            HashMap<Character, Integer> referencemap, boolean equalsOnly,
            ArrayList<Integer> chunklist, ArrayList<TNode<String>> chunklistNodes, int[] chunk, ArrayList<TNode<String>> chunkNodes) {

        chunk[0] = chunk[2] - chunklist.get(chunklist.size() - 1) - chunklist.get(chunklist.size() - 2);
        chunk[1] = chunk[2] - chunklist.get(chunklist.size() - 1);
        
        chunkNodes.set(1, chunklistNodes.get(chunklistNodes.size() - 1));
        chunkNodes.set(0, chunklistNodes.get(chunklistNodes.size() - 2));        

        while ((chunklist.size() >= 2 && (chunklist.get(chunklist.size() - 1).equals(chunklist.get(chunklist.size() - 2)))) || !equalsOnly) {
            
            ArrayList<TNode<String>> chunkNodesCopy = (ArrayList<TNode<String>>) chunkNodes.clone();
            if(!(chunkNodes.get(1).equals(chunklistNodes.get(chunklistNodes.size() - 1)))){
                System.out.println(chunk[1]);
            }
            if(chunkNodesCopy.get(1) == null){
                System.out.println(chunk[1]);
            } 
            mergeSortedArrayListRegionsIII(sortlist, chunk[0], chunk[1], chunk[2], chunkNodesCopy, chunklistNodes, referencemap);

            chunklist.add(chunk[2] - chunk[0]);
            chunklist.remove(chunklist.size() - 2);
            chunklist.remove(chunklist.size() - 2);

            chunklistNodes.remove(chunklistNodes.size() - 1);

            chunk[1] = chunk[0];
            chunk[0] = chunk[0] - (chunklist.size() < 2 ? chunk[0] :(int) chunklist.get(chunklist.size() - 2));

            chunkNodes.set(1, chunklistNodes.get(chunklist.size() - 1));
            
            int chunkNode0offset = 2;
            if(chunklist.size() < 2){
                chunkNode0offset = 1;
                
            }

            chunkNodes.set(0, chunklistNodes.get(chunklist.size() - chunkNode0offset));

            if (chunk[2] == sortlist.size() && chunklist.size() == 1)
                break;
        }
        return sortlist;

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
    public static TLinkedList<String> mergeSortedArrayListRegionsIII(TLinkedList<String> sortliste, int IndexBoarder1,
            int IndexBoarder2,  int IndexBoarder3, 
            ArrayList<TNode<String>> chunkNodes, ArrayList<TNode<String>> chunklistNodes, HashMap<Character, Integer> referencemap) {

        int c = IndexBoarder1;
        int i = IndexBoarder2;

        TNode<String> firstNodeNewChunk = chunkNodes.get(0);
         

        //long current = System.nanoTime();
        //TNode<String> chunkNodes.get(0) = sortliste.getNodeIndex(c);
        //TNode<String> chunkNodes.get(1) = sortliste.getNodeIndex(i);
        //sumMergeTimetogetArrayIndex += (System.nanoTime() - current);

        while (i < IndexBoarder3) { // interates over 2nd listpart
            
            if (Sort.firstStringbool(chunkNodes.get(1).getValue(), chunkNodes.get(0).getValue(), referencemap)) {
                
                TNode<String> insertedNode = sortliste.insert(chunkNodes.get(1).getValue(), chunkNodes.get(0));
                sortliste.remove(chunkNodes.get(1)); // remove old element i, but it is moved bc of the insert (--> +1)
                //set firstNodeNewChunk, if this is the first comparison, if i comes before c. (--> then this if statement is called)
                if (c == IndexBoarder1 && i == IndexBoarder2) {
                    firstNodeNewChunk = insertedNode;
                }
                i++;
                c++;
                chunkNodes.set(1, chunkNodes.get(1).getNextNode()); // take next character from index
            } else {
                c++;
                chunkNodes.set(0,chunkNodes.get(0).getNextNode()); // compare to next character
            }
            if (chunkNodes.get(1) == null || c > i){
                break;
            }
        }
        chunklistNodes.set(chunklistNodes.size() - 2, firstNodeNewChunk);
        return sortliste;

    }

}