package PracticeProjects;

import java.util.ArrayList;
import java.util.HashMap;

/**
 *
 */
public class Taskmanager{
    public static void main(String[] args){
        
        compareSortingalgorithms();
        //testmerge();
    }

    public static void compareSortingalgorithms(){
        /** comapres the speed of my InsertSort algorithm and my Implementation of a Randix sort, both for strings */
        Filemanager.println("\n\nSorting Test of 7 different sorting algorithms: -----------------------------");
        int AmountOfElementsTobeSorted = 60000;
        Filemanager.printtp("Amount of Items sorted:", AmountOfElementsTobeSorted);
        Filemanager.println("");
        String[] strings = Stringoperations.createRandomStringArray(AmountOfElementsTobeSorted);
        Filemanager.println("-----------------------------------------------------------------------------");

        double t1 = testInsertSortingalgorithms(strings);          //t1 is the reference time for the other algorithms
        testradixalgorithms(strings, t1);

        Filemanager.println("-----------------------------------------------------------------------------");
        
    }

    public static double testInsertSortingalgorithms(String[] strings){
        
        
        Filemanager.println("------ Comaprison based - Sortingalgorithms ------------------------I");
        long current;
        
        // Insert Sort my first implementation
        Filemanager.println("------ InsertI -------------------------");
        current = System.nanoTime();
        String[] sortedString1 = Sort.StringInsertSort(strings); // <--calling the Algorithm
        double t1 = (System.nanoTime() - current) * Math.pow(10, -6);

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
        String[] sortedString2 = Sort.StringInsertSortII(strings);  // <--calling the Algorithm
        double t2 = (System.nanoTime() - current) * Math.pow(10, -6);

        Filemanager.printtp("StringInsertSort time in milliseconds: ", t2);
        Filemanager.printtp("Time per Element: ", t2 / strings.length);
        Filemanager.printtp("ratio (Insert/Insert): ", (double) (t1 / t2));
        
        Filemanager.createFile("PracticeProjects/Textfiles/sortedTextoutputfiles/Sortiert5.txt", true);
        Filemanager.writeToFile("PracticeProjects/Textfiles/sortedTextoutputfiles/Sortiert5.txt", sortedString2, true);
        Filemanager.println("Saved Insert II to file 5...");
        
        Filemanager.println("");

       
        
        // MergeSort 
        Filemanager.println("------ MergeSort ------------------------");
        current = System.nanoTime();
        String[] sortedString3 = Sort.MergeSort(strings, "PracticeProjects/Textfiles/CharacterOrder.txt"); // <--calling the Algorithm
        double t3 = (System.nanoTime() - current) * Math.pow(10, -6);

        Filemanager.printtp("MergeSort time in milliseconds: ", t3);
        Filemanager.printtp("Time per Element: ", t3 / strings.length);
        Filemanager.printtp("ratio (InsertSort 1/MergeSort): ", (double) (t1 / t3));
        Filemanager.println("");

        Filemanager.createFile("PracticeProjects/Textfiles/sortedTextoutputfiles/Sortiert6.txt", true);
        Filemanager.writeToFile("PracticeProjects/Textfiles/sortedTextoutputfiles/Sortiert6.txt", sortedString3, true);
        Filemanager.println("Saved MergeSort to file 6...");
        
        Filemanager.println("");

        // MergeInsertSort
        Filemanager.println("------ MergeInsertSort ------------------------");
        current = System.nanoTime();
        String[] sortedString4 = Sort.mergeInsertSort(strings, "PracticeProjects/Textfiles/CharacterOrder.txt", 6); // <--calling the Algorithm
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
        current = System.nanoTime();
        
        String[] sortedString1 = Sort.radixsort(strings, "PracticeProjects/Textfiles/CharacterOrder.txt");
        
        double r1 = (System.nanoTime() - current) * Math.pow(10, -6);

        Filemanager.printtp("RadixSort time in milliseconds: ", r1);
        Filemanager.printtp("Time per Element: ", r1 / strings.length);
        Filemanager.printtp("ratio (Insert/radix): ", (double) (t1 / r1));

        Filemanager.createFile("PracticeProjects/Textfiles/sortedTextoutputfiles/Sortiert1.txt", true);
        Filemanager.writeToFile("PracticeProjects/Textfiles/sortedTextoutputfiles/Sortiert1.txt", sortedString1, true);
        Filemanager.println("Saved radix Sort to file 1...");

        Filemanager.println("");



        // RadixInsertSort
        Filemanager.println("------ RadixInsertSort ---------------");
        current = System.nanoTime();
        String[] sortedString2 = Sort.radixInsertSort(strings, "PracticeProjects/Textfiles/CharacterOrder.txt", .9d);
        double t2 = (System.nanoTime() - current) * Math.pow(10, -6);

        Filemanager.printtp("radixInsertSort time in milliseconds: ", t2);
        Filemanager.printtp("Time per Element: ", t2 / strings.length);
        Filemanager.printtp("ratio (Insert/radixInsert): ", (double) (t1 / t2));
        
        Filemanager.createFile("PracticeProjects/Textfiles/sortedTextoutputfiles/Sortiert2.txt", true);
        Filemanager.writeToFile("PracticeProjects/Textfiles/sortedTextoutputfiles/Sortiert2.txt", sortedString2, true);
        Filemanager.println("Saved RadixInsertSort to file 2...");

        Filemanager.println("");

        // radixMergeInsertSort
        Filemanager.println("------ radixMergeInsertSort ----------");
        current = System.nanoTime();
        String[] sortedString3 = Sort.radixMergeInsertSort(strings, "PracticeProjects/Textfiles/CharacterOrder.txt",
                .9d);
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
        Sort.mergeSortedArrayListRegionsII(test, 0, 3, 6, counterrefference);
        for (String string : test) {
            System.out.println(string);
        }
    }
}
