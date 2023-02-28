package PracticeProjects.Sortingalgorythms.ThreadedMergeSort;

import java.util.ArrayList;

import PracticeProjects.TLinkedList;
import PracticeProjects.TNode;

public class Chunk<T> {
    TNode<T> firstNode;
    TNode<T> upperNode;

    private int firstIndex;
    private int upperIndex;

    public TLinkedList<Chunk<T>> chunkList;
    TNode<Chunk<T>> selfNode;
    public boolean locked = false;
    private int size;

    public Chunk(TNode<T> firstNode, TNode<T> upperNode, int firstIndex, int upperIndex,
            TLinkedList<Chunk<T>> chunkList) {
        this.firstNode = firstNode;
        this.upperNode = upperNode;
        this.firstIndex = firstIndex;
        this.upperIndex = upperIndex;
        size = upperIndex - firstIndex;
        this.chunkList = chunkList;
        chunkList.add(this);
        selfNode = chunkList.getTail();
    }

    public synchronized void setSelfNode() {
        selfNode = chunkList.getTail();
    }

    public int[] getIndexChunk() {
        return new int[] { firstIndex, upperIndex };
    }

    public ArrayList<TNode<T>> getNodeChunk() {
        ArrayList<TNode<T>> list = new ArrayList<TNode<T>>();
        list.add(firstNode);
        list.add(upperNode);
        return list;
    }

    public TNode<T> getFirstNode() {
        return firstNode;
    }

    public TNode<T> getUpperNode() {
        return upperNode;
    }

    public int getFirstIndex() {
        return firstIndex;
    }

    public synchronized void setFirstIndex(int firstIndex) {
        this.firstIndex = firstIndex;
        size = upperIndex - firstIndex;
    }

    public int getUpperIndex() {
        return upperIndex;
    }

    public void setUpperIndex(int upperIndex) {
        this.upperIndex = upperIndex;
        size = upperIndex - firstIndex;
    }

    public int getSize() {
        return size;
    }

    public void extend(boolean upper, int amount, TNode<T> newEdgeNode) {
        if (upper) {
            size += amount;
            upperIndex += amount;
            upperNode = newEdgeNode;
        } else {
            size += amount;
            firstIndex -= amount;
            firstNode = newEdgeNode;
        }

    }

    public void setUpperNode(TNode<T> node) {
        this.upperNode = node;
    }

    public void setFirstNode(TNode<T> node) {
        TNode<Chunk<T>> beforeNode = this.selfNode.getBeforeNode();
        if (beforeNode != null) {
            Chunk<T> beforechunk = beforeNode.getValue();
            if (beforechunk != null)
                beforechunk.setUpperNode(node);
        }
        this.firstNode = node;
    }

    public boolean canMergeNext(double ratio) {
        if (selfNode.getNextNode() == null)
            return false;
        int s1 = (int) this.size;
        int s2 = (int) selfNode.getNextNode().getValue().getSize();
        if ((s2 >= s1) || (Math.abs(s2 - s1) / s1) > (ratio))
            return true;
        return false;
    }

    public synchronized void remove() {
        chunkList.remove(selfNode);
    }
}
