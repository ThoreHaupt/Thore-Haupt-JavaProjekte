package neuralNetTestsIG.TestBasicNeuralNet;

import java.util.Arrays;
import java.util.concurrent.Semaphore;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.Function;

import java.io.Serializable;

import Commons.CalulationTools.MatrixCalculation;
import Commons.CalulationTools.SupportingCalculations;
import Commons.LambdaInterfaces.TriConsumer;
import neuralNetTestsIG.Data.Dataset;
import neuralNetTestsIG.NeuralInterfaceProgramm.HandwritingWindow;

public class NeuralNet {

    private int[] hiddenLayerSizes;
    private int hiddenLayerAmount;
    private InputLayer inputLayer;

    //for only working on the main thread
    private WeightedLayer[] hiddenLayers;
    private WeightedLayer outputLayer;

    //when using multithreading one needs baseLayers
    private ThreadBaseLayer[] hiddenBaseLayers;
    private ThreadBaseLayer outputBaseLayer;

    private Dataset trainingData;
    private Dataset testData;

    private int threadNumber;
    private Semaphore threadManager;
    private trainingThread[] threads;

    private int costFunction;
    private String[] activationFunctions;

    private double learnrate;
    private int batchSize;
    private double currentBatchCost = 0;
    private double currentEpochCost = 0;
    private double lastCostAverage = 0;

    public NeuralNet(int inputNodesNum, int[] hiddenLayerSizes, String[] activationFunctions) {
        this.hiddenLayerAmount = hiddenLayerSizes.length;
        this.hiddenLayerSizes = hiddenLayerSizes;
        this.activationFunctions = activationFunctions;
        initiateNormalNeuralLayers(inputNodesNum, hiddenLayerSizes);
    }

    public NeuralNet(WeightedLayer[] hiddenLayers, WeightedLayer outputLayer, String[] activationFunctions) {
        inputLayer = new InputLayer(hiddenLayers[0].getWeights()[0].length,
                activationFunctions[0]);
        this.hiddenLayers = hiddenLayers;
        this.outputLayer = outputLayer;
        this.activationFunctions = activationFunctions;
        this.hiddenLayerAmount = hiddenLayers.length;
        this.hiddenLayerSizes = new int[hiddenLayerAmount];
        for (int i = 0; i < activationFunctions.length; i++) {
            hiddenLayerSizes[i] = hiddenLayers[i].nodeAmount;
        }

    }

    private void initiateNormalNeuralLayers(int inputNodesNum, int[] hiddenLayerSizes) {
        // one extra layer for the inputs and then one extra layer for the cost, which is a layer in itself
        inputLayer = new InputLayer(inputNodesNum,
                activationFunctions[0]);
        hiddenLayers = new WeightedLayer[hiddenLayerAmount];
        for (int i = 0; i < hiddenLayerAmount; i++) {
            hiddenLayers[i] = new WeightedLayer(hiddenLayerSizes[i], i == 0 ? inputLayer : hiddenLayers[i - 1],
                    activationFunctions[i + 1],
                    activationFunctions[i + 1]);
        }
        outputLayer = new WeightedLayer(10, hiddenLayers[hiddenLayers.length - 1],
                activationFunctions[hiddenLayerAmount + 1],
                activationFunctions[hiddenLayerAmount + 1]);

    }

    private void initiateThreadNeuralLayers(int[] hiddenLayerSizes) {
        // one extra layer for the inputs and then one extra layer for the cost, which is a layer in itself
        hiddenBaseLayers = new ThreadBaseLayer[hiddenLayerAmount];
        for (int i = 0; i < hiddenLayerAmount; i++) {
            hiddenBaseLayers[i] = new ThreadBaseLayer(hiddenLayerSizes[i], i == 0 ? inputLayer : hiddenLayers[i - 1],
                    NetworkFunctionCollection.getActivationFunction(activationFunctions[i + 1]),
                    NetworkFunctionCollection.getActivationFunctionDerivative(activationFunctions[i + 1]));
            hiddenBaseLayers[i].setBiases(MatrixCalculation.deepCopy(hiddenLayers[i].getBiases()));
            hiddenBaseLayers[i].setWeights(MatrixCalculation.deepCopy(hiddenLayers[i].getWeights()));
        }
        outputBaseLayer = new ThreadBaseLayer(10, hiddenLayers[hiddenLayers.length - 1],
                NetworkFunctionCollection
                        .getActivationFunction(activationFunctions[hiddenLayerAmount + 1]),
                NetworkFunctionCollection.getActivationFunctionDerivative(activationFunctions[hiddenLayerAmount + 1]));

        outputBaseLayer.setBiases(MatrixCalculation.deepCopy(outputLayer.getBiases()));
        outputBaseLayer.setWeights(MatrixCalculation.deepCopy(outputLayer.getWeights()));

        threadManager = new Semaphore(0);
        // build the threads that are going to be used for training
        for (int i = 0; i < threadNumber; i++) {

        }
    }

