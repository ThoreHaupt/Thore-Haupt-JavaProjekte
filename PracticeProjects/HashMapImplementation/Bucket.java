package PracticeProjects.HashMapImplementation;

import java.util.Iterator;
import java.util.List;

public class Bucket<Value> implements Iterable<HashNode<Value>> {
    THashMap<?, Value> map;
    HashNode<Value> head = null;
    int size = 0;
    boolean tree = false;

    public Bucket(THashMap<?, Value> map) {
        this.map = map;
    }

    public Value get(int hash) {
        if (head == null)
            return null;
        if (tree) {

        }
        LinkedNode<Value> currentNode = (LinkedNode<Value>) head;
        while (currentNode.hash != hash) {
            currentNode = currentNode.next;
        }
        return currentNode.value;
    }

    /**
     */
    public boolean add(int hash, Value value) {
        if (tree) {

        }

        LinkedNode<Value> newNode = new LinkedNode<Value>(hash, value);

        if (head == null) {
            head = newNode;
            size++;
            return true;
        }

        LinkedNode<Value> current = (LinkedNode<Value>) head;
        if (current.hash == hash) {
            current.value = value;
            return false;
        }
        while (current.next != null) {
            if (current.hash == hash) {
                current.value = value;
                return false;
            }
            current = current.next;
        }
        current.next = newNode;
        size++;
        return true;
    }

    @Override
    public Iterator<HashNode<Value>> iterator() {
        if (tree) {
        }
        Iterator<HashNode<Value>> iterator = new Iterator<HashNode<Value>>() {

            private LinkedNode<Value> currentNode = (LinkedNode<Value>) head;

            @Override
            public boolean hasNext() {
                if (currentNode != null)
                    return true;
                return false;
            }

            @Override
            public HashNode<Value> next() {
                LinkedNode<Value> outputNode = currentNode;
                if (currentNode != null)
                    currentNode = currentNode.next;
                return outputNode;
            }

        };
        return iterator;
    }

    public int size() {
        return size;
    }

}
