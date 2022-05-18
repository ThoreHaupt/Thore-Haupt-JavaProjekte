package PracticeProjects.HashMapImplementation;

public class HashNode<K, V> {
    int hash;
    K key;
    V value;

    public HashNode(int hash, K key, V value) {
        this.hash = hash;
        this.value = value;
    }
}
