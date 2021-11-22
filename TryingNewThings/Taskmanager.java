package TryingNewThings;

import java.util.ArrayList;
import java.util.HashMap;

public class Taskmanager{
    public static void main(String[] args){
        
        compareSortingalgorythems();
    }

    public static void compareSortingalgorythems(){
        /** comapres the speed of my InsertSort algorythem and my Implementation of a Randix sort, both for strings */
        Filemanager.println("Sorting Test of 6 different sorting algorithms: -----------------------------");
        int AmountOfElementsTobeSorted = 100;
        Filemanager.printtp("Amount of Items sorted:", AmountOfElementsTobeSorted);
        Filemanager.println("");
        String[] strings = Stringoperations.createRandomStringArray(AmountOfElementsTobeSorted);
        Filemanager.println("-----------------------------------------------------------------------------");

        double t1 = testInsertSortingAlgorythems(strings);
        testradixAlgorythems(strings, t1);

        Filemanager.println("-----------------------------------------------------------------------------");
        
    }

    public static double testInsertSortingAlgorythems(String[] strings){
        
        
        Filemanager.println("------ Insert - Sortingalgorithms -----------------------------I");
        long current;
        /*
        // 
        Filemanager.println("------ InsertI -------------------------");
        current = System.nanoTime();
        String[] sortedString1 = Sort.StringInsertSort(strings);
        double t1 = (System.nanoTime() - current) * Math.pow(10, -6);

        Filemanager.printtp("StringInsertSort time in microseconds: ", t1);
        Filemanager.printtp("ratio (Insert/Insert): ", (double) (t1 / t1));
        
        Filemanager.createFile("TryingNewThings/Textfiles/Sortiert4.txt", true);
        Filemanager.writeToFile("TryingNewThings/Textfiles/Sortiert4.txt", sortedString1, true);
        Filemanager.println("Saved Insert I to file 4...");
        
        Filemanager.println("");

        // InsertII
        Filemanager.println("------ InsertII ------------------------");
        current = System.nanoTime();
        String[] sortedString2 = Sort.StringInsertSortII(strings);
        double t2 = (System.nanoTime() - current) * Math.pow(10, -6);

        Filemanager.printtp("StringInsertSort time in microseconds: ", t1);
        Filemanager.printtp("ratio (Insert/Insert): ", (double) (t1 / t2));
        
        Filemanager.createFile("TryingNewThings/Textfiles/Sortiert5.txt", true);
        Filemanager.writeToFile("TryingNewThings/Textfiles/Sortiert5.txt", sortedString2, true);
        Filemanager.println("Saved Insert II to file 5...");
        
        Filemanager.println("");

        */
        
        // MergeSort
        Filemanager.println("------ MergeSort ------------------------");
        current = System.nanoTime();
        String[] sortedString3 = Sort.mergeSort(strings, "TryingNewThings/Textfiles/CharacterOrder.txt", 
                (int) ((Math.log(strings.length) / Math.log(2))/1.2));
        double t3 = (System.nanoTime() - current) * Math.pow(10, -6);

        Filemanager.printtp("StringInsertSort time in microseconds: ", t3);
        Filemanager.printtp("ratio (MergeSort/MergeSort): ", (double) (t3 / t3));
        Filemanager.println("");

        Filemanager.createFile("TryingNewThings/Textfiles/Sortiert6.txt", true);
        Filemanager.writeToFile("TryingNewThings/Textfiles/Sortiert6.txt", sortedString3, true);
        Filemanager.println("Saved MergeSort to file 6...");
        
        Filemanager.println("");
    
        return t3;
    }

    public static void testradixAlgorythems(String[] strings, double t1){
        long current;

        Filemanager.println("------ Radix - Sortingalgorithms -----------------------------I");
        
        
        // RadixSort
        Filemanager.println("------ RadixSort ---------------------");
        current = System.nanoTime();
        
        String[] sortedString1 = Sort.radixsort(strings, "TryingNewThings/Textfiles/CharacterOrder.txt");
        
        double r1 = (System.nanoTime() - current) * Math.pow(10, -6);

        Filemanager.printtp("RadixSort time in microseconds: ", r1);
        Filemanager.printtp("ratio (Insert/radix): ", (double) (t1 / r1));

        Filemanager.createFile("TryingNewThings/Textfiles/Sortiert1.txt", true);
        Filemanager.writeToFile("TryingNewThings/Textfiles/Sortiert1.txt", sortedString1, true);
        Filemanager.println("Saved radix Sort to file 1...");

        Filemanager.println("");



        // RadixInsertSort
        Filemanager.println("------ RadixInsertSort ---------------");
        current = System.nanoTime();
        String[] sortedString2 = Sort.radixInsertSort(strings, "TryingNewThings/Textfiles/CharacterOrder.txt", .9d);
        double t2 = (System.nanoTime() - current) * Math.pow(10, -6);

        Filemanager.printtp("radixInsertSort time in microseconds: ", t2);
        Filemanager.printtp("ratio (Insert/radixInsert): ", (double) (t1 / t2));
        
        Filemanager.createFile("TryingNewThings/Textfiles/Sortiert2.txt", true);
        Filemanager.writeToFile("TryingNewThings/Textfiles/Sortiert2.txt", sortedString2, true);
        Filemanager.println("Saved RadixInsertSort to file 2...");

        Filemanager.println("");

        // radixMergeInsertSort
        Filemanager.println("------ radixMergeInsertSort ----------");
        current = System.nanoTime();
        String[] sortedString3 = Sort.radixMergeInsertSort(strings, "TryingNewThings/Textfiles/CharacterOrder.txt",
                .9d);
        double t4 = (System.nanoTime() - current) * Math.pow(10, -6);

        Filemanager.printtp("radixMergeInsertSort time in microseconds: ", t4);
        Filemanager.printtp("ratio (Insert/radixMergeInsertSort): ", (double) (t1 / t4));
        
        Filemanager.createFile("TryingNewThings/Textfiles/Sortiert3.txt", true);
        Filemanager.writeToFile("TryingNewThings/Textfiles/Sortiert3.txt", sortedString3, true);
        Filemanager.println("Saved RadixInsertSort to file 3...");

        Filemanager.println("");

        

        

        
    }

    public static void testmerge(){
        HashMap<Character, Integer> counterrefference = new HashMap<Character, Integer>();
        {
            String[] orderinfo = Filemanager.getallLinesFromFile("TryingNewThings/Textfiles/CharacterOrder.txt");
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
        Sort.mergeSortedArrayListRegionsII(test, 0, 3, 5, counterrefference);
        for (String string : test) {
            System.out.println(string);
        }
    }
}
