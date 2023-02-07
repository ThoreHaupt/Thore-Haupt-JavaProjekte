package CodingClasses.testStuff;

import java.io.*;
import java.util.ArrayList;

import org.junit.experimental.theories.Theories;

public class Aufgabe5 {
    public static void main(String[] args) throws FileNotFoundException {
        ArrayList<?> l = new ArrayList<>();

        ((ArrayList<Integer>) l).add(Integer.valueOf(12));
        l = ((ArrayList<String>) l);

        System.out.println(l.get(0));

        Runnable run1 = new runObj();
        Runnable run2 = () -> System.out.println("hallo");
        Runnable run3 = Math::random;
        Runnable run4 = () -> Math.random();

        Thread t1 = new Thread(run1);
        Thread t2 = new Thread(run2);
        Thread t3 = new Thread(run3);

        //Inputstream as Source
        InputStream inputStream = System.in;

        // Inputstream to Character Stream ->
        InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
        BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

        //File Input as source
        FileReader fileReader = new FileReader(new File("test123"));
        BufferedReader bufferedReader2 = new BufferedReader(fileReader);
    }

}

class runObj implements Runnable {
    @Override
    public void run() {
        System.out.println("hallo");
    }
}