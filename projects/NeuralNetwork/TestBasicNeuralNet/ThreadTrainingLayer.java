package Projects.NeuralNetwork.TestBasicNeuralNet;

import java.util.function.BiConsumer;

import Commons.CalulationTools.MatrixCalculation;

public class ThreadTrainingLayer extends Layer {

    Layer previousLayer;
    ThreadBaseLayer baseLayer;

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

    public ThreadTrainingLayer(ThreadBaseLayer baseLayer, Layer preveousLayer) {
        super(baseLayer.nodeAmount);
        this.baseLayer = baseLayer;
        this.previousLayer = preveousLayer;
        this.nodeAmount = baseLayer.getNodeAmount();

        Z = new double[1][nodeAmount];
        activationValues = new double[1][nodeAmount];

        // should exist more than once, for each thread
        weightGradient = new double[nodeAmount][previousLayer.getNodeAmount()];
        biasGradient = new double[1][nodeAmount];

        activation_Gradient = new double[1][nodeAmount];
        Z_Gradient = new double[1][nodeAmount];
        weightGradientBuffer = new double[nodeAmount][previousLayer.getNodeAmount()];

        // init all the important Arrays that wont be initiated naturally. Like the initial weights and biases (randomly) and set all values in each GradientSum to 0
        // copy initiated values from Thread base Layer
    }

    void calculateActivationValues() {
        Z = new double[1][activationValues[0].length];
        for (int i = 0; i < activationValues[0].length; i++) {
            for (int j = 0; j < previousLayer.activationValues[0].length; j++) {
                Z[0][i] += previousLayer.activationValues[0][j] * weights[i][j];
            }
            Z[0][i] += biases[0][i];
        }
        baseLayer.activationFunction.accept(Z, activationValues);
    }

    void calculateGradients(double[][] nextLayer_Z_Gradient,
            double[][] nextLayer_Weights,
            BiConsumer<double[][], double[][]> costFuntionDerivative) {

        // Derive dc/dz+1 (given) to dc/da (matrix product with weights matrix of the next layer)
        // when this is the last layer(outputlayer) then use the derivative of the cost funtion
        if (costFuntionDerivative == null)
            MatrixCalculation.matrixMultiplikation(nextLayer_Z_Gradient, nextLayer_Weights, activation_Gradient);
        else
            costFuntionDerivative.accept(nextLayer_Z_Gradient, activation_Gradient);

        //weightedInputValue Gradient dc/da -> dc/dz (S'(z) hamardProduct with the activation_Gradient)
        baseLayer.activationFunctionDerivative.accept(Z, Z_Gradient);
        MatrixCalculation.hamardProdukt(Z_Gradient, activation_Gradient, Z_Gradient);

        //add weight Gradient
        MatrixCalculation.matrixMultiplikationFirstTransposed(Z_Gradient, previousLayer.activationValues,
                weightGradientBuffer);
        MatrixCalculation.addtoMatix(weightGradient, weightGradientBuffer);
        //baseLayer.addWeightGradientStep(weightGradientBuffer);

        //add bias Gradient
        MatrixCalculation.addtoMatix(biasGradient, Z_Gradient);
        //baseLayer.addBiasGradientStep(Z_Gradient);
    }

    public void calculateGradients(double[][] costDiff, BiConsumer<double[][], double[][]> costFunctionDerivative) {
        calculateGradients(costDiff, null, costFunctionDerivative);
    }

    public void calculateGradients(double[][] z_Gradient2, double[][] weights2) {
        calculateGradients(z_Gradient2, weights2, null);
    }

    /**
     * A little inefficient, because all threads have to add their weights and biases at 
     * the same time and only one can go 
     */
    public void applyGradientToBaseLayer() {
        baseLayer.addBiasGradientStep(biasGradient);
        baseLayer.addWeightGradientStep(weightGradient);

        //set Gradients to 0
        weightGradient = new double[weightGradient.length][weightGradient[0].length];
        biasGradient = new double[biasGradient.length][biasGradient[0].length];
    }

    public void updateVariabels() {
        biases = MatrixCalculation.deepCopy(baseLayer.biases);
        weights = MatrixCalculation.deepCopy(baseLayer.weights);
    }
}
