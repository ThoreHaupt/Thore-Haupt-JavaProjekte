package CodingClasses.ProkSy.Tutorium.T5;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class inputStreamReaderTest {
    static boolean running = true;

    public static void main(String[] args) throws IOException {
        InputStreamReader ir = new InputStreamReader(System.in);

        BufferedReader br = new BufferedReader(ir);

        while (running) {
            /*
             * if (ir.ready()) {
             * continue;
             * }
             */
            char i = (char) br.read();
            if (i == -1) {
                running = false;
            }
            System.out.println(i);
        }
        System.out.println("end");
        ir.close();
    }
}