package AIFB.ArrayListen;

import java.util.Arrays;

public class ArrayListe {
    int lastIndex = -1;
    int size = 0;
    Integer[] array = new Integer[10];

    public void nachRechtsVerschieben(int index) {
        if (index >= size || index < 0) {
            return;
        }

        if (lastIndex >= array.length) {
            return;
        }

        int currentIndex = lastIndex;
        while (currentIndex > index) {
            array[currentIndex + 1] = array[currentIndex];
            currentIndex--;
        }
        array[index] = null;
        lastIndex++;
    }

    public void nachLinksVerschieben(int index) {
        if (index >= size || index < 0) {
            return;
        }

        if (index > lastIndex) {
            return;
        }

        int currentIndex = index;
        while (currentIndex < lastIndex) {
            array[currentIndex] = array[currentIndex + 1];
            currentIndex++;
        }
        array[lastIndex] = null;
        lastIndex--;
    }

    public void neuesArray() {
        Integer[] neuesArray = new Integer[array.length * 2];
        for (int i = 0; i <= lastIndex; i++) {
            neuesArray[i] = array[i];
        }
        array = neuesArray;
    }

    public void add(Integer wert) {
        if (lastIndex > array.length * 0.8) {
            neuesArray();
        }
        array[lastIndex + 1] = wert;
        lastIndex++;
        size++;
    }

    /**
     * einen Wert in die Arraylist einfügen
     * @param index
     * @param wert
     */
    public void add(int index, Integer wert) {
        if (index > lastIndex || index < 0) {
            throw new IndexOutOfBoundsException("Index nich gültig");
        }
        if (lastIndex > array.length * 0.8) {
            neuesArray();
        }
        nachRechtsVerschieben(index);
        array[index] = wert;
    }

    public Integer get(int index) {
        if (index > lastIndex || index < 0) {
            throw new IndexOutOfBoundsException("Index nich gültig");
        }
        return array[index];
    }

    public void remove(int index) {
        if (index > lastIndex || index < 0) {
            throw new IndexOutOfBoundsException("Index nich gültig");
        }
        nachLinksVerschieben(index);
    }

    public String toString() {
        return Arrays.toString(array);
    }

}
