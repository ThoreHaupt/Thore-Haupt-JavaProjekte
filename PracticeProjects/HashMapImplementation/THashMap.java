package PracticeProjects.HashMapImplementation;

import java.util.Iterator;
import java.util.zip.CRC32;

public class THashMap<Key, Value> implements Iterable<Value> {
    static CRC32 crc32 = new CRC32();

    private Bucket<Value>[] buckets;
    private int currentExponentSize = 4;
    private float loadFactor;
    private int size = 0;

    public THashMap() {
        this((int) Math.pow(2, 4), 0.75f);
    }

    public THashMap(int starterArraySize, float loadFactor) {
        this.buckets = new Bucket[starterArraySize];
        this.loadFactor = loadFactor;

    }

    public void put(Key key, Value value) {
        if (key == null)
            new RuntimeException("can't put a null as key for entry");
        int hash = calculateHash(key);
        if (put(hash, value, buckets, buckets.length)) {
            size++;
            if (checkForRehash()) {
                rehash(1);
            }
        }
    }

    private boolean put(int hash, Value value, Bucket<Value>[] bucketArray, int bucketArraySize) {
        int bucket = (hash % (bucketArraySize - 1));
        return addNodeToBucket(bucketArray, bucket, hash, value);
    }

    public Value get(Key key) throws KeyNotFoundException {
        int hash = calculateHash(key);
        int bucketIndex = (hash % (buckets.length - 1));
        if ((buckets[bucketIndex]) == null)
            return null;
        return buckets[bucketIndex].get(hash);
    }

    private boolean addNodeToBucket(Bucket<Value>[] bucketArray, int bucketIndex, int hash, Value value) {
        if (bucketArray[bucketIndex] == null) {
            bucketArray[bucketIndex] = new Bucket<Value>(this);
        }
        // returns true, if a new Element is added and not replaced
        return bucketArray[bucketIndex].add(hash, value);
    }

    private boolean checkForRehash() {
        double freshhold = (double) size / (double) buckets.length;
        if (freshhold > loadFactor) {
            return true;
        }
        return false;
    }

    private void rehash(int exponentChange) {
        int tempBucketArraySize = (int) Math.pow(2, currentExponentSize + exponentChange);
        currentExponentSize += exponentChange;
        Bucket<Value>[] bucketsTemp = new Bucket[tempBucketArraySize];
        for (Bucket<Value> bucket : buckets) {
            if (bucket == null || bucket.head == null)
                continue;
            for (HashNode<Value> node : bucket) {
                put(node.hash, node.value, bucketsTemp, tempBucketArraySize);
            }
        }
        this.buckets = bucketsTemp;
    }

    private int calculateHash(Key key) {

        return System.identityHashCode(key);

    }

    // not needed rn
    private int getLastUsedBucketIndex() {
        int last = -1;
        for (int i = 0; i < buckets.length; i++) {
            if (buckets[i] != null && buckets[i].size() > 0)
                last = i;
        }

        return last;
    }

    private int getNextUsedBucketIndex(int start) {
        int i = start + 1;
        while (buckets[i] == null || buckets[i].head == null) {
            i++;
            if (i > buckets.length - 1)
                return -1;
        }
        return i;
    }

    @Override
    public Iterator<Value> iterator() {
        Iterator<Value> iterator = new Iterator<Value>() {

            int currentBucket = getNextUsedBucketIndex(-1);
            int lastUsedBucketIndex = getLastUsedBucketIndex();
            Iterator<HashNode<Value>> currentInterator = buckets[currentBucket].iterator();

            @Override
            public boolean hasNext() {

                if (currentBucket < lastUsedBucketIndex && currentBucket != -1) {
                    return true;
                }
                if (currentBucket == -1) {
                    return false;
                }
                return currentInterator.hasNext();
            }

            @Override
            public Value next() {

                if (!currentInterator.hasNext()) {
                    currentBucket = getNextUsedBucketIndex(currentBucket);
                    currentInterator = buckets[currentBucket].iterator();
                }
                return currentInterator.next().value;
            }

        };
        return iterator;
    }

    public int size() {
        return size;
    }

}
