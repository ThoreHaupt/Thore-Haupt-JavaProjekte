package Projects.OR.lib;

import java.util.Arrays;

public class TargetFunction {
    boolean isMaximizationProblem = false;
    double[] targetCoefficients;

    public TargetFunction(String s) {
        if (s.contains("max")) {
            isMaximizationProblem = true;
        }
        s = s.replace("max", "");
        s = s.replace("min", "");
        targetCoefficients = TermLogic.stringToArray(s);
    }

    @Override
    public String toString() {
        String result = "";
        for (int j = 0; j < targetCoefficients.length; j++) {
            if (targetCoefficients[j] != 0) {
                result += (targetCoefficients[j] > 0 && j > 0 ? "+" : "")
                        + String.format("%5.2f", targetCoefficients[j]) + " x"
                        + String.format("%-2d", j + 1);
            } else {
                result += String.format("%9s", " ");
            }

        }
        return (isMaximizationProblem ? "max" : "min") + " " + result;
    }

    public void flipWeights() {
        targetCoefficients = Arrays.stream(targetCoefficients).map(x -> x * -1).toArray();
    }
}
