package Projects.NeuralNetwork.Data;

import Commons.FileHandiling.FileManager;
import Commons.FileHandiling.Pair;

public class Dataset {
    int imageNumber;
    int imageXsize;
    int imageYsize;
    int imagePixels;
    int[][] pixelData;
    int[][] imageLabels;
    int[] imageLabelsAbsolut;

    public Dataset(String pixeldataPath, String labelDataPath) {

        Pair<int[][], int[][]> dataset = FileManager.loadDataSet(pixeldataPath, labelDataPath);

        pixelData = dataset.a;
        imageLabels = dataset.b;
        imageNumber = pixelData.length;

        imageXsize = (int) Math.sqrt(pixelData[0].length);
        imageYsize = (int) Math.sqrt(pixelData[0].length);

        imagePixels = pixelData[0].length;

        imageLabelsAbsolut = new int[imageLabels.length];

        for (int i = 0; i < imageLabels.length; i++) {
            for (int j = 0; j < 10; j++) {
                if (imageLabels[i][j] == 1)
                    imageLabelsAbsolut[i] = j;
            }
        }
    }

    /**
     * @return the imagePixels
     */
    public int getImagePixels() {
        return imagePixels;
    }

    /**
     * @return the pixelData
     */
    public int[][] getPixelData() {
        return pixelData;
    }

    /**
     * @return the imageLabels als Arrays. The position of the one is the number in the image.
     * equals the perfekt result that could be given in the outputlayers activation values
     */
    public int[][] getImageLabels() {
        return imageLabels;
    }

    /**
     * @return the imageLabelsAbsolut
     */
    public int[] getImageLabelsAbsolut() {
        return imageLabelsAbsolut;
    }

    public int getDatasetSize() {
        return pixelData.length;
    }

    public int getImagePixel_X() {
        return imageXsize;
    }

    public int getImagePixel_Y() {
        return imageYsize;
    }

    public int getAbsolutValueByIndex(int index) {
        return imageLabelsAbsolut[index];
    }
}
