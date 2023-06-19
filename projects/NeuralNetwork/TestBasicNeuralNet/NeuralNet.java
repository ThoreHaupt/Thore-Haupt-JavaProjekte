package Projects.NeuralNetwork.TestBasicNeuralNet;

import java.util.Arrays;
import java.util.concurrent.Semaphore;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.Function;

import javax.swing.SwingUtilities;

import java.io.Serializable;

import Commons.CalulationTools.MatrixCalculation;
import Commons.CalulationTools.SupportingCalculations;
import Commons.LambdaInterfaces.TriConsumer;
import Commons.UIElements.Progressbar;
import Projects.NeuralNetwork.Data.Dataset;
import Projects.NeuralNetwork.NeuralInterfaceProgramm.HandwritingWindow;

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
    private double momentumRate = 0.1;
    private int batchSize;
    private double currentBatchCost = 0;
    private double currentEpochCost = 0;
    private double lastCostAverage = 0;
    private Progressbar pbar;

    boolean dbg = false;

    public NeuralNet(int inputNodesNum, int[] hiddenLayerSizes, String[] activationFunctions) {
        this.hiddenLayerAmount = hiddenLayerSizes.length;
        this.hiddenLayerSizes = hiddenLayerSizes;
        this.activationFunctions = activationFunctions;
        initiateNormalNeuralLayers(inputNodesNum, hiddenLayerSizes);
    }

    public NeuralNet(WeightedLayer[] hidden_Layers, WeightedLayer outputLayer, String[] activationFunctions) {
        inputLayer = new InputLayer(hidden_Layers[0].getWeights()[0].length,
                activationFunctions[0]);
        this.hiddenLayers = hidden_Layers;
        this.outputLayer = outputLayer;
        this.activationFunctions = activationFunctions;
        this.hiddenLayerAmount = hidden_Layers.length;
        this.hiddenLayerSizes = new int[hiddenLayerAmount];
        for (int i = 0; i < hidden_Layers.length; i++) {
            hiddenLayerSizes[i] = hidden_Layers[i].nodeAmount;
        }
        hidden_Layers[0].previousLayer = inputLayer;
        for (int i = 1; i < hidden_Layers.length; i++) {
            hidden_Layers[i].previousLayer = hidden_Layers[i - 1];
        }
        outputLayer.previousLayer = hidden_Layers[hidden_Layers.length - 1];
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
                    NeuralNet.getActivationFunction(activationFunctions[i + 1]),
                    NeuralNet.getActivationFunctionDerivative(activationFunctions[i + 1]));
            hiddenBaseLayers[i].setBiases(MatrixCalculation.deepCopy(hiddenLayers[i].getBiases()));
            hiddenBaseLayers[i].setWeights(MatrixCalculation.deepCopy(hiddenLayers[i].getWeights()));
        }
        outputBaseLayer = new ThreadBaseLayer(10, hiddenLayers[hiddenLayers.length - 1],
                NeuralNet
                        .getActivationFunction(activationFunctions[hiddenLayerAmount + 1]),
                NeuralNet.getActivationFunctionDerivative(activationFunctions[hiddenLayerAmount + 1]));

        outputBaseLayer.setBiases(MatrixCalculation.deepCopy(outputLayer.getBiases()));
        outputBaseLayer.setWeights(MatrixCalculation.deepCopy(outputLayer.getWeights()));

        threadManager = new Semaphore(0);
        // build the threads that are going to be used for training
        for (int i = 0; i < threadNumber; i++) {

        }
    }

    public ImageApproximation testRandomImage() {
        return testImage((int) (Math.random() * testData.getDatasetSize()));
    }

    public ImageApproximation testImage(int n) {
        int[] image = testData.getPixelData()[n];
        ImageApproximation res = testImage(image);
        res.actualNumber = testData.getAbsolutValueByIndex(n);
        return res;
    }

    /**
     * tests random images from test set and returns fraction that is correct
     * @param num
     * @return
     */
    public double evalCurrentModel(int num) {
        int correct = 0;
        for (int i = 0; i < num; i++) {
            correct += testRandomImage().isCorrect() ? 1 : 0;
        }
        return (double) correct / num;
    }

    public ImageApproximation testImage(int[] image) {
        propagateInput(image);
        double[][] prediction = outputLayer.activationValues;
        double sum = MatrixCalculation.MatrixSum(prediction);
        double[] confidence = MatrixCalculation.scalarMultiplication(1 / sum, prediction)[0];
        ImageApproximation a = new ImageApproximation(image, confidence, -1);
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
            double momentumRate,
            int batchSize,
            int costFunction,
            Dataset trainingData,
            int threadNumber) {

        this.learnrate = learnrate;
        this.momentumRate = momentumRate;
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
            if (pbar != null) {
                Runnable updatePBar = () -> pbar.advance(1);
                SwingUtilities.invokeLater(updatePBar);
            }
            // double averageEpochCost = 1 - currentEpochCost / 10 / trainingData.getDatasetSize();
            double averageEpochCost = evalCurrentModel(1000);
            currentEpochCost = 0;
            System.out.println("Epoch: " + i + " - current Cost score: "
                    + SupportingCalculations.round(averageEpochCost, 5));

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
        trainingData.shuffle();
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
        NeuralNet.getCostFunction(costFunction).accept(costDiff, cost);
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
            hiddenBaseLayers[i].applyGradients(learnrate / batchSize, momentumRate);
        }
        outputBaseLayer.applyGradients(learnrate / batchSize, momentumRate);

    }

    public double[] propagateInput(int[] pixel) {
        double[][] pixelArray = new double[][] { Arrays.stream(pixel).mapToDouble(x -> (double) x).toArray() };

        if (dbg) {
            System.out.println("Propagate Input:");
            System.out.println("IL IV: " + MatrixCalculation.MatrixSum(pixelArray));
        }

        //printimage(pixel);
        inputLayer.setInputData(pixelArray);

        if (dbg) {
            System.out.println("IL AV: " + MatrixCalculation.MatrixSum(inputLayer.activationValues));
        }

        //System.out.println(Arrays.toString(inputLayer.activationValues[0]) + "\n");

        for (int i = 0; i < hiddenLayers.length; i++) {
            hiddenLayers[i].calculateActivationValues();
            if (dbg)
                System.out
                        .println("HL AV [" + i + "] :" + MatrixCalculation.MatrixSum(hiddenLayers[i].activationValues));
        }

        outputLayer.calculateActivationValues();
        if (dbg)
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

    public void train(int epochAmount, double learnrate, double momentumRate, int batchSize, int threadNumber,
            Progressbar pbar) {
        this.pbar = pbar;
        Dataset trainingData = new Dataset("Projects/NeuralNetwork/Data/Datasets/NIST/train-images",
                "Projects/NeuralNetwork/Data/Datasets/NIST/train-labels");
        System.out.println(batchSize);
        Runnable trainThread = () -> train(epochAmount, learnrate,
                momentumRate, batchSize, costFunction, trainingData,
                threadNumber);
        new Thread(trainThread).start();
    }

    final public static String SIGMOID = "SIGMOID";
    final public static String TANH = "TANH";
    final public static String RELU = "RELU";
    final public static String LINEAR = "LINEAR";
    final public static String SOFTMAX = "SOFTMAX";

    final public static int NOCOSTFUNCTION = 0;
    final public static int SQUAREDISTANCE = 1;

    public static BiConsumer<double[][], double[][]> getActivationFunction(String s) {
        return switch (s) {
            case SIGMOID:
                yield NeuralNet.AFsigmoidL;
            case TANH:
                yield NeuralNet.AFtanh;
            case RELU:
                yield NeuralNet.AFrelu;
            case LINEAR:
                yield NeuralNet.AFlinear;
            case SOFTMAX:
                yield NeuralNet.AFsoftmax;
            default:
                yield NeuralNet.AFsigmoidL;

        };
    }

    public static BiConsumer<double[][], double[][]> getActivationFunctionDerivative(String s) {
        return switch (s) {
            case SIGMOID:
                yield NeuralNet.AFsigmoidLDerivative;
            case TANH:
                yield NeuralNet.AFtanhDerivative;
            case RELU:
                yield NeuralNet.AFreluDerivative;
            case LINEAR:
                yield NeuralNet.AFlinearDerivative;
            case SOFTMAX:
                yield NeuralNet.AFsoftmaxDerivatie;
            default:
                yield NeuralNet.AFsigmoidLDerivative;

        };
    }

    static BiConsumer<double[][], double[][]> getCostFunctionDerivative(int costFunction) {
        return NeuralNet.AFCostFunctionDiffDerivative;
    }

    static BiConsumer<double[][], double[][]> getCostFunction(int costFunction) {
        return NeuralNet.AFCostFunctionDiff;
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

    final transient static BiConsumer<double[][], double[][]> AFlinear = (x,
            y) -> {
        // here you might want a check for same size, but I want to save that performance cuz that should never happen.
        for (int i = 0; i < x.length; i++)
            for (int j = 0; j < x[0].length; j++) {
                y[i][j] = x[i][j];
            }
    };

    /**
     * saves the sigmoidL values of the first Array into the second. Of each first element in each Subarray
     */
    final transient static BiConsumer<double[][], double[][]> AFlinearDerivative = (
            x, y) -> {
        for (int i = 0; i < x.length; i++) {
            for (int j = 0; j < x[0].length; j++)
                y[i][j] = 1;
        }
    };

    /**
     * saves the sigmoidL values of the first Array into the second.
     */

    final transient static BiConsumer<double[][], double[][]> AFsoftmax = (x,
            y) -> {
        double sum = 0;
        double min = MatrixCalculation.min(x);
        double max = MatrixCalculation.max(x);
        double d = (max + min) / 2;
        for (int i = 0; i < x.length; i++)
            for (int j = 0; j < x[0].length; j++) {
                sum += Math.exp(x[i][j] - d);
            }
        for (int i = 0; i < x.length; i++)
            for (int j = 0; j < x[0].length; j++) {
                y[i][j] += Math.exp(x[i][j] - d) / sum;
            }

    };

    /**
     * saves the sigmoidL values of the first Array into the second. Of each first element in each Subarray
     */
    final transient static BiConsumer<double[][], double[][]> AFsoftmaxDerivatie = (
            x, y) -> {
        AFsoftmax.accept(x, y);
        for (int i = 0; i < x.length; i++) {
            for (int j = 0; j < x[0].length; j++) {
                if (Math.abs(y[i][j]) > 10000)
                    System.out.println(y[i][j]);
                y[i][j] = y[i][j] - Math.pow(y[i][j], 2);
            }
        }
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

    public Dataset getTestData() {
        return testData;
    }
}
