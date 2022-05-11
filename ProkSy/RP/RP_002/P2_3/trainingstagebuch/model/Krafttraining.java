package ProkSy.RP.RP_002.P2_3.trainingstagebuch.model;

import java.util.Date;

public class Krafttraining extends Trainingseinheit {
    private int numberOfExercises;

    /**
     * @param date
     * @param numberOfExercises
     */
    public Krafttraining(Date date, int numberOfExercises) {
        super(date);
        this.numberOfExercises = numberOfExercises;
    }

    /**
     * @return the numberOfExercises
     */
    public int getNumberOfExercises() {
        return numberOfExercises;
    }

    /**
     * @param numberOfExercises the numberOfExercises to set
     */
    public void setNumberOfExercises(int numberOfExercises) {
        this.numberOfExercises = numberOfExercises;
    }

    @Override
    public String toString() {
        return super.toString() + " - Kraft: " + numberOfExercises + " Übungen";
    }
}
