package neuralNetTestsIG.TestBasicNeuralNet;

import Commons.CalulationTools.MatrixCalculation;

public class Layer {
    public double[][] activationValues;

    public Layer(int nodes) {
        activationValues = new double[1][nodes];
        MatrixCalculation.initializeArrayValuesWithValue(activationValues, 0);
    }

    public int getNodeAmount() {
        return activationValues[0].length;
    }
}
