package PracticeProjects;

public class TNode<T> {
    private TNode<T> nextNode;
    private TNode<T> beforeNode;
    public TLinkedList<T> list;
    private T value;

    public TNode(TLinkedList<T> list, TNode<T> nextNode, TNode<T> beforeNode, T value) {
        this.list = list;
        this.nextNode = nextNode;
        this.beforeNode = beforeNode;
        this.value = value;
        this.list = list;
        if (beforeNode != null){
            beforeNode.setNextNode(this);
        }
        if (nextNode != null) {
            nextNode.setBeforeNode(this);
        }
    }

    public TNode(){};

    public TNode<T> createNextNode(T value){
        nextNode = new TNode<T>(list, nextNode, this, value);
        return nextNode;
    }

    public TNode<T> createBeforeNode(T value) {
        beforeNode = new TNode<T>(list, this, this.beforeNode, value);
        return beforeNode;
    }
    
    public TNode<T> getNextNode() {
        return nextNode;
    }
    
    public TNode<T> getBeforeNode() {
        return beforeNode;
    }
    
    public synchronized void setNextNode(TNode<T> nextNode) {
        this.nextNode = nextNode;
    }
    
    public synchronized void setBeforeNode(TNode<T> beforeNode) {
        this.beforeNode = beforeNode;
    }
    public T getValue() {
        return value;
    }
    public void setValue(T value) {
        this.value = value;
    }
    
    public void removeNextNode(){
        if (nextNode == null)return;
            nextNode = nextNode.getNextNode();
        if (nextNode.getNextNode() == null)
            nextNode.getNextNode().setBeforeNode(this);
    }

    public void removeNode() {
        if (nextNode != null && beforeNode != null){
            list.testIntegrityFull();
            beforeNode.setNextNode(nextNode);
            nextNode.setBeforeNode(beforeNode);
            
        }else if(nextNode == null && beforeNode == null){
            list.setFirstTNode(null);
            list.setLastNode(null);
        }else if (nextNode != null && beforeNode == null){
            nextNode.setBeforeNode(null);
        }else{
            beforeNode.setNextNode(null);
        }
    }

    
}
