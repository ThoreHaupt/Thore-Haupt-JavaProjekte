package ProkSy.RP.RP_002.P2_3.trainingstagebuch.model;

import java.util.Date;

import Commons.CalulationTools.SupportingCalculations;

public class Lauftraining extends Trainingseinheit {
    private double distance;
    private int time;

    /**
     * @param date
     * @param distance
     * @param time
     */
    public Lauftraining(Date date, double distance, int time) {
        super(date);
        this.distance = distance;
        this.time = time;
    }

    public double getSpeed() {
        return SupportingCalculations.round((distance / (time)) * 3600, 4);
    }

    public double getDistance() {
        return this.distance;
    }

    public void setDistance(double distance) {
        this.distance = distance;
    }

    /**
     * @return the time
     */
    public int getTime() {
        return time;
    }

    /**
     * @param time the time to set
     */
    public void setTime(int time) {
        this.time = time;
    }

    @Override
    public String toString() {
        return super.toString() + " - Lauf: " + distance + " km (" + this.getSpeed() + "km/h)";
    }

}
