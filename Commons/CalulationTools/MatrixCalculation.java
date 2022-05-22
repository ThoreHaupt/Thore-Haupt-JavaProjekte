package Commons.CalulationTools;

import java.util.Arrays;

import testStuff.testPolymorphie.A;

public class MatrixCalculation {

    public static void main(String[] args) {
        double[][] b = { { 1f, 2f, 3f }, { 2, 2, 3, } };
        double[][] a = { { 1, 1, 0 }, { 2, 2, 0 }, { 1, 1, 1 } };

        System.out.println(Arrays.toString(matrixMultiplikation(a, b)[0]) + Arrays
                .toString(matrixMultiplikation(a, b)[1]));
    }

    public static double[][] maticesAdd(double[][] a, double[][] b) {
        if (a.length == 0 || a[0].length == 0) {
            System.out.println("ERROR, a is not fully initiallized");
            return null;
        }
        if (b.length == 0 || b[0].length == 0) {
            System.out.println("ERROR, b is not fully initiallized");
            return null;
        }
        if (a.length != b.length || a[0].length != b[0].length) {
            System.out.println("ERROR, tryied to add two matrixies with different dimenstions");
            return null;
        }

        double[][] outputmatrix = new double[a.length][a[0].length];
        for (int i = 0; i < a.length; i++) {
            for (int j = 0; j < a[0].length; j++) {
                outputmatrix[i][j] = a[i][j] + b[i][j];
            }
        }

        return outputmatrix;
    }

    public static double[][] maticesAddAll(double[][] a, double[][] b) {
        if (a.length == 0 || a[0].length == 0) {
            System.out.println("ERROR, a is not fully initiallized");
            return null;
        }
        if (b.length == 0 || b[0].length == 0) {
            System.out.println("ERROR, b is not fully initiallized");
            return null;
        }
        if (b.length != 1) {
            System.out.println("ERROR, b is not 1 x a[0].length - Dimensional, m");
            return null;
        }
        if (a[0].length != b[0].length) {
            System.out.println("ERROR, tryied to addAll two matrixies with different dimenstions");
            return null;
        }

        double[][] outputmatrix = new double[a.length][a[0].length];
        for (int i = 0; i < a.length; i++) {
            for (int j = 0; j < a[0].length; j++) {
                outputmatrix[i][j] = a[i][j] + b[0][j];
            }
        }

        return outputmatrix;
    }

    /**
     * a == b[0] sein, der Rest ist egal
     * 
     * @param a
     * @param b
     * @return double[][] matrix with dimensions: b x a[0]
     */
    public static double[][] matrixMultiplikation(double[][] a, double[][] b) {
        if (a.length != b[0].length) {
            System.out.println("Jo Jungs, hier ist was nicht richtig mit den Matrix Multiplication Inputs");
            return null;
        }
        double[][] outputmatrix = new double[b.length][a[0].length];
        for (int i = 0; i < a[0].length; i++) { // a[0]
            for (int k = 0; k < b.length; k++) {
                int s = 0;
                for (int j = 0; j < a.length; j++) { // a[0 oder b[0]
                    s += a[j][i] * b[k][j];
                }
                outputmatrix[k][i] = s;
            }
        }
        return outputmatrix;
    }

    /**
     * 
     * @param a
     * @param layer
     * @return matrix with dimensions: layer x layer[0]
     */
    public static double[][] scalarMultiplication(double a, double[][] layer) {
        for (int i = 0; i < layer.length; i++) {
            for (int j = 0; j < layer[i].length; j++) {
                layer[i][j] *= a;
            }
        }
        return layer;
    }

    public static double[][] transposeMatrix(double[][] matrix) {
        double[][] newMatrix = new double[matrix[0].length][matrix.length];
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                newMatrix[j][matrix.length - i - 1] = matrix[i][j];
            }
        }
        // matrix = newMatrix;
        return newMatrix;
    }

    public static double[] matrixVectorProdukt(double[][] matrix, double[] vector) {
        double[] outputVector = new double[matrix[0].length];
        for (int i = 0; i < matrix[0].length; i++) {
            double sum = 0;
            for (int j = 0; j < vector.length; j++) {
                sum += matrix[j][i] * vector[j];
            }
            outputVector[i] = sum;
        }
        return outputVector;
    }

    public static double[] create1Vector(int length) {
        double[] v = new double[length];
        for (int i = 0; i < v.length; i++) {
            v[i] = 1;
        }
        return v;
    }

    public static double[][] get_X_Range(double[][] matrix, int start, int last) {
        double[][] output = new double[last - start + 1][];
        for (int i = 0; i < output.length; i++) {
            output[i] = matrix[i];
        }
        return output;
    }

}
