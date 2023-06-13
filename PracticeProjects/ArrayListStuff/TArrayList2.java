package PracticeProjects.ArrayListStuff;

import java.util.Arrays;
import java.util.Comparator;

public class TArrayList2<T> {
    private Object[] array;
    private int arrSize = 0;
    private int lastIndex = -1;
    private Comparator<T> comparator;
    // final Class<T> type;

    public static void main(String[] args) {
        TArrayList<String> list = new TArrayList<String>();
        list.add("1");
        list.add("2");
        list.add("3");
        list.insert("element", 1);
        System.out.println(list.toString());
        for (int i = 0; i < 20; i++) {
            list.add("" + i);
        }
        System.out.println(list.toString());

    }

    static class wrongTypeException extends RuntimeException {
        public wrongTypeException(String str) {
            super(str);
        }
    }

    public TArrayList2() {
        this(8);
    }

    public TArrayList2(int startSize) {
        this.arrSize = startSize;
        this.array = new Object[startSize];
        // type = (Class<T>) ((ParameterizedType)
        // getClass().getGenericSuperclass()).getActualTypeArguments()[0];
    }

    public TArrayList2(Object[] arr) throws wrongTypeException {
        /*
         * for (int i = 0; i < arr.length; i++) {
         * 
         * if (type.isInstance(arr[i]))
         * throw new wrongTypeException("Type is" + type.toString());
         * 
         * }
         */
        this.array = arr;
    }

    public void add(T element) {
        handleChangeInSize(1);
        array[lastIndex++ + 1] = element;
    }

    public void add(T[] arr) {
        int newArraySize = getNewArraySize(arr.length);
        Object[] tempArr = new Object[newArraySize];
        for (int index = 0; index < lastIndex + 1; index++) {
            tempArr[index] = (T) array[index];
        }
        for (int index = lastIndex + 1; index < lastIndex + arr.length; index++) {
            tempArr[index] = (T) arr[index - lastIndex];
        }
        array = tempArr;
    }

    public void add(TArrayList<T> list) {
        this.add(list.asArray());
    }

    public void insert(T element, int index) throws wrongTypeException {
        shiftArray(index, 1);
        /*
         * if (type.isInstance(element))
         * throw new wrongTypeException("Type is" + type.toString());
         */
        array[index] = element;
    }

    public void insert(T[] array, int firstIndex) {
        shiftArray(firstIndex, array.length);
        for (int i = 0; i < array.length; i++) {
            this.array[firstIndex + i] = array[i];
        }
    }

    @SuppressWarnings("unchecked")
    public T get(int index) throws IndexOutOfBoundsException {
        return (T) array[index];
    }

    private int sizeCheck(int amount) {
        return sizeCheck(amount, arrSize);
    }

    private int sizeCheck(int amount, int currentSize) {
        if (currentSize > lastIndex + amount + 1) {
            if (currentSize > (lastIndex + amount + 1) * 2 && currentSize > 16)
                return -1;
            return 0;
        } else {
            return 1;
        }
    }

    private int getNewArraySizeIII(int amount) {
        int newArraySize = arrSize;
        if (sizeCheck(amount) == 0) {
            newArraySize = arrSize;
        } else if (sizeCheck(amount) == 1) {
            while (sizeCheck(amount, newArraySize) != 0)
                newArraySize *= 2;
        } else if (sizeCheck(amount) == -1) {
            while (sizeCheck(amount, newArraySize) != 0)
                newArraySize /= 2;
        }
        return newArraySize;
    }

    private int getNewArraySize(int amount) {

        int newExp = (int) (Math.log(arrSize + amount) / Math.log(2)) + 1;
        int newArraySize = (int) Math.pow(2, newExp);
        return newArraySize;
    }

    private void changeSize(int newArraySize) {
        Object[] tempArr = new Object[newArraySize];
        for (int i = 0; i < Math.min(array.length, newArraySize); i++) {
            tempArr[i] = array[i];
        }
        arrSize = newArraySize;
        array = tempArr;
    }

    private void handleChangeInSize(int amount) {
        if (sizeCheck(amount) == 0)
            return;
        int newArraySize = getNewArraySize(amount);
        changeSize(newArraySize);
    }

    private TArrayList2<T> shiftArray(int index, int amount) {
        int newArraySize = getNewArraySize(amount);

        Object[] tempArray = new Object[newArraySize];
        lastIndex += amount;
        if (amount > 0) {
            for (int i = 0; i < index; i++) {
                tempArray[i] = array[i];
            }
            for (int i = index + amount; i < lastIndex + 1; i++) {
                tempArray[i] = array[i - amount];
            }
        }
        if (amount < 0) {
            for (int i = 0; i < index + amount; i++) {
                tempArray[i] = array[i];
            }
            for (int i = index; i < lastIndex + 1; i++) {
                tempArray[i] = array[i - amount];
            }
        }

        return this;
    }

    @Override
    public String toString() {
        String out = "(";
        for (int index = 0; index < lastIndex; index++) {
            out += array[index] + ", ";
        }
        out += array[lastIndex] + ")";
        return out;
    }

    public int size() {
        return lastIndex + 1;
    }

    public T[] asArray() {
        Object[] returnarray = new Object[lastIndex];
        for (int i = 0; i < returnarray.length; i++) {
            returnarray[i] = array[i];
        }
        return (T[]) returnarray;
    }

    public void remove(int index) {
        shiftArray(index, -1);
    }

    public void sort() {
        Arrays.sort(array);
    }
}
