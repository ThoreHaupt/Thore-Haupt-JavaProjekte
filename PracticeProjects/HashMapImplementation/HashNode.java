package PracticeProjects.HashMapImplementation;

public abstract class HashNode<T> {
    int hash;
    T value;
    protected int listlength;

    public HashNode(int hash, T value, int listlength) {
        this.hash = hash;
        this.value = value;
        this.listlength = listlength;
    }
}
