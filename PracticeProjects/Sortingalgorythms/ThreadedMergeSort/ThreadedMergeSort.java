package PracticeProjects.Sortingalgorythms.ThreadedMergeSort;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

import PracticeProjects.Filemanager;
import PracticeProjects.Progressbart;
import PracticeProjects.TLinkedList;
import PracticeProjects.TNode;

public class ThreadedMergeSort {

    public static void main(String[] args) {
        String[] a = PracticeProjects.Stringoperations.createRandomStringArray(10000);
        TLinkedList<String> liste = new TLinkedList<>(Arrays.asList(a));
        MergeSortLinkedAlgorythmThreaded(liste, "PracticeProjects/Textfiles/CharacterOrder.txt");
        sortList.toArray(a);
        Filemanager.createFile("PracticeProjects/Textfiles/sortedTextoutputfiles/Sortiert9.txt", true);
        Filemanager.writeToFile("PracticeProjects/Textfiles/sortedTextoutputfiles/Sortiert9.txt", a, true);
        Filemanager.println("Saved MergeSort to file 9...");
    }



    public static int livingThreads = 0;
    public static int maxThreads = 3;

    public static TLinkedList<String> sortList;
    public static TLinkedList<Chunk<String>> chunkList;
    public static HashMap<Character, Integer> counterrefference;
    public static ArrayList<MergeWorker<String>> Threads;

    public static void MergeSortLinkedAlgorythmThreaded(TLinkedList<String> sortList, String elementorderfilepath) {
        chunkList = new TLinkedList<Chunk<String>>(); // list of chunks sorted
        sortList = sortList;
        Progressbart progressbar = new Progressbart("PracticeProjects/Textfiles/Console.txt", "Mergesort Progress:");
        // first index of first chunk, first Index of second chunk, index after second
        // chunk

        counterrefference = new HashMap<Character, Integer>(); // The order of characters
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
        MergeWorker newThread = new MergeWorker(sortList, chunkList, Thread.currentThread(), true, counterrefference);
        Threads.add(newThread);
        livingThreads++;
        

    }

    public static void closeThread(Thread t){
        livingThreads--;
    }

    public static void createThread(){
        if (maxThreads<livingThreads){
            Threads.add(new MergeWorker(sortList, chunkList, Thread.currentThread(), false, counterrefference));
            livingThreads++;
        }
    }
}
