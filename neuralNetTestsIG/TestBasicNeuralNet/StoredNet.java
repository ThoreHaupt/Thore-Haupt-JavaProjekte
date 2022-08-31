package neuralNetTestsIG.TestBasicNeuralNet;

import java.io.Serializable;

public class StoredNet implements Serializable {
    WeightedLayer[] hiddenLayers;
    WeightedLayer outputLayer;
    String[] activationFunctions;

    /**
     * @param hiddenLayers
     * @param outputLayer
     * @param activationfunctions
     */
    public StoredNet(NeuralNet neuralNet) {
        this.hiddenLayers = neuralNet.getHiddenLayers();
        this.outputLayer = neuralNet.getOutputLayer();
        this.activationFunctions = neuralNet.getActivationFunctions();
    }

    public NeuralNet restoreNet() {
        NeuralNet n = new NeuralNet(hiddenLayers, outputLayer, activationFunctions);
        return n;
    }
}
