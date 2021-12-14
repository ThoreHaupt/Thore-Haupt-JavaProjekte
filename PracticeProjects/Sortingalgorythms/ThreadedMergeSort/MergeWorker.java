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

    TNode<Chunk<T>> currentChunkNode;

    Thread parentThread;
    boolean isFirst;
    int merges;
    

    /**
     * @param sortlist
     * @param ChunkList
     * @param isFirst
     * @param referenceMap
     */
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

    private void routine(){ // gets called when the thread is created, which only happens when there are two new chunks
        currentChunkNode = chunkList.getFirstNode();
        while(chunkList.getLastNode().getValue() != currentChunkNode.getNextNode().getValue() || currentChunkNode
            .getNextNode().getValue().upperNode != null){
            if(currentChunkNode.getValue() != null && currentChunkNode.getNextNode().getValue() != null){
                mergeAlgorythms.mergeChunks(sortList, currentChunkNode.getValue(),
                        currentChunkNode.getNextNode().getValue(), referenceMap);
                
                merges++;
                if(merges == 2){
                    ThreadedMergeSort.createThread();
                }
            }
            else{
                // wait until there are more threads, waiting in the form of sleeping. This should not happen theoratically.
                try {
                    sleep(0,10);
                } catch (InterruptedException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                    System.out.println("Sleep went wrong or sth.");
                }
            }
        }
        
        
    }
    // when we reach this this layer has terminated

}
