package PracticeProjects.HashMapImplementation;

import java.util.Iterator;

public class Bucket<K, V> implements Iterable<HashNode<K, V>> {
    THashMap<K, V> map;
    LinkedHashNode<K, V> head = null;
    TreeHashNode<K, V> root = null;
    int size = 0;
    boolean tree = false;

    private final int TREEIFY_THRESHOLD = 2;
    private final int UNTREEIFY_THRESHOLD = 1;

    public Bucket(THashMap<K, V> map) {
        this.map = map;
    }

    public V get(int hash) {
        if (head == null)
            return null;
        if (tree) {

        }
        LinkedHashNode<K, V> currentNode = (LinkedHashNode<K, V>) head;
        while (currentNode.hash != hash) {
            currentNode = currentNode.next;
            if (currentNode == null)
                return null;
        }
        return currentNode.value;
    }

    /**
     */
    public boolean add(int hash, K key, V value) {
        if (tree) {
            if (root == null)
                throw new RuntimeException("There is a tree, but its empty");
            TreeHashNode<K, V> newNode = new TreeHashNode<K, V>(this, hash, key, value);
            if (root.add(newNode)) {
                size++;
                return true;
            } else {
                return false;
            }
        }

        LinkedHashNode<K, V> newNode = new LinkedHashNode<K, V>(this, hash, key, value);

        if (head == null) {
            head = newNode;
            size++;
            return true;
        }

        if (head.hash == hash) {
            head.value = value;
            return false;
        }
        LinkedHashNode<K, V> current = (LinkedHashNode<K, V>) head;
        while (current.next != null) {
            if (current.hash == hash) {
                current.value = value;
                return false;
            }
            current = current.next;
        }
        current.next = newNode;
        size++;
        checkTREEIFY();
        return true;
    }

    public boolean remove(int hash, K key) {
        if ((!tree && head == null) || (tree && root == null)) {
            // throw new KeyNotFoundException();
            return false;
        }
        if (tree) {
            if (root.remove(hash)) {
                size--;
                checkUNTREEIFY();
                return true;
            }
            return false;
        }
        if (head.hash == hash) {
            head = ((LinkedHashNode<K, V>) head).next;
            return true;
        }
        LinkedHashNode<K, V> current = (LinkedHashNode<K, V>) head;
        while (current.next != null) {
            if (current.next.hash == hash) {
                current.next = current.next.next;
                size--;
                return true;
            }
            current = current.next;
        }
        return false;
    }

    private void checkUNTREEIFY() {
        if (size <= UNTREEIFY_THRESHOLD) {
            tree = false;
            UNTREEIFY();
        }
    }

    private void checkTREEIFY() {
        if (size >= TREEIFY_THRESHOLD) {
            tree = true;
            TREEIFY();
        }
    }

    private void TREEIFY() {
        System.out.println("treeify");
        LinkedHashNode<K, V> current = head.next;
        root = new TreeHashNode<K, V>(this, head.hash, head.key, head.value);
        while (current != null) {
            root.add(new TreeHashNode<K, V>(this, current.hash, current.key, current.value));
            current = current.next;
        }
        head = null;
    }

    private void UNTREEIFY() {
        System.out.println("untreeify");
        TreeHashNode<K, V> current = root;
        boolean sideLeft = true;
        LinkedHashNode<K, V> newNode;
        while (current.parent != null && current.left == null && current.right == null) {
            if (current.left != null) {
                current = current.left;
                sideLeft = true;
                continue;
            }
            if (current.right != null) {
                current = current.right;
                sideLeft = false;
                continue;
            }
            newNode = new LinkedHashNode<K, V>(this, current.hash, current.key, current.value);
            newNode.next = head;
            head = newNode;
            current = current.parent;
            if (sideLeft) {
                current.left = null;
            } else {
                current.right = null;
            }
        }
    }

    public LinkedHashNode<K, V> getNodeByHash(int hash) {
        LinkedHashNode<K, V> current = (LinkedHashNode<K, V>) head;
        while (current.next != null) {
            if (current.hash == hash) {
                return current;
            }
            current = current.next;
        }
        return null;
    }

    @Override
    public Iterator<HashNode<K, V>> iterator() {
        if (tree) {
        }
        Iterator<HashNode<K, V>> iterator = new Iterator<HashNode<K, V>>() {

            private LinkedHashNode<K, V> currentNode = (LinkedHashNode<K, V>) head;

            @Override
            public boolean hasNext() {
                if (currentNode != null)
                    return true;
                return false;
            }

            @Override
            public HashNode<K, V> next() {
                LinkedHashNode<K, V> outputNode = currentNode;
                if (currentNode != null)
                    currentNode = currentNode.next;
                return outputNode;
            }

        };
        return iterator;
    }

    public int size() {
        return size;
    }

}
