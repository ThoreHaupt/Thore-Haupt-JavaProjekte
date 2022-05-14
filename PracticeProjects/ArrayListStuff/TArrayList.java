package PracticeProjects.ArrayListStuff;

import javax.xml.namespace.QName;

public class TArrayList<T> {
    private Object[] array;
    private int arrSize = 0;
    private int lastIndex = 0;
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

    public TArrayList() {
        this(10);
    }

    public TArrayList(int startSize) {
        this.arrSize = (startSize != 0) ? startSize : 1;
        this.array = new Object[arrSize];
        // type = (Class<T>) ((ParameterizedType)
        // getClass().getGenericSuperclass()).getActualTypeArguments()[0];
    }

    public TArrayList(Object[] arr) throws wrongTypeException {
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
        sizeCheck(1);
        array[lastIndex++] = element;
    }

    public void add(T[] arr) {
        sizeCheck(arr.length);
        for (T t : arr) {
            add(t);
        }
    }

    public void add(TArrayList<T> list) {
        sizeCheck(list.size());
        for (T object : list.asArray()) {
            this.add(object);
        }
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

    public T get(int index) throws IndexOutOfBoundsException {

        return (T) array[index];
    }

    private void sizeCheck(int amount) {
        if (arrSize > lastIndex + amount - 1) {
            return;
        } else {
            Object[] newArray = new Object[arrSize * 2];
            for (int i = 0; i < array.length; i++) {
                newArray[i] = array[i];
            }
            array = newArray;
            arrSize *= 2;
            return;
        }
    }

    private TArrayList<T> shiftArray(int index, int amount) {
        sizeCheck(amount);
        Object[] tempArray = new Object[Math.abs(amount)];
        int tempLoc = 0;
        Object help;
        lastIndex += amount;
        if (amount > 0) {
            for (int i = index; i < lastIndex; i++) {
                tempLoc = (i - index) % amount;
                help = tempArray[tempLoc];
                tempArray[tempLoc] = (i >= lastIndex) ? null : array[i];
                array[i] = help;
            }
        } else {
            for (int i = index; i > lastIndex; i++) {
                array[i] = array[i + Math.abs(amount)];
            }
        }

        return this;
    }

    public String toString() {
        String out = "(";
        for (int index = 0; index < lastIndex - 1; index++) {
            if (array[index] != null) {
                out += array[index] + ", ";
            }
        }
        if (array[lastIndex - 1] != null) {
            out += array[lastIndex - 1];
        }
        out += ")";
        return out;
    }

    public int size() {
        return lastIndex;
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
        lastIndex--;
    }

    public void clear() {
        array = new Object[16];
        arrSize = 16;
        lastIndex = -1;
    }
}
