package testStuff;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;

public class iteratortest123 {
    public static void main(String[] args) {
        HashSet<Integer> h = new HashSet<Integer>();
        ArrayList<Integer> m = new ArrayList<Integer>();

        for (int i = 0; i < 100000; i++) {
            h.add(Integer.valueOf(i));
        }
        for (int i = 0; i < 100000; i++) {
            m.add(Integer.valueOf(i));
        }
        long current1 = System.nanoTime();
        Object[] a = h.toArray();
        double t1 = (System.nanoTime() - current1) * Math.pow(10, -6);
        System.out.println(t1);
        long current2 = System.nanoTime();
        Object[] b = m.toArray();
        double t2 = (System.nanoTime() - current2) * Math.pow(10, -6);
        System.out.println(t2);
    }
}
