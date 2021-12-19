package PracticeProjects.Sortingalgorythms.ThreadedMergeSort;

import java.util.HashMap;

import PracticeProjects.TLinkedList;
import PracticeProjects.TNode;
import PracticeProjects.Sortingalgorythms.Common;
import PracticeProjects.Sortingalgorythms.MergeSortLinkedII;

public class MergeWorker extends Thread {

    public TLinkedList<String> sortList;
    public TLinkedList<Chunk<String>> chunkList;

    public HashMap<Character, Integer> referenceMap;

    TNode<Chunk<String>> currentChunkNode;

    Thread mainThread;
    private MergeWorker lowerThread;
    public MergeWorker higherThread = null;
    boolean isFirst;
    boolean running = true;
    boolean waiting = false;
    int chunksQued = 1;
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
        if(lowerThread!= null)this.chunksQued = lowerThread.merges;
        try {
            // Displaying the thread that is running
            System.out.println(
                    "Worker Level " + Thread.currentThread().getName()
                            + " is running");
        } catch (Exception e) {
            // Throwing an exception
            System.out.println("Exception is caught");
        }

        if (!isFirst) {
            routine();
        } else {
            createFirstLayerRoutine();
        }

    }

    private void createFirstLayerRoutine() {
        int currentIndex = 1;
        int firstChunkIndex = 0;
        TNode<String> currentTNode = sortList.getFirstNode().getNextNode();
        TNode<String> firstNode = sortList.getFirstNode();
        do {
            // moves over sortlist, until it finds a wrong oder.
            while (Common.firstStringbool((String) currentTNode.getBeforeNode().getValue(),
                    (String) currentTNode.getValue(), referenceMap)) {
                currentTNode = currentTNode.getNextNode();
                currentIndex++;
                if (currentTNode == null)
                    break;
            }
            // create new Chunk out of these elements
            new Chunk<String>(firstNode, currentTNode, firstChunkIndex, currentIndex, chunkList);
            merges++;
            // move bordernodes/indeces along to next chunk
            firstNode = currentTNode;
            firstChunkIndex = currentIndex++;
            if (currentTNode != null)
                currentTNode = currentTNode.getNextNode();

            
            // creates higher thread at first oppportunity
            if (merges == 2) {
                createHigherThread();
            }
            if (getHigherThread() != null) { // if the higher thread already exists

                getHigherThread().deltaChunksQued(1); // increase the qued Chunks by one

                // if it is waiting, notify so that it may check weather it has enough Chunks in
                // que to proceed.
                if (getHigherThread().waiting && getHigherThread().chunksQued > 2) {
                    synchronized (getHigherThread()) {
                        getHigherThread().notify();
                    }
                }
            }
            if (currentTNode == null)
                break;

        } while ((!(chunkList.getLastNode().getValue().upperNode.getNextNode() == null)) && currentTNode != null);

        if (getHigherThread() != null) {
            System.out.println("Error, lower thread finished with higher thread waiting");
            getHigherThread().setLowerThread(null);
            synchronized (getHigherThread()) {
                if (getHigherThread().waiting)
                    getHigherThread().notify();
            }
        }

        ThreadedMergeSort.closeThread((MergeWorker) Thread.currentThread());
    }

    private synchronized void routine() { // gets called when the thread is created, which only happens when there are
                                          // 3 new chunks
        currentChunkNode = chunkList.getFirstNode();
        while (running) {

            synchronized (this) {
                // 4 so that there is some distance between this thread and the next on
                // Chunklist --> reduces errors
                while (chunksQued < 4 && lowerThread != null) { 
                    try {
                        if (lowerThread != null) {
                            waiting = true;
                            wait();
                            waiting = false;
                        }
                    } catch (InterruptedException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                }
            }

            //stop the thread if it is at the end of Chunklist
            if (currentChunkNode == null) {
                if (Common.log(2, chunkList.size()) < 3) {
                    //stoppThreading();
                }
                // if lower thread is done, which it should, break and wait for it to give you the signal to termiante
                if(getLowerThread() == null){ 
                    break;
                }else{
                    synchronized(this){
                        waiting = true;
                        try {
                            wait();
                        } catch (InterruptedException e) {
                            System.out.println("couldnt put Thread" + Thread.currentThread().getName() + " into waiting, becasue:");
                            e.printStackTrace();
                        }
                        waiting = false;
                        break;
                    }
                }
            }
            // if there is one other Chunk, which has no second one to pair, then 
            if (currentChunkNode.getNextNode() == null) {
                
                
                if (currentChunkNode.getBeforeNode().getValue().canMergeNext(0.9)) {
                    mergeAlgorythms.mergeChunks(currentChunkNode.getBeforeNode().getValue(),
                            currentChunkNode.getValue(), referenceMap);
                    sortList.testIntegrityFull();
                } else if (higherThread != null){
                    ((MergeWorker)getHigherThread()).deltaChunksQued(1);
                }
                ((MergeWorker) MergeWorker.currentThread()).deltaChunksQued(-1);
                if (Common.log(2, chunkList.size()) < 4) { // bei wenigen Chunks ist es schneller die ohne neue
                                                        // threads zu sortieren
                    //stoppThreading();
                }
                if(getLowerThread() == null){ 
                    sortList.testIntegrityFull();
                    break;
                }else{
                    synchronized(this){
                        try {
                            waiting = true;
                            wait();
                            
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        waiting = false;
                        break;
                    }
                }
            }
            
            
            mergeAlgorythms.mergeChunks(currentChunkNode                    //merge the next two chunks, extend the first chunk to full size and delete the second chunk
                .getValue(),
                currentChunkNode.getNextNode().getValue(), referenceMap);
            ((MergeWorker) MergeWorker.currentThread()).deltaChunksQued(-2);   //reduce the Chunks in que by one
            merges++;                                                          //Increase the amount of merges by 1
            sortList.testIntegrityFull();
            // if the thread has already merged 4 chunks to 2 chunks it can create the next
            // higher thread to merge those two chunks
            if (merges == 2) {          
                createHigherThread();
            }
            
            if (getHigherThread() != null) { // if the higher thread already exists
                
                getHigherThread().deltaChunksQued(1);   // increase the qued Chunks by one
                
                // if it is waiting, notify so that it may check weather it has enough Chunks in
                // que to proceed.
                if (getHigherThread().waiting && getHigherThread().chunksQued > 2) {    
                    synchronized(getHigherThread()){
                        getHigherThread().notify();
                    }  
                }
            }

            
            // in the case that currentChunknode.getNextNode != null, move the current
            // Chunknode up by 1 so that the thrad can merge the next two chunks

            currentChunkNode = currentChunkNode.getNextNode(); 
        }
        if (getHigherThread() != null) {
            System.out.println("Error, lower thread finished with higher thread waiting");
            getHigherThread().setLowerThread(null);
            synchronized (getHigherThread()) {
                if (getHigherThread().waiting)getHigherThread().notify();
            }
        }
        /*
         * try {
         * wait();
         * } catch (InterruptedException e) {
         * // TODO Auto-generated catch block
         * e.printStackTrace();
         * }
         */
        ThreadedMergeSort.closeThread((MergeWorker) Thread.currentThread());
        
    }

    public synchronized void createHigherThread() {
        higherThread = ThreadedMergeSort.createThread((MergeWorker) Thread.currentThread());
    }

    public synchronized MergeWorker getLowerThread() {
        return lowerThread;
    }

    public synchronized void setLowerThread(MergeWorker w) {
        lowerThread = w;
    }
    
    public synchronized MergeWorker getHigherThread() {
        return higherThread;
    }

    public synchronized void setHigherThread(MergeWorker w) {
        higherThread = w;
    }

    public synchronized void deltaChunksQued(int change){
        chunksQued += change;
    }

    public void stoppThreading(){
        
        System.out.println(chunkList.size());
        ThreadedMergeSort.stopAllThreads((MergeWorker) MergeWorker.currentThread());
        System.out.println(chunkList.size());
        try {
            sleep(1000);
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        System.out.println(chunkList.size());
        MergeSortLinkedII.MergeSortLinkedAlgorythm(sortList, referenceMap);
        
    }
}
