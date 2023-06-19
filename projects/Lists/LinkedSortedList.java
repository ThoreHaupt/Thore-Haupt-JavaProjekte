package Projects.Lists;

import java.util.Collection;
import java.util.Comparator;
import java.util.Iterator;

public class LinkedSortedList<T> implements Iterable<T> {

    public static void main(String[] args) {
        LinkedSortedList<Integer> list = new LinkedSortedList<>(new Comparator<Integer>() {

            @Override
            public int compare(Integer o1, Integer o2) {
                if (o1.equals(o2))
                    return 0;
                else if (o1.intValue() > o2.intValue())
                    return 1;
                else
                    return -1;
            }

        });

        for (int i = 1; i < 20; i++) {
            list.putEnd(i);
            System.out.println(list);
        }
        for (int i = 30; i > 0; i--) {
            list.putStart(i);
            System.out.println(list);
        }
        list.insertionSortForeward();
        System.out.println(list);
    }

    private Element<T> head;
    private Element<T> tail;
    private Comparator<T> comparator;
    private int size = 0;

    /**
     * @author ME
     *
     * @param <T>
     */
    private class Element<E> {

        private E value;
        private Element<E> next;
        private Element<E> pred;

        public Element(E val) {
            this.value = val;
        }

        public void setNext(Element<E> next) {
            this.next = next;
        }

        public void setPred(Element<E> pred) {
            this.pred = pred;
        }

        public Element<E> next() {
            return next;
        }

        public Element<E> pred() {
            return pred;
        }

        public E val() {
            return value;
        }

        public void setVal(E val) {
            this.value = val;
        }

    }

    public LinkedSortedList(Comparator<T> comparator) {
        this.comparator = comparator;
    }

    public void add(T val) {
        putEnd(val);
    }

    public void putStart(T val) {
        insertNext(null, val);
        insertionSortForeward();
    }

    public void putEnd(T val) {
        insertNext(tail, val);
        insertionSortBackward();
    }

    public void setComparator(Comparator<T> comparator) {
        this.comparator = comparator;
        sort();
    }

    protected void insertNext(Element<T> pred, T val) {
        insertNext(new Element<T>(val), pred);
    }

    public int size() {
        return size;
    }

    protected void insertNext(Element<T> newEl, Element<T> pred) {
        if (head == null) {
            head = newEl;
            tail = newEl;
        } else if (pred == null) {
            newEl.setNext(head);
            newEl.setPred(null);
            head.setPred(newEl);
            head = newEl;
        } else if (pred == tail) {
            pred.setNext(newEl);
            newEl.setPred(pred);
            newEl.setNext(null);
            tail = newEl;
        } else {
            newEl.setNext(pred.next);
            newEl.setPred(pred);
            pred.setNext(newEl);
            newEl.next().setPred(newEl);
        }
        size++;
    }

    public void removeEl(Element<T> element) {
        if (element == null)
            return;
        if (element.next() != null) {
            element.next().setPred(element.pred());
        } else {
            tail = element.pred();
            if (tail != null)
                tail.setNext(null);
        }
        if (element.pred() != null) {
            element.pred().setNext(element.next());
        } else {
            head = element.next();
            if (head != null)
                head.setPred(null);
        }
        size--;
    }

    public boolean remove(T val) {
        Element<T> current = head;
        while (current != null && comparator.compare(current.val(), val) != 0) {
            current = current.next();
        }
        if (current != null)
            return false;
        else {
            removeEl(current);
            return true;
        }
    }

    @Override
    public String toString() {
        String out = "[";
        Element<T> current = head;
        while (current != null) {
            out += current.val().toString();
            if (current.next() != null)
                out += ", ";
            current = current.next();
        }
        return out + "]";

    }

    public void add(int index, T value) {
        Element<T> pred = getElbyIndex(index - 1);
        insertNext(pred, value);
    }

    Element<T> getElbyIndex(int index) {
        Element<T> current = head;
        for (int i = 0; i < index; i++) {
            current = current.next();
        }
        return current;
    }

    public void sort() {
        insertionSortBackward();
    }

