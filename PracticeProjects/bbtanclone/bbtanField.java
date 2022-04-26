package PracticeProjects.bbtanclone;

import PracticeProjects.ArrayListStuff.TArrayList;

public class bbtanField {
    public static int fieldBlockSizeX = 6;
    public static int fieldBlockSizeY = 6;
    public static int blockSize = 40;
    public static double blockSizeMargin = .04;
    static {

    }
    public static int radius;

    private TArrayList<int[]> arr;

    public int level = 0;

    public bbtanField(String[] args) {
        arr = new TArrayList<>(6);

    }

    public void shiftfield() {
        arr.add(generateNewRow(level));
        arr.remove(0);
    }

    public static int[] generateNewRow(int level) {
        int[] newRow = new int[fieldBlockSizeX];
        for (int i = 0; i < fieldBlockSizeX; i++) {
            if (Math.random() < 0.6) {
                newRow[i] = level;
            } else {
                newRow[i] = 0;
            }
        }
        return newRow;
    }

    public void hit(int x, int y) {
        arr.get(y)[x]--;
    }

}
