package OR.lib;

public class TermLogic {
    public static double[] stringToArray(String s) {
        s = s.strip().replace(" ", "");
        double[] coefficient;
        int numNeg = (int) s.chars().filter(x -> x == '-').count();
        int numPos = (int) s.chars().filter(x -> x == '+').count();
        if (s.charAt(0) != '-')
            numPos++;
        String[] positiveTerms = new String[numPos];
        String[] negativeTerms = new String[numNeg];

        int currentIndex = 1;
        int currentSubstringStart = 0;
        int posIndex = 0;
        int negIndex = 0;

        while (currentIndex < s.length()) {

            if (s.charAt(currentIndex) == '+' || s.charAt(currentIndex) == '-' || currentIndex + 1 == s.length()) {
                if (currentIndex + 1 == s.length())
                    currentIndex++;
                if (s.charAt(currentSubstringStart) == '-') {
                    negativeTerms[negIndex++] = s.substring(currentSubstringStart, currentIndex);
                    currentSubstringStart = currentIndex;
                } else {
                    positiveTerms[posIndex++] = s.substring(currentSubstringStart, currentIndex).replace("+", "");
                    currentSubstringStart = currentIndex;
                }
            }
            currentIndex++;
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
            coefficient[Integer.parseInt(part[1]) - 1] += Double.parseDouble(part[0] != "" ? part[0] : "1");
        }
        return coefficient;
    }
}
