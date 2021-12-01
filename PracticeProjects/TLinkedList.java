package PracticeProjects;

import java.util.List;
import java.util.Objects;

public class TLinkedList<T>{
    private int size = 0;
    private TNode<T> fisTNode = null;
    public TNode<T> getFisTNode() {
        return fisTNode;
    }

    public void setFisTNode(TNode<T> fisTNode) {
        this.fisTNode = fisTNode;
    }

    public TNode<T> getLastNode() {
        return lastNode;
    }

    public void setLastNode(TNode<T> lastNode) {
        this.lastNode = lastNode;
    }

    private TNode<T> lastNode = null;

    public TLinkedList() {
    }

    public TLinkedList(List<T> list){
        if (list.size() == 0) return;
        createFirstNode(list.get(0));
        for (int i = 1; i < list.size(); i++) {
            add(list.get(i));
        }
    }

    public int size(){
        return this.size;
    }

    public void add(T value){
        lastNode = createNextNode(lastNode, value);
        size++;
    }

    private TNode<T> createNextNode(TNode<T> startnode, T value) {
        TNode<T> newNode;
        if (size > 0){
            newNode = startnode.createNextNode(value);
        }else{
            newNode = createFirstNode(value);
        }
        return newNode;
    }

    public void set(T value, TNode<T> node){
        node.setValue(value);
    }

    public void set(T value, int index) {
        getNodeIndex(index).setValue(value);
    }

    public void insert(T value, int index) {
        getNodeIndex(index).createNextNode(value);
        lastNode = lastNode.getNextNode();
        size++;
    }

    public void insert(T value, TNode<T> node){
        node.createNextNode(value);
        lastNode = lastNode.getNextNode();
        size++;
    }

    public void remove(int index){
        TNode<T> node = getNodeIndex(index - 1);
        node.setNextNode(null);
        size--;
    }
    
    public void remove(TNode<T> node) {
        node.removeNode();
        size--;
    }
    public void removeNext(TNode<T> node){
        node.removeNextNode();
        size--;
    }
    
    public T pullFirst(){
        T value = fisTNode.getValue();
        fisTNode = fisTNode.getNextNode();
        fisTNode.setBeforeNode(null);
        size--;
        return value;
    }

    public T get(int index){
        return getNodeIndex(index).getValue();
    }
    
    public T get(TNode<T> node) {
        return node.getValue();
    }

    public TNode<T> getNodeIndex(int index){
        Objects.checkIndex(index, size);
        TNode<T> current = fisTNode;
        int i = 0;
        while(i < index){
            current = current.getNextNode();
            i++;
        }
        return current;
    }

    public Object[] toArray(){
        Object[] arr = new Object[size];
        TNode<T> node = fisTNode;
        for (int i = 0; i < arr.length; i++) {
            arr[i] = node.getValue();
            node = node.getNextNode();
        }
        return arr;
    }
    
    public TNode<T> createFirstNode(T value){
        fisTNode = new TNode<T>(this, null, null, value, 0);
        return fisTNode;
    }
}