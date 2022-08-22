package neuralNetTestsIG.oldTrieshaha;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.DuplicateFormatFlagsException;

import Commons.CalulationTools.MatrixCalculation;
import Commons.CalulationTools.SupportingCalculations;
import Commons.FileHandiling.FileManager;
import Commons.FileHandiling.Pair;

public class NeuralNet {

    int imagesize = 28;
    int[] nodeLayerSizes = { imagesize * imagesize, 10, 10 };
    int[] functionTypesPerLayer = { 2, 3 };

    double alpha = .1f;
    int layerThickness = 1;

    double[][][] nodes = new double[nodeLayerSizes.length][][];
    double[][][] weights = new double[nodeLayerSizes.length - 1][][];
    double[][][] biases = new double[nodeLayerSizes.length - 1][][];

    double[][][] dZs = new double[nodeLayerSizes.length - 1][][];
    double[][][] dWs = new double[nodeLayerSizes.length - 1][][];
    double[][][] dBs = new double[nodeLayerSizes.length - 1][][];

    public static void mainn(String[] args) {
        NeuralNet NN = new NeuralNet();
        double[][] arr = { { 255, 255, 150 } };
        NN.sigmoid(arr);
        System.out.println(Arrays.toString(arr[0]));

    }

    public static void main(String[] args) {
        NeuralNet NN = new NeuralNet();
        Pair<int[][], int[][]> dataset = FileManager.loadDataSet("neuralNetTestsIG/Data/Datasets/NIST/train-images",
                "neuralNetTestsIG/Data/Datasets/NIST/train-labels");

        int thickness = 1;
        double[][] inputLayer = new double[thickness][dataset.a[0].length];
        for (int i = 0; i < thickness; i++) {
            for (int j = 0; j < dataset.a[0].length; j++) {
                inputLayer[i][j] = (double) dataset.a[i][j];
            }
        }

        NN.nodes[0] = inputLayer;
        NN.fore_prop();

        double[][] lastlayer = NN.nodes[NN.nodes.length - 1];

        for (int i = 0; i < lastlayer[0].length; i++) {
            String LayerString = "";
            for (int j = 0; j < lastlayer.length; j++) {
                LayerString += String.format("%5f",
                        SupportingCalculations.round(lastlayer[j][i], 4)) + " ";
            }
            System.out.println(LayerString);
        }

        //NN.back_propagation(MatrixCalculation.get_X_Range(dataset.b, 0, 0));
        NN.applyNudges();

    }

    public NeuralNet() {

        initiallizeBiases();
        initiallizeWeightes();

    }

    public void calculateResult(int[] image) {

    }

    public void back_propagation(double[][] result) {

        for (int i = nodeLayerSizes.length - 2; i >= 0; i--) {
            // layerthickness nodeLayerSizes[i]

            if (i == nodeLayerSizes.length - 2) {
                dZs[i] = MatrixCalculation
                        .maticesAdd(nodes[nodes.length - 1], MatrixCalculation.scalarMultiplication(-1, result));
            } else {
                double[][] factor = switch (functionTypesPerLayer[i]) {
                    case 1 -> derivativeSigmoid(dZs[i + 1]);
                    case 2 -> derivativeReLu(dZs[i + 1]);
                    default -> derivativeReLu(dZs[i + 1]);
                };

                double[][] u = MatrixCalculation.matrixMultiplikation(MatrixCalculation.transposeMatrix(dWs[i + 1]),
                        dZs[i + 1]);
                // calculate chanche to next layer
                dZs[i] = MatrixCalculation.matrixMultiplikation(MatrixCalculation.transposeMatrix(factor), u);
            }

            // dW
            dWs[i] = MatrixCalculation.scalarMultiplication(1 / nodeLayerSizes[i + 1],
                    MatrixCalculation.matrixMultiplikation(
                            MatrixCalculation.transposeMatrix(nodes[i + 1]), dZs[i]));

            // db
            dBs[i] = MatrixCalculation.scalarMultiplication(1 / nodeLayerSizes[i + 1],
                    MatrixCalculation.matrixMultiplikation(MatrixCalculation.transposeMatrix(dWs[i]),
                            dWs[i]));

        }
    }

    private void applyNudges() {
        for (int i = 0; i < weights.length; i++) {
            weights[i] = MatrixCalculation.maticesAdd(weights[i],
                    MatrixCalculation.scalarMultiplication(-1 * alpha, dWs[i]));
            biases[i] = MatrixCalculation.maticesAdd(biases[i],
                    MatrixCalculation.scalarMultiplication(-1 * alpha, dWs[i]));
        }
    }

