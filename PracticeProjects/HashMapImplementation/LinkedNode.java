package PracticeProjects.HashMapImplementation;

public class LinkedNode<T> extends HashNode<T> {

    protected LinkedNode<T> next;

    public LinkedNode(int hash, T value) {
        super(hash, value);
    }

}
