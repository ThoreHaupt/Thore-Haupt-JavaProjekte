package PracticeProjects.ArrayListStuff;

import PracticeProjects.TLinkedList;

public class compareArrayLists {
    public static void main(String[] args) {

        TArrayList<Integer> L = new TArrayList<Integer>();
        TArrayList<Integer> L1 = new TArrayList<Integer>();
        TArrayList2<Integer> L2 = new TArrayList2<Integer>();
        TLinkedList<Integer> L3 = new TLinkedList<Integer>();
        int sizeVar = 10;

        for (int i = 0; i < sizeVar; i++) {
            L.add(i);
        }

        long t1 = System.nanoTime();
        for (int i = 0; i < sizeVar; i++) {
            L1.add(i);
            L1.insert(i, i / 2);
        }
        L1.add(L);
        System.out.println("L1: Time = " + (System.nanoTime() - t1));

        long t2 = System.nanoTime();
        for (int i = 0; i < sizeVar; i++) {
            L2.add(i);
            L2.insert(i, i / 2);
        }
        L2.add(L);

        System.out.println("L2: Time = " + (System.nanoTime() - t2));

        long t3 = System.nanoTime();
        for (int i = 0; i < sizeVar; i++) {
            L3.add(i);
            L3.insert(i, i / 2);
        }

        System.out.println("L3: Time = " + (System.nanoTime() - t3));
    }
}
