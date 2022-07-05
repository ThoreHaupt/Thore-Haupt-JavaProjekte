package PracticeProjects.Sortingalgorythms;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Quicksort {
    static int[] arr;

    public static void main(String[] args) {
        int groeße = 100000000;
        List<Integer> l = Stream.generate(Math::random).map(x -> (int) (x * groeße) + 1).limit(groeße)
                .collect(Collectors.toList());
        arr = l.stream().mapToInt(x -> (int) x).toArray();
        long t = System.currentTimeMillis();
        quicksort(0, arr.length - 1);
        System.out.println(System.currentTimeMillis() - t);
        int[] arr2 = l.stream().mapToInt(x -> (int) x).toArray();
        long t2 = System.currentTimeMillis();
        Arrays.sort(arr2);
        System.out.println(System.currentTimeMillis() - t2);
    }

    public static void quicksort(int li, int re) {
        int pivot = arr[li];
        int i = li;
        int j = re;

        if (li == re)
            return;
        // System.out.print(li + "-" + re + " :" + Arrays.toString(arr) + " ->");

        while (i < j) {
            while (arr[i] <= pivot && i < re) {
                i++;
            }
            while (arr[j] > pivot && j > li) {
                j--;
            }
            if (i == j && arr[i] == arr[j]) {
                break;
            }
            if (j > i)
                switchIndexes(i, j);
        }

        switchIndexes(j, li);
        //System.out.println(Arrays.toString(arr));
        if (j > li)
            quicksort(li, j - 1);
        if (i < re - 1)
            quicksort(i, re);
    }

    static void switchIndexes(int a, int b) {
        int temp = arr[a];
        arr[a] = arr[b];
        arr[b] = temp;
        // System.out.print(" s(" + a + "," + b + ")");
    }
}