package chatGPTExperiments;

import java.util.*;

public class TTimSort {

    private static final int MIN_RUN_LENGTH = 32;

    private static int[] arr;
    private static int[] temp;
    private static int[] runs;

    public static void main(String[] args) {
        int[] array = new int[100000];
        for (int i = 0; i < array.length; i++) {
            array[i] = (int) (Math.random() * 3000000);
        }
        int[] array2 = new int[array.length];
        System.arraycopy(array, 0, array2, 0, array.length);
        long l1 = System.nanoTime();
        TTimSort.sort(array);
        System.out.println("TTimSort: " + (System.nanoTime() - l1) / 1000);
        long l2 = System.nanoTime();
        Arrays.sort(array2);
        System.out.println("TimSort: " + (System.nanoTime() - l2) / 1000);
        //System.out.println(Arrays.toString(array));
    }

    public static void sort(int[] arr) {
        sort(arr, 0, arr.length);
    }

    public static void sort(int[] arr, int lo, int hi) {
        TTimSort.arr = arr;
        TTimSort.temp = new int[arr.length];

        organizeRuns(lo, hi);
        // merge Runs

        // Queue with all run size Differences to choose adjasent Subarrays with lowest size difference
        PriorityQueue<RunDesc<Integer, Integer>> subArrDiffs = new PriorityQueue<RunDesc<Integer, Integer>>();
        // maintain collection of all SubArrayDiff objects to remove from Queue
        ArrayList<RunDesc<Integer, Integer>> subArrSizes = new ArrayList<RunDesc<Integer, Integer>>(runs.length);

        // copy values from runs array to linked list
        for (int i = 0; i < runs.length - 2; i++) {
            RunDesc<Integer, Integer> newEl = new RunDesc<Integer, Integer>(
                    Math.abs(runs[i + 1] - runs[i] - (runs[i + 2] - runs[i + 1])), runs[i]);
            subArrSizes.add(newEl);
            subArrDiffs.add(newEl);
        }
        RunDesc<Integer, Integer> newElLast = new RunDesc<Integer, Integer>(
                Math.abs(runs[runs.length - 2] - runs[runs.length - 3]
                        - (runs[runs.length - 1] - runs[runs.length - 2])),
                runs[runs.length - 2]);
        subArrSizes.add(newElLast);
        subArrDiffs.add(newElLast);

        RunDesc<Integer, Integer> lastEl = new RunDesc<Integer, Integer>(
                Integer.MAX_VALUE, runs[runs.length - 1]);
        subArrSizes.add(lastEl);

        while (subArrDiffs.size() > 0) {
            // find most equal
            RunDesc<Integer, Integer> lowestDiffDesc = subArrDiffs.poll();
            int runArrIndex = findByValue(subArrSizes, lowestDiffDesc.b.intValue());
            int hiIndex = (runArrIndex + 2 < subArrSizes.size()) ? subArrSizes.get(runArrIndex + 2).b : hi;
            merge(subArrSizes.get(runArrIndex).b.intValue(), hiIndex, subArrSizes.get(runArrIndex + 1).b.intValue());

            // remove merged Array
            // remove difference of consumed SubArray from priority Queue

            subArrDiffs.remove(subArrSizes.get(runArrIndex + 1)); // got merched away
            subArrSizes.remove(runArrIndex + 1);

            // update the prev difference of the subArray before (if current aint the first subArr)
            //abs(current subAr len - prev subAr len)
            if (runArrIndex > 0 && runArrIndex < subArrSizes.size() - 1) {
                subArrSizes.get(runArrIndex).a = Math
                        .abs((subArrSizes.get(runArrIndex + 1).b - lowestDiffDesc.b)
                                - (lowestDiffDesc.b - subArrSizes.get(runArrIndex - 1).b));
                subArrDiffs.remove(subArrSizes.get(runArrIndex - 1));
                subArrDiffs.add(subArrSizes.get(runArrIndex - 1));
            }

            // abs(current subAr len - next subAr len)
            // if current aint the last subArr
            if (runArrIndex < subArrSizes.size() - 1) {
                lowestDiffDesc.a = Math
                        .abs((subArrSizes.get(runArrIndex + 1).b - lowestDiffDesc.b) - (hiIndex
                                - subArrSizes.get(runArrIndex + 1).b));
                subArrDiffs.add(lowestDiffDesc);
            } else {

            }
        }
    }

