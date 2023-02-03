package CodingClasses.testStuff.IOStreams;

import java.io.*;
import java.util.Arrays;
import java.util.Scanner;

public class IOStreamTest {
    public static void main(String[] args) throws FileNotFoundException, IOException {

        //Inputstreams:

        File f = new File("test123");
        f.createNewFile();

        //InputStream as source
        InputStream inputStream = System.in; // InputStream abstract

        // Inputstream/ ByteStream to Character Stream ->
        InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
        // Buffer that Reader
        BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

        /* 
        Normal von Socket: 
        InputStream -> InputStreamReader -> BufferedReader
        
        
        
        */
        /* 
        ObjectInputStream ois = new ObjectInputStream(inputStream);
         */
        //File Input as source
        FileReader fileReader = new FileReader(f);

        BufferedReader bufferedReader2 = new BufferedReader(fileReader);
        bufferedReader2.readLine();

        //Outputstreams:
        //Console Outputstream: 
        /** 
         * @see OutputStream (abstract) -> PrintStream
         * */
        PrintStream out = System.out;
        OutputStream s; // ist abstract

        File file2 = new File("pathname");
        file2.createNewFile();
        FileOutputStream f2 = new FileOutputStream(file2);
        BufferedOutputStream bos = new BufferedOutputStream(f2);
        OutputStreamWriter osw = new OutputStreamWriter(f2);
        BufferedWriter bw2 = new BufferedWriter(osw);
        ObjectOutputStream oos = new ObjectOutputStream(f2);
        /*  bos.write(Boolean.valueOf(true));
        bos.flush();
        bw2.write(Boolean.valueOf(true));
        bw2.flush();
        osw.write(Boolean.valueOf(true));
        osw.flush(); */
        oos.writeObject(Boolean.valueOf(true));

        PrintWriter pw3 = new PrintWriter(f2);
        osw.write("hallo");

        // Outputs to File with autoflushing -> Empties automatically
        PrintWriter printWriter = new PrintWriter(new File("test123"));
        PrintWriter printWriter2 = new PrintWriter(out, true);
        printWriter.write("s");
        printWriter.println();

        /* 
           PrintWriter ist ein Writer, aber braucht einen anderen Writer, 
           der die Writer Klasen implementiert.
           Deswegen muss man da zum Beispiel einen Outputstream geben bzw eigentlich ist der Autputstream selber
           ja ein PrintStream zumindestens für die Konsole
        
         */
        // Buffered Outputstream Writes to out stream, once flush is done/ buffer full.
        BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(out);

        ObjectOutputStream objoutputStream = new ObjectOutputStream(out);
        objoutputStream.writeObject(Integer.valueOf(100));
        DataOutputStream dataOutPStream;

    }
}
