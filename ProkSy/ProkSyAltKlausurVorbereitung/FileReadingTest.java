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
            ObjectInputStream ois = new ObjectInputStream(new FileInputStream("file"));
            Object i = ois.readObject();
        } catch (FileNotFoundException e) {
            //TODO: handle exception
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally {
            //fileReader.close();
        }
        String i = args[-1];

    }
}
