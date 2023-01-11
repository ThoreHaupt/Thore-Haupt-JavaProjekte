package PracticeProjects;

public class languageDetection {
    public static void main(String[] args) {
        System.out.println(isMatch("ch", ".*g*h*"));
        System.out.println(isMatch("abcaaaaaaabaabcabac", ".*ab.a.*a*a*.*b*b*"));
        System.out.println(isMatch("aaghfffhzuiojtasda", "aa..f*g*h*.*j*.*"));
    }

    public static boolean isMatch2(String s, String p) {
        char currentRequiered; // current String in pattern
        boolean repeating = false;

        int sIndex = 0;
        int pIndex = 0;
        if (p.length() == 0 && s.length() > 0) {
            return false;
        }

        // outer loop: while there is still unmatched inputString left
        while (sIndex < s.length() || pIndex < p.length()) {
            repeating = false;
            // get current pattern
            currentRequiered = p.charAt(pIndex);

            // check if subPattern has *
            if (pIndex + 1 < p.length() && p.charAt(pIndex + 1) == '*') {
                repeating = true;
                pIndex += 2;

                // if subbpattern is . we have to do a non deterministic approch to find wether or not this language fits
                if (currentRequiered == '.') {
                    int subShift = 0;
                    // .* can have arbitrary size, so we must test all posibilites of the substring after / pattern after this point depending on how far .* covers.
                    while (!isMatch2(s.substring(subShift++ + sIndex, s.length()), p.substring(pIndex, p.length()))) {
                        if (subShift + sIndex >= s.length()) {
                            if (p.length() == pIndex)
                                return true;
                            return false;
                        }
                    }
                } else {

                    int maxSequenzLength = 0;
                    int shift = 0;
                    // inner loop: while we can continue with current sub pattern eg a* which is not . 
                    while (repeating && sIndex + shift < s.length()
                            && (currentRequiered == s.charAt(sIndex + shift++))) {
                        maxSequenzLength++;
                    }
                    int subShift = 0;

                    while (!isMatch2(s.substring(sIndex + maxSequenzLength - subShift, s.length()),
                            p.substring(pIndex, p.length()))) {
                        if (subShift + sIndex >= s.length()) {
                            if (p.length() == pIndex)
                                return true;
                            return false;
                        }
                        subShift++;
                        if (subShift > maxSequenzLength) {
                            return false;
                        }
                    }
                    return true;
                    /*  // check if there is the same simbol afterward the * and remove them from the pattern.
                    int shift = 0;
                    while (pIndex + shift < p.length() && p.charAt(pIndex + shift) == currentRequiered) {
                        shift++;
                    }
                    p = p.substring(0, pIndex) + p.substring(pIndex + shift, p.length());
                    // inner loop: while we can continue with current sub pattern eg a* which is not . 
                    while(repeating && sIndex < s.length() && (currentRequiered == s.charAt(sIndex))){
                        sIndex++;
                    } */
                }
            } else {
                if (sIndex >= s.length() && pIndex < p.length())
                    return false;
                repeating = false;
                if (currentRequiered != s.charAt(sIndex) && currentRequiered != '.') {
                    return false;
                }
                pIndex++;
                sIndex++;
            }
            if (pIndex >= p.length() && sIndex < s.length()) {
                return false;
            }

        }
        if (pIndex < p.length() && sIndex >= s.length())
            return false;
        return true;
    }

    public static boolean isMatch(String s, String p) {
        if (p.length() <= 0 && s.length() > 0) {
            return false;
        }

        if (s.length() == 0 && p.length() == 0) {
            return true;
        }

        // check for star after first character
        if (p.length() > 1 && p.charAt(1) == '*') {
            int subShift = 0;
            // .* can have arbitrary size, so we must test all posibilites of the substring after / pattern after this point depending on how far .* covers.
            while (!isMatch(s.substring(subShift, s.length()), p.substring(2, p.length()))) {
                if (subShift < s.length() && (s.charAt(subShift) == p.charAt(0) || p.charAt(0) == '.')) {
                    subShift++;
                } else {
                    return false;
                }
            }
            return true;
        } else {
            if ((s.length() == 0 && p.length() > 0) || !(s.charAt(0) == p.charAt(0) || p.charAt(0) == '.')) {
                return false;
            }
            return isMatch(s.substring(1, s.length()), p.substring(1, p.length()));
        }
    }
}