    /**
     * sorts in an acending order for Type T with O(n^2) (worst case time complexity)
     * sorts by comparing backwards -> optimal for inserting at the end
     */
    private void insertionSortBackward() {
        Element<T> current = head;
        Element<T> back = head;
        while (current != null) {
            back = current.pred();
            while (back != null && comparator.compare(current.val(), back.val()) == -1) {
                back = back.pred();
            }
            if (back == current.pred()) {
                current = current.next();
                continue;
            }
            Element<T> nextCurrent = current.next();
            removeEl(current);
            insertNext(current, back);
            current = nextCurrent;
        }
    }

    /**
     * sorts in an acending order for Type T with O(n^2) (worst case time complexity)
     * sorts by sorting in comparing forewards -> optimal for inserting at the end
     */
    private void insertionSortForeward() {
        Element<T> current = tail;
        Element<T> front = tail;
        while (current != null) {
            front = current.next();
            while (front != null && comparator.compare(current.val(), front.val()) == 1) {
                front = front.next();
            }
            if (front == current.next()) {
                current = current.pred();
                continue;
            }
            Element<T> nextCurrent = current.pred();
            removeEl(current);
            insertNext(current, front == null ? tail : front.pred());
            current = nextCurrent;
        }
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            Element<T> current = head;

            @Override
            public boolean hasNext() {
                return head.next() != null;
            }

            @Override
            public T next() {
                current = current.next();
                return current.val();
            }

        };
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public boolean contains(T o) {
        Element<T> current = head;
        while (current != null) {
            if (comparator.compare(current.val(), o) == 0)
                return true;
        }
        return false;
    }

    public Object[] toArray() {
        Object[] arr = new Object[size];
        Element<T> current = head;
        int index = 0;
        while (current != null) {
            arr[index++] = current.val();
        }
        return arr;
    }

    public boolean containsAll(Collection<? extends T> c) {
        for (T element : c) {
            if (!contains(element))
                return false;
        }
        return true;
    }

    public boolean addAll(Collection<? extends T> c) {
        for (T element : c) {
            add(element);
        }
        return true;
    }

    public boolean removeAll(Collection<? extends T> c) {
        boolean success = true;
        for (T element : c) {
            if (!remove(element)) {
                success = false;
            }
        }
        return success;
    }

    public void clear() {
        head = null;
        tail = null;
        size = 0;
    }

    public T get(int index) {
        if (index > size) {
            throw new IndexOutOfBoundsException();
        }
        Element<T> current = head;
        for (int i = 0; i < index; i++) {
            current = current.next();
        }
        return current.val();
    }

    public T set(int index, T element) {
        if (index > size) {
            throw new IndexOutOfBoundsException();
        }
        Element<T> current = head;
        for (int i = 0; i < index; i++) {
            current = current.next();
        }
        T prevVal = current.val();
        current.setVal(element);
        return prevVal;
    }

    /**
     * returns -1 if val not in list
     * @param o
     * @return
     */
    public int indexOf(T o) {
        Element<T> current = head;
        int index = 0;
        while (comparator.compare(current.val(), o) != 0) {
            current = current.next();
            index++;
            if (current == null)
                return -1;
        }
        return index;
    }

    public int lastIndexOf(T o) {
        Element<T> current = head;
        int index = 0;
        while (comparator.compare(current.val(), o) != 0) {
            current = current.next();
            index++;
            if (current == null)
                return -1;
        }
        while (current != null && comparator.compare(current.next().val(), o) != 0) {
            current = current.next();
            index++;
        }

        return index;

    }

    public LinkedSortedList<T> subList(int fromIndex, int toIndex) {
        if (fromIndex > size || toIndex > size)
            throw new IndexOutOfBoundsException();
        LinkedSortedList<T> newSubList = new LinkedSortedList<T>(comparator);
        if (toIndex < fromIndex) {
            return newSubList;
        }
        Element<T> current = head;
        for (int i = 0; i < fromIndex; i++) {
            current = current.next();
        }
        for (int i = fromIndex; i < toIndex; i++) {
            newSubList.putStart(current.val());
            current = current.next();
        }
        newSubList.putStart(current.val());
        return newSubList;
    }
}
