package neuralNetTestsIG.TestBasicNeuralNet;

import java.util.Arrays;
import java.util.function.BiConsumer;
import java.util.function.Function;

import Commons.CalulationTools.MatrixCalculation;
import Commons.CalulationTools.SupportingCalculations;
import Commons.LambdaInterfaces.TriConsumer;
import PracticeProjects.test;
import neuralNetTestsIG.Data.Dataset;
import neuralNetTestsIG.NeuralInterfaceProgramm.HandwritingWindow;

import java.util.function.Consumer;

public class NeuralNet {

    final static int SIGMOID = 0;
    final static int TANH = 1;
    final static int RELU = 2;

    final static int SQUAREDISTANCE = 0;

    final Function<Double, Double> sigmoidL = x -> 1 / (1 + Math.exp(-x));
    final Function<Double, Double> sigmoidLDerivative = x -> {
        double sigmVal = sigmoidL.apply(x);
        return (sigmVal * (1 - sigmVal));
    };

    final Consumer<double[]> ACsigmoidL = x -> {
        for (int i = 0; i < x.length; i++)
            x[i] = 1 / (1 + Math.exp(-x[i]));
    };
    final Consumer<double[]> ACsigmoidLDerivative = x -> {
        for (int i = 0; i < x.length; i++)
            x[i] = 1 / (1 + Math.exp(-x[i]));
        for (int i = 0; i < x.length; i++) {
            x[i] = x[i] * (1 - x[i]);
        }
    };

    /**
     * saves the sigmoidL values of the first Array into the second.
     */
    final BiConsumer<double[], double[]> VFsigmoidL = (x, y) -> {
        // here you might want a check for same size, but I want to save that performance cuz that should never happen.
        for (int i = 0; i < x.length; i++)
            y[i] = 1 / (1 + Math.exp(-x[i]));
    };

    /**
     * saves the sigmoidL values of the first Array into the second.
     */
    final BiConsumer<double[][], double[][]> AFsigmoidL = (x, y) -> {
        // here you might want a check for same size, but I want to save that performance cuz that should never happen.
        for (int i = 0; i < x.length; i++)
            for (int j = 0; j < x[0].length; j++) {
                y[i][j] = 1 / (1 + Math.exp(-x[i][j]));
            }
    };

    /**
     * saves the sigmoidL values of the first Array into the second. Of each first element in each Subarray
     */
    final BiConsumer<double[][], double[][]> AFsigmoidLDerivative = (x, y) -> {
        AFsigmoidL.accept(x, y);
        for (int i = 0; i < x.length; i++) {
            for (int j = 0; j < x[0].length; j++)
                y[i][j] = y[i][j] * (1 - y[i][j]);
        }
    };

    final BiConsumer<double[][], double[][]> AFtanh = (x, y) -> {
        for (int i = 0; i < x.length; i++) {
            for (int j = 0; j < x[0].length; j++)
                y[i][j] = Math.tanh(x[i][j]);
        }
    };
    final BiConsumer<double[][], double[][]> AFtanhDerivative = (x, y) -> {
        for (int i = 0; i < x.length; i++) {
            for (int j = 0; j < x[0].length; j++)
                y[i][j] = 1 - Math.pow(Math.tanh(x[i][j]), 2);
        }
    };

    final BiConsumer<double[][], double[][]> AFrelu = (x, y) -> {
        for (int i = 0; i < x.length; i++) {
            for (int j = 0; j < x[0].length; j++)
                y[i][j] = y[i][j] > 0 ? y[i][j] : 0;
        }
    };
    final BiConsumer<double[][], double[][]> AFreluDerivative = (x, y) -> {
        for (int i = 0; i < x.length; i++) {
            for (int j = 0; j < x[0].length; j++)
                y[i][j] = y[i][j] > 0 ? 1 : 0;
        }
    };

