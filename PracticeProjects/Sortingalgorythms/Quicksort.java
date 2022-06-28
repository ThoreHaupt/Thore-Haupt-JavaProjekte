package PracticeProjects.Sortingalgorythms;

import java.lang.invoke.SwitchPoint;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Quicksort {
    static int[] arr;

    public static void main(String[] args) {
        int groeße = 5;
        List<Integer> l = Stream.generate(Math::random).map(x -> (int) (x * groeße) + 1).limit(groeße)
                .collect(Collectors.toList());
        arr = l.stream().mapToInt(x -> (int) x).toArray();
        /* arr = Stream.iterate(1, x -> ++x).limit(groeße)
                .collect(Collectors.toList()).stream().mapToInt(x -> (int) x).toArray(); */
        quicksort(0, arr.length - 1);
        System.out.println(Arrays.toString(arr));
    }

    public static void quicksort(int li, int re) {
        int pivot = arr[li];
        int i = pivot + 1;
        int j = re;
        int[] loc = arr;

        System.out.print(li + "-" + re + " :" + Arrays.toString(arr) + " ->");

        while (i < j) {

            while (arr[i] <= pivot && i < re + 1) {
                i++;
            }
            while (arr[j] >= pivot && j > li) {
                j--;
            }
            if (i == j && arr[i] == arr[j]) {
                //j--;
                //switchIndexes(i, j);
                //break;
            }
            if (j > i)
                switchIndexes(i, j);
        }

        switchIndexes(j, li);
        System.out.println(Arrays.toString(arr));
        if (j > li)
            quicksort(li, j - 1);
        if (i < re - 1)
            quicksort(j + 1, re);
    }

    static void switchIndexes(int a, int b) {
        int temp = arr[a];
        arr[a] = arr[b];
        arr[b] = temp;
        System.out.print(" s(" + a + "," + b + ")");
    }
}