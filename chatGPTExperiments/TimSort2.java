package chatGPTExperiments;

import java.util.Arrays;

public class TimSort2 {

    private static final int MIN_MERGE = 32;

    private final int[] array;
    private final int[] tmp;

    public TimSort2(int[] array) {
        this.array = array;
        tmp = new int[array.length];
    }

    private void merge(int lo, int mid, int hi) {
        int i = lo, j = mid + 1, k = lo;
        while (i <= mid && j <= hi) {
            if (array[i] < array[j]) {
                tmp[k++] = array[i++];
            } else {
                tmp[k++] = array[j++];
            }
        }
        while (i <= mid) {
            tmp[k++] = array[i++];
        }
        while (j <= hi) {
            tmp[k++] = array[j++];
        }
        System.arraycopy(tmp, lo, array, lo, hi - lo + 1);
    }

    private void sort(int lo, int hi) {
        if (hi - lo < MIN_MERGE) {
            for (int i = lo; i <= hi; i++) {
                for (int j = i; j > lo && array[j - 1] > array[j]; j--) {
                    int tmp = array[j];
                    array[j] = array[j - 1];
                    array[j - 1] = tmp;
                }
            }
            return;
        }

        int mid = (hi + lo) / 2;
        sort(lo, mid);
        sort(mid + 1, hi);
        if (array[mid] <= array[mid + 1]) {
            return;
        }
        merge(lo, mid, hi);
    }

    public void sort() {
        sort(0, array.length - 1);
    }

    private static void binarySort(int[] a, int lo, int hi) {
        int start = lo + 1;
        for (; start < hi; start++) {
            int pivot = a[start];

            int left = lo;
            int right = start;
            while (left < right) {
                int mid = (left + right) >>> 1;
                if (pivot < a[mid]) {
                    right = mid;
                } else {
                    left = mid + 1;
                }
            }

            int n = start - left;
            switch (n) {
                case 2:
                    a[left + 2] = a[left + 1];
                case 1:
                    a[left + 1] = a[left];
                    break;
                default:
                    System.arraycopy(a, left, a, left + 1, n);
            }
            a[left] = pivot;
        }
    }

    public static void main(String[] args) {
        int[] array = new int[1000];
        for (int i = 0; i < array.length; i++) {
            array[i] = (int) (Math.random() * 30000);
        }
        /* TimSort2 timSort = new TimSort2(array);
        timSort.sort(); */
        TimSort2.binarySort(array, 0, array.length);
        System.out.println(Arrays.toString(array));
    }
}