package PracticeProjects.BitManTests;

import java.util.ArrayList;
import java.util.Arrays;

public class BitOperationStuff {
    public static void main(String[] args) {
        /* int i = 1;
        char c = 'c';
        int cval = c - 97;
        System.out.println(bitVals(i));
        i = i << cval;
        System.out.println(bitVals(i)); */
        char[] arr = new char[60];
        for (int j = 0; j < 34; j++) {
            arr[j] = (char) (j % 13 + 97);
        }
        for (int j = 34; j < arr.length; j++) {
            arr[j] = (char) (j + 97 - 34);
        }
        System.out.println(Arrays.toString(arr));
        System.out.println(findSubstring(arr));
        char[] arr2 = new char[14];
        System.arraycopy(arr, findSubstring(arr) - 14, arr2, 0, 14);
        System.out.println(Arrays.toString(arr2));
    }

    public static String bitVals(int i) {
        String o = "";
        for (int j = 0; j < 32; j++) {
            o = Math.abs(i % 2) + o;
            i = i >>> 1;
        }
        return o;
    }

    public static int findSubstring(char[] arr) {
        int bitMask = 1;
        int appMask = 0;
        int appMaskD = 0;
        int currentIndex = 0;
        int currentLastIndex = 0;
        int currentLength = 0;

        appMask = (1 << arr[currentIndex] - 97);
        bitMask = 0;
        while (currentLength < 14) {
            if ((bitMask & appMask) != 0) {
                //remove stuff

                appMaskD = appMask;

                // remove values from in front of the chunk
                while (true) {
                    appMask = 1 << (arr[currentLastIndex++] - 97);
                    if (appMask == appMaskD) {
                        break;
                    } else {
                        // remove char before border
                        bitMask = bitMask ^ appMask;
                        currentLength--;
                    }
                }
                // add values unitl we reach currentIndex
                // if new double then set
            } else {
                // add appMask and 
                bitMask = bitMask ^ appMask;
                currentLength++;
            }

            appMask = (1 << (arr[++currentIndex] - 97));
        }
        return currentIndex;
    }
}
