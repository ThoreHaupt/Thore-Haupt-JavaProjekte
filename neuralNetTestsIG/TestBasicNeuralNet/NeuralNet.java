package neuralNetTestsIG.TestBasicNeuralNet;

import java.util.Arrays;
import java.util.function.BiConsumer;
import java.util.function.Function;

import Commons.LambdaInterfaces.TriConsumer;
import neuralNetTestsIG.Data.Dataset;

import java.util.function.Consumer;

public class NeuralNet {

    final static int SIGMOID = 0;
    final static int RELU = 1;

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
            for (int j = 0; j < y[0].length; j++) {
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

    /**
     * saves the CostFunction values of the first Array into the second.
     * Cost Function: (a-o)^2
     */
    final TriConsumer<double[][], double[][], double[][]> AFCostFunction = (x, y, z) -> {
        // here you might want a check for same size, but I want to save that performance cuz that should never happen.
        for (int i = 0; i < x.length; i++)
            for (int j = 0; j < y[0].length; j++) {
                z[i][j] = 2 * (x[i][j] - y[i][j]);
            }
    };

    /**
     * saves the Derivative of the Cost Function with values x,y into Array z
     * Cost function Derivatie: 2*(a-o)
     */
    final TriConsumer<double[][], double[][], double[][]> AFCostFunctionDerivative = (x, y, z) -> {
        AFsigmoidL.accept(x, y);
        for (int i = 0; i < x.length; i++) {
            for (int j = 0; j < x[0].length; j++)
                z[i][j] = Math.pow((x[i][j] - y[i][j]), 2);
        }
    };

    /* 
    final BiFunction<Double, Double, Double> costFunction = (a, o) -> Math.pow(a - o, 2);
    final BiFunction<Double, Double, Double> costFunctionDerivative = (a, o) -> 2 * (a - o); */

    InputLayer inputLayer;
    HiddenLayer[] hiddenLayers;
    HiddenLayer outputLayer;
    double learnrate;

    public NeuralNet(int[] hiddenLayerSizes, double learnrate, int batchSize, int algorythm, Dataset trainingData,
            Dataset testData) {

        this.learnrate = learnrate;

        // one extra layer for the inputs and then one extra layer for the cost, which is a layer in itself
        inputLayer = new InputLayer(trainingData.getImagePixels(), AFsigmoidL);
        hiddenLayers = new HiddenLayer[hiddenLayerSizes.length];
        for (int i = 0; i < hiddenLayerSizes.length; i++) {
            hiddenLayers[i] = new HiddenLayer(hiddenLayerSizes[i], i == 0 ? inputLayer : hiddenLayers[i - 1],
                    getActivationFunction(algorythm), getActivationFunctionDerivative(algorythm));
        }
        outputLayer = new HiddenLayer(10, hiddenLayers[hiddenLayers.length - 1], getActivationFunction(algorythm),
                getActivationFunctionDerivative(algorythm));
    }

    public static void main(String[] args) {
        Dataset trainingData = new Dataset("neuralNetTestsIG/Data/Datasets/NIST/train-images",
                "neuralNetTestsIG/Data/Datasets/NIST/train-labels");
        Dataset testData = new Dataset("neuralNetTestsIG/Data/Datasets/NIST/test-images",
                "neuralNetTestsIG/Data/Datasets/NIST/test-labels");

        NeuralNet NN = new NeuralNet(new int[] { 128 }, 0.5, 50, SIGMOID, trainingData, testData);
        NN.train();
        NN.test();
        int n = 0;
        System.out.println(trainingData.getImageLabelsAbsolut()[n]);
        NN.calculateImage(trainingData.getPixelData()[n]);
    }

    private void test() {
    }

    public void train() {

    }

    public double[] calculateImage(int[] pixel) {
        double[][] pixelArray = new double[][] { Arrays.stream(pixel).mapToDouble(x -> (double) x).toArray() };

        printimage(pixel);
        inputLayer.setInputData(pixelArray);

        System.out.println(Arrays.toString(inputLayer.activationValues[0]) + "\n");

        for (int i = 0; i < hiddenLayers.length; i++) {
            hiddenLayers[i].calculateActivationValues();
            System.out.println(Arrays.toString(hiddenLayers[i].activationValues[0]) + "\n");
        }

        outputLayer.calculateActivationValues();
        System.out.println(Arrays.toString(outputLayer.activationValues[0]));
        return outputLayer.activationValues[0];
    }

    public BiConsumer<double[][], double[][]> getActivationFunction(int i) {
        return AFsigmoidL;
    }

    public BiConsumer<double[][], double[][]> getActivationFunctionDerivative(int i) {
        return AFsigmoidLDerivative;
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
}
