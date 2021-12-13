package PracticeProjects.Sortingalgorythms.ThreadedMergeSort;

import java.util.HashMap;

import PracticeProjects.TLinkedList;
import PracticeProjects.TNode;
import PracticeProjects.Sortingalgorythms.Common;
import PracticeProjects.Sortingalgorythms.MergeSortLinkedII;

public class MergeWorker<T> extends Thread {
    
    public TLinkedList<T> sortList;
    public TLinkedList<Chunk<T>> chunkList;

    public HashMap<String, Integer> referenceMap;

    TNode<Chunk<T>> currentChunk;

    Thread parentThread;
    boolean isFirst;
    int merges;
    
    public MergeWorker(TLinkedList<T> sortList, TLinkedList<Chunk<T>> ChunkList, Thread parentThread, boolean isFirst, HashMap<String, Integer> referenceMap) {
        this.sortList = sortList;
        this.chunkList = ChunkList;
        this.isFirst = isFirst;
        this.parentThread = parentThread;
    }

    @Override
    public void run() {
        // TODO Auto-generated method stub
        super.run();
        try {
            // Displaying the thread that is running
            System.out.println(
                    "Worker Level " + Thread.currentThread().getId()
                            + " is running");
        } catch (Exception e) {
            // Throwing an exception
            System.out.println("Exception is caught");
        }
        
        if (!isFirst){
            routine();
        }else{
            createFirstLayerRoutine();
        }

    }

    private void createFirstLayerRoutine(){
        int currentIndex = 1;
        int firstChunkIndex = 0;
        TNode<T> currentTNode = sortList.getFirstNode().getNextNode();
        TNode<T> firstNode = sortList.getFirstNode();
        while(!(chunkList.getLastNode().getValue().upperNode.getNextNode() == null)){ // If ! the node after the last node in sortList == null
            
            while(Common.firstStringbool(currentTNode.getValue(), currentTNode.getBeforeNode().getValue(), referenceMap)){
                currentTNode = currentTNode.getNextNode();
                currentIndex++;
            }
            Chunk<T> newChunk = new Chunk<T>(firstNode, currentTNode, firstChunkIndex, currentIndex, chunkList);
            chunkList.add(newChunk);
            newChunk.setSelfNode();
            firstNode = currentTNode;
            firstChunkIndex = currentIndex++;
            currentTNode = currentTNode.getNextNode();
        }
        // parentThread.threadsAmount--;
    }

    private void routine(){
        while(chunkList.getLastNode().getValue() != currentChunk.getNextNode().getValue()){
            
        }
    }

    

}
