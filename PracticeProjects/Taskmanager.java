package PracticeProjects;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

import PracticeProjects.Sortingalgorythms.MergeHybrids;
import PracticeProjects.Sortingalgorythms.MergeSortArrayList;

/**
 *
 */
public class Taskmanager{
    public static void main(String[] args){
        
        /*
        TLinkedList<Integer> testlist = new TLinkedList<>();
        Integer[] intarr = new Integer[1000000];
        TNode<Integer> node = new TNode();
        for (int i = 0; i < 1000000; i++) {
            testlist.add(i);
            if(i == 500000){node = testlist.getLastNode();}
            intarr[i] = i;
        }
        testlist.get(node);
        
        long current = System.nanoTime();
        //testlist.insert(-1, node);
        //testlist.remove(node.getNextNode().getNextNode());
        System.out.println(testlist.get(node.getNextNode()));

        ArrayList<Integer> a = new ArrayList<Integer>(Arrays.asList(intarr));
        TLinkedList<Integer> testlistII = new TLinkedList<Integer>(a);
        System.out.println(testlistII.get(500000));
        System.out.println(System.nanoTime() - current);
        */


        compareSortingalgorithms();
        //testmerge();

    }

    public static void compareSortingalgorithms(){
        /** comapres the speed of my InsertSort algorithm and my Implementation of a Randix sort, both for strings */
        Filemanager.println("\n\nSorting Test of 7 different sorting algorithms: -----------------------------");
        int AmountOfElementsTobeSorted = 10000;
        Filemanager.printtp("Amount of Items sorted:", AmountOfElementsTobeSorted);
        Filemanager.println("");
        String[] strings = Stringoperations.createRandomStringArray(AmountOfElementsTobeSorted);
        Sort.MergeSortLinked(strings, "PracticeProjects/Textfiles/CharacterOrder.txt");
        Filemanager.println("-----------------------------------------------------------------------------");

        double t1 = 100000; 
        testInsertSortingalgorithms(strings);          //t1 is the reference time for the other algorithms
        testradixalgorithms(strings, t1);

        Filemanager.println("------ Comaprison based - Sortingalgorithms ------------------------I");

        String[] stringII = strings.clone();
        long current = System.nanoTime();
        Arrays.sort(stringII); // <--calling the Algorithm
        double t2 = (System.nanoTime() - current) * Math.pow(10, -6);
        Filemanager.printtp("Collections time in milliseconds: ", t2);
        Filemanager.printtp("Time per Element: ", t2 / strings.length);
        Filemanager.printtp("ratio (Insert/TimSort): ", (double) (t1 / t2));


        Filemanager.println("-----------------------------------------------------------------------------");
        
    }

