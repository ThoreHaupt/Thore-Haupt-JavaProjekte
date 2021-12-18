package PracticeProjects.Sortingalgorythms.ThreadedMergeSort;


import java.util.HashMap;

import PracticeProjects.TLinkedList;
import PracticeProjects.TNode;
import PracticeProjects.Sortingalgorythms.Common;

public class MergeWorker extends Thread {
    
    public TLinkedList<String> sortList;
    public TLinkedList<Chunk<String>> chunkList;

    public HashMap<Character, Integer> referenceMap;

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
        this.referenceMap = referenceMap;
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
            while(Common.firstStringbool((String)currentTNode.getBeforeNode().getValue(),
                    (String) currentTNode.getValue(), referenceMap)){
                currentTNode = currentTNode.getNextNode();
                currentIndex++;
                if(currentTNode == null)break;
            }
            //create new Chunk out of these elements
            new Chunk<String>(firstNode, currentTNode, firstChunkIndex, currentIndex, chunkList);           
            
            //move bordernodes/indeces along to next chunk
            firstNode = currentTNode;
            firstChunkIndex = currentIndex++;
            if (currentTNode != null)currentTNode = currentTNode.getNextNode();
            
            if(higherThread!=null) higherThread.chunksQued++;
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
            if (currentTNode == null)break;

        }while((!(chunkList.getLastNode().getValue().upperNode.getNextNode() == null)) && currentTNode != null);

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
                while (chunksQued < 4 && lowerThread.isAlive()) {
                    try {
                        if(!lowerThread.isAlive())wait();
                    } catch (InterruptedException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                }
            }

            Chunk<String> chunk1 = currentChunkNode.getValue();
            Chunk<String> chunk2 = currentChunkNode.getNextNode().getValue();


            mergeAlgorythms.mergeChunks(chunk1,
                    chunk2, referenceMap);
            
            chunksQued -= 2;
            merges++;

            if (merges == 2) {
                createHigherThread();
            }
            if (higherThread != null)
                higherThread.chunksQued++;
            if (merges % 2 == 0 && higherThread != null) {
                synchronized (higherThread) {
                    higherThread.notify();
                }
            }
            
            if(currentChunkNode.getNextNode() == null && !lowerThread.isAlive()){
                System.out.println("last iteration: " + Thread.currentThread().getName());
                break;
            }
            if(currentChunkNode.getNextNode().getNextNode() == null && !lowerThread.isAlive()){
                if (higherThread != null)
                    higherThread.chunksQued++;
                System.out.println("last iteration: " + Thread.currentThread().getName() + "quit with other Chunk " + merges%2);
                break;
            }

            currentChunkNode = currentChunkNode.getNextNode();
            
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
}
