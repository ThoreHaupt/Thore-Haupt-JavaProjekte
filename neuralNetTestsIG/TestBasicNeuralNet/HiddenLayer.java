package neuralNetTestsIG.TestBasicNeuralNet;

import java.util.function.BiConsumer;

import Commons.CalulationTools.MatrixCalculation;

public class HiddenLayer extends Layer {

    final double lowerInitValue = -0.1;
    final double upperInitValue = 0.1;

    Layer previousLayer;

    int nodeAmount;
    int nodePrevLayer;

    // values after foreward prop
    double[][] weights;
    double[][] biases;
    double[][] weightedBiasedInputs; // (z)
    //double[] activationValue; already exists in Layer class

    // safe gradients for next layers
    // this is what accumulates all the notches in the batch. gets applied at the end. -> exists once when there is more than one thread doing backprob
    double[][] weightGradient;
    double[][] biasGradient;

    // should exist more than once, for each thread
    double[][] activationDerivativeValue;
    double[][] inputWBDerivativeValue;
    double[][] weightGradientBuffer;

    BiConsumer<double[][], double[][]> activationFunction;
    BiConsumer<double[][], double[][]> activationFunctionDerivative;

    public HiddenLayer(int nodeAmount, Layer preveousLayer,
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
        weightedBiasedInputs = new double[1][nodeAmount];
        activationValues = new double[1][nodeAmount];

        weightGradient = new double[nodeAmount][previousLayer.getNodeAmount()];
        biasGradient = new double[1][nodeAmount];

        // should exist more than once, for each thread
        activationDerivativeValue = new double[1][nodeAmount];
        inputWBDerivativeValue = new double[1][nodeAmount];
        weightGradientBuffer = new double[nodeAmount][previousLayer.getNodeAmount()];

        // init all the important Arrays that wont be initiated naturally. Like the initial weights and biases (randomly) and set all values in each GradientSum to 0
        MatrixCalculation.fillMatrixWithRandomValues(weights, lowerInitValue, upperInitValue);
        MatrixCalculation.fillMatrixWithRandomValues(biases, lowerInitValue, upperInitValue);

        MatrixCalculation.initializeArrayValuesWithValue(weightGradient, 0);
        MatrixCalculation.initializeArrayValuesWithValue(biasGradient, 0);
        MatrixCalculation.initializeArrayValuesWithValue(weightedBiasedInputs, 0);
    }

    void calculateActivationValues() {
        for (int i = 0; i < activationValues[0].length; i++) {
            for (int j = 0; j < previousLayer.activationValues[0].length; j++) {
                weightedBiasedInputs[0][i] += previousLayer.activationValues[0][j] * weights[i][j];
            }
            weightedBiasedInputs[0][i] += biases[0][i];
        }
        activationFunction.accept(weightedBiasedInputs, activationValues);
    }

    void calculateGradients(double[][] nextLayerActivation,
            BiConsumer<double[][], double[][]> activationDerivativeValueL) {
        //weightedInputValue Gradient
        activationDerivativeValueL.accept(nextLayerActivation, inputWBDerivativeValue);
        MatrixCalculation.matrixMultiplikation(nextLayerActivation, weights, activationDerivativeValue);
        //add weight Gradient
        MatrixCalculation.matrixMultiplikation(inputWBDerivativeValue, activationValues, weightGradientBuffer);
        MatrixCalculation.maticesAdd(weightGradient, weightGradientBuffer);
        //add bias Gradient
        MatrixCalculation.maticesAdd(biasGradient, inputWBDerivativeValue);
    }

    void calculateGradients(double[][] nextLayerActivation) {
        calculateGradients(nextLayerActivation,
                activationFunctionDerivative);
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
    }
}
