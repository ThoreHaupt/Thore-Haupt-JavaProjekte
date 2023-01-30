package testStuff;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class KonstruktorTets extends SuperTest {

    static class Land {
        String name = "";

        String att1 = "";
        String att2 = "";
        String att3 = "";

        public Land(String name, String att1) {
            this.name = name;
            this.att1 = att1;
        }

    }

    public static void main(String[] args) {
        System.out.println("main");
        HashMap<String, Land> map = new HashMap<>();
        map.put("Deutschland", new Land("Deutschland", "dumm"));
        System.out.println(map.get("Deutschland").att1);

        Land[] arr = new Land[1];
        arr[0] = new Land("land1", "eigenschaft");

        ArrayList<Land> arrList = new ArrayList<>();

        Scanner scanner = new Scanner(System.in);

        String entry = scanner.nextLine();
        for (int i = 0; i < arr.length; i++) {
            if (arr[i].name.equals(entry)) {
                System.out.println(arr[i].name + ": " + arr[i].att1);
                break;
            }
        }
        scanner.close();

    }
}
