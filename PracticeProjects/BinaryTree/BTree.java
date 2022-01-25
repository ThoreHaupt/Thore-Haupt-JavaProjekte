package PracticeProjects.BinaryTree;

import java.util.ArrayList;
import java.util.Arrays;

public class BTree {

    TreeNode root;

    public BTree() {
        root = new TreeNode(0);
        root.level = 0;
    }

    public BTree(Integer RootValue) {
        root = new TreeNode(0);
        root.level = 0;
    }

    public BTree(Integer[] values) {
        root = new TreeNode(values[0]);
        root.level = 0;
        for (int i = 1; i < values.length; i++) {
            root.insert(values[i]);
        }
    }

    public void insert(Integer value) {
        root.insert(value);
    }

    public String toString() {
        return root.toString();
    }

    public static String createStringFromList(ArrayList<String> list) {
        int len = list.size();
        String outString = "";
        String[][] arr = new String[len][];
        for (int i = 0; i < len - 1; i++) {
            arr[i] = list.get(i).split(" ");
            ArrayList<String> nextLayer = new ArrayList<String>(Arrays.asList(list.get(i + 1).split(" ")));
            for (int j = 0; j < arr[i].length; j++) {
                if (arr[i][j].equals("|-|")) {
                    String ins = "";
                    for (int k = 0; k < j; k++) {
                        ins += nextLayer.get(k);
                    }
                    ins += " |-| |-|";

                    for (int k = j; k < arr[i].length; k++) {
                        ins += " " + nextLayer.get(k);
                    }
                    list.set(i + 1, ins);
                }
            }
        }
        for (String strings : list) {
            outString += strings + "\n";
        }

        return outString;
    }

    public static void main(String[] args) {
        BTree tree = new BTree(new Integer[] { 1, 2, 3, 4, 5, 2, 1 });
        System.out.println(tree);
        System.out.println("-----------");
        ArrayList<String> liste = new ArrayList<String>(6);
        tree.root.addToList(liste);
        System.out.println(createStringFromList(liste));

    }
}
