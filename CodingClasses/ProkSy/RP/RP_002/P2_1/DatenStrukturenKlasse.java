package CodingClasses.ProkSy.RP.RP_002.P2_1;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.TreeSet;

public class DatenStrukturenKlasse {
    int[] nummern = { 10, 17, 5, 7, 9, 10 };

    HashSet<Integer> HS = new HashSet<Integer>();
    TreeSet<Integer> TS = new TreeSet<Integer>();
    HashMap<Integer, Integer> HM = new HashMap<Integer, Integer>();
    ArrayList<Integer> AL = new ArrayList<Integer>();
    LinkedList<Integer> LL = new LinkedList<Integer>();

    public static void main(String[] args) {
        DatenStrukturenKlasse dc = new DatenStrukturenKlasse();
        for (int i = 0; i < dc.nummern.length; i++) {
            dc.AL.add(dc.nummern[i]);
            dc.LL.add(dc.nummern[i]);
            dc.HS.add(dc.nummern[i]);
            dc.TS.add(dc.nummern[i]);
            dc.HM.put(i, dc.nummern[i]);
        }
        System.out.println("ArrayList: " + dc.AL.toString());
        System.out.println("LinkedList: " + dc.LL.toString());
        System.out.println("TreeSet: " + dc.TS.toString());
        System.out.println("HashSet: " + dc.HS.toString());
        System.out.println("HashMap: " + dc.HM.toString());

        Collections.sort(dc.LL);

        System.out.println(dc.LL.toString());
    }
}