package neuralNetTestsIG.TestBasicNeuralNet;

import java.io.Serializable;

import Commons.CalulationTools.MatrixCalculation;

public class Layer implements Serializable {
    public double[][] activationValues;

    public Layer(int nodes) {
        activationValues = new double[1][nodes];
        MatrixCalculation.initializeArrayValuesWithValue(activationValues, 0);
    }

    public int getNodeAmount() {
        return activationValues[0].length;
    }
}
