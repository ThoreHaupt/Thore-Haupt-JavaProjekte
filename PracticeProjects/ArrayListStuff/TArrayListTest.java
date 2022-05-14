package PracticeProjects.ArrayListStuff;

import static org.junit.jupiter.api.Assertions.assertEquals;

import javax.swing.text.html.FormView;

import org.junit.jupiter.api.Test;

public class TArrayListTest {
    TArrayList<Integer> list = new TArrayList<Integer>(0);
    TArrayList<Integer> help;
    Integer[] intArr = new Integer[100];

    @Test
    void testAdd() {
        list.add(10);
        list.add(10);
        assertEquals(2, list.size());
        assertEquals("(10, 10)", list.toString());

        fill();
        help = new TArrayList<Integer>(intArr);
        list.add(help);
        list.add(intArr);
    }

    void fill() {
        for (int i = 0; i < 100; i++) {
            intArr[i] = i;

        }
    }

    @Test
    void testInsert() {

    }

    @Test
    void testMain() {

    }

    @Test
    void testPrint() {

    }
}