    private static void organizeRuns(int lo, int hi) {
        ArrayList<Integer> tempRun = new ArrayList<>();
        int currentIndex = lo;
        int currentChainStart = currentIndex;
        boolean splittedSubArray = false;
        hi = Math.min(hi, arr.length);
        while (currentIndex < hi) {
            tempRun.add(currentIndex);
            //currentIndex += Math.min(MIN_RUN_LENGTH, arr.length - currentIndex);
            while (MIN_RUN_LENGTH > currentIndex - tempRun.get(tempRun.size() - 1) && currentIndex < hi) {
                currentChainStart = currentIndex;
                currentIndex++;
                while (currentIndex < hi && arr[currentIndex - 1] <= arr[currentIndex]) {
                    currentIndex++;
                }
                if (currentIndex - currentChainStart > MIN_RUN_LENGTH) {
                    tempRun.add(currentChainStart);
                    splittedSubArray = true;
                    break;
                }
            }
            currentIndex = Math.min(hi, currentIndex++);
            if (splittedSubArray) {
                binarySort(tempRun.get(tempRun.size() - 2), tempRun.get(tempRun.size() - 1));
                splittedSubArray = false;
            } else {
                binarySort(tempRun.get(tempRun.size() - 1), currentIndex);
            }
        }
        TTimSort.runs = tempRun.stream().mapToInt(Integer::intValue).toArray();
    }

    private static void merge(int lo, int hi, int middle) {

        int subArrayIndex1 = lo;
        int subArrayIndex2 = middle;
        for (int i = lo; i < hi; i++) {
            if (arr[subArrayIndex1] < arr[subArrayIndex2]) {
                temp[i] = arr[subArrayIndex1++];
            } else {
                temp[i] = arr[subArrayIndex2++];
            }
            if (subArrayIndex1 > middle - 1 || subArrayIndex2 > hi - 1) {
                i++;
                for (; i < hi; i++) {
                    if (subArrayIndex1 > middle - 1) {
                        temp[i] = arr[subArrayIndex2++];
                    } else {
                        temp[i] = arr[subArrayIndex1++];
                    }
                }
                break;
            }
        }

        System.arraycopy(temp, lo, arr, lo, hi - lo);
    }

    private static void binarySort(int lo, int hi) {
        int start = lo + 1;
        for (; start < hi; start++) {
            int pivot = arr[start];

            int left = lo;
            int right = start;
            while (left < right) {
                int mid = (left + right) >>> 1;
                if (pivot < arr[mid]) {
                    right = mid;
                } else {
                    left = mid + 1;
                }
            }

            int n = start - left;
            switch (n) {
                case 2:
                    arr[left + 2] = arr[left + 1];
                case 1:
                    arr[left + 1] = arr[left];
                    break;
                default:
                    System.arraycopy(arr, left, arr, left + 1, n);
            }
            arr[left] = pivot;
        }
    }

    static int findByValue(ArrayList<RunDesc<Integer, Integer>> data, int val) {
        int i = 0;
        while (data.get(i).b.intValue() != val)
            i++;
        return i;
    }

    static class Element {
        int val;
        Element next;

        public Element(int val) {
            this.val = val;
        }
    }

    static class RunDesc<K extends Comparable<K>, V> implements Comparable<RunDesc<K, V>> {
        K a;
        V b;

        /**
         * @param a
         * @param b
         */
        public RunDesc(K a, V b) {
            this.a = a;
            this.b = b;
        }

        @Override
        public int compareTo(RunDesc<K, V> o) {
            // TODO Auto-generated method stub
            return a.compareTo(o.a);
        }

    }

}
