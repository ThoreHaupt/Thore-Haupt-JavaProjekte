package RP.W5;

public class W5A2 {
    static int[][][] feld = new int[][][]{{{1,2},{9,8,7,6},{1,1,1}},{{1,2,5},{1}}};
    public static void main(String[] args) {
        
    }
    public static int[][][] feldkopierenfalch(int[][][] array){
        return array;
    }

    public static int[][][] deepcopyI(int[][][] array){
        return array.clone();
    }
    
    public static int[][][] deepcopyII(int[][][] array) {
        int[][][] feld = new int[array.length][][];
        for (int i = 0; i < array.length; i++) {
            feld[i] = new int[array[i].length][];
            for (int j = 0; j < array.length; j++) {
                feld[i][j] = new int[array[j].length];
                for (int j2 = 0; j2 < array.length; j2++) {
                    feld[i][j][j2] = array[i][j][j2];
                }
            }
        }
        return feld;
    }
}
