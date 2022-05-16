package PracticeProjects;

import java.util.ArrayList;
import java.util.HashMap;

public class findKomplements {
    public static void main(String[] args) {
        HashMap<Integer, ArrayList<Integer>> komplements = new HashMap<Integer, ArrayList<Integer>>();
        int target = 10;
        int[] array = { 8, 2, 3, 5, 6, 7, 34, 2, 3, 4, 2, 4, 3, 3, 5, 234, 3, 8 };

        for (int i = 0; i < array.length; i++) {
            int komplement = target - array[i];
            if (komplements.containsKey(komplement)) {
                komplements.get(komplement).add(i);
            } else {
                komplements.put(array[i], new ArrayList<Integer>());
            }
        }

        System.out.println(komplements.toString());

        for (Integer key : komplements.keySet()) {
            for (Integer i : komplements.get(key)) {
                System.out.println("" + (key) + " " + array[i.intValue()]);
            }
        }
    }

}
