package OR.lib;

import java.util.ArrayList;

public class LinearProblem {

    ArrayList<Restriction> restrictions;
    TargetFunction tf;

    double[][] problemMatrix;
    double[] constants;

    int baseVariableAmount = 0;

    public LinearProblem(ArrayList<Restriction> restrictions, TargetFunction tf) {
        this.restrictions = restrictions;
        this.tf = tf;

        for (Restriction r : restrictions) {
            baseVariableAmount = Math.max(baseVariableAmount, r.getBaseVariableAmount());
        }
    }

    public void standardize() {

        //standardize targetFunction

        //standardize restrictions
        Restriction[] initialRrestrictions = (Restriction[]) restrictions.toArray();

        for (int i = 0; i < initialRrestrictions.length; i++) {

        }
    }

    private void updateMatrixFromResctrictions() {
        // get number of base variables

    }

    private void addRestriction() {

    }
}
