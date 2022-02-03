package PracticeProjects.ArrayListStuff;

public class TArrayList<T> {
    Object[] array;
    int arrSize = 0;
    int lastIndex = 0;
    Class<T> type;

    public static void main(String[] args) {
        TArrayList<String> list = new TArrayList<String>();
        list.add("1");
        list.add("2");
        list.add("3");
        list.insert("element", 2);
        list.print();

    }

    static class wrongTypeException extends RuntimeException {
        public wrongTypeException(String str) {
            super(str);
        }
    }

    public TArrayList() {
        this.arrSize = 10;
        this.array = new Object[10];
    }

    public TArrayList(int startSize) {
        this.array = new Object[startSize];
    }

    public TArrayList(Object[] arr) throws wrongTypeException {
        for (int i = 0; i < arr.length; i++) {
            if (type.isInstance(arr[i]))
                throw new wrongTypeException("Type is" + type.toString());
        }
        this.array = arr;
    }

    public void add(T element) {
        sizeManager(1);
        array[lastIndex] = element;
    }

    public void insert(T element, int index) throws wrongTypeException {
        shiftArray(index, 1);
        if (type.isInstance(element))
            throw new wrongTypeException("Type is" + type.toString());
        array[index] = element;
    }

    private void sizeManager(int amount) {
        if (arrSize + amount < lastIndex - 1) {
            return;
        } else {
            Object[] newArray = new Object[arrSize * 2];
            for (int i = 0; i < array.length; i++) {
                newArray[i] = array[i];
            }
            array = newArray;
            return;
        }
    }

    private TArrayList<T> shiftArray(int index, int amount) {
        sizeManager(amount);
        Object[] tempArray = new Object[amount];
        int tempLoc = 0;
        Object help;
        for (int i = index; i < lastIndex; i++) {
            tempLoc = (i - index) % amount;
            help = tempArray[tempLoc];
            tempArray[tempLoc] = array[i];
            array[i] = help;
        }
        return this;
    }

    public String print() {
        String out = "";
        for (int index = 0; index < array.length; index++) {
            if (array[index] != null) {
                out += array[index];
            }
        }
        return out;
    }
}
