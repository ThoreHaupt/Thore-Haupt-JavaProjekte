package RP.W6.A4;

import java.util.ArrayList;
import java.util.Scanner;
import java.text.*;
import java.util.*;

public class ExperimentauswertungDemo {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ArrayList<Double> inputWeights = new ArrayList<>();
        ArrayList<Integer> inputValues = new ArrayList<>();
        Messung messung = new Messung();
        while (true){
            System.out.print("bitte die Gewichte angeben: ");
            String input = scanner.next();
            if (input.equals("next"))break;
            try {
                inputWeights.add(Double.parseDouble(input));
            } catch (Exception e) {
                System.out.println("Bitte eine Zahl eingebe oder next um die Werte einzutragen");
            }
            
            }
        while (true) {
            System.out.print("bitte die Messwerte angeben: ");
            String input = scanner.next();
            if (input.equals("stop")){
                if (inputValues.size() <= inputWeights.size())continue;
                break;
            }
            inputValues.add(Integer.parseInt(input));
            if (inputValues.size() == inputWeights.size())break;
        }
        messung.gewichtungsreihe = new double[inputWeights.size()];
        messung.messreihe = new int[inputWeights.size()];
        for (int i = 0; i < messung.gewichtungsreihe.length; i++) {
            messung.gewichtungsreihe[i] = inputWeights.get(i);
            messung.messreihe[i] = inputValues.get(i);
            
        }
        SimpleDateFormat formatter = new SimpleDateFormat("HH:mm:ss");
        Date date = new Date();
        String output = formatter.format(date);

        Experimentauswertung.gewichteterMesswert(messung);
        System.out.println("Gewichteter Messwert für diese Messreihe (berechnet um " + output + "Uhr): " + messung.messwertGewichtet);
        scanner.close();
    }
}