    /**
     * saves the CostFunction values of the first Array into the second.
     * Cost Function: (a-o)^2
     */
    final TriConsumer<double[][], double[][], double[][]> AFCostFunction = (x, y, z) -> {
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
    final TriConsumer<double[][], double[][], double[][]> AFCostFunctionDerivative = (x, y, z) -> {
        for (int i = 0; i < x.length; i++) {
            for (int j = 0; j < x[0].length; j++)
                z[i][j] = 2 * (x[i][j] - y[i][j]);
        }
    };

    /**
     * saves the CostFunction values of the first Array into the second.
     * Cost Function: (a-o)^2
     */
    final BiConsumer<double[][], double[][]> AFCostFunctionDiff = (x, y) -> {
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
    final BiConsumer<double[][], double[][]> AFCostFunctionDiffDerivative = (x, y) -> {
        for (int i = 0; i < x.length; i++) {
            for (int j = 0; j < x[0].length; j++)
                y[i][j] = 2 * (x[i][j]);
        }
    };

    /* 
    final BiFunction<Double, Double, Double> costFunction = (a, o) -> Math.pow(a - o, 2);
    final BiFunction<Double, Double, Double> costFunctionDerivative = (a, o) -> 2 * (a - o); */

    InputLayer inputLayer;
    WeightedLayer[] hiddenLayers;
    WeightedLayer outputLayer;

    Dataset trainingData;
    Dataset testData;

    int costFunction;
    int activationFunction;

    double learnrate;
    int batchSize;
    double currentBatchCost = 0;
    double lastCostAverage = 0;

    public NeuralNet(int[] hiddenLayerSizes, double learnrate, int batchSize, int activationFunction, int costFunction,
            Dataset trainingData,
            Dataset testData) {

        this.learnrate = learnrate;
        this.batchSize = batchSize;

        this.costFunction = costFunction;
        this.activationFunction = activationFunction;

        this.trainingData = trainingData;
        this.testData = testData;

        // one extra layer for the inputs and then one extra layer for the cost, which is a layer in itself
        inputLayer = new InputLayer(trainingData.getImagePixels(), AFsigmoidL);
        hiddenLayers = new WeightedLayer[hiddenLayerSizes.length];
        for (int i = 0; i < hiddenLayerSizes.length; i++) {
            hiddenLayers[i] = new WeightedLayer(hiddenLayerSizes[i], i == 0 ? inputLayer : hiddenLayers[i - 1],
                    getActivationFunction(activationFunction), getActivationFunctionDerivative(activationFunction));
        }
        outputLayer = new WeightedLayer(10, hiddenLayers[hiddenLayers.length - 1],
                getActivationFunction(SIGMOID), getActivationFunctionDerivative(SIGMOID));
    }

    public static void main(String[] args) {
        Dataset trainingData = new Dataset("neuralNetTestsIG/Data/Datasets/NIST/train-images",
                "neuralNetTestsIG/Data/Datasets/NIST/train-labels");
        Dataset testData = new Dataset("neuralNetTestsIG/Data/Datasets/NIST/test-images",
                "neuralNetTestsIG/Data/Datasets/NIST/test-labels");

        NeuralNet NN = new NeuralNet(new int[] { 32 }, 0.3, 100, TANH, SQUAREDISTANCE, trainingData, testData);

        /* NN.train();
        NN.test();
        System.out.println(trainingData.getImageLabelsAbsolut()[n]);
        NN.calculateImage(trainingData.getPixelData()[n]); */
        //NN.backpropagation(trainingData.getPixelData()[n], trainingData.getImageLabels()[n]);

        NN.train(10);

        HandwritingWindow h = new HandwritingWindow(NN);

        /* for (int i = 0; i < 10; i++) {
            NN.testExampel(i);
        } */

        /* NN.testExampel(5);
        NN.backpropagation(testData.getPixelData()[5], testData.getImageLabels()[5]);
        NN.testExampel(5); */
    }

    /**
     * this method can test the network on the ith datapoint in the test set
     * @param i
     */
    private void testExampel(int i) {
        int[] image = testData.getPixelData()[i];
        printimage(image);
        propagateInput(image);

        double[][] diff = calcCostAndRate(testData.getImageLabels()[i]);
        getCostFunction(costFunction).accept(diff, diff);
        System.out.println("correct Number: " + testData.getImageLabelsAbsolut()[i]);
        System.out.println("Cost: " + MatrixCalculation.MatrixSum(diff));
        System.out.println("Cost Array: " + Arrays.toString(diff[0]));
        double outputSum = MatrixCalculation.MatrixSum(outputLayer.activationValues);
        System.out.println("Sum: " + outputSum);
        System.out.println(Arrays.toString(outputLayer.activationValues[0]));
        for (int j = 0; j < outputLayer.activationValues[0].length; j++) {
            System.out
                    .print(j + ": " + SupportingCalculations.round(outputLayer.activationValues[0][j] / outputSum, 2));
            System.out.println(" (" + SupportingCalculations.round(outputLayer.activationValues[0][j], 2) + ") + "
                    + testData.getImageLabels()[i][j]);
        }
    }

    public ImageApproximation testImage(int n) {
        int[] image = testData.getPixelData()[n];

        propagateInput(image);
        double[][] diff = calcCostAndRate(testData.getImageLabels()[n]);
        getCostFunction(costFunction).accept(diff, diff);
        double sum = MatrixCalculation.MatrixSum(diff);
        double[] scores = MatrixCalculation.scalarMultiplication(1 / sum, diff)[0];

        return new ImageApproximation(image, scores, testData.getImageLabelsAbsolut()[n]);
    }

    private void test() {
    }

    /**
     * trains the network by calling the learn batch funktion multible times with the new start index for the next batch
     * @param epochs
     */
    public void train(int epochs) {

        for (int i = 0; i < epochs; i++) {
            learnEpoch(batchSize);
            System.out.println("Epoch: " + i + " - current Cost score: "
                    + SupportingCalculations.round(lastCostAverage, 4));

        }
    }

    /**
     * This calls the backpropagation function for each input in the batch. 
     * then it lets the layers apply their gradients.
     * and calculates the average cost of this batch
     * @param startindex
     * @param batchSize
     */
    void learnEpoch(int batchSize) {

        int i = 0;
        while (i < trainingData.getDatasetSize() - batchSize) {
            currentBatchCost = 0;
            for (int j = 0; j < batchSize; j++) {
                backpropagation(trainingData.getPixelData()[i], trainingData.getImageLabels()[i]);
            }
            i += batchSize;
            applyGradientSteps();
            lastCostAverage = 1 - (currentBatchCost / batchSize / 10);
            //System.out.println(lastCostAverage);
        }
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
        outputLayer.calculateGradients(costDiff, getCostFunctionDerivative(costFunction));
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
        getCostFunction(costFunction).accept(costDiff, cost);
        double costSum = MatrixCalculation.MatrixSum(cost);
        currentBatchCost += costSum;
        //System.out.println(costSum);
        return costDiff;
    }

    /**
     * adds weight/bias gradients stored in each layer to their weight and bias matrices
     * -= gradient*lernrate
     */
    void applyGradientSteps() {
        outputLayer.applyGradients(learnrate / batchSize);
        for (int i = 0; i < hiddenLayers.length; i++) {
            hiddenLayers[i].applyGradients(learnrate / batchSize);
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

    public double[][] calculateCostDiff(double[][] activationValuesOutputLayer, double[][] solution) {
        double[][] costDiff = MatrixCalculation.maticesAdd(
                activationValuesOutputLayer,
                MatrixCalculation.scalarMultiplication(-1d, solution));
        return costDiff;
    }

    public BiConsumer<double[][], double[][]> getActivationFunction(int i) {
        return switch (i) {
            case 0:
                yield AFsigmoidL;
            case 1:
                yield AFtanh;
            case 2:
                yield AFrelu;
            default:
                yield AFsigmoidL;

        };
    }

    public BiConsumer<double[][], double[][]> getActivationFunctionDerivative(int i) {
        return switch (i) {
            case 0:
                yield AFsigmoidLDerivative;
            case 1:
                yield AFtanhDerivative;
            case 2:
                yield AFreluDerivative;
            default:
                yield AFsigmoidLDerivative;

        };
    }

    private BiConsumer<double[][], double[][]> getCostFunctionDerivative(int costFunction) {
        return AFCostFunctionDiffDerivative;
    }

    private BiConsumer<double[][], double[][]> getCostFunction(int costFunction) {
        return AFCostFunctionDiff;
    }

    void printimage(int[] pixel) {
        for (int i = 0; i < 28; i++) {
            for (int j = 0; j < 28; j++) {
                //System.out.print(pixel[i * 28 + j]);
                System.out.print(String.format("%3d", pixel[i * 28 + j]));
            }
            System.out.println();
        }
    }

    public Dataset getTestDataset() {
        return testData;
    }
}
