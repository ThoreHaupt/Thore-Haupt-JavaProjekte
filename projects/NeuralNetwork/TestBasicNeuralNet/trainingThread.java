package Projects.NeuralNetwork.TestBasicNeuralNet;

import java.util.Arrays;
import java.util.concurrent.Semaphore;

import Commons.CalulationTools.MatrixCalculation;
import Projects.NeuralNetwork.Data.Dataset;

public class trainingThread extends Thread {

    boolean running = true;
    Semaphore runManager;

    InputLayer inputLayer;
    ThreadTrainingLayer[] hiddenLayers;
    ThreadTrainingLayer outputLayer;

    NeuralNet NN;

    int startIndex = 0;
    int endIndex = 0;
    Dataset trainingData;

    double currentBatchCost = 0;

    public trainingThread(Semaphore runManager, NeuralNet NN) {
        this.runManager = runManager;
        this.NN = NN;

        inputLayer = new InputLayer(NN.getTrainingData().getImagePixels(),
                NN.getActivationFunctions()[0]);

        hiddenLayers = new ThreadTrainingLayer[NN.getHiddenLayers().length];
        for (int i = 0; i < hiddenLayers.length; i++) {
            hiddenLayers[i] = new ThreadTrainingLayer(NN.getHiddenBaseLayer(i),
                    (i == 0) ? inputLayer : hiddenLayers[i - 1]);
        }

        outputLayer = new ThreadTrainingLayer(NN.getOutputBaseLayer(),
                hiddenLayers.length > 0 ? hiddenLayers[hiddenLayers.length - 1] : inputLayer);

        trainingData = NN.getTrainingData();
    }

    @Override
    public void run() {
        while (running) {
            try {
                runManager.acquire(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            //fetch all the new biases and weights
            for (int i = 0; i < hiddenLayers.length; i++) {
                hiddenLayers[i].updateVariabels();
            }
            outputLayer.updateVariabels();

            // do backpropagation on the assigned part of the training data and safe the changes to the baseLayer Gradient
            for (int i = startIndex; i < endIndex; i++) {
                backpropagation(trainingData.getPixelData()[i], trainingData.getImageLabels()[i]);
            }

            //apply all biases and weight gradients to base.

            for (int i = 0; i < hiddenLayers.length; i++) {
                hiddenLayers[i].applyGradientToBaseLayer();
            }
            outputLayer.applyGradientToBaseLayer();
        }
    }

    public double[] propagateInput(int[] pixel) {
        double[][] pixelArray = new double[][] { Arrays.stream(pixel).mapToDouble(x -> (double) x).toArray() };

        //printimage(pixel);
        inputLayer.setInputData(pixelArray);

        //System.out.println(Arrays.toString(inputLayer.activationValues[0]) + "\n");

        for (int i = 0; i < hiddenLayers.length; i++) {
            hiddenLayers[i].calculateActivationValues();
            //System.out.println(Arrays.toString(hiddenLayers[i].activationValues[0]) + "\n");
        }

        outputLayer.calculateActivationValues();
        //System.out.println(Arrays.toString(outputLayer.activationValues[0]));
        return outputLayer.activationValues[0];
    }

    /**
     * this propages the input values through the network 
     * calls the function that calculates the difference in cost to the optimal solution.
     * passes that matrix and the derivative of the cost function into the outputlayer
     * then calls each layer backwards to calculate their respective gradients
     * @param inputArray
     * @param solution
     */
    void backpropagation(int[] inputArray, int[] solution) {

        //forewardPropagation:
        propagateInput(inputArray);

        //calculating cost:
        double[][] costDiff = calcCostAndRate(solution);

        //backpropagation itself:
        // calculating the gradinents in reversed order for each layer
        outputLayer.calculateGradients(costDiff,
                NeuralNet.getCostFunctionDerivative(NN.getCostFunction()));
        for (int i = hiddenLayers.length - 1; i >= 0; i--) {
            if (i == hiddenLayers.length - 1) {
                hiddenLayers[i].calculateGradients(outputLayer.Z_Gradient, outputLayer.weights);
            } else {
                hiddenLayers[i].calculateGradients(hiddenLayers[i + 1].Z_Gradient, hiddenLayers[i + 1].weights);
            }
        }
    }

    /**
     * calls the costDiff function, which returns the difference between the optimal outputlayer and the actual activation values of the output layer.
     * Then it calculates the cost using the cost function
     * 
     * it adds the sum of each cost to the cost of the current batch. This will be used to calculate the average cost after training on this batch.
     * returns the cost diff because the backpropagation needs that to pass it into the derivative of the cost function.
     * @param solution
     * @return
     */
    double[][] calcCostAndRate(int[] solution) {
        double[][] solutionMatrix = new double[][] { Arrays.stream(
                solution).mapToDouble(x -> (double) x).toArray() };
        double[][] costDiff = calculateCostDiff(outputLayer.activationValues, solutionMatrix);
        double[][] cost = new double[1][costDiff[0].length];
        NeuralNet.getCostFunction(NN.getCostFunction()).accept(costDiff, cost);
        double costSum = MatrixCalculation.MatrixSum(cost);
        currentBatchCost += costSum;
        //System.out.println(costSum);
        return costDiff;
    }

    /**
    * adds weight/bias gradients stored in each layer to their weight and bias matrices
    * -= gradient*lernrate
    */
    /* void applyGradientSteps() {
        outputLayer.updateVariabels();
        for (int i = 0; i < hiddenLayers.length; i++) {
            hiddenLayers[i].updateVariabels();
        }
    } */

    public double[][] calculateCostDiff(double[][] activationValuesOutputLayer, double[][] solution) {
        double[][] costDiff = MatrixCalculation.maticesAdd(
                activationValuesOutputLayer,
                MatrixCalculation.scalarMultiplication(-1d, solution));
        return costDiff;
    }

    public void setLimits(int lower, int upper) {
        this.startIndex = lower;
        this.endIndex = upper;
    }

    public double getCurrentBatchCost() {
        double help = currentBatchCost;
        currentBatchCost = 0;
        return help;
    }

    /**
     * @return the hiddenLayers
     */
    public ThreadTrainingLayer[] getHiddenLayers() {
        return hiddenLayers;
    }

    /**
     * @return the outputLayer
     */
    public ThreadTrainingLayer getOutputLayer() {
        return outputLayer;
    }

    public void retire() {
        running = false;
    }
}
