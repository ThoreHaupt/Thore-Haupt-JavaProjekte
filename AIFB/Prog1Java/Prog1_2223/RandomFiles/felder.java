package AIFB.Prog1Java.Prog1_2223.RandomFiles;

import java.util.Arrays;

public class felder {
    public static void main(String[] args) {

        int[][] arr1 = { { 4, 8 }, { 15, 16, 23 }, { 42 } };
        int[][] arrKopie = new int[3][];

        int[][] arr2 = new int[3][];
        for (int i = 0; i < arr2.length; i++) {
            arr2[i] = new int[arr1[i].length];

        }

        for (int i = 0; i < arrKopie.length; i++) {
            arrKopie[i] = new int[arr1[i].length];

            for (int j = 0; j < arr1[i].length; j++) {
                arrKopie[i][j] = arr1[i][j];
            }
        }

        for (int i = 0; i < arrKopie.length; i++) {
            System.out.println(Arrays.toString(arrKopie[i]));
        }
    }
}
