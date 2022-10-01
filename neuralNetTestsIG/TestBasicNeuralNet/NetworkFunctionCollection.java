package neuralNetTestsIG.TestBasicNeuralNet;

import java.util.function.*;

import Commons.LambdaInterfaces.TriConsumer;

import java.io.Serializable;

public final class NetworkFunctionCollection implements Serializable {

    final public static String SIGMOID = "SIGMOID";
    final public static String TANH = "TANH";
    final public static String RELU = "RELU";

    final public static int NOCOSTFUNCTION = 0;
    final public static int SQUAREDISTANCE = 1;

    public static BiConsumer<double[][], double[][]> getActivationFunction(String s) {
        return switch (s) {
            case SIGMOID:
                yield NetworkFunctionCollection.AFsigmoidL;
            case TANH:
                yield NetworkFunctionCollection.AFtanh;
            case RELU:
                yield NetworkFunctionCollection.AFrelu;
            default:
                yield NetworkFunctionCollection.AFsigmoidL;

        };
    }

    public static BiConsumer<double[][], double[][]> getActivationFunctionDerivative(String s) {
        return switch (s) {
            case SIGMOID:
                yield NetworkFunctionCollection.AFsigmoidLDerivative;
            case TANH:
                yield NetworkFunctionCollection.AFtanhDerivative;
            case RELU:
                yield NetworkFunctionCollection.AFreluDerivative;
            default:
                yield NetworkFunctionCollection.AFsigmoidLDerivative;

        };
    }

    static BiConsumer<double[][], double[][]> getCostFunctionDerivative(int costFunction) {
        return NetworkFunctionCollection.AFCostFunctionDiffDerivative;
    }

    static BiConsumer<double[][], double[][]> getCostFunction(int costFunction) {
        return NetworkFunctionCollection.AFCostFunctionDiff;
    }

    final transient static Function<Double, Double> sigmoidL = x -> 1 / (1 + Math.exp(-x));
    final transient static Function<Double, Double> sigmoidLDerivative = x -> {
        double sigmVal = sigmoidL.apply(x);
        return (sigmVal * (1 - sigmVal));
    };

    final transient static Consumer<double[]> ACsigmoidL = x -> {
        for (int i = 0; i < x.length; i++)
            x[i] = 1 / (1 + Math.exp(-x[i]));
    };
    final transient static Consumer<double[]> ACsigmoidLDerivative = x -> {
        for (int i = 0; i < x.length; i++)
            x[i] = 1 / (1 + Math.exp(-x[i]));
        for (int i = 0; i < x.length; i++) {
            x[i] = x[i] * (1 - x[i]);
        }
    };

    /**
     * saves the sigmoidL values of the first Array into the second.
     */
    final transient static BiConsumer<double[], double[]> VFsigmoidL = (x, y) -> {
        // here you might want a check for same size, but I want to save that performance cuz that should never happen.
        for (int i = 0; i < x.length; i++)
            y[i] = 1 / (1 + Math.exp(-x[i]));
    };

    /**
     * saves the sigmoidL values of the first Array into the second.
     */

    final transient static BiConsumer<double[][], double[][]> AFsigmoidL = (x,
            y) -> {
        // here you might want a check for same size, but I want to save that performance cuz that should never happen.
        for (int i = 0; i < x.length; i++)
            for (int j = 0; j < x[0].length; j++) {
                y[i][j] = 1 / (1 + Math.exp(-x[i][j]));
            }
    };

    /**
     * saves the sigmoidL values of the first Array into the second. Of each first element in each Subarray
     */
    final transient static BiConsumer<double[][], double[][]> AFsigmoidLDerivative = (
            x, y) -> {
        AFsigmoidL.accept(x, y);
        for (int i = 0; i < x.length; i++) {
            for (int j = 0; j < x[0].length; j++)
                y[i][j] = y[i][j] * (1 - y[i][j]);
        }
    };

    final transient static BiConsumer<double[][], double[][]> AFtanh = (x, y) -> {
        for (int i = 0; i < x.length; i++) {
            for (int j = 0; j < x[0].length; j++)
                y[i][j] = Math.tanh(x[i][j]);
        }
    };
    final transient static BiConsumer<double[][], double[][]> AFtanhDerivative = (x, y) -> {
        for (int i = 0; i < x.length; i++) {
            for (int j = 0; j < x[0].length; j++)
                y[i][j] = 1 - Math.pow(Math.tanh(x[i][j]), 2);
        }
    };

    final transient static BiConsumer<double[][], double[][]> AFrelu = (x, y) -> {
        for (int i = 0; i < x.length; i++) {
            for (int j = 0; j < x[0].length; j++)
                y[i][j] = y[i][j] > 0 ? y[i][j] : 0;
        }
    };
    final transient static BiConsumer<double[][], double[][]> AFreluDerivative = (x, y) -> {
        for (int i = 0; i < x.length; i++) {
            for (int j = 0; j < x[0].length; j++)
                y[i][j] = y[i][j] > 0 ? 1 : 0;
        }
    };

    /**
    * saves the CostFunction values of the first Array into the second.
    * Cost Function: (a-o)^2
    */
    final transient static TriConsumer<double[][], double[][], double[][]> AFCostFunction = (TriConsumer<double[][], double[][], double[][]> & Serializable) (
            x, y, z) -> {
        // here you might want a check for same size, but I want to save that performance cuz that should never happen.
        for (int i = 0; i < x.length; i++)
            for (int j = 0; j < y[0].length; j++) {
                z[i][j] = Math.pow((x[i][j] - y[i][j]), 2);

            }
    };

    /**
     * saves the Derivative of the Cost Function with values x,y into Array z
     * Cost function Derivatie: 2*(a-o)
     */
    final transient static TriConsumer<double[][], double[][], double[][]> AFCostFunctionDerivative = (TriConsumer<double[][], double[][], double[][]> & Serializable) (
            x, y, z) -> {
        for (int i = 0; i < x.length; i++) {
            for (int j = 0; j < x[0].length; j++)
                z[i][j] = 2 * (x[i][j] - y[i][j]);
        }
    };

    /**
     * saves the CostFunction values of the first Array into the second.
     * Cost Function: (a-o)^2
     */
    final transient static BiConsumer<double[][], double[][]> AFCostFunctionDiff = (
            x, y) -> {
        // here you might want a check for same size, but I want to save that performance cuz that should never happen.
        for (int i = 0; i < x.length; i++)
            for (int j = 0; j < x[0].length; j++) {
                y[i][j] = Math.pow((x[i][j]), 2);
            }
    };

    /**
     * saves the Derivative of the Cost Function with values x,y into Array z
     * Cost function Derivatie: 2*(a-o)
     * input x is already (a-o)
     */
    final transient static BiConsumer<double[][], double[][]> AFCostFunctionDiffDerivative = (
            x, y) -> {
        for (int i = 0; i < x.length; i++) {
            for (int j = 0; j < x[0].length; j++)
                y[i][j] = 2 * (x[i][j]);
        }
    };
}
