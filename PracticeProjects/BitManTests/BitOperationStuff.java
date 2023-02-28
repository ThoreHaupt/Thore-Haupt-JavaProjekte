package PracticeProjects.BitManTests;

import java.util.Arrays;

public class BitOperationStuff {
    public static void main(String[] args) {
        /* int i = 1;
        char c = 'c';
        int cval = c - 97;
        System.out.println(bitVals(i));
        i = i << cval;
        System.out.println(bitVals(i)); */
        /*  char[] arr = new char[60];
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
        System.out.println(Arrays.toString(arr2)); */

        Double d = Double.valueOf(0d);

        System.out.println(bitVals(d));
        System.out.println(bitVals(Double.MAX_VALUE));
        System.out.println(bitVals(Double.valueOf(-1d)));
        System.out.println(bitVals(Double.valueOf(0.5d)));
    }

    public static String bitVals(int i) {
        String o = "";
        for (int j = 0; j < 32; j++) {
            o = Math.abs(i % 2) + o;  
            i = i >>> 1;
        }
        return o;
    }

    public static String bitVals(double i) {
        String o = "";
        long l = Double.doubleToLongBits(12);
        System.out.print(l + ": ");
        for (int j = 0; j < 64; j++) {
            o = Math.abs(l % 2) + o;
            l = l >>> 1;
            if (j == 51 || j == 62) {
                o = " " + o;
            }
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
                        bitMask ^= appMask;
                        currentLength--;
                    }
                }
            } else {
                // add appMask and bitMask
                bitMask ^= appMask;
                currentLength++;
            }

            appMask = (1 << (arr[++currentIndex] - 97));
        }
        return currentIndex;
    }
}
