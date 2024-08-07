package Projects.NeuralNetwork.TestBasicNeuralNet;

import java.util.function.BiConsumer;

public class InputLayer extends Layer {

    String activationFunction;

    public InputLayer(int imageSize, String activationFunction) {
        super(imageSize);
        this.activationFunction = activationFunction;
    }

    public void setInputData(double[][] inputValues) {
        NeuralNet.getActivationFunction(activationFunction).accept(inputValues, activationValues);
    }
}
