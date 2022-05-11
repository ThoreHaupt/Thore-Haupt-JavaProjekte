package ProkSy.RP.RP_002.P2_3.trainingstagebuch.model;

import java.security.cert.TrustAnchor;
import java.text.DateFormatSymbols;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class Trainingseinheit implements Comparable<Trainingseinheit> {

    private Date date;

    public Trainingseinheit(Date date) {
        this.date = date;
    }

    public Date getDate() {
        return this.date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Override
    public int compareTo(Trainingseinheit o) {
        return this.date.compareTo(o.date);
    }

    @Override
    public boolean equals(Object o) {
        if (o == null) {
            return false;
        }
        if (o instanceof Trainingseinheit) {
            return false;
        }
        return (this.compareTo((Trainingseinheit) o) == 0) ? true : false;
    }

    @Override
    public String toString() {
        SimpleDateFormat df = new SimpleDateFormat("HH:MM");
        Calendar c = Calendar.getInstance();
        DateFormatSymbols dfs = new DateFormatSymbols();
        c.setTime(date);
        String wochentag = dfs.getWeekdays()[c.get(Calendar.DAY_OF_WEEK) - 1];
        int monatsTag = c.get(Calendar.DAY_OF_MONTH);
        String monat = dfs.getMonths()[c.get(Calendar.MONTH) - 1];
        int jahr = c.get(Calendar.YEAR);
        String uhrzeit = df.format(date);

        return "Trainingseinhat am: " + wochentag + ", dem " + monatsTag + ". " + monat + " " + jahr + " um " + uhrzeit
                + " Uhr";
    }

    public static void main(String[] args) {
        Lauftraining d = new Lauftraining(new Date(72131231212L), 100, 1);
        System.out.println(d);
    }
}
