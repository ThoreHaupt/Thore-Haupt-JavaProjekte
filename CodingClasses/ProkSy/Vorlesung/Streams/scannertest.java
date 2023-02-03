package CodingClasses.ProkSy.Vorlesung.Streams;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

public class scannertest implements Serializable {
    int a = 10;
    String b = "hallo World!";

    public static void main(String[] args) throws IOException {
        File file = new File("log/log.txt");
        file.createNewFile();

        scannertest s = new scannertest();
        scannertest sa = new scannertest();
        File f = new File("hallo");
        f.createNewFile();
        FileOutputStream datAus = new FileOutputStream(f);

        ObjectOutputStream os = new ObjectOutputStream(datAus);

        os.writeObject(s);
        sa.a += 10;
        os.writeObject(sa);

        FileInputStream datIn = new FileInputStream(f);
        ObjectInputStream oi = new ObjectInputStream(datIn);

        try {
            scannertest a = (scannertest) oi.readObject();
            System.out.println(a.a);
            scannertest b = (scannertest) oi.readObject();
            System.out.println(b.a);
        } catch (ClassNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }

    public static void x() throws Exception {

    }
}
