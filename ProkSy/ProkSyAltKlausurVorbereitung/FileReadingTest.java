package ProkSy.ProkSyAltKlausurVorbereitung;

import java.io.*;

public class FileReadingTest {

    public static void main(String[] args) {
        File f = new File("ProkSy/ProkSyAltKlausurVorbereitung/Text.txt");
        try {
            FileReader fr = new FileReader(f);
            BufferedReader br = new BufferedReader(fr);
            System.out.println("hall");

            while (br.ready()) {
                System.out.println(br.readLine());
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            FileReader fileReader = new FileReader("file");

        } catch (FileNotFoundException e) {
            //TODO: handle exception
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            //fileReader.close();
        }
        String i = args[-1];

    }
}
