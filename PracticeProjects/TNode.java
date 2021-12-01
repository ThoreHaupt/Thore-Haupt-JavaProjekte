package PracticeProjects;

public class TNode<T> {
    private TNode<T> nextNode;
    private TNode<T> beforeNode;
    public TLinkedList<T> list;
    private T value;
    private int index;

    public TNode(TLinkedList<T> list, TNode<T> nextNode, TNode<T> beforeNode, T value, int index) {
        this.list = list;
        this.nextNode = nextNode;
        this.beforeNode = beforeNode;
        this.value = value;
        this.list = list;
        this.index = index;
    }

    public TNode(){};

    public TNode<T> createNextNode(T value){
        nextNode = new TNode<T>(list, nextNode, this, value, index + 1);
        return nextNode;
    }
    
    public TNode<T> getNextNode() {
        return nextNode;
    }
    
    public TNode<T> getBeforeNode() {
        return beforeNode;
    }
    
    public void setNextNode(TNode<T> nextNode) {
        this.nextNode = nextNode;
    }
    
    public void setBeforeNode(TNode<T> beforeNode) {
        this.beforeNode = beforeNode;
    }
    public T getValue() {
        return value;
    }
    public void setValue(T value) {
        this.value = value;
    }
    
    public void removeNextNode(){
        nextNode = nextNode.getNextNode();
    }

    public void removeNode() {
        if (nextNode != null){
            beforeNode.setNextNode(nextNode.getNextNode());
        }else{
            beforeNode.setNextNode(null);
        }
    }
}
