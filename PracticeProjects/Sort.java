package PracticeProjects;

import java.util.ArrayList;

import PracticeProjects.Sortingalgorythms.MergeSortLinked;
import PracticeProjects.Sortingalgorythms.MergeSortLinkedII;
import PracticeProjects.Sortingalgorythms.RadixHybrids;
import PracticeProjects.Sortingalgorythms.RadixSort;

/**some random sorting method */
public class Sort {

    

    /**
     * randixsort algorithm for stringsorting O(n, maxlength(n)) sortorder by
     * default from /Textfiles/CharakterOrder
     * 
     * @param Inputarray given Array with Strings to sort
     * 
     * @return sorted Array of the Strings
     */
    public static String[] radixsort(String[] Inputarray){
        return RadixSort.radixsortAlgorythm(Inputarray, "PracticeProjects/Textfiles/CharacterOrder.txt");
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
        
        return RadixSort.radixsortAlgorythm(Inputarray, elementorderfilepath);
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
        return RadixHybrids.radixInsertSortAlgorythm(Inputarray, "PracticeProjects/Textfiles/CharacterOrder.txt", .9d); 
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
        return RadixHybrids.radixInsertSortAlgorythm(Inputarray, path, capoff);
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
        return RadixHybrids.radixInsertMergeSortAlgorythm(Inputarray, path, capoff);
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


    public static String[] MergeSortLinked(String[] Inputarray, String elementorderfilepath){
        return MergeSortLinkedII.MergeSortLinkedAlgorythm(Inputarray, elementorderfilepath);
    }

}
