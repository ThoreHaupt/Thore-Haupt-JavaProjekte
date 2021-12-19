package PracticeProjects.Sortingalgorythms.ThreadedMergeSort;

import java.util.ArrayList;
import java.util.HashMap;

import PracticeProjects.TLinkedList;
import PracticeProjects.TNode;
import PracticeProjects.Sortingalgorythms.Common;

public class mergeAlgorythms {
    
    public static <T> void mergeChunks(Chunk<T> chunk1, Chunk<T> chunk2, HashMap<Character, Integer> referenceMap) {
        
        int c = chunk1.getFirstIndex();
        int i = chunk2.getFirstIndex();

        TNode<T> firstNodeNewChunk = chunk1.getFirstNode();
        ArrayList<TNode<T>> chunkNodes = new ArrayList<TNode<T>>();
        chunkNodes.add(chunk1.getFirstNode());
        chunkNodes.add(chunk2.getFirstNode());
        chunkNodes.add(chunk2.getUpperNode());
        

        while (i < chunk2.getUpperIndex()) { // interates over 2nd listpart

            if (Common.firstStringbool((String)chunkNodes.get(1).getValue(), 
                    (String)chunkNodes.get(0).getValue(), referenceMap)) {
                
                chunk1.upperNode.list.printListChunk(chunk1.getFirstIndex(), chunk2.getUpperIndex());
                TNode<T> insertedNode = chunkNodes.get(0).createBeforeNode(chunkNodes.get(1).getValue());
                chunk1.upperNode.list.printListChunk(chunk1.getFirstIndex(), chunk2.getUpperIndex());
                chunk1.upperNode.list.testIntegrityFull();
                chunkNodes.get(1).removeNode(); // remove old element i, but it is moved bc of the insert (--> +1)
                chunk1.upperNode.list.printListChunk(chunk1.getFirstIndex(), chunk2.getUpperIndex());
                chunk1.upperNode.list.testIntegrityFull();
                // set firstNodeNewChunk, if this is the first comparison, if i comes before c.
                // (--> then this if statement is called)
                if (c == chunk1.getFirstIndex() && i == chunk2.getFirstIndex()) {
                    firstNodeNewChunk = insertedNode;
                }
                i++;
                c++; // c index has to move bc i was inserted before it. --> compare next element
                     // from i to c
    
                chunkNodes.set(1, chunkNodes.get(1).getNextNode()); // take next word from 2nd chunk(move i to the
                                                                    // right)
            } else {
                c++;
                chunkNodes.set(0, chunkNodes.get(0).getNextNode()); // compare to next character
            }
            if (chunkNodes.get(1) == null || c >= i) {
                break;
            }

        }
        chunk1.upperNode.list.testIntegrityFull();
        chunk1.setFirstNode(firstNodeNewChunk);   
        chunk1.extend(true, chunk2.getSize(), chunk2.getUpperNode());
        chunk2.remove();     
        chunk1.upperNode.list.testIntegrityFull();
        }
}
