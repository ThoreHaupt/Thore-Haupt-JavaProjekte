package projects;

import java.io.FileWriter;
import java.io.IOException;

public class SupportingClaculations {
    public static double round(double d, int i) {
        return (double) ((int) (d * Math.pow(10, i))) / Math.pow(10, i);
    }

    public static void main(String[] args) {
        System.out.println(round(1.1234123, 5));
    }

    public static void addToFile(String path, String input, boolean newline) {
        try {
            FileWriter myWriter = new FileWriter(path, true);
            input += newline ? "\n" : "";
            myWriter.write(input);
            myWriter.close();
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }
}
