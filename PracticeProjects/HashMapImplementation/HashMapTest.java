package PracticeProjects.HashMapImplementation;

import java.util.TreeSet;

public class HashMapTest {
    public static void main(String[] args) {
        THashMap<Integer, Integer> map = new THashMap<Integer, Integer>();
        TreeSet<Integer> l = new TreeSet<Integer>();
        map.put(2, 1);
        map.put(2, 2);

        for (int i = 0; i < 10000; i++) {
            map.put(i, i);
        }
        for (Integer integer : map) {
            l.add(integer);
        }

        long t1 = System.nanoTime();
        for (int i = 0; i < 10000; i++) {
            map.get(i);
        }
        System.out.println(System.nanoTime() - t1);

        // System.out.println(l);
        System.out.println(l.size());
    }
}
