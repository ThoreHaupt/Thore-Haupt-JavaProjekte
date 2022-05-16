package PracticeProjects.HashMapImplementation;

import java.io.UncheckedIOException;
import java.util.Iterator;
import java.util.zip.CRC32;

import javax.management.RuntimeErrorException;

public class THashMap<Key, Value> implements Iterable<Value> {
    static CRC32 crc32 = new CRC32();

    private HashNode[] buckets;
    private float loadFactor;
    private int size = 0;

    public THashMap() {
        this(16, 0.75f);
    }

    public THashMap(int starterArraySize, float loadFactor) {
        this.buckets = new HashNode[starterArraySize];
        this.loadFactor = loadFactor;

    }

    public void put(Key key, Value value) {
        if (key == null)
            new RuntimeException("can't put a null as key for entry");
        int hash = calculateHash(key);
        int bucket = (hash % buckets.length);
        addNodeToBucket(bucket, hash, value);
    }

    private void addNodeToBucket(int bucket, int hash, Value value) {
        if (buckets[bucket] == null) {
            buckets[bucket] = new LinkedNode<Value>(hash, value, 1);
        } else {
            // check if bucket is too full, then change to Treeset
            LinkedNode<Value> newNode = new LinkedNode<Value>(hash, value, ++buckets[bucket].listlength);
            if (buckets[bucket] instanceof LinkedNode)
                newNode.next = (LinkedNode) buckets[bucket];
            buckets[bucket] = newNode;
        }
    }

    private int calculateHash(Key key) {
        return System.identityHashCode(key);

    }

    @Override
    public Iterator<Value> iterator() {
        // TODO Auto-generated method stub
        return null;
    }

}
