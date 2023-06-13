package AIFB;

import java.util.Comparator;

public class test2 implements Comparable<test2>, Comparator<test2> {
    private int i = 0;

    public static void main(String[] args) {
        System.out.println();
    }

    public int compareTo(test2 other) {
        return 0;
    }

    public int compare(test2 o1, test2 o2) {
        return i;
    }

}
