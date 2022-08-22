package PracticeProjects.leetcode;

import java.util.Arrays;
import java.util.Collection;
import java.util.Comparator;
import java.util.PriorityQueue;

class ListNode {
    int val;
    ListNode next;

    ListNode() {
    }

    ListNode(int val) {
        this.val = val;
    }

    ListNode(int val, ListNode next) {
        this.val = val;
        this.next = next;
    }
}

class NodeCompareator implements Comparator<ListNode> {

    @Override
    public int compare(ListNode o1, ListNode o2) {
        return Integer.compare(o1.val, o2.val);
    }

}

public class mergeLinkedListsThingy {

    public static void main(String[] args) {
        int l = 100000;
        ListNode[] arr = new ListNode[l];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = new ListNode(i);
        }
        mergeLinkedListsThingy m = new mergeLinkedListsThingy();
        m.mergeKLists(arr);
    }

    public ListNode mergeKLists(ListNode[] lists) {
        PriorityQueue<ListNode> nodes = new PriorityQueue<ListNode>(new NodeCompareator());

        ListNode out = null;
        ListNode current = null;
        ListNode newNode = null;

        for (ListNode listNode : lists) {
            if (listNode != null)
                nodes.add(listNode);
        }

        if ((newNode = nodes.poll()) != null) {
            if (newNode.next != null)
                nodes.add(newNode.next);
            out = newNode;
            current = newNode;
        }

        while (!nodes.isEmpty()) {
            newNode = nodes.poll();

            if (newNode.next != null)
                nodes.add(newNode.next);

            current.next = newNode;
            current = newNode;
        }

        return out;

    }
}
