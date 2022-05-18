package PracticeProjects.HashMapImplementation;

public class TreeHashNode<K, V> extends HashNode<K, V> {

    TreeHashNode<K, V> parent;
    TreeHashNode<K, V> left;
    TreeHashNode<K, V> right;

    public TreeHashNode(int hash, K key, V value) {
        super(hash, key, value);
    }

    public boolean add(TreeHashNode<K, V> node) {
        if (node.hash < this.hash) {
            if (left == null) {
                left = node;
                node.parent = this;
                return true;
            } else {
                return left.add(node);
            }
        } else if (node.hash > this.hash) {
            if (right == null) {
                right = node;
                node.parent = this;
                return true;
            } else {
                return right.add(node);
            }

        } else {
            this.value = node.value;
            return false;
        }
    }

    public void copyNodeToThis(TreeHashNode<K, V> node) {
        super.copyNodeToThis(node);
    }

    public boolean remove(int hash) {
        if (hash < this.hash) {
            if (left.hash != hash) {
                return left.remove(hash);
            }

            if (left.left == null && left.right == null) {
                left = null;
                return true;
            }

            if (left.left != null && left.right == null) {
                left = left.left;
                left.parent = this;
                return true;
            }

            if (left.left == null && left.right != null) {
                left = left.right;
                left.parent = this;
                return true;
            }
            TreeHashNode<K, V> current = left.right;
            while (current.left != null) {
                current = current.left;
            }
            left.copyNodeToThis(current);
            return true; //

        } else if (hash > this.hash) {
            if (right.hash != hash) {
                return right.remove(hash);
            }
        } else if (hash == this.hash) {
            // then this is seed and we want to delete Seed

        }
        return true;//

    }

    public void delete(TreeHashNode<K, V> parent, boolean left) {

    }

}
