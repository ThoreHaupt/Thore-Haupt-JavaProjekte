package PracticeProjects;

import java.util.List;
import java.util.Objects;

public class TLinkedList<T> {
    private int size = 0;
    private int modCount = 0;
    private TNode<T> head = null;

    private TNode<T> tail = null;

    public TLinkedList() {
    }

    public TLinkedList(List<T> list) {
        if (list.size() == 0)
            return;
        createFirstNode(list.get(0));
        size++;
        for (int i = 1; i < list.size(); i++) {
            add(list.get(i));
        }
    }

    public TNode<T> getHead() {
        return head;
    }

    public synchronized void setFirstTNode(TNode<T> fisTNode) {
        this.head = fisTNode;
        modCount++;
    }

    public TNode<T> getTail() {
        return tail;
    }

    public synchronized void setTail(TNode<T> lastNode) {
        this.tail = lastNode;
        modCount++;
    }

    public int size() {
        return this.size;
    }

    public synchronized void add(T value) {
        tail = createNextNode(tail, value);
        size++;
        modCount++;
    }

    private synchronized TNode<T> createNextNode(TNode<T> startnode, T value) {
        TNode<T> newNode;
        if (size > 0) {
            newNode = startnode.createNextNode(value);
            if (startnode == tail) {
                tail = newNode;
            }
        } else {
            newNode = createFirstNode(value);
        }
        return newNode;
    }

    private synchronized TNode<T> createBeforeNode(TNode<T> startnode, T value) {
        TNode<T> newNode;
        if (size > 0) {
            newNode = startnode.createBeforeNode(value);
        } else {
            newNode = createFirstNode(value);
        }
        return newNode;
    }

    public synchronized void set(T value, TNode<T> node) {
        node.setValue(value);
        modCount++;
    }

    public synchronized void set(T value, int index) {
        getNodeIndex(index).setValue(value);
        modCount++;
    }

    public synchronized void insertAfter(T value, int index) {
        TNode<T> node = getNodeIndex(index);
        insertAfter(value, node);
    }

    public synchronized void insertAfter(T value, TNode<T> node) {
        TNode<T> newNode = node.createNextNode(value);
        tail = (node == tail ? newNode : tail);
        size++;
        modCount++;
    }

    public synchronized void insert(T value, int index) {
        TNode<T> node = getNodeIndex(index);
        insert(value, node);
    }

    public synchronized TNode<T> insert(T value, TNode<T> node) {
        TNode<T> newNode = createBeforeNode(node, value);
        if (head != null && head == node) {
            this.head = newNode;
        }
        size++;
        modCount++;
        return newNode;
    }

    public void remove(int index) {
        TNode<T> node = getNodeIndex(index - 1);
        remove(node);
    }

    public void remove(TNode<T> node) {
        size--;
        if (node == tail) {
            tail = node.getBeforeNode();
        }
        node.removeNode();

        modCount++;
    }

    public void removeNext(TNode<T> node) {
        node.removeNextNode();
        size--;
        modCount++;
    }

    public synchronized T pullFirst() {
        T value = head.getValue();
        head = head.getNextNode();
        head.setBeforeNode(null);
        size--;
        modCount++;
        return value;
    }

    public T get(int index) {
        return getNodeIndex(index).getValue();
    }

    public T get(TNode<T> node) {
        return node.getValue();
    }

    public TNode<T> getNodeIndex(int index) {
        Objects.checkIndex(index, size);
        TNode<T> current = head;
        int i = 0;
        while (i < index) {
            if (current.getNextNode() == null) {
                System.out.println("TLInkedList is only" + i + "elements long");
                break;
            }
            current = current.getNextNode();
            i++;
        }
        return current;
    }

    public boolean testIntegrity() {
        TNode<T> current = head;
        int i = 0;
        while (current != null) {

            current = current.getNextNode();
            i++;
        }
        if (i == this.size) {
            return true;
        } else {
            System.out.println("size of list = " + size + "| actual size = " + i);
            return false;
        }
    }

    public void testIntegrityFull() {
        if (testIntegrity()) {
            System.out.println("Diese List ist in Ordnung");
        } else {
            System.out.println(Thread.currentThread().getName());
            System.out.println("Diese List ist nicht heile");
        }
    }

    public void toArray(Object[] arr) {
        TNode<T> node = head;
        for (int i = 0; i < arr.length; i++) {
            arr[i] = node.getValue();
            node = node.getNextNode();
        }
    }

    public TNode<T> createFirstNode(T value) {
        head = new TNode<T>(this, null, null, value);
        tail = head;
        return head;
    }

    public int modCount() {
        return this.modCount;
    }

    public void printListChunk(int indexa, int indexb) {
        TNode<T> node = getNodeIndex(indexa);
        for (int i = indexa; i < indexb - indexa - 1; i++) {
            System.out.println("Index: " + i + ": " + node.getValue());
            if (node != null)
                node = node.getNextNode();
        }
    }
}