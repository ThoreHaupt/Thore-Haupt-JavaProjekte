package neuralNetTestsIG.TestBasicNeuralNet;

import java.util.ArrayList;
import neuralNetTestsIG.CalulationTools.MatixCalculation;
import neuralNetTestsIG.Data.FileHandiling.FileManager;
import neuralNetTestsIG.Data.FileHandiling.Pair;
import testStuff.Paar;

public class NeuralNet {
    ArrayList<float[][]> nodes;
    ArrayList<float[][]> weigths;
    ArrayList<float[][]> biases;

    int imagesize = 28;
    int[] nodeLayerSizes = { imagesize * imagesize, 126, 10 };
    int[] functionTypesPerLayer = { 1, 2 };

    private void initiateWeightes() {
        for (int i = 0; i < nodeLayerSizes.length - 1; i++) {
            weigths.add(new float[nodeLayerSizes[i + 1]][nodeLayerSizes[i]]);
        }
    }

    private void calculateResultLayer() {
        for (int i = 0; i < nodeLayerSizes.length - 1; i++) {
            float[][] z = MatixCalculation.matrixMultiplikation(weigths.get(i), nodes.get(i));
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
