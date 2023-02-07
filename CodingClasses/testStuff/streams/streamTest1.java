package CodingClasses.testStuff.streams;

import java.io.*;
import java.rmi.Naming;
import java.rmi.registry.Registry;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.Stream;

import java.awt.*;

public class streamTest1<T> implements test {

    private static final String T = null;
    T object = null;

    Object i = 0;

    public static void main(String[] args) {
        System.out.println(args[0]);

        int[] arr = (int[]) Stream.iterate(0, x -> x = x + 1).limit(6).mapToInt(x -> Integer.valueOf(x)).toArray();
        System.out.println(Arrays.toString(arr));

        Arrays.stream(arr).peek(x -> System.out.println(x)).map(x -> x = x * 2).forEach(x -> System.out.println(x));

        arr = Arrays.stream(arr).mapToObj(x -> Integer.valueOf(x)).mapToInt(x -> x.intValue()).toArray();
        Stream.generate(() -> Math.random()).limit(10).forEach(x -> System.out.println(x));
        Thread t = null;
        GridLayout l = new GridLayout();
        InputStream in = System.in;
        InputStreamReader ir = new InputStreamReader(in);
        BufferedReader bir = new BufferedReader(ir);

        OutputStream out = System.out;
        OutputStreamWriter ow = new OutputStreamWriter(out);
        BufferedWriter bow = new BufferedWriter(ow);

        test.doSth();

        String inp;
        try {
            while ((inp = bir.readLine()) != null) {
                bow.append(inp);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        ArrayList<?> liste = new ArrayList<>();
        liste.add(Integer.valueOf(10), null);
    }

    public void m1() {

        if (object instanceof String) {

        }

    }

}

interface test {
    static void doSth() {
        System.out.println("jaja");
    }

    default void doSth2() {
        System.out.println("nene");
    }
}