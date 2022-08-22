package neuralNetTestsIG.TestBasicNeuralNet;

import java.util.function.BiConsumer;

public class InputLayer extends Layer {

    BiConsumer<double[][], double[][]> activationFunction;

    public InputLayer(int imageSize, BiConsumer<double[][], double[][]> activationFunction) {
        super(imageSize);
        this.activationFunction = activationFunction;
    }

    public void setInputData(double[][] inputValues) {
        activationFunction.accept(inputValues, activationValues);
    }
}
