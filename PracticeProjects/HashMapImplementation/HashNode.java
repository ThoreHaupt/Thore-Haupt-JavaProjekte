package PracticeProjects.HashMapImplementation;

public class HashNode<T> {
    int hash;
    T value;
    protected int listlength;

    public HashNode(int hash, T value) {
        this.hash = hash;
        this.value = value;
    }
}
