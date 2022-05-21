package neuralNetTestsIG.TestBasicNeuralNet;

import java.util.ArrayList;
import neuralNetTestsIG.CalulationTools.MatixCalculation;
import neuralNetTestsIG.Data.FileHandiling.FileManager;
import neuralNetTestsIG.Data.FileHandiling.Pair;

public class NeuralNet {
    ArrayList<float[][]> nodes;
    ArrayList<float[][]> weigths;
    ArrayList<float[][]> biases;

    int imagesize = 28;
    int[] nodeLayerSizes = { imagesize * imagesize, 126, 10 };
    int[] functionTypesPerLayer = { 1, 2 };

    float sepSize = .1f;
    int layerThickness = 1;

    public void calculateResult(int[] image) {

    }

    /**
     * initiallizes weight-matrixes with random Values between 0 and 1
     * dimensions : next layer x prior layer ---> weight of a neuron is in a row
     * corresponding to all other neurons
     * 
     */
    private void initiallizeWeightes() {
        for (int i = 0; i < nodeLayerSizes.length - 1; i++) {
            float[][] arr = new float[nodeLayerSizes[i + 1]][nodeLayerSizes[i]];
            for (int j = 0; j < nodeLayerSizes[i + 1]; j++) {
                for (int k = 0; k < nodeLayerSizes[i]; k++) {
                    arr[j][k] = (float) Math.random();
                }
            }
            weigths.add(arr);
        }
    }

    private void initiallizeBiases() {

        float scale = .6f;
        float offSet = -.5f;

        for (int i = 1; i < nodeLayerSizes.length; i++) {
            float[][] arr = new float[layerThickness][nodeLayerSizes[i]];
            for (int j = 0; j < layerThickness; j++) {
                for (int k = 0; k < nodeLayerSizes[i]; k++) {
                    arr[j][k] = (float) Math.random() * scale + offSet;
                }
            }
            biases.add(arr);
        }
    }

    private void calculateOutputLayer() {
        for (int i = 0; i < nodeLayerSizes.length - 1; i++) {
            float[][] z = MatixCalculation.maticesAdd(
                    MatixCalculation.matrixMultiplikation(weigths.get(i), nodes.get(i)),
                    biases.get(i));
            if (functionTypesPerLayer[i] == 1)
                z = sigmoid(z);
            if (functionTypesPerLayer[i] == 2)
                z = softmaxActivationFunction(z);
            nodes.add(z);
        }
    }

    private float[][] calculateCosts(float[][] correctResultMatrix) {
        float[][] l = nodes.get(nodes.size() - 1);
        float[][] costMatrix = new float[correctResultMatrix.length][correctResultMatrix[0].length];
        for (int i = 0; i < l.length; i++) {
            for (int j = 0; j < l[i].length; j++) {
                costMatrix[i][j] = (float) Math.pow(correctResultMatrix[i][j] - l[i][j], 2);
            }
        }
        return costMatrix;
    }

    // 1
    private float[][] sigmoid(float[][] layer) {
        for (int i = 0; i < layer.length; i++) {
            for (int j = 0; j < layer[i].length; j++) {
                layer[i][j] = 1 / (1 + (float) Math.pow(Math.E, -layer[i][j]));
            }
        }
        return layer;
    }

    // 2
    private float[][] softmaxActivationFunction(float[][] layer) {
        float[] sum = new float[layer[0].length];
        for (int i = 0; i < layer.length; i++) {
            for (int j = 0; j < layer[i].length; j++) {
                sum[i] += (float) Math.pow(Math.E, layer[i][j]);
            }
        }
        for (int i = 0; i < layer.length; i++) {
            for (int j = 0; j < layer[i].length; j++) {
                layer[i][j] = (float) Math.pow(Math.E, layer[i][j]) / sum[i];
            }
        }
        return layer;
    }

    public String safeNetwork(String filePath) {
        ArrayList<float[][]> matrices = new ArrayList<>();
        matrices.addAll(weigths);
        matrices.addAll(biases);
        Pair<int[], ArrayList<float[][]>> neuralInformation = new Pair<int[], ArrayList<float[][]>>(
                new int[] { weigths.size(), biases.size() }, matrices);
        return FileManager.storeMatrices(filePath, neuralInformation);
    }
}