    public ImageApproximation testImage(int n) {
        int[] image = testData.getPixelData()[n];

        propagateInput(image);
        double[][] prediction = outputLayer.activationValues;
        double sum = MatrixCalculation.MatrixSum(prediction);
        double[] scores = MatrixCalculation.scalarMultiplication(1 / sum, prediction)[0];
        ImageApproximation a = new ImageApproximation(image, scores, testData.getImageLabelsAbsolut()[n]);
        System.out.println(a.getImageScores().get(0).a);
        return a;
    }

    public ImageApproximation testImage(int[] image) {
        propagateInput(image);
        //printimage(image);
        double[][] prediction = outputLayer.activationValues;
        double sum = MatrixCalculation.MatrixSum(prediction);
        double[] confidence = MatrixCalculation.scalarMultiplication(1 / sum, prediction)[0];
        ImageApproximation a = new ImageApproximation(image, confidence, -1);
        System.out.println(a.getImageScores().get(0).a);
        return a;
    }

    private void test(Dataset testData) {
        this.testData = testData;
    }

    /**
     * trains the network by calling the learn batch funktion multible times with the new start index for the next batch
     * @param epochs
     */
    public void train(int epochs,
            double learnrate,
            int batchSize,
            int costFunction,
            Dataset trainingData,
            int threadNumber) {

        this.learnrate = learnrate;
        this.batchSize = batchSize;

        this.costFunction = costFunction;
        this.trainingData = trainingData;

        this.threadNumber = threadNumber;

        if (threadNumber > 1)
            initiateThreadNeuralLayers(hiddenLayerSizes);

        threads = new trainingThread[threadNumber];
        for (int i = 0; i < threadNumber; i++) {
            threads[i] = new trainingThread(threadManager, this);
            threads[i].start();
        }

        ThreadTrainingLayer[][] hiddenTrainingLayersThreadLevel = new ThreadTrainingLayer[hiddenLayerAmount][threadNumber];
        for (int i = 0; i < hiddenLayerAmount; i++) {
            for (int j = 0; j < threadNumber; j++) {
                hiddenTrainingLayersThreadLevel[i][j] = threads[j].getHiddenLayers()[i];
            }
        }

        // giving each base layer references to each training layer above them
        for (int i = 0; i < hiddenBaseLayers.length; i++) {
            hiddenBaseLayers[i].setTrainignLayers(hiddenTrainingLayersThreadLevel[i]);
        }
        ThreadTrainingLayer[] outputLayerTrainingThreadLayers = new ThreadTrainingLayer[threadNumber];
        for (int i = 0; i < threadNumber; i++) {
            outputLayerTrainingThreadLayers[i] = threads[i].getOutputLayer();
        }
        outputBaseLayer.setTrainignLayers(outputLayerTrainingThreadLayers);

        for (int i = 0; i < epochs; i++) {
            learnEpoch(batchSize);

            double averageEpochCost = 1 - currentEpochCost / 10 / trainingData.getDatasetSize();
            currentEpochCost = 0;
            System.out.println("Epoch: " + i + " - current Cost score: "
                    + SupportingCalculations.round(averageEpochCost, 6));

            if (averageEpochCost > 0.99)
                break;
        }
        System.out.println("training done. lets have a look.");
        for (int i = 0; i < threadNumber; i++) {
            threads[i].retire();
        }
        threadManager.release(threadNumber);
        testImage(trainingData.getPixelData()[1]);
        testImage(trainingData.getPixelData()[2]);
        testImage(trainingData.getPixelData()[3]);
    }

    private void learnEpoch(int batchSize) {
        if (threadNumber == 1) {
            learnEpochMainThread(batchSize);
        } else {
            learnEpochThreaded(batchSize);
        }
    }

