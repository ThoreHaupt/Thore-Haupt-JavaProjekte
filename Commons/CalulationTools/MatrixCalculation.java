package Commons.CalulationTools;

import java.util.Arrays;

import testStuff.testPolymorphie.A;

public class MatrixCalculation {

    public static void main(String[] args) {
        double[][] a = { { 1 }, { 2 }, { 3 } };
        //double[][] a = { { 1, 2, 3 } };

        double[][] b = { { 4, 5, 6 } };

        double[][] r = new double[3][3];

        //matrixMultiplikationFirstTransposed(a, b, r);
        matrixMultiplikation(a, b, r);
        System.out.println(Arrays.toString(r[0]));
        System.out.println(Arrays.toString(r[1]));
        System.out.println(Arrays.toString(r[2]));
    }

    /**
     * adds all values from the second matrix to the first matrix
     * @param a
     * @param b
     */
    public static void addtoMatix(double[][] a, double[][] b) {
        for (int i = 0; i < a.length; i++) {
            for (int j = 0; j < a[0].length; j++) {
                a[i][j] += b[i][j];
            }
        }
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
                outputmatrix[j][i] = a[i][j] + b[0][j];
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
     * a == b[0] sein, der Rest ist egal
     * 
     * @param a matrix a
     * @param b matrix b
     * @param result saves to this matrix. size needs to be: a[0]xb
     * @return double[][] matrix with dimensions: b x a[0]
     */
    public static double[][] matrixMultiplikation(double[][] a, double[][] b, double[][] result) {
        if (a[0].length != b.length) {
            System.out.println("Jo Jungs, input Matrizen Dimensionen falsch");
            return null;
        }

        if (result.length != a.length) {
            System.out.println("Jo Jungs, result höhe ist falsch");
            return null;
        }
        if (result[0].length != b[0].length) {
            System.out.println("Jo Jungs, result breite ist falsch");
            return null;
        }
        for (int i = 0; i < a.length; i++) {
            for (int j = 0; j < b[0].length; j++) {
                result[i][j] = 0;
                for (int k = 0; k < a[0].length; k++) {
                    result[i][j] += a[i][k] * b[k][j];
                }
            }
        }
        return result;
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

    public static void hamardProdukt(double[][] a, double[][] b, double[][] result) {
        for (int i = 0; i < result.length; i++) {
            for (int j = 0; j < result[i].length; j++) {
                result[i][j] = a[i][j] * b[i][j];
            }
        }
    }

    public static void fillMatrixWithRandomValues(double[][] array, double lower, double upper) {
        double center = (lower + upper) / 2;
        double distance = upper - lower;

        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array[i].length; j++) {
                array[i][j] = (Math.random() - 0.5 + center) * distance;
            }
        }
    }

    public static void fillMatrixWithRandomValues(double[] array, double lower, double upper) {
        double center = (lower + upper) / 2;
        double distance = upper - lower;

        for (int i = 0; i < array.length; i++) {
            array[i] = (Math.random() - 0.5 + center) * distance;
        }
    }

    public static void initializeArrayValuesWithValue(double[][] array, double value) {
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array[i].length; j++) {
                array[i][j] = value;
            }
        }
    }

    public static void initializeArrayValuesWithValue(double[] array, double value) {
        for (int i = 0; i < array.length; i++) {
            array[i] = value;
        }
    }

    public static double MatrixSum(double[][] a) {
        double sum = 0;
        for (int i = 0; i < a.length; i++) {
            for (int j = 0; j < a[i].length; j++) {
                sum += a[i][j];
            }
        }
        return sum;
    }

    public static void matrixMultiplikationFirstTransposed(double[][] a, double[][] b,
            double[][] weightGradientBuffer) {
        for (int i = 0; i < a[0].length; i++) {
            for (int j = 0; j < b[0].length; j++) {
                weightGradientBuffer[i][j] = 0;
                for (int k = 0; k < a.length; k++) {
                    weightGradientBuffer[i][j] += a[k][i] * b[k][j];
                    //[i][j] += a[i][k] * b[k][j];
                }
            }
        }
    }
}
