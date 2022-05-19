package PracticeProjects.HashMapImplementation;

import java.util.HashMap;
import java.util.Random;
import java.util.TreeSet;

public class HashMapTest {
    public static void main(String[] args) {
        THashMap<Integer, Integer> map = new THashMap<Integer, Integer>(16, 0.7f);
        // HashMap<Integer, Integer> mapOff = new HashMap<Integer, Integer>();
        TreeSet<Integer> l = new TreeSet<Integer>();
        TreeSet<Integer> s = new TreeSet<Integer>();
        int length = 1000;
        int[] array = new int[length];

        for (int i = 0; i < length; i++) {
            array[i] = i;
        }

        shuffleArray(array);
        for (int i = 0; i < length; i++) {
            map.put(array[i], i);
        }

        for (KeyValuePair<Integer, Integer> KVPair : map.asKeyValuePair()) {

            System.out.println(KVPair.toString());
        }

        for (int i = 0; i < length; i += 1) {
            map.remove(i);
        }

        System.out.println(map.size());

        /*
         * for (int i = 0; i < 10000; i++) {
         * mapOff.put(i, i);
         * 
         * }
         */

        /*
         * for (Integer integer : map) {
         * l.add(integer);
         * }
         */
        /*
         * long t1 = System.nanoTime();
         * for (int i = 0; i < length; i++) {
         * l.add(map.get(i));
         * }
         * System.out.println((System.nanoTime() - t1) / 10000);
         */
        /*
         * long t2 = System.nanoTime();
         * for (int i = 0; i < 10000; i++) {
         * s.add(mapOff.get(i));
         * }
         * System.out.println((System.nanoTime() - t2) / 10000);
         */

        // System.out.println(l);
        /*
         * System.out.println(l.size());
         * System.out.println(s.size());
         */
    }

    private static void shuffleArray(int[] array) {
        int index, temp;
        Random random = new Random();
        for (int i = array.length - 1; i > 0; i--) {
            index = random.nextInt(i + 1);
            temp = array[index];
            array[index] = array[i];
            array[i] = temp;
        }
    }
}
