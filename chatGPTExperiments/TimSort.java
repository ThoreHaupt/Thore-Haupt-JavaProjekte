package chatGPTExperiments;

import java.util.Arrays;

public class TimSort {

    public static void main(String[] args) {
        int[] array = new int[1000];
        for (int i = 0; i < array.length; i++) {
            array[i] = (int) (Math.random() * 30000);
        }
        TimSort.sort(array);
        System.out.println(Arrays.toString(array));
    }

    private static final int MIN_RUN_LENGTH = 32;
    private static final int INSERTION_SORT_THRESHOLD = 32;

    public static void sort(int[] array) {
        if (array.length < MIN_RUN_LENGTH) {
            insertionSort(array, 0, array.length - 1);
            return;
        }

        int[] runStarts = new int[array.length / MIN_RUN_LENGTH + 1];
        int runStartIndex = 0;
        int currentRunStart = 0;

        // Identify the start of each run in the array
        while (currentRunStart < array.length) {
            int currentRunEnd = findRunEnd(array, currentRunStart);
            runStarts[runStartIndex++] = currentRunStart;
            currentRunStart = currentRunEnd + 1;
        }

        // Sort each run in the array
        int[] tempArray = new int[array.length];
        for (int i = 0; i < runStartIndex; i++) {
            int runStart = runStarts[i];
            int runEnd = (i == runStartIndex - 1) ? array.length - 1 : runStarts[i + 1] - 1;
            //insertionSort(array, runStart, runEnd);
            if (i >= 1) {
                // If the current run is odd, then merge the two previous runs
                merge(array, tempArray, runStarts[i - 1], runEnd - 1, runEnd);
            } else if (i == runStartIndex - 1) {
                // If the current run is the last run, then copy it to the temporary array
                System.arraycopy(array, runStart, tempArray, runStart, runEnd - runStart + 1);
            }
        }

        if (runStartIndex % 2 == 1) {
            // If the number of runs is odd, then merge the two last runs
            int runStart = runStarts[runStartIndex - 2];
            int runEnd = runStarts[runStartIndex - 1] - 1;
            merge(tempArray, array, runStart, runEnd - 1, runEnd);
        } else {
            // Otherwise, copy the last run from the temporary array back to the original array
            int runStart = runStarts[runStartIndex - 1];
            int runEnd = array.length - 1;
            System.arraycopy(tempArray, runStart, array, runStart, runEnd - runStart + 1);
        }
    }

    private static void insertionSort(int[] array, int start, int end) {
        for (int i = start + 1; i <= end; i++) {
            int key = array[i];
            int j = i - 1;
            while (j >= start && array[j] > key) {
                array[j + 1] = array[j];
                j--;
            }
            array[j + 1] = key;
        }
    }

    private static int findRunEnd(int[] array, int start) {
        int runEnd = start + 1;
        while (runEnd < array.length && array[runEnd] >= array[runEnd - 1]) {
            runEnd++;
        }
        int runLength = runEnd - start;
        if (runLength < MIN_RUN_LENGTH) {
            int insertionSortThreshold = Math.min(start + MIN_RUN_LENGTH, array.length - 1);
            insertionSort(array, start, insertionSortThreshold);
            runLength = insertionSortThreshold - start + 1;
        }
        return start + runLength - 1;
    }

    private static void merge(int[] sourceArray, int[] targetArray, int start, int middle, int end) {
        int i = start;
        int j = middle + 1;
        for (int k = start; k <= end; k++) {
            if (i > middle) {
                targetArray[k] = sourceArray[j++];
            } else if (j > end) {
                targetArray[k] = sourceArray[i++];
            } else if (sourceArray[i] < sourceArray[j]) {
                targetArray[k] = sourceArray[i++];
            } else {
                targetArray[k] = sourceArray[j++];
            }
        }
    }
}