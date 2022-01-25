package PracticeProjects.BinaryTree;

import java.util.ArrayList;

public class TreeNode {
    private TreeNode leftNode;
    private TreeNode rightNode;

    private Integer value;

    public int level;

    /**
     * @param value
     */
    public TreeNode() {
    }

    /**
     * @param value
     */
    public TreeNode(Integer value) {
        this.value = value;
    }

    public void insert(Integer value) {
        if (Integer.valueOf(value) < Integer.valueOf(this.value)) {
            leftNode = insertNode(leftNode, value);
        } else {
            rightNode = insertNode(rightNode, value);
        }
    }

    public TreeNode insertNode(TreeNode node, Integer value) {
        if (node == null) {
            node = new TreeNode(value);
            node.level = this.level + 1;
        } else {
            node.insert(value);

        }
        return node;
    }

    /**
     * @return the value
     */
    public Integer getValue() {
        return value;
    }

    /**
     * @param value the value to set
     */
    public void setValue(Integer value) {
        this.value = value;
    }

    public void switchNodes() {
        TreeNode help = leftNode;
        leftNode = rightNode;
        rightNode = help;

    }

    @Override
    public String toString() {
        return ("  " + this.value + "  \n" + leftNode + " | " + rightNode);
    }

    public void addToList(ArrayList<String> liste) {

        while (liste.size() < this.level + 2) {
            liste.add("");
        }
        String outputThis = Integer.toString(this.value);
        String out = liste.get(this.level) + " |" + outputThis + "|";
        liste.set(this.level, out);

        if (leftNode != null) {
            leftNode.addToList(liste);
        } else {
            liste.set(this.level + 1, out = liste.get(this.level + 1) + " |-|");
        }

        if (rightNode != null) {
            rightNode.addToList(liste);
        } else {
            liste.set(this.level + 1, liste.get(this.level + 1) + " |-|");
        }

    }

}
