package Projects.NeuralNetwork.TestBasicNeuralNet;

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
    //double[][] activationValue; already exists in Layer class

    // safe gradients for next layers
    // this is what accumulates all the notches in the batch. gets applied at the end. -> exists once when there is more than one thread doing backprob
    double[][] weightGradient;
    double[][] biasGradient;

    double[][] lastWeightGradient;
    double[][] lastBiasGradient;

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

        weightGradient = new double[nodeAmount][previousLayer.getNodeAmount()];
        biasGradient = new double[1][nodeAmount];

        lastWeightGradient = new double[nodeAmount][previousLayer.getNodeAmount()];
        lastBiasGradient = new double[1][nodeAmount];

        // init all the important Arrays that wont be initiated naturally. Like the initial weights and biases (randomly) and set all values in each GradientSum to 0
        /* MatrixCalculation.fillMatrixWithRandomValues(weights, lowerInitValue, upperInitValue);
        MatrixCalculation.fillMatrixWithRandomValues(biases, lowerInitValue, upperInitValue); */
    }

    void applyGradients(double learnrate, double momentumRate) {
        for (int i = 0; i < weightGradient.length; i++) {
            for (int j = 0; j < weightGradient[0].length; j++) {
                double v = -weightGradient[i][j] * learnrate + lastWeightGradient[i][j] * momentumRate;
                lastWeightGradient[i][j] = v;
                weights[i][j] += v;
            }
        }
        for (int i = 0; i < activationValues.length; i++) {
            double v = -biasGradient[0][i] * learnrate + lastBiasGradient[0][i] * momentumRate;
            lastBiasGradient[0][i] = v;
            biases[0][i] += v;
        }

        weightGradient = new double[weightGradient.length][weightGradient[0].length];
        biasGradient = new double[biasGradient.length][biasGradient[0].length];

    }

    public synchronized void addWeightGradientStep(double[][] weightGradientStep) {
        MatrixCalculation.addtoMatix(weightGradient, weightGradientStep);

    }

    public synchronized void addBiasGradientStep(double[][] biasGradientStep) {
        MatrixCalculation.addtoMatix(biasGradient, biasGradientStep);
    }

    /**
     * @param trainignLayers the trainignLayers to set
     */
    public void setTrainignLayers(ThreadTrainingLayer[] trainignLayers) {
        this.trainignLayers = trainignLayers;
    }

    /**
     * @return the weights
     */
    public double[][] getWeights() {
        return weights;
    }

    /**
     * @param weights the weights to set
     */
    public void setWeights(double[][] weights) {
        this.weights = weights;
    }

    /**
     * @return the biases
     */
    public double[][] getBiases() {
        return biases;
    }

    /**
     * @param biases the biases to set
     */
    public void setBiases(double[][] biases) {
        this.biases = biases;
    }

}
