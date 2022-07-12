package testStuff.streams;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.stream.Stream;

public class streamTest1 {
    public static void main(String[] args) {
        int[] arr = (int[]) Stream.iterate(0, x -> x = x + 1).limit(6).mapToInt(x -> Integer.valueOf(x)).toArray();
        System.out.println(Arrays.toString(arr));

        Arrays.stream(arr).peek(x -> System.out.println(x)).map(x -> x = x * 2).forEach(x -> System.out.println(x));

        File f = new File("hallo");
        try (FileReader fr = new FileReader(f)) {
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
