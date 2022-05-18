package PracticeProjects.HashMapImplementation;

public class LinkedNode<K, V> extends HashNode<K, V> {

    protected LinkedNode<K, V> next;

    public LinkedNode(int hash, K key, V value) {
        super(hash, key, value);
    }

}
