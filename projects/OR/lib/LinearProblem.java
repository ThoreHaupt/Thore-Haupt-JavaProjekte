package Projects.OR.lib;

import java.util.ArrayList;
import java.util.Stack;

public class LinearProblem {

    public static void main(String[] args) {

        String p = "aa*aaabb";
        int pIndex = 4;
        int shift = 3;
        p = p.substring(0, pIndex - 1) + p.substring(pIndex + shift - 1, p.length());
        System.out.println(p);
    }

    ArrayList<Restriction> restrictions;
    ArrayList<Restriction> standartizedRestrictions;
    TargetFunction tf;

    double[][] problemMatrix_st;
    double[] constants_st;
    int baseVariableAmount_st = 0;

    double[][] problemMatrix;
    double[] constants;
    int baseVariableAmount = 0;

    boolean standarizedUpdated = false;

    public LinearProblem(ArrayList<Restriction> restrictions, TargetFunction tf) {
        this.restrictions = (restrictions != null ? restrictions : new ArrayList<>());
        this.standartizedRestrictions = new ArrayList<>();
        this.tf = tf;

        for (Restriction r : this.restrictions) {
            baseVariableAmount_st = Math.max(baseVariableAmount_st, r.getBaseVariableAmount());
        }
    }

    public void standardize() {
        if (standarizedUpdated) {
            return;
        }

        System.out.println("standartizing...");
        //standardize targetFunction
        if (!tf.isMaximizationProblem) {
            tf.flipWeights();
            tf.isMaximizationProblem = true;
        }

        //standardize restrictions
        Stack<Restriction> toStandartize = new Stack<Restriction>();
        standartizedRestrictions = new ArrayList<Restriction>();
        restrictions.stream().forEach(e -> {
            if (e.type != 0)
                toStandartize.add(e);
            else
                standartizedRestrictions.add(e);
        });

        while (!toStandartize.isEmpty()) {
            Restriction restriction = toStandartize.pop();
            switch (restriction.type) {
                case Restriction.BIGGER:
                    restriction.flipWeights();
                    restriction.type = Restriction.SMALLER;
                    toStandartize.add(restriction);
                    break;

                case Restriction.SMALLER:
                    restriction.type = Restriction.SMALLEREQUAL;
                    standartizedRestrictions.add(restriction);
                    break;
                case Restriction.BIGGEREQUAL:
                    restriction.flipWeights();
                    restriction.type = Restriction.SMALLEREQUAL;
                    standartizedRestrictions.add(restriction);
                    break;

                case Restriction.EQUAL:
                    restriction.type = Restriction.SMALLEREQUAL;
                    standartizedRestrictions.add(restriction);
                    restriction = restriction.clone();
                    restriction.type = Restriction.BIGGEREQUAL;
                    toStandartize.add(restriction);
                    break;
                default:
                    break;
            }
        }

        // fix variables that do not have the nonnegativity restriction

        standarizedUpdated = true;
        updateFromResctrictions();
    }

    private void updateFromResctrictions() {
        updateBaseVariableAmount();

        problemMatrix = restrictionsToMatrix(restrictions);
        constants = restrictionsToConstants(restrictions);

        problemMatrix_st = restrictionsToMatrix(standartizedRestrictions);
        constants_st = restrictionsToConstants(standartizedRestrictions);
    }

    private double[] restrictionsToConstants(ArrayList<Restriction> restrictions) {

        double[] constants = new double[restrictions.size()];
        for (int i = 0; i < restrictions.size(); i++) {
            constants[i] = restrictions.get(i).getConstant();
        }
        return constants;
    }

    private double[][] restrictionsToMatrix(ArrayList<Restriction> restrictions) {
        int baseVarAmount = getBaseVariableAmountFromRestrictions(restrictions);
        double[][] matrix = new double[baseVarAmount][restrictions.size()];
        Restriction r;
        for (int i = 0; i < baseVarAmount; i++) {
            for (int j = 0; j < restrictions.size(); j++) {
                if (restrictions.get(j).coefficients.length > j) {
                    r = restrictions.get(j);
                    matrix[i][j] = r.coefficients.length > i ? r.coefficients[i] : 0;
                }
            }
        }
        return matrix;
    }

    private int getBaseVariableAmountFromRestrictions(ArrayList<Restriction> restrictions) {
        int amount = 0;
        for (Restriction r : restrictions) {
            amount = Math.max(amount, r.getBaseVariableAmount());
        }
        return amount;
    }

    private void updateBaseVariableAmount() {
        // update base variable amount
        baseVariableAmount_st = getBaseVariableAmountFromRestrictions(standartizedRestrictions);
        baseVariableAmount = getBaseVariableAmountFromRestrictions(restrictions);
        baseVariableAmount_st = Math.max(baseVariableAmount_st, tf.targetCoefficients.length);
        baseVariableAmount = Math.max(baseVariableAmount, tf.targetCoefficients.length);
    }

    public void addRestriction(Restriction restriction) {
        standarizedUpdated = false;
        restrictions.add(restriction);
    }

    private String problemToString(double[][] matrix, double[] constants) {
        String result = "\nProblem:\n";

        result += tf.toString() + " s.t.\n";

        for (int i = 0; i < matrix[0].length; i++) {
            for (int j = 0; j < matrix.length; j++) {
                if (matrix[j][i] != 0) {
                    result += (matrix[j][i] > 0 ? j > 0 ? "+" : " " : "-")
                            + String.format("%5.2f", Math.abs(
                                    matrix[j][i]))
                            + " x"
                            + String.format("%-2d", j + 1);
                } else {
                    result += String.format("%10s", " ");
                }

            }
            result += " <= " + constants[i] + "\n";
        }
        return result;
    }

    public String toString() {
        return problemToString_st();
    }

    public String problemToString_st() {
        updateFromResctrictions();
        return problemToString(problemMatrix_st, constants_st);
    }

    public String problemToString() {
        updateFromResctrictions();
        return problemToString(problemMatrix, constants);
    }

    public int getBaseVariableAmount_st() {
        updateBaseVariableAmount();
        return baseVariableAmount_st;
    }

    public int getRestrictionAmount() {
        return standartizedRestrictions.size();
    }

    public double[] getTargetFunctionCoefficients() {
        double[] targetCoefficientsComplete = new double[baseVariableAmount];
        for (int i = 0; i < tf.targetCoefficients.length; i++) {
            targetCoefficientsComplete[i] = tf.targetCoefficients[i];
        }
        tf.targetCoefficients = targetCoefficientsComplete;
        return targetCoefficientsComplete;
    }

    public double[] getConstants_st() {
        return constants_st;
    }

    public double[][] getProblemMatrix() {
        if (tf != null && problemMatrix_st.length < tf.targetCoefficients.length) {
            double[][] temp = new double[tf.targetCoefficients.length][problemMatrix_st[0].length];
            for (int i = 0; i < problemMatrix_st.length; i++) {
                temp[i] = problemMatrix_st[i];
            }
            for (int i = problemMatrix_st.length; i < temp.length; i++) {
                temp[i] = new double[problemMatrix_st[0].length];
            }
            problemMatrix_st = temp;
        }
        return problemMatrix_st;
    }

    public double[][] getProblemMatruix(boolean stand) {
        if (stand) {
            return restrictionsToMatrix(standartizedRestrictions);
        } else {
            return restrictionsToMatrix(restrictions);
        }
    }

    public void setTF(TargetFunction tf) {
        this.tf = tf;
    }

}
