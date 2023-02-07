package Projects.OR.lib;

import java.util.Arrays;

public class Solution {
    boolean solvable = true;
    double targetValue;
    double[] solution;

    public Solution(LinearProblem lp) {

    }

    public Solution(boolean solvable) {
        solvable = false;
    }

    public Solution(double[] solution2, double targetValue2) {
        this.solution = solution2;
        this.targetValue = targetValue2;
    }

    public String toString() {
        return solvable ? "solution: " + targetValue + Arrays.toString(solution)
                : "there is no solution to this problem.";
    }
}