    /**
     * This calls the backpropagation function for each input in the batch. 
     * then it lets the layers apply their gradients.
     * and calculates the average cost of this batch
     * Uses multible threads in order to be faster
     * @param batchSize
     */
    void learnEpochThreaded(int batchSize) {

        int i = 0;
        while (i < (trainingData.getDatasetSize() - batchSize)) {
            currentBatchCost = 0;
            //devide batch into threadNumber different sections on the array
            int threadBatchSize = batchSize / threadNumber;
            //set the different ranges for each thread
            for (int j = 0; j < threadNumber; j++) {
                threads[j].setLimits(i, i + threadBatchSize);
                i += threadBatchSize;
            }

            //give threadManager threadNumber permits. They update their weights and biases from the base layer
            threadManager.release(threadNumber);

            //join / wait until all threads are done
            while (threadManager.getQueueLength() < threadNumber) {
                Thread.yield();
            }

            //apply bias and weight gradients to base Layers
            applyGradientStepsBaseLayer();

            // get batchCost from each thread
            for (int j = 0; j < threadNumber; j++) {
                currentBatchCost += threads[j].getCurrentBatchCost();
            }

            lastCostAverage = 1 - (currentBatchCost / threadBatchSize / threadNumber / 10);
            currentEpochCost += currentBatchCost;
            //System.out.println(lastCostAverage);
        }
        copyBaseLayersValuesToNormalLayers();
    }

    public synchronized void addBatchCost(double cost) {
        currentBatchCost += cost;
    }

    private void copyBaseLayersValuesToNormalLayers() {
        for (int i = 0; i < hiddenLayerAmount; i++) {
            hiddenLayers[i].setBiases(MatrixCalculation.deepCopy(hiddenBaseLayers[i].getBiases()));
            hiddenLayers[i].setWeights(MatrixCalculation.deepCopy(hiddenBaseLayers[i].getWeights()));
        }
        outputLayer.setBiases(MatrixCalculation.deepCopy(outputBaseLayer.getBiases()));
        outputLayer.setWeights(MatrixCalculation.deepCopy(outputBaseLayer.getWeights()));
    }

    /**
     * This calls the backpropagation function for each input in the batch. 
     * then it lets the layers apply their gradients.
     * and calculates the average cost of this batch
     * @param startindex
     * @param batchSize
     */
    void learnEpochMainThread(int batchSize) {

        int i = 0;
        while (i < (trainingData.getDatasetSize() - batchSize) / 1) {
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
        outputLayer.calculateGradients(costDiff, costFunction);
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
        NetworkFunctionCollection.getCostFunction(costFunction).accept(costDiff, cost);
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

    /**
     * adds weight/bias gradients stored in each layer to their weight and bias matrices
     * -= gradient*lernrate
     */
    void applyGradientStepsBaseLayer() {
        for (int i = 0; i < hiddenBaseLayers.length; i++) {
            hiddenBaseLayers[i].applyGradients(learnrate / batchSize);
        }
        outputBaseLayer.applyGradients(learnrate / batchSize);
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
        System.out.println(Arrays.toString(outputLayer.activationValues[0]));
        return outputLayer.activationValues[0];
    }

    public double[][] calculateCostDiff(double[][] activationValuesOutputLayer, double[][] solution) {
        double[][] costDiff = MatrixCalculation.maticesAdd(
                activationValuesOutputLayer,
                MatrixCalculation.scalarMultiplication(-1d, solution));
        return costDiff;
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

    public Dataset getTrainingData() {
        return trainingData;
    }

    public WeightedLayer[] getHiddenLayers() {
        return hiddenLayers;
    }

    public ThreadBaseLayer getHiddenBaseLayer(int i) {
        return hiddenBaseLayers[i];
    }

    public ThreadBaseLayer getOutputBaseLayer() {
        return outputBaseLayer;
    }

    /**
    * @return the outputLayer
    */
    public WeightedLayer getOutputLayer() {
        return outputLayer;
    }

    /**
     * @return the hiddenBaseLayers
     */
    public ThreadBaseLayer[] getHiddenBaseLayers() {
        return hiddenBaseLayers;
    }

    public String[] getActivationFunctions() {
        return activationFunctions;
    }

    public int getCostFunction() {
        return costFunction;
    }

    public void setTestData(Dataset dataset) {
        testData = dataset;
    }

    public void train(int epochAmount) {
        train(epochAmount, learnrate, batchSize, costFunction, trainingData, threadNumber);
    }

}
