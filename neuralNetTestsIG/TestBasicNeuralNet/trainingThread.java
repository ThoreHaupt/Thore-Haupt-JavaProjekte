package neuralNetTestsIG.TestBasicNeuralNet;

import java.util.Arrays;

import Commons.CalulationTools.MatrixCalculation;

public class trainingThread extends Thread {

    InputLayer inputLayer;
    WeightedLayer[] hiddenLayers;
    WeightedLayer outputLayer;

    NeuralNet NN;

    public trainingThread() {

    }

    @Override
    public void run() {

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

        //backpropagation:
        outputLayer.calculateGradients(costDiff, NN.getCostFunctionDerivative(NN.costFunction));
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
        NN.getCostFunctionDerivative(NN.costFunction).accept(costDiff, cost);
        double costSum = MatrixCalculation.MatrixSum(cost);
        NN.currentBatchCost += costSum;
        //System.out.println(costSum);
        return costDiff;
    }

    /**
     * adds weight/bias gradients stored in each layer to their weight and bias matrices
     * -= gradient*lernrate
     */
    void applyGradientSteps() {
        outputLayer.applyGradients(NN.learnrate / NN.batchSize);
        for (int i = 0; i < hiddenLayers.length; i++) {
            hiddenLayers[i].applyGradients(NN.learnrate / NN.batchSize);
        }
    }

    public double[][] calculateCostDiff(double[][] activationValuesOutputLayer, double[][] solution) {
        double[][] costDiff = MatrixCalculation.maticesAdd(
                activationValuesOutputLayer,
                MatrixCalculation.scalarMultiplication(-1d, solution));
        return costDiff;
    }
}
