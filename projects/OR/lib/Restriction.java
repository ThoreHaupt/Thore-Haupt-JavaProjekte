package Projects.OR.lib;

import java.util.Arrays;

public class Restriction {

    public static final int SMALLEREQUAL = 0;
    public static final int SMALLER = 1;
    public static final int BIGGEREQUAL = 2;
    public static final int BIGGER = 3;
    public static final int EQUAL = 4;

    int variables;
    int type = -1;
    double[] coefficients;
    double constant;

    public Restriction(String s) {
        String[] split = null;

        // split input into sides and set comparison type
        String[] comperators = { "<=", "<", ">=", ">", "=" };
        for (int i = 0; i < 5; i++) {
            split = s.split(comperators[i]);
            if (split.length == 2) {
                type = i;
                break;
            }
        }

        if (split == null) {
            throw new IllegalArgumentException();
        }

        // {"3x1 + 3x3 - 5x7", "5"}
        split[0] = split[0].strip().replace(" ", "");
        coefficients = TermLogic.stringToArray(split[0]);
        constant = Double.parseDouble(split[1]);
    }

    private Restriction(Restriction r) {
        this.constant = r.constant;
        this.type = r.type;
        this.variables = r.variables;
        this.coefficients = coefficients.clone();
    }

    public int getBaseVariableAmount() {
        return coefficients.length;
    }

    public int getType() {
        return type;
    }

    public void flipWeights() {
        coefficients = Arrays.stream(coefficients).map(x -> x * -1).toArray();
        constant *= -1;
    }

    public Restriction clone() {
        return new Restriction(this);
    }

    public double getConstant() {
        return constant;
    }
}
