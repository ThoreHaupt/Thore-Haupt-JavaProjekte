package neuralNetTestsIG.Data.FileHandiling;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class FileManager {

    public static void main(String[] args) {
        Pair<int[][], byte[]> dataset = loadDataSet("neuralNetTestsIG/Data/Datasets/NIST/train-images",
                "neuralNetTestsIG/Data/Datasets/NIST/train-labels");
        int nummer = 0;
        System.out.println(dataset.b[nummer]);
        String outputString = "";
        for (int i = 0; i < 28 * 28; i++) {
            if (i % 28 == 0)
                outputString += "\n";
            outputString += String.format("%3d", dataset.a[nummer][i]);
        }
        System.out.println(outputString);
    }

    public static byte[] convertMatricesToByteArray(ArrayList<float[][]> matrixes) {
        byte[][] byteArray = new byte[matrixes.size()][];
        for (int m = 0; m < matrixes.size(); m++) {
            float[][] matrix = matrixes.get(m);
            ByteBuffer buffer = ByteBuffer.allocate(matrix.length * matrix[0].length * 4 + 4 * 3);

            // metadata
            buffer.putFloat(m);
            buffer.putFloat(matrix.length);
            buffer.putFloat(matrix[0].length);

            for (int i = 0; i < matrix.length; i++) {
                for (int j = 0; j < matrix[i].length; j++) {
                    buffer.putFloat(matrix[i][j]);
                }
            }
            byteArray[m] = buffer.array();
        }
        int length = 0;
        for (int i = 0; i < byteArray.length; i++) {
            length += byteArray[i].length;
        }
        byte[] outputArray = new byte[length];
        int pos = 0;
        for (int i = 0; i < byteArray.length; i++) {
            for (int j = 0; j < byteArray[i].length; j++) {
                outputArray[pos++] = byteArray[i][j];
            }
        }

        return outputArray;
    }

    public static String storeMatrices(String filePath, Pair<int[], ArrayList<float[][]>> neuralInformation) {

        int[] maxtrixInterpretation = neuralInformation.a;
        ArrayList<float[][]> matrices = neuralInformation.b;

        ByteBuffer metadata = ByteBuffer.allocate(4 + maxtrixInterpretation.length * 4);
        metadata.putInt(maxtrixInterpretation.length);

        for (int i : maxtrixInterpretation) {
            metadata.putInt(i);
        }

        byte[] matricesArray = convertMatricesToByteArray(matrices);

        Date date = new Date();
        SimpleDateFormat tf = new SimpleDateFormat("dd:hh:mm:ss");
        String name = "NN_ " + tf.format(date);
        Path path = FileSystems.getDefault().getPath(filePath, name);

        try {
            Files.write(path, metadata.array(), StandardOpenOption.CREATE_NEW);
            Files.write(path, matricesArray, StandardOpenOption.APPEND);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return path.toString();
    }

    public static Pair<int[], ArrayList<float[][]>> loadMatricesFromFile(String filePath) {
        byte[] arr;
        try {
            arr = Files.readAllBytes(Paths.get(filePath));
        } catch (IOException e) {
            e.printStackTrace();
            arr = new byte[0];
        } finally {

        }
        ByteBuffer buffer = ByteBuffer.wrap(arr);

        int matrixDistributionLength = buffer.getInt();
        int[] matricesIntegerpretation = new int[matrixDistributionLength];
        for (int i = 0; i < matrixDistributionLength; i++) {
            matricesIntegerpretation[i] = buffer.getInt();
        }
        ArrayList<float[][]> matricesList = new ArrayList<float[][]>();
        for (int i = 0; i < matricesIntegerpretation.length; i++) {
            for (int j = 0; j < matricesIntegerpretation[i]; j++) {
                if (buffer.getInt() != j) {
                    System.out.println("reading file had a Problem, File and read is not synced up");
                    return null;
                }
                int matrix_X_length = buffer.getInt();
                int matrix_Y_length = buffer.getInt();

                float[][] matrix = new float[matrix_X_length][matrix_Y_length];
                for (int k = 0; k < matrix_X_length; k++) {
                    for (int k2 = 0; k2 < matrix_Y_length; k2++) {
                        matrix[k][k2] = buffer.getFloat();
                    }
                }
                matricesList.add(matrix);
            }
        }
        return new Pair<int[], ArrayList<float[][]>>(matricesIntegerpretation, matricesList);

    }

    private static Pair<int[][], byte[]> loadDataSet(String dataPath, String labelPath) { // Pair<byte[][], byte[]>
        byte[] arrLabels;
        byte[] arrData;
        try {
            arrData = Files.readAllBytes(Paths.get(dataPath));
            arrLabels = Files.readAllBytes(Paths.get(labelPath));
        } catch (IOException e) {
            e.printStackTrace();
            arrLabels = new byte[0];
            arrData = new byte[0];
        }
        ByteBuffer labelBuffer = ByteBuffer.wrap(arrLabels);
        ByteBuffer dataBuffer = ByteBuffer.wrap(arrData);

        int num_LABEL = labelBuffer.position(4).getInt();
        int num_DATA = dataBuffer.position(4).getInt();
        int image_X = dataBuffer.getInt(); // 28
        int image_Y = dataBuffer.getInt(); // 28
        int image_FirstOffset = 16;
        int label_FirstOffset = 8;

        byte[] label = new byte[num_LABEL];
        int[][] data = new int[num_DATA][image_X * image_Y];

        for (int i = 0; i < num_LABEL; i++) {
            label[i] = arrLabels[i + label_FirstOffset];
        }

        for (int i = 0; i < data.length; i++) {
            for (int j = 0; j < image_X * image_Y; j++) {
                data[i][j] = arrData[i * image_X * image_Y + j + image_FirstOffset] & 0xFF;
            }
        }
        return new Pair<int[][], byte[]>(data, label);
    }
}
