package OR.lib;

import java.util.ArrayList;
import java.util.Stack;

public class LinearProblem {

    ArrayList<Restriction> restrictions;
    ArrayList<Restriction> standartizedRestrictions;
    TargetFunction tf;

    double[][] problemMatrix;
    double[] constants;

    int baseVariableAmount = 0;

    boolean standarized = false;

    public LinearProblem(ArrayList<Restriction> restrictions, TargetFunction tf) {
        this.restrictions = (restrictions != null ? restrictions : new ArrayList<>());
        this.tf = tf;

        for (Restriction r : this.restrictions) {
            baseVariableAmount = Math.max(baseVariableAmount, r.getBaseVariableAmount());
        }
    }

    public void standardize() {
        if (standarized) {
            return;
        }
        //standardize targetFunction

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

        standarized = true;
        updateMatrixFromResctrictions();
        System.out.println(this);
    }

    private void updateMatrixFromResctrictions() {
        problemMatrix = new double[standartizedRestrictions.size()][baseVariableAmount];
        constants = new double[standartizedRestrictions.size()];
        for (int i = 0; i < standartizedRestrictions.size(); i++) {
            for (int j = 0; j < standartizedRestrictions.get(i).coefficients.length; j++) {
                problemMatrix[i][j] = standartizedRestrictions.get(i).coefficients[j];
            }
        }
    }

    private void updateBaseVariableAmount() {
        // update base variable amount
        for (Restriction r : standartizedRestrictions) {
            baseVariableAmount = Math.max(baseVariableAmount, r.getBaseVariableAmount());
        }
    }

    public void addRestriction(Restriction restriction) {
        standarized = false;
        restrictions.add(restriction);
    }

    public String matrixToString() {
        String result = "Problem:";

        result += tf.toString();

        for (int i = 0; i < problemMatrix.length; i++) {
            for (int j = 0; j < baseVariableAmount; j++) {
                if (problemMatrix[i][j] != 0) {
                    result += String.format("%f4.2", problemMatrix[i][j]) + "x"
                            + String.format("%f2.0", j + 1);
                } else {
                    result += String.format("%s6", " ");
                }

            }
            result += " <= " + constants[i] + "\n";
        }
        return result;
    }

    public String toString() {
        return matrixToString();
    }

    public int getBaseVariableAmount() {
        updateBaseVariableAmount();
        return baseVariableAmount;
    }

    public int getRestrictionAmount() {
        return standartizedRestrictions.size();
    }

    public double[] getTargetFunctionCoefficients() {
        return tf.targetCoefficients;
    }

    public double[] getConstants() {
        return constants;
    }

    public double[][] getProblemMatruix() {
        return problemMatrix;
    }

    public void setTF(TargetFunction tf) {
        this.tf = tf;
    }

}
