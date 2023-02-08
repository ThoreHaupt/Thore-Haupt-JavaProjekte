package Projects.NeuralNetwork.TestBasicNeuralNet;

import Commons.CalulationTools.MatrixCalculation;

public class WeightedLayer extends Layer {

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

    // should exist more than once, for each thread
    double[][] activation_Gradient;
    double[][] Z_Gradient;
    double[][] weightGradientBuffer;

    String activationFunction;
    String activationFunctionDerivative;

    public WeightedLayer(int nodeAmount, Layer preveousLayer,
            String activationFunction,
            String activationFunctionDerivative) {
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

        // should exist more than once, for each thread
        activation_Gradient = new double[1][nodeAmount];
        Z_Gradient = new double[1][nodeAmount];
        weightGradientBuffer = new double[nodeAmount][previousLayer.getNodeAmount()];

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
        NeuralNet.getActivationFunction(activationFunction).accept(Z, activationValues);
    }

    void calculateGradients(double[][] nextLayer_Z_Gradient,
            double[][] nextLayer_Weights,
            int costFuntionDerivative) {

        // Derive dc/dz+1 (given) to dc/da (matrix product with weights matrix of the next layer)
        // when this is the last layer(outputlayer) then use the derivative of the cost funtion
        if (costFuntionDerivative == NeuralNet.NOCOSTFUNCTION)
            MatrixCalculation.matrixMultiplikation(nextLayer_Z_Gradient, nextLayer_Weights, activation_Gradient);
        else
            NeuralNet.getCostFunctionDerivative(costFuntionDerivative).accept(nextLayer_Z_Gradient,
                    activation_Gradient);

        //weightedInputValue Gradient dc/da -> dc/dz (S'(z) hamardProduct with the activation_Gradient)
        NeuralNet.getActivationFunctionDerivative(activationFunction).accept(Z, Z_Gradient);
        MatrixCalculation.hamardProdukt(Z_Gradient, activation_Gradient, Z_Gradient);

        //add weight Gradient
        MatrixCalculation.matrixMultiplikationFirstTransposed(Z_Gradient, previousLayer.activationValues,
                weightGradientBuffer);
        MatrixCalculation.addtoMatix(weightGradient, weightGradientBuffer);
        //add bias Gradient
        MatrixCalculation.addtoMatix(biasGradient, Z_Gradient);
    }

    synchronized void applyGradients(double learnrate) {
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

    public void calculateGradients(double[][] costDiff, int costFunctionDerivative) {
        calculateGradients(costDiff, null, costFunctionDerivative);
    }

    public void calculateGradients(double[][] z_Gradient2, double[][] weights2) {
        calculateGradients(z_Gradient2, weights2, NeuralNet.NOCOSTFUNCTION);
    }

    /**
     * @param biases the biases to set
     */
    public void setBiases(double[][] biases) {
        this.biases = biases;
    }

    /**
     * @return the weights
     */
    public double[][] getWeights() {
        return weights;
    }

    /**
     * @return the biases
     */
    public double[][] getBiases() {
        return biases;
    }

    /**
     * @param weights the weights to set
     */
    public void setWeights(double[][] weights) {
        this.weights = weights;
    }
}