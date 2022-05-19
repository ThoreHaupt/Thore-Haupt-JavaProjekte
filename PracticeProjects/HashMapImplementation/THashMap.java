package PracticeProjects.HashMapImplementation;

import java.util.Iterator;

public class THashMap<K, V> implements Iterable<V> {

    private Bucket<K, V>[] buckets;
    private int currentExponentSize = 4;
    private float loadFactor;
    private int size = 0;

    public THashMap() {
        this((int) Math.pow(2, 4), 0.75f);
    }

    public THashMap(float loadFactor) {
        this((int) Math.pow(2, 4), loadFactor);
    }

    @SuppressWarnings("unchecked")
    public THashMap(int starterArraySize, float loadFactor) {
        this.buckets = new Bucket[starterArraySize];
        this.loadFactor = loadFactor;

    }

    public void put(K key, V value) {
        if (key == null)
            new RuntimeException("can't put a null as key for entry");
        int hash = calculateHash(key);
        if (put(hash, key, value, buckets, buckets.length)) {
            size++;
            if (checkForRehash()) {
                rehash(1);
            }
        }
    }

    private boolean put(int hash, K key, V value, Bucket<K, V>[] bucketArray, int bucketArraySize) {
        int bucket = calculateBucketIndex(hash, bucketArraySize);
        return addNodeToBucket(bucketArray, bucket, hash, key, value);
    }

    public V get(K key) throws KeyNotFoundException {
        int hash = calculateHash(key);
        int bucketIndex = calculateBucketIndex(hash, buckets.length);
        if ((buckets[bucketIndex]) == null)
            return null;
        return buckets[bucketIndex].get(hash);
    }

    public void remove(K key) {
        int hash = calculateHash(key);
        int bucketIndex = calculateBucketIndex(hash, buckets.length);
        if (buckets[bucketIndex] == null) {
            System.out.println("PROBLEM");
            return;
        }
        if (buckets[bucketIndex].remove(hash, key)) {
            size--;
        }
    }

    private boolean addNodeToBucket(Bucket<K, V>[] bucketArray, int bucketIndex, int hash, K key, V value) {
        if (bucketArray[bucketIndex] == null) {
            bucketArray[bucketIndex] = new Bucket<K, V>(this);
        }
        // returns true, if a new Element is added and not replaced
        return bucketArray[bucketIndex].add(hash, key, value);
    }

    private boolean checkForRehash() {
        double freshhold = (double) size / (double) buckets.length;
        if (freshhold > loadFactor) {
            return true;
        }
        return false;
    }

    @SuppressWarnings("unchecked")
    private void rehash(int exponentChange) {
        int tempBucketArraySize = (int) Math.pow(2, currentExponentSize + exponentChange);
        currentExponentSize += exponentChange;
        Bucket<K, V>[] bucketsTemp = new Bucket[tempBucketArraySize];
        for (Bucket<K, V> bucket : buckets) {
            if (bucket == null || bucket.head == null)
                continue;
            for (HashNode<K, V> node : bucket) {
                put(node.hash, node.key, node.value, bucketsTemp, tempBucketArraySize);
            }
        }
        this.buckets = bucketsTemp;
    }

    private int calculateHash(K key) {

        // return System.identityHashCode(key);

        return key.hashCode();

    }

    private int calculateBucketIndex(int hash, int bucketArraySize) {
        return (hash % (bucketArraySize));
    }

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
        while (buckets[i] == null || (buckets[i].head == null && buckets[i].root == null)) {
            i++;
            if (i > buckets.length - 1)
                return -1;
        }
        return i;
    }

    @Override
    public Iterator<V> iterator() {

        Iterator<V> iterator = new Iterator<V>() {

            int currentBucket = getNextUsedBucketIndex(-1);
            int lastUsedBucketIndex = getLastUsedBucketIndex();
            Iterator<HashNode<K, V>> currentInterator = buckets[currentBucket].iterator();

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
            public V next() {

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
