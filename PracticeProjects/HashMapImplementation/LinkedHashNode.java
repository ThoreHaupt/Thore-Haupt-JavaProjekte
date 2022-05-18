package PracticeProjects.HashMapImplementation;

public class LinkedHashNode<K, V> extends HashNode<K, V> {

    protected LinkedHashNode<K, V> next;

    public LinkedHashNode(int hash, K key, V value) {
        super(hash, key, value);
    }

}