    public static double testInsertSortingalgorithms(String[] strings){
        
        
        Filemanager.println("------ Comaprison based - Sortingalgorithms ------------------------I");
        long current;
        double t1 = 100000;
        
        /*
        // Insert Sort my first implementation
        Filemanager.println("------ InsertI -------------------------");
        current = System.nanoTime();
        String[] sortedString1 = Sort.StringInsertSort(strings.clone()); // <--calling the Algorithm
        t1 = (System.nanoTime() - current) * Math.pow(10, -6);

        Filemanager.printtp("StringInsertSort time in milliseconds: ", t1);
        Filemanager.printtp("Time per Element: ", t1 / strings.length);
        Filemanager.printtp("ratio (Insert/Insert): ", (double) (t1 / t1));
        
        Filemanager.createFile("PracticeProjects/Textfiles/sortedTextoutputfiles/Sortiert4.txt", true);
        Filemanager.writeToFile("PracticeProjects/Textfiles/sortedTextoutputfiles/Sortiert4.txt", sortedString1, true);
        Filemanager.println("Saved Insert I to file 4...");
        
        Filemanager.println("");

        // InsertII 2. Implementation
        Filemanager.println("------ InsertII ------------------------");
        current = System.nanoTime();
        String[] sortedString2 = Sort.StringInsertSortII(strings.clone());  // <--calling the Algorithm
        double t2 = (System.nanoTime() - current) * Math.pow(10, -6);

        Filemanager.printtp("StringInsertSort time in milliseconds: ", t2);
        Filemanager.printtp("Time per Element: ", t2 / strings.length);
        Filemanager.printtp("ratio (Insert/Insert): ", (double) (t1 / t2));
        
        Filemanager.createFile("PracticeProjects/Textfiles/sortedTextoutputfiles/Sortiert5.txt", true);
        Filemanager.writeToFile("PracticeProjects/Textfiles/sortedTextoutputfiles/Sortiert5.txt", sortedString2, true);
        Filemanager.println("Saved Insert II to file 5...");
        
        Filemanager.println("");
        */
       
        
        // MergeSort 
        Filemanager.println("------ MergeSort ------------------------");
        current = System.nanoTime();
        String[] sortedString3 = MergeSortArrayList.MergeSort(strings.clone(), "PracticeProjects/Textfiles/CharacterOrder.txt"); // <--calling the Algorithm
        double t3 = (System.nanoTime() - current) * Math.pow(10, -6);

        Filemanager.printtp("MergeSort time in milliseconds: ", t3);
        Filemanager.printtp("Time per Element: ", t3 / strings.length);
        Filemanager.printtp("ratio (InsertSort 1/MergeSort): ", (double) (t1 / t3));
        Filemanager.println("");

        Filemanager.createFile("PracticeProjects/Textfiles/sortedTextoutputfiles/Sortiert6.txt", true);
        Filemanager.writeToFile("PracticeProjects/Textfiles/sortedTextoutputfiles/Sortiert6.txt", sortedString3, true);
        Filemanager.println("Saved MergeSort to file 6...");
        
        Filemanager.println("");

        // MergeSortLinked
        Filemanager.println("------ MergeSortLinked ----------------");
        current = System.nanoTime();
        String[] sortedString5 = Sort.MergeSortLinked(strings.clone(), "PracticeProjects/Textfiles/CharacterOrder.txt"); // <--calling
                                                                                                           // the
                                                                                                           // Algorithm
        double t5 = (System.nanoTime() - current) * Math.pow(10, -6);

        Filemanager.printtp("MergeSort time in milliseconds: ", t5);
        Filemanager.printtp("Time per Element: ", t5 / strings.length);
        Filemanager.printtp("ratio (InsertSort 1/MergeSortLinked): ", (double) (t1 / t5));
        Filemanager.println("");

        Filemanager.createFile("PracticeProjects/Textfiles/sortedTextoutputfiles/Sortiert8.txt", true);
        Filemanager.writeToFile("PracticeProjects/Textfiles/sortedTextoutputfiles/Sortiert8.txt", sortedString5, true);
        Filemanager.println("Saved MergeSort to file 8...");

        Filemanager.println("");

        // MergeSortLinkedII
        Filemanager.println("------ MergeSortLinkedII ----------------");
        current = System.nanoTime();
        String[] sortedString6 = Sort.MergeSortLinkedII(strings.clone(), "PracticeProjects/Textfiles/CharacterOrder.txt"); // <--calling
        // the
        // Algorithm
        double t6 = (System.nanoTime() - current) * Math.pow(10, -6);

        Filemanager.printtp("MergeSort time in milliseconds: ", t6);
        Filemanager.printtp("Time per Element: ", t6 / strings.length);
        Filemanager.printtp("ratio (InsertSort 1/MergeSortLinkedII): ", (double) (t1 / t6));
        Filemanager.println("");

        Filemanager.createFile("PracticeProjects/Textfiles/sortedTextoutputfiles/Sortiert9.txt", true);
        Filemanager.writeToFile("PracticeProjects/Textfiles/sortedTextoutputfiles/Sortiert9.txt", sortedString6, true);
        Filemanager.println("Saved MergeSort to file 9...");

        Filemanager.println("");



        // MergeInsertSort
        Filemanager.println("------ MergeInsertSort ------------------------");
        current = System.nanoTime();
        String[] sortedString4 = MergeHybrids.mergeInsertSort(
                strings.clone(), "PracticeProjects/Textfiles/CharacterOrder.txt", 6); // <--calling the Algorithm
        double t4 = (System.nanoTime() - current) * Math.pow(10, -6);

        Filemanager.printtp("MergeInsertSort time in milliseconds: ", t4);
        Filemanager.printtp("Time per Element: ", t4 / strings.length);
        Filemanager.printtp("ratio (InsertSort 1/MergeInsertSort): ", (double) (t1 / t4));
        Filemanager.println("");

        Filemanager.createFile("PracticeProjects/Textfiles/sortedTextoutputfiles/Sortiert7.txt", true);
        Filemanager.writeToFile("PracticeProjects/Textfiles/sortedTextoutputfiles/Sortiert7.txt", sortedString4, true);
        Filemanager.println("Saved MergeInsertSort to file 7...");

        Filemanager.println("");

    
        return t1;
    }

