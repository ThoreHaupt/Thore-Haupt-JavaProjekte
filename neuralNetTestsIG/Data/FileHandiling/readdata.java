package neuralNetTestsIG.Data.FileHandiling;

import java.io.IOException;
import java.math.BigInteger;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;

public class readdata {

    // Main driver method
    public static void main(String[] args)
            throws IOException {

        // Creating an object of Path class and
        // assigning local directory path of file to it
        Path path = Paths.get(
                "neuralNetTestsIG/Data/train-images");

        // Converting the file into a byte array
        // using Files.readAllBytes() method
        byte[] arr = Files.readAllBytes(path);

        // Printing the above byte array

        int off = 8;
        byte[] array = new byte[4];

        for (int i = 0; i < 4; i++) {
            array[i] = arr[off + i];
        }

        /*
         * for (int i = 0; i < 3; i++) {
         * array[i] = arr[off + i];
         * }
         */

        System.out.println("Offset: " + off);
        System.out.println("Value: (32-int) " + new BigInteger(array).intValue());

    }

}
