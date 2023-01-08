package OR.lib;

public class Restriction {

    public static final int SMALLEREQUAL = 0;
    public static final int SMALLER = 1;
    public static final int BIGGEREQUAL = 2;
    public static final int BIGGER = 3;
    public static final int EQUAL = 4;

    int variables;
    int type = -1;
    double[] weights;
    double constant;

    public Restriction(String s) {
        String[] split = null;

        // split input into sides and set comparison type
        String[] comperators = { "<=", "<", ">=", ">", "=" };
        for (int i = 0; i < 5; i++) {
            split = s.split(comperators[i]);
            if (split.length == 2) {
                type = i;
            }
        }

        if (split == null) {
            throw new IllegalArgumentException();
        }

        // {"3x1 + 3x3 - 5x7", "5"}
        split[0].strip();

        String[] positiveTerms = split[0].split("+");
        int negatives = (int) split[0].chars().filter(x -> x == '-').count();
        String[] negativeTerms = new String[negatives];
        int c = 0;
        for (int i = 0; i < positiveTerms.length; i++) {
            String[] subterms = positiveTerms[i].split("-");
            if (subterms.length > 1) {
                for (int j = 1; j < subterms.length; j++) {
                    negativeTerms[c++] = subterms[j];
                }
            }
        }
        // get the index of the largest argument
        int highest = 0;
        for (int i = 0; i < positiveTerms.length; i++) {
            String[] part = positiveTerms[i].split("x");
            highest = Math.max(highest, Integer.parseInt(part[1]));
        }
        for (int i = 0; i < negativeTerms.length; i++) {
            String[] part = negativeTerms[i].split("x");
            highest = Math.max(highest, Integer.parseInt(part[1]));
        }
        weights = new double[highest];

        for (int i = 0; i < positiveTerms.length; i++) {
            String[] part = positiveTerms[i].split("x");
            weights[Integer.parseInt(part[1]) - 1] += Double.parseDouble(part[0]);
        }
        for (int i = 0; i < negativeTerms.length; i++) {
            String[] part = negativeTerms[i].split("x");
            weights[Integer.parseInt(part[1]) - 1] -= Double.parseDouble(part[0]);
        }

        constant = Double.parseDouble(split[1]);
    }

    public int getBaseVariableAmount() {
        return weights.length;
    }

    public int getType() {
        return type;
    }
}