    /**
     * initiallizes weight-matrixes with random Values between 0 and 1
     * dimensions : next layer x prior layer ---> weight of a neuron is in a row
     * corresponding to all other neurons
     * 
     */
    private void initiallizeWeightes() {

        double scale = 1f;
        double offSet = -.5f;

        for (int i = 0; i < nodeLayerSizes.length - 1; i++) {
            double[][] arr = new double[nodeLayerSizes[i]][nodeLayerSizes[i + 1]];
            for (int j = 0; j < nodeLayerSizes[i]; j++) {
                for (int k = 0; k < nodeLayerSizes[i + 1]; k++) {
                    arr[j][k] = (double) Math.random() * scale + offSet;
                }
            }
            weights[i] = arr;
        }
    }

    private void initiallizeBiases() {

        double scale = 0f;
        double offSet = 0f;

        for (int i = 0; i < biases.length; i++) {
            double[][] arr = new double[layerThickness][nodeLayerSizes[i + 1]];
            for (int j = 0; j < layerThickness; j++) {
                for (int k = 0; k < nodeLayerSizes[i + 1]; k++) {
                    arr[j][k] = (double) Math.random() * scale + offSet;
                }
            }
            biases[i] = arr;
        }
    }

    private void fore_prop() {
        // sigmoid(nodes.get(0));
        for (int i = 0; i < nodeLayerSizes.length - 1; i++) {
            double[][] layerWithoutBias = MatrixCalculation.matrixMultiplikation(weights[i], nodes[i]);
            double[][] z = MatrixCalculation.maticesAddAll(
                    layerWithoutBias,
                    biases[i]);
            if (functionTypesPerLayer[i] == 1)
                z = sigmoid(z);
            if (functionTypesPerLayer[i] == 2)
                z = reLu(z);
            if (functionTypesPerLayer[i] == 3)
                z = softmaxActivationFunction(z);
            nodes[i + 1] = z;
        }
    }

    private double[][] calculateCosts(double[][] correctResultMatrix) {
        double[][] l = nodes[nodes.length - 1];
        double[][] costMatrix = new double[correctResultMatrix.length][correctResultMatrix[0].length];
        for (int i = 0; i < l.length; i++) {
            for (int j = 0; j < l[i].length; j++) {
                costMatrix[i][j] = (double) Math.pow(correctResultMatrix[i][j] - l[i][j], 2);
            }
        }
        return costMatrix;
    }

    // 1

    // (1/ (1 + exp(-x)))' -> (exp(-x)(1+exp(-x)^2))
    private double[][] sigmoid(double[][] layer) {
        for (int i = 0; i < layer.length; i++) {
            for (int j = 0; j < layer[i].length; j++) {
                layer[i][j] = Math.tanh(layer[i][j]);
            }
        }
        return layer;
    }

    private double[][] derivativeSigmoid(double[][] layer) {
        for (int i = 0; i < layer.length; i++) {
            for (int j = 0; j < layer[i].length; j++) {
                layer[i][j] = 1 / Math.pow(Math.cos(layer[i][j]), 2);
            }
        }
        return layer;

    }

    // 3
    private double[][] softmaxActivationFunction(double[][] layer) {
        double[] sum = new double[layer.length];
        for (int i = 0; i < layer.length; i++) {
            for (int j = 0; j < layer[i].length; j++) {
                sum[i] += Math.pow(layer[i][j], 2);// Math.pow(Math.E, layer[i][j]);
            }
        }
        for (int i = 0; i < layer.length; i++) {
            for (int j = 0; j < layer[i].length; j++) {
                layer[i][j] = Math.pow(layer[i][j], 2) / sum[i];
            }
        }
        return layer;
    }

    // 2
    private double[][] reLu(double[][] layer) {
        for (int i = 0; i < layer.length; i++) {
            for (int j = 0; j < layer[i].length; j++) {
                layer[i][j] = Math.max(0, layer[i][j]);
            }
        }
        return layer;
    }

    private double[][] derivativeReLu(double[][] layer) {
        for (int i = 0; i < layer.length; i++) {
            for (int j = 0; j < layer[i].length; j++) {
                layer[i][j] = (layer[i][j] >= 0) ? 1 : 0;
            }
        }
        return layer;
    }

    public String safeNetwork(String filePath) {
        ArrayList<double[][]> matrices = new ArrayList<>();
        for (double[][] weightMatrix : weights) {
            matrices.add(weightMatrix);
        }
        for (double[][] biasMatrix : biases) {
            matrices.add(biasMatrix);
        }
        Pair<int[], ArrayList<double[][]>> neuralInformation = new Pair<int[], ArrayList<double[][]>>(
                new int[] { weights.length, biases.length }, matrices);
        return FileManager.storeMatrices(filePath, neuralInformation);
    }
}
