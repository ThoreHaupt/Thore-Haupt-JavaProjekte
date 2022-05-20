package neuralNetTestsIG.CalulationTools;

public class MatixCalculation {

    public static float[][] matixiesAdd(float[][] a, float[][] b) {
        if (a.length == 0 || a[0].length == 0) {
            System.out.println("ERROR, a is not fully initiallized");
            return null;
        }
        if (b.length == 0 || b[0].length == 0) {
            System.out.println("ERROR, b is not fully initiallized");
            return null;
        }
        if (a.length != b.length && a[0].length != b[0].length) {
            System.out.println("ERROR, tryied to add two matrixies with different dimenstions");
            return null;
        }

        float[][] outputmatrix = new float[a.length][a[0].length];
        for (int i = 0; i < a.length; i++) {
            for (int j = 0; j < a[0].length; j++) {
                outputmatrix[i][j] = a[i][j] + b[i][j];
            }
        }

        return outputmatrix;
    }

    public static float[][] crossProduct(float[][] a, float[][] b) {
        float[][] outputmatrix = new float[a.length][a[0].length];
        for (int i = 0; i < a.length; i++) {
            for (int j = 0; j < a[0].length; j++) {
                outputmatrix[i][j] = a[i][j] + b[i][j];
            }
        }
    }

}
