package ProkSy.ProkSyAltKlausurVorbereitung;

import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class TokenizerBeispiel {
    public static void main(String[] args) {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StreamTokenizer t = new StreamTokenizer(br);
        while (true) {
            try {
                int tt = t.nextToken();
                switch (tt) {
                    case StreamTokenizer.TT_NUMBER:
                        System.out.println(t.nval);
                        break;
                    case StreamTokenizer.TT_WORD:
                        System.out.println(t.sval);
                        break;
                    case StreamTokenizer.TT_EOF:
                        System.out.println("End of Stream");
                        break;

                    case StreamTokenizer.TT_EOL:
                        System.out.println("End of line");
                        break;

                    default:
                        break;
                }
            } catch (IOException e) {

            }

        }
    }
}
