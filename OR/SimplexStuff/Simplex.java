package OR.SimplexStuff;

import java.rmi.ConnectIOException;
import java.util.concurrent.ArrayBlockingQueue;

import OR.lib.LinearProblem;
import OR.lib.Restriction;
import OR.lib.TargetFunction;
import OR.lib.Solution;

public class Simplex {

    public static Solution solve(LinearProblem lp) {

        lp.standardize();
        Solution solution = null;

        int iterationStep = 0;

        // Simplex tablo
        double[][] problemMatrix = Commons.CalulationTools.MatrixCalculation.transposeMatrix(lp.getProblemMatruix());
        double[] targetCoefficients = lp.getTargetFunctionCoefficients();
        double[] constants = lp.getConstants_st();

        int numBaseVariables = lp.getBaseVariableAmount_st();
        // num restrictions is the amount of nonBaseVariables
        int numNonBaseVariables = lp.getRestrictionAmount();

        int[] baseVars = new int[numBaseVariables];
        int[] nonBaseVars = new int[numNonBaseVariables];

        // fill base Variables 
        for (int i = 0; i < baseVars.length; i++) {
            baseVars[i] = i;
        }

        for (int i = 0; i < numNonBaseVariables; i++) {
            nonBaseVars[i] = i + numBaseVariables;
        }

        // flip signs of target function coeficcients
        for (int i = 0; i < targetCoefficients.length; i++) {
            targetCoefficients[i] *= -1;
        }

        while (true) {
            iterationStep++;
            // select pivot element

            int pivotColum = getPivotColum(lp);

            if (pivotColum == -1) {
                solution = new Solution(false);
                break;
            }
            int pivotRow = getPivotRow(lp, pivotColum);
            if (pivotRow == -1) {
                solution = new Solution(false);
                break;
            }

            double pivotElement = problemMatrix[pivotColum][pivotRow];

            // apply triange rule xD
            for (int i = 0; i < numBaseVariables; i++) {
                for (int j = 0; j < numNonBaseVariables; j++) {
                    if (i == pivotColum) {
                        continue;
                    }
                    if (j == pivotRow) {
                        continue;
                    }
                    problemMatrix[i][j] -= problemMatrix[i][pivotRow] * problemMatrix[pivotColum][j] / pivotElement;
                }
                if (i != pivotColum)
                    targetCoefficients[i] -= problemMatrix[i][pivotRow] * targetCoefficients[pivotColum] / pivotElement;
            }

            for (int i = 0; i < numNonBaseVariables; i++) {
                if (i != pivotRow)
                    constants[i] -= problemMatrix[pivotColum][i] * constants[pivotRow] / pivotElement;
            }

            // devide pivot row by pivot element + constant value in pivot row
            for (int i = 0; i < numBaseVariables; i++) {
                if (i == pivotColum)
                    continue;
                problemMatrix[i][pivotRow] /= pivotElement;
            }

            // devide pivot colum by -1*pivot + target function coefficient in pivot colum
            for (int j = 0; j < numNonBaseVariables; j++) {
                if (j == pivotRow)
                    continue;
                problemMatrix[pivotColum][j] /= -pivotElement;
            }

            // change pivot Element
            problemMatrix[pivotColum][pivotRow] = 1 / pivotElement;

            // switch base and non base varibles in table
            int help = baseVars[pivotColum];
            baseVars[pivotColum] = nonBaseVars[pivotRow];
            nonBaseVars[pivotRow] = help;

            System.out.println("Iteratrionsschritt gemacht");

        }
        return solution == null
                ? currentSolution(targetCoefficients, nonBaseVars, constants, numBaseVariables + numNonBaseVariables)
                : solution;
    }

    private static int getPivotColum(LinearProblem lp) {
        int currentSmallest = -1;
        double smallestValue = Double.MAX_VALUE;
        double[] targetCoefficients = lp.getTargetFunctionCoefficients();
        for (int i = 0; i < targetCoefficients.length; i++) {
            if (targetCoefficients[i] < smallestValue) {
                currentSmallest = i;
                smallestValue = targetCoefficients[i];
            }
        }
        if (smallestValue > 0) {
            return -1;
        } else {
            return currentSmallest;
        }

    }

    private static int getPivotRow(LinearProblem lp, int colum) {
        double[] b = lp.getConstants_st();
        double[][] problemMatrix = lp.getProblemMatruix();
        int currentSmallest = -1;
        double smallestValue = Double.MAX_VALUE;
        for (int i = 0; i < b.length; i++) {
            if (problemMatrix[colum][i] <= 0) {
                continue;
            }
            double current = b[i] / problemMatrix[colum][i];
            if (current < smallestValue) {
                currentSmallest = i;
                smallestValue = current;
            }
        }
        if (currentSmallest == -1) {
            return -1;
        }
        return 0;
    }

    private static Solution currentSolution(double[] targetCoefficients, int[] nonBaseVars, double[] constants,
            int varAmount) {
        double[] solution = new double[varAmount];
        double targetValue = 0;

        for (int i = 0; i < nonBaseVars.length; i++) {
            solution[nonBaseVars[i]] = constants[i];
            if (nonBaseVars[i] >= targetCoefficients.length) {
                continue;
            }
            targetValue += targetCoefficients[nonBaseVars[i]] * constants[i];
        }

        return new Solution(solution, targetValue);
    }
}