    public static void testradixalgorithms(String[] strings, double t1){
        long current;

        Filemanager.println("------ Radix - Sortingalgorithms -----------------------------I");
        
        
        // RadixSort
        Filemanager.println("------ RadixSort ---------------------");
        String[] str1 = strings.clone();
        current = System.nanoTime();
        String[] sortedString1 = Sort.radixsort(str1, "PracticeProjects/Textfiles/CharacterOrder.txt");
        double r1 = (System.nanoTime() - current) * Math.pow(10, -6);

        Filemanager.printtp("RadixSort time in milliseconds: ", r1);
        Filemanager.printtp("Time per Element: ", r1 / strings.length);
        Filemanager.printtp("ratio (Insert/radix): ", (double) (t1 / r1));

        Filemanager.createFile("PracticeProjects/Textfiles/sortedTextoutputfiles/Sortiert1.txt", true);
        Filemanager.writeToFile("PracticeProjects/Textfiles/sortedTextoutputfiles/Sortiert1.txt", sortedString1, true);
        Filemanager.println("Saved radix Sort to file 1...");

        Filemanager.println("");


        /*
        // RadixInsertSort
        Filemanager.println("------ RadixInsertSort ---------------");
        String[] str2 = strings.clone();
        current = System.nanoTime();
        String[] sortedString2 = Sort.radixInsertSort(str2, "PracticeProjects/Textfiles/CharacterOrder.txt", .99d);
        double t2 = (System.nanoTime() - current) * Math.pow(10, -6);

        Filemanager.printtp("radixInsertSort time in milliseconds: ", t2);
        Filemanager.printtp("Time per Element: ", t2 / strings.length);
        Filemanager.printtp("ratio (Insert/radixInsert): ", (double) (t1 / t2));
        
        Filemanager.createFile("PracticeProjects/Textfiles/sortedTextoutputfiles/Sortiert2.txt", true);
        Filemanager.writeToFile("PracticeProjects/Textfiles/sortedTextoutputfiles/Sortiert2.txt", sortedString2, true);
        Filemanager.println("Saved RadixInsertSort to file 2...");

        Filemanager.println("");

        */

        
        // radixMergeInsertSort
        Filemanager.println("------ radixMergeInsertSort ----------");
        String[] str3 = strings.clone();
        current = System.nanoTime();
        String[] sortedString3 = Sort.radixMergeInsertSort(str3, "PracticeProjects/Textfiles/CharacterOrder.txt",
                .98d);
        double t3 = (System.nanoTime() - current) * Math.pow(10, -6);

        Filemanager.printtp("radixMergeInsertSort time in milliseconds: ", t3);
        Filemanager.printtp("Time per Element: ", t3 / strings.length);
        Filemanager.printtp("ratio (Insert/radixMergeInsertSort): ", (double) (t1 / t3));
        
        Filemanager.createFile("PracticeProjects/Textfiles/sortedTextoutputfiles/Sortiert3.txt", true);
        Filemanager.writeToFile("PracticeProjects/Textfiles/sortedTextoutputfiles/Sortiert3.txt", sortedString3, true);
        Filemanager.println("Saved RadixInsertSort to file 3...");

        Filemanager.println("");

        

        

        
    }

    public static void testmerge(){
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

        ArrayList<String> test = new ArrayList<>();
        test.add("ba");
        test.add("c");
        test.add("d");
        test.add("aa");
        test.add("ab");
        test.add("ca");
        MergeSortArrayList.mergeSortedArrayListRegionsII(test, 0, 3, 6, counterrefference);
        for (String string : test) {
            System.out.println(string);
        }
    }
}
