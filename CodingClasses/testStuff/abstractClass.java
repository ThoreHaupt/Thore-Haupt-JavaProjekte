package CodingClasses.testStuff;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;

public class abstractClass {
    public static void main(String[] args) throws IOException {
        File f = new File("listeMittwoch.txt");
        f.createNewFile();
        FileWriter fw = new FileWriter(f, false);
        String s = "ujurs@student.kit.edu ulqqw@student.kit.edu uuxkv@student.kit.edu utwsg@student.kit.edu uxeta@student.kit.edu uhfng@student.kit.edu upyai@student.kit.edu ukwjd@student.kit.edu ulqef@student.kit.edu ufkic@student.kit.edu uezbg@student.kit.edu ufwjc@student.kit.edu unfgi@student.kit.edu utktd@student.kit.edu ukfjj@student.kit.edu uxwix@student.kit.edu uwhmb@student.kit.edu urzse@student.kit.edu uyxfz@student.kit.edu uiwfj@student.kit.edu utiqt@student.kit.edu uusec@student.kit.edu uxwdq@student.kit.edu ufzeu@student.kit.edu uhqrh@student.kit.edu ujzoz@student.kit.edu uvvpp@student.kit.edu uwmmg@student.kit.edu uhgiu@student.kit.edu";
        System.out.println(Arrays.stream(s.split("@")).count());
        s = s.replace("@student.kit.edu", "\n").replace(" ", "");

        fw.write(s);
        fw.close();

    }
}
