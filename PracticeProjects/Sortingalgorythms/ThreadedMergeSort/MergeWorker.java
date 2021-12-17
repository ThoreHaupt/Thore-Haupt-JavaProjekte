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

    Thread mainThread;
    MergeWorker<T> lowerThread;
    MergeWorker<T> higherThread = null;
    boolean isFirst;
    boolean running = false;
    int chunkstep = 2;
    int merges;
    

    /**
     * @param sortlist
     * @param ChunkList
     * @param isFirst
     * @param referenceMap
     */
    public MergeWorker(TLinkedList<T> sortList, TLinkedList<Chunk<T>> ChunkList, MergeWorker<T> mainThread, 
            MergeWorker<T> lowerThread, boolean isFirst, HashMap<String, Integer> referenceMap) {
        
        this.sortList = sortList;
        this.chunkList = ChunkList;
        this.isFirst = isFirst;
        this.mainThread = mainThread;
        this.lowerThread = lowerThread;
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
        while(running){
            if(chunkstep == 2){
                currentChunkNode = currentChunkNode.getNextNode();
            }

            mergeAlgorythms.mergeChunks(sortList, currentChunkNode.getValue(),
                    currentChunkNode.getNextNode().getValue(), referenceMap);
            
            merges++;
            if(merges == 2){
                createHigherThread();
            }
            if(merges%2 == 0 && merges >= 2){
                higherThread.notify();
            }

            try {
                Thread.currentThread().wait();
            } catch (InterruptedException e) {
                System.out.println("Something went wrong in Mergeworker, could not wait because:");
                e.printStackTrace();
            }
        }
        
        
    }
    // when we reach this this layer has terminated


    public void createHigherThread(){
        higherThread = ThreadedMergeSort.createThread((MergeWorker<T>) Thread.currentThread());

    }
}
