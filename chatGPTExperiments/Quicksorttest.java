package chatGPTExperiments;

import java.util.Arrays;

public class Quicksorttest<T extends Comparable<T>> {

    public static void main(String[] args) {
        Integer[] intArray = { 3, 1, 4, 2, 5, 6, 5 };
        Quicksorttest<Integer> intSorter = new Quicksorttest<>();
        intSorter.sort(intArray);
        System.out.println(Arrays.toString(intArray));
    }

    public void sort(T[] arr) {
        quickSort(arr, 0, arr.length - 1);
    }

    private void quickSort(T[] arr, int low, int high) {
        if (low < high) {
            int pivotIndex = partition(arr, low, high);
            quickSort(arr, low, pivotIndex);
            quickSort(arr, pivotIndex + 1, high);
        }
    }

    private int partition(T[] arr, int low, int high) {
        T pivot = arr[low];
        int i = low - 1;
        int j = high + 1;
        while (true) {
            do {
                i++;
            } while (arr[i].compareTo(pivot) < 0);
            do {
                j--;
            } while (arr[j].compareTo(pivot) > 0);
            if (i >= j) {
                return j;
            }
            swap(arr, i, j);
        }
    }

    private void swap(T[] arr, int i, int j) {
        T temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
}
