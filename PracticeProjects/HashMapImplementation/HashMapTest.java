package PracticeProjects.HashMapImplementation;

import java.util.HashMap;
import java.util.TreeSet;

public class HashMapTest {
    public static void main(String[] args) {
        THashMap<Integer, Integer> map = new THashMap<Integer, Integer>();
        // HashMap<Integer, Integer> mapOff = new HashMap<Integer, Integer>();
        TreeSet<Integer> l = new TreeSet<Integer>();
        TreeSet<Integer> s = new TreeSet<Integer>();

        for (int i = 0; i < 80; i++) {
            map.put(i, i);

        }

        /*
         * for (int i = 0; i < 10000; i++) {
         * mapOff.put(i, i);
         * 
         * }
         */

        // wenn das da ist, funktioniert die HashMap, wenn nicht, dann nicht. Wired.
        /*
         * for (Integer integer : map) {
         * l.add(integer);
         * }
         */

        long t1 = System.nanoTime();
        for (int i = 0; i < 80; i++) {
            Integer integer = map.get(Integer.valueOf(i));
            if (integer == null) {
                int hash = System.identityHashCode(Integer.valueOf(i));
                System.out.println("somethings i wrong");
            }
            if (integer != null)
                l.add(integer);
        }
        System.out.println((System.nanoTime() - t1) / 10000);

        /*
         * long t2 = System.nanoTime();
         * for (int i = 0; i < 10000; i++) {
         * s.add(mapOff.get(i));
         * }
         * System.out.println((System.nanoTime() - t2) / 10000);
         */

        // System.out.println(l);
        System.out.println(l.size());
        System.out.println(s.size());
    }
}
