package OR.lib;

public class TermLogic {
    public static double[] stringToArray(String s) {
        s = s.strip().replace(" ", "");
        double[] coefficient;
        String[] positiveTerms = s.split("\\+");
        int negatives = (int) s.chars().filter(x -> x == '-').count();
        String[] negativeTerms = new String[negatives];
        int c = 0;
        for (int i = 0; i < positiveTerms.length; i++) {
            String[] subterms = positiveTerms[i].split("-");
            if (subterms.length > 1) {
                for (int j = 1; j < subterms.length; j++) {
                    negativeTerms[c++] = subterms[j];
                }
                positiveTerms[i] = subterms[0];
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
        coefficient = new double[highest];

        for (int i = 0; i < positiveTerms.length; i++) {
            String[] part = positiveTerms[i].split("x");
            coefficient[Integer.parseInt(part[1]) - 1] += Double.parseDouble(part[0] != "" ? part[0] : "1");
        }
        for (int i = 0; i < negativeTerms.length; i++) {
            String[] part = negativeTerms[i].split("x");
            coefficient[Integer.parseInt(part[1]) - 1] -= Double.parseDouble(part[0] != "" ? part[0] : "1");
        }
        return coefficient;
    }
}
