package neuralNetTestsIG.CalulationTools;

import java.util.Arrays;

import testStuff.testPolymorphie.A;

public class MatixCalculation {

    public static void main(String[] args) {
        float[][] b = { { 1f, 2f, 3f }, { 2, 2, 3, } };
        float[][] a = { { 1, 1, 0 }, { 2, 2, 0 } };

        System.out.println(Arrays.toString(matrixMultiplikation(a, b)[0]) + Arrays
                .toString(matrixMultiplikation(a, b)[1]));
    }

    public static float[][] maticesAdd(float[][] a, float[][] b) {
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

    public static float[][] matrixMultiplikation(float[][] a, float[][] b) {
        if (a.length != b[0].length)
            return null;
        float[][] outputmatrix = new float[b.length][a[0].length];
        for (int i = 0; i < a[0].length; i++) { // a[0]
            for (int k = 0; k < b.length; k++) {
                int s = 0;
                for (int j = 0; j < a[0].length; j++) { // a[0 oder b[0]
                    s += a[i][j] * b[k][j];
                }
                outputmatrix[k][i] = s;
            }
        }
        return outputmatrix;
    }

    public static float[][] scalarMultiplication(float a, float[][] layer) {
        for (int i = 0; i < layer.length; i++) {
            for (int j = 0; j < layer[i].length; j++) {
                layer[i][j] *= a;
            }
        }
        return layer;
    }

    public static float[][] transposeMatrix(float[][] matrix) {
        float[][] newMatrix = new float[matrix[0].length][matrix.length];
        for (int i = 0; i < newMatrix.length; i++) {
            for (int j = 0; j < newMatrix.length; j++) {
                newMatrix[j][i] = matrix[i][j];
            }
        }
        // matrix = newMatrix;
        return newMatrix;
    }
}
