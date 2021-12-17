package PracticeProjects.Sortingalgorythms.ThreadedMergeSort;

import java.lang.reflect.WildcardType;
import java.util.HashMap;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;

import PracticeProjects.TLinkedList;
import PracticeProjects.TNode;
import PracticeProjects.Sortingalgorythms.Common;

public class MergeWorker extends Thread {
    
    public TLinkedList<String> sortList;
    public TLinkedList<Chunk<String>> chunkList;

    public HashMap<String, Integer> referenceMap;

    TNode<Chunk<String>> currentChunkNode;

    Thread mainThread;
    MergeWorker lowerThread;
    MergeWorker higherThread = null;
    boolean isFirst;
    boolean running = true;
    int chunksQued = 1;
    int chunkstep = 2;
    int merges;
    

    /**
     * @param sortlist
     * @param ChunkList
     * @param isFirst
     * @param referenceMap
     */
    public MergeWorker(TLinkedList<String> sortList, TLinkedList<Chunk<String>> ChunkList, Thread mainThread, 
            MergeWorker lowerThread, boolean isFirst, HashMap<Character, Integer> referenceMap) {
        
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
                    "Worker Level " + Thread.currentThread().getName()
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
        TNode<String> currentTNode = sortList.getFirstNode().getNextNode();
        TNode<String> firstNode = sortList.getFirstNode();
        do{ 
            
            
            //moves over sortlist, until it finds a wrong oder.
            while(Common.firstStringbool(currentTNode.getBeforeNode().getValue(), currentTNode.getValue(), referenceMap)){
                currentTNode = currentTNode.getNextNode();
                currentIndex++;
            }
            //create new Chunk out of these elements
            new Chunk<String>(firstNode, currentTNode, firstChunkIndex, currentIndex, chunkList);           
            
            //move bordernodes/indeces along to next chunk
            firstNode = currentTNode;
            firstChunkIndex = currentIndex++;
            currentTNode = currentTNode.getNextNode();
            
            if(higherThread!=null) higherThread.chunksQued ++;
            //creates higher thread at first oppportunity
            if (merges == 2) {
                createHigherThread();
            }
            if (higherThread != null)
                higherThread.chunksQued++;
            if (merges % 2 == 0 && higherThread != null) {
                synchronized(higherThread){
                    higherThread.notify();
                }
            }
            merges++;

        }while(!(chunkList.getLastNode().getValue().upperNode.getNextNode() == null));

        if (higherThread != null) {
            System.out.println("Error, higher thread finished before lower Thread");
            synchronized (higherThread) {
                higherThread.notify();
            }
        }
        ThreadedMergeSort.closeThread((MergeWorker)Thread.currentThread());
    }

    private synchronized void routine(){ // gets called when the thread is created, which only happens when there are two new chunks
        currentChunkNode = chunkList.getFirstNode();
        while(running){
            
            synchronized (this) {
                while (chunksQued < 3 && lowerThread.isAlive()) {
                    try {
                        wait();
                    } catch (InterruptedException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                }
            }
            
            
            running = (chunkstep==1)?false:true;

            Chunk<String> chunk1 = currentChunkNode.getValue();
            Chunk<String> chunk2 = currentChunkNode.getNextNode().getValue();


            mergeAlgorythms.mergeChunks(chunk1,
                    chunk2, referenceMap);
            
            nodeSetandThreadmanagement();
            
            if(currentChunkNode.getNextNode() == null || currentChunkNode.getNextNode().getNextNode() == null){
                chunkstep = 1;
            }
            if (chunkstep == 2) {
                currentChunkNode = currentChunkNode.getNextNode();
            }
            if (higherThread != null)
                higherThread.chunksQued++;
        
        }
        if(higherThread != null){
            System.out.println("Error, higher thread finished before lower Thread");
            synchronized (higherThread) {
                higherThread.notify();
            }
        }
        ThreadedMergeSort.closeThread((MergeWorker)Thread.currentThread());    
        
    }
    // when we reach this this layer has terminated


    public synchronized void createHigherThread(){
        higherThread = ThreadedMergeSort.createThread((MergeWorker) Thread.currentThread());
    }

    public void nodeSetandThreadmanagement(){
        chunksQued -= 2;
        merges++;
        if(merges == 2){
            createHigherThread();
        }
        if (higherThread != null)
            higherThread.chunksQued++;
        if(merges%2 == 0 && higherThread != null){
            synchronized (higherThread) {
                higherThread.notify();
            }
        }
        
    }
}
