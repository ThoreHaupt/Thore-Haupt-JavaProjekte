package neuralNetTestsIG.TestBasicNeuralNet;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import Commons.FileHandiling.Pair;

public class ImageApproximation {
    int[] pixel;
    ArrayList<Pair<Integer, Double>> imageScores;
    int actualNumber;

    /**
     * @param pixel
     * @param imageScores
     * @param actualNumber
     */
    public ImageApproximation(int[] pixel, double[] imageScoresDoubleValOrdered, int actualNumber) {
        this.pixel = pixel;
        this.actualNumber = actualNumber;

        imageScores = new ArrayList<Pair<Integer, Double>>(imageScoresDoubleValOrdered.length);
        for (int i = 0; i < imageScoresDoubleValOrdered.length; i++) {
            imageScores.add(new Pair<Integer, Double>(i, imageScoresDoubleValOrdered[i]));
        }
        Collections.sort(imageScores, sortScores);
    }

    /**
     * @return the pixel
     */
    public int[] getPixel() {
        return pixel;
    }

    /**
     * @return the imageScores
     */
    public ArrayList<Pair<Integer, Double>> getImageScores() {
        return imageScores;
    }

    /**
     * @return the actualNumber
     */
    public int getActualNumber() {
        return actualNumber;
    }

    public boolean isCorrect() {
        return actualNumber == imageScores.get(0).a.intValue();
    }

    Comparator<Pair<Integer, Double>> sortScores = (o1, o2) -> -Double.compare(o1.b, o2.b);

}
