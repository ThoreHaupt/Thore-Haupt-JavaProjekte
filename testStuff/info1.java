package testStuff;

import java.util.LinkedList;
import java.util.Queue;

public class info1 {
    public static void main(String[] args) {
        System.out.println(a1(2, 10));
        System.out.println(a2(1, 11, 2));
        Queue<Integer> a = new LinkedList<Integer>();
        Queue<Integer> b = new LinkedList<Integer>(a);
    }

    static int a1(int x, int n) {
        int q = 1;
        for (int i = 1; i <= n; i++) {
            q = q * x;
        }
        return q;
    }

    static int a2(int a, int b, int x) {
        System.out.println(a + " " + b);
        if ((a < b)) {
            return a2(a, ((a + b) / 2), x) * a2((a + b) / 2 + 1, b, x);
        } else {
            return x;
        }
    }
}
