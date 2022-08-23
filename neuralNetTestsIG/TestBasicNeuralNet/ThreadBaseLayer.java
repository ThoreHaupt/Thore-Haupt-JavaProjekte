package neuralNetTestsIG.TestBasicNeuralNet;

import java.util.function.BiConsumer;

import Commons.CalulationTools.MatrixCalculation;

public class ThreadBaseLayer extends Layer {

    public static void main(String[] args) {

    }

    final double lowerInitValue = -0.5;
    final double upperInitValue = 0.5;

    Layer previousLayer;

    int nodeAmount;
    int nodePrevLayer;

    // values after foreward prop
    double[][] weights;
    double[][] biases;
    double[][] Z; // (z)
    //double[][] activationValue; already exists in Layer class

    // safe gradients for next layers
    // this is what accumulates all the notches in the batch. gets applied at the end. -> exists once when there is more than one thread doing backprob
    double[][] weightGradient;
    double[][] biasGradient;

    BiConsumer<double[][], double[][]> activationFunction;
    BiConsumer<double[][], double[][]> activationFunctionDerivative;

    ThreadTrainingLayer[] trainignLayers;

    public ThreadBaseLayer(int nodeAmount, Layer preveousLayer,
            BiConsumer<double[][], double[][]> activationFunction,
            BiConsumer<double[][], double[][]> activationFunctionDerivative) {
        super(nodeAmount);
        this.nodeAmount = nodeAmount;
        this.previousLayer = preveousLayer;

        //activationFunction
        this.activationFunction = activationFunction;
        this.activationFunctionDerivative = activationFunctionDerivative;

        biases = new double[1][nodeAmount];
        weights = new double[nodeAmount][preveousLayer.getNodeAmount()];
        Z = new double[1][nodeAmount];
        activationValues = new double[1][nodeAmount];

        weightGradient = new double[nodeAmount][previousLayer.getNodeAmount()];
        biasGradient = new double[1][nodeAmount];

        // init all the important Arrays that wont be initiated naturally. Like the initial weights and biases (randomly) and set all values in each GradientSum to 0
        MatrixCalculation.fillMatrixWithRandomValues(weights, lowerInitValue, upperInitValue);
        MatrixCalculation.fillMatrixWithRandomValues(biases, lowerInitValue, upperInitValue);
    }

    void calculateActivationValues() {
        Z = new double[1][activationValues[0].length];
        for (int i = 0; i < activationValues[0].length; i++) {
            for (int j = 0; j < previousLayer.activationValues[0].length; j++) {
                Z[0][i] += previousLayer.activationValues[0][j] * weights[i][j];
            }
            Z[0][i] += biases[0][i];
        }
        activationFunction.accept(Z, activationValues);
    }

    void applyGradients(double learnrate) {
        for (int i = 0; i < weightGradient.length; i++) {
            for (int j = 0; j < weightGradient[0].length; j++) {
                weights[i][j] -= weightGradient[i][j] * learnrate;
            }
        }
        for (int i = 0; i < activationValues.length; i++) {
            biases[0][i] -= biasGradient[0][i] * learnrate;
        }

        MatrixCalculation.initializeArrayValuesWithValue(weightGradient, 0);
        MatrixCalculation.initializeArrayValuesWithValue(biasGradient, 0);

        for (int i = 0; i < trainignLayers.length; i++) {
            trainignLayers[i].updateVariabels();
        }
    }

    public synchronized void addWeightGradientStep(double[][] weightGradientBuffer) {
        MatrixCalculation.addtoMatix(weightGradient, weightGradientBuffer);

    }

    public synchronized void addBiasGradientStep(double[][] z_Gradient) {
        MatrixCalculation.addtoMatix(biasGradient, z_Gradient);
    }

    /**
     * @param trainignLayers the trainignLayers to set
     */
    public void setTrainignLayers(ThreadTrainingLayer[] trainignLayers) {
        this.trainignLayers = trainignLayers;
    }

}
