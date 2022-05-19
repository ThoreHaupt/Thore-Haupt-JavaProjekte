package PracticeProjects.HashMapImplementation;

public class TreeHashNode<K, V> extends HashNode<K, V> {

    TreeHashNode<K, V> parent;
    TreeHashNode<K, V> left;
    TreeHashNode<K, V> right;
    boolean ISLEFT;

    public TreeHashNode(Bucket<K, V> bucket, int hash, K key, V value) {
        super(bucket, hash, key, value);
    }

    public boolean add(TreeHashNode<K, V> node) {
        if (node.hash < this.hash) {
            if (left == null) {
                left = node;
                node.parent = this;
                node.ISLEFT = true;
                return true;
            } else {
                return left.add(node);
            }
        } else if (node.hash > this.hash) {
            if (right == null) {
                right = node;
                node.parent = this;
                node.ISLEFT = false;
                return true;
            } else {
                return right.add(node);
            }

        } else {
            this.value = node.value;
            return false;
        }
    }

    public void overrideThisNode(TreeHashNode<K, V> node) {
        super.overrideThisNode(node);
    }

    public boolean remove(int hash) {
        if (hash < this.hash) {
            return left.remove(hash);

        } else if (hash > this.hash) {
            return right.remove(hash);

        } else if (hash == this.hash) {
            TreeHashNode<K, V> parentAssign;
            if (left == null & right == null) {
                parentAssign = null;

            } else if (left != null ^ right != null) {
                if (left == null) {
                    parentAssign = right;
                } else {
                    parentAssign = left;
                }
            } else {
                TreeHashNode<K, V> current = right;
                while (current.left != null) {
                    current = current.left;
                }
                overrideThisNode(current);
                current.remove(current.hash);
                return true;
            }
            if (parent == null) {
                bucket.root = parentAssign;
                return true;
            } else if (ISLEFT) {
                parent.left = parentAssign;
                return true;
            } else {
                parent.right = parentAssign;
                return true;
            }
        }
        return false;

    }

    public void delete(TreeHashNode<K, V> parent, boolean left) {

    }

}
