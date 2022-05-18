package PracticeProjects.HashMapImplementation;

import java.util.Iterator;
import java.util.List;

public class Bucket<K, V> implements Iterable<HashNode<K, V>> {
    THashMap<K, V> map;
    HashNode<K, V> head = null;
    int size = 0;
    boolean tree = false;

    public Bucket(THashMap<K, V> map) {
        this.map = map;
    }

    public V get(int hash) {
        if (head == null)
            return null;
        if (tree) {

        }
        LinkedNode<K, V> currentNode = (LinkedNode<K, V>) head;
        while (currentNode.hash != hash) {
            currentNode = currentNode.next;
            if (currentNode == null)
                return null;
        }
        return currentNode.value;
    }

    /**
     */
    public boolean add(int hash, K key, V value) {
        if (tree) {

        }

        LinkedNode<K, V> newNode = new LinkedNode<K, V>(hash, key, value);

        if (head == null) {
            head = newNode;
            size++;
            return true;
        }

        LinkedNode<K, V> current = (LinkedNode<K, V>) head;
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
    public Iterator<HashNode<K, V>> iterator() {
        if (tree) {
        }
        Iterator<HashNode<K, V>> iterator = new Iterator<HashNode<K, V>>() {

            private LinkedNode<K, V> currentNode = (LinkedNode<K, V>) head;

            @Override
            public boolean hasNext() {
                if (currentNode != null)
                    return true;
                return false;
            }

            @Override
            public HashNode<K, V> next() {
                LinkedNode<K, V> outputNode = currentNode;
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
