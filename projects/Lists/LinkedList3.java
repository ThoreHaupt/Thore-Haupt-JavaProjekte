package Projects.Lists;

import java.util.Comparator;

import javax.swing.text.html.parser.Element;

public class LinkedList3<T> {

    public static void main(String[] args) {
        LinkedList3<Integer> list = new LinkedList3<>(new Comparator<Integer>() {

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
            list.putLast(i);
            System.out.println(list);
        }
        for (int i = 30; i > 0; i--) {
            list.putFirst(i);
            System.out.println(list);
        }
        list.insertionSortForeward();
        System.out.println(list);
    }

    Element<T> head;
    Element<T> tail;
    Comparator<T> comparator;

    /**
     * @author ME
     *
     * @param <T>
     */
    class Element<T> {

        private T value;
        private Element<T> next;
        private Element<T> pred;

        public Element(T val) {
            this.value = val;
        }

        public void setNext(Element<T> next) {
            this.next = next;
        }

        public void setPred(Element<T> pred) {
            this.pred = pred;
        }

        public Element<T> next() {
            return next;
        }

        public Element<T> pred() {
            return pred;
        }

        public T val() {
            return value;
        }

        public void setVal(T val) {
            this.value = val;
        }

    }

    public LinkedList3(Comparator<T> comparator) {
        this.comparator = comparator;
    }

    public void putFirst(T val) {
        insertNext(null, val);
        insertionSortForeward();
    }

    public void putLast(T val) {
        insertNext(tail, val);
        insertionSortBackward();
    }

    protected void insertNext(Element<T> pred, T val) {
        insertNext(new Element<T>(val), pred);
    }

    public void setComparator(Comparator<T> comparator) {
        this.comparator = comparator;
        sort();
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
    }

    public void remove(T val) {
        Element<T> current = head;
        while (current != null && comparator.compare(current.val(), val) != 0) {
            current = current.next();
        }
        removeEl(current);
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
}
