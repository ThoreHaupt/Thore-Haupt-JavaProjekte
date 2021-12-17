package PracticeProjects;

import java.util.List;
import java.util.Objects;

public class TLinkedList<T> {
    private int size = 0;
    private int modCount = 0;
    private TNode<T> firstNode = null;

    private TNode<T> lastNode = null;

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

    public TNode<T> getFirstNode() {
        return firstNode;
    }

    public synchronized void setFirstTNode(TNode<T> fisTNode) {
        this.firstNode = fisTNode;
        modCount++;
    }

    public TNode<T> getLastNode() {
        return lastNode;
    }

    public synchronized void setLastNode(TNode<T> lastNode) {
        this.lastNode = lastNode;
        modCount++;
    }

    public int size() {
        return this.size;
    }

    public synchronized void add(T value) {
        lastNode = createNextNode(lastNode, value);
        size++;
        modCount++;
    }

    private synchronized TNode<T> createNextNode(TNode<T> startnode, T value) {
        TNode<T> newNode;
        if (size > 0) {
            newNode = startnode.createNextNode(value);
            if (startnode == lastNode) {
                lastNode = newNode;
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
        lastNode = (node == lastNode ? newNode : lastNode);
        size++;
        modCount++;
    }

    public synchronized void insert(T value, int index) {
        TNode<T> node = getNodeIndex(index);
        insert(value, node);
    }

    public synchronized TNode<T> insert(T value, TNode<T> node) {
        TNode<T> newNode = createBeforeNode(node, value);
        if (firstNode != null && firstNode == node) {
            this.firstNode = newNode;
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
        if (node == lastNode) {
            lastNode = node.getBeforeNode();
        }
        node.removeNode();
        size--;
        modCount++;
    }

    public void removeNext(TNode<T> node) {
        node.removeNextNode();
        size--;
        modCount++;
    }

    public synchronized T pullFirst() {
        T value = firstNode.getValue();
        firstNode = firstNode.getNextNode();
        firstNode.setBeforeNode(null);
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
        TNode<T> current = firstNode;
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
        TNode<T> current = firstNode;
        int i = 0;
        while (i < this.size - 1) {
            if (current.getNextNode() == null) {
                return false;
            }
            current = current.getNextNode();
            i++;
        }
        return true;
    }

    public void toArray(Object[] arr) {
        TNode<T> node = firstNode;
        for (int i = 0; i < arr.length; i++) {
            arr[i] = node.getValue();
            node = node.getNextNode();
        }
    }

    public TNode<T> createFirstNode(T value) {
        firstNode = new TNode<T>(this, null, null, value);
        lastNode = firstNode;        
        return firstNode;
    }

    public int modCount() {
        return this.modCount;
    }
}