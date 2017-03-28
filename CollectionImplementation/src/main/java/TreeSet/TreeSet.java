package TreeSet;

import java.util.*;

public class TreeSet<V extends Comparable> implements Set<V> {
    private Node root;
    private int size;

    @Override
    public int size() {
        return this.size;
    }

    @Override
    public boolean isEmpty() {
        return this.size == 0;
    }

    @Override
    public boolean contains(Object o) {
        if (o == null)
            throw new NullPointerException("This collection can't contain null values");

        return contains((V) o, root);
    }


    private boolean contains(V value, Node curNode) {
        if (curNode == null)
            return false;
        if (value.compareTo(curNode.value) == 0)
            return true;
        if (value.compareTo(curNode.value) < 0)
            return contains(value, curNode.left);
        else
            return contains(value, curNode.right);
    }

    @Override
    public Iterator<V> iterator() {
        return null;
    }

    @Override
    public Object[] toArray() {
        Object[] arr = new Object[this.size];
        this.size = 0;
        toArr(root, arr);
        this.size = arr.length;
        return arr;
    }

    private void toArr(Node curNode, Object[] arr) {
        if (curNode == null)
            return;
        toArr(curNode.left, arr);
        arr[size++] = curNode.value;
        toArr(curNode.right, arr);
    }

    @Override
    public <T> T[] toArray(T[] a) {
        T[] arr = (T[]) toArray();
        for (int i = 0; i < Math.min(a.length, arr.length); i++)
            a[i] = arr[i];
        return a;
    }

    @Override
    public boolean add(V value) {
        Objects.requireNonNull(value, "Null value is not permitted");
        if (root == null) {
            root = new Node(value);
            size++;
            return true;
        } else return add(value, root);
    }

    private boolean add(V value, Node curNode) {
        if (value.compareTo(curNode.value) == 0) {
            return false;
        }
        if (value.compareTo(curNode.value) < 0) {
            if (curNode.left == null) {
                curNode.left = new Node(value);
                this.size++;
                return true;
            } else
                return add(value, curNode.left);
        }
        if (value.compareTo(curNode.value) > 0)
            if (curNode.right == null) {
                curNode.right = new Node(value);
                this.size++;
                return true;
            } else
                return add(value, curNode.right);

        return false;
    }

    @Override
    public boolean remove(Object o) {
        if (root == null)
            return false;
        if (root.value.equals(o)) {
            root = removeOrReplace(root);
            this.size--;
            return true;
        }
        return remove((V) o, root, root);
    }

    private boolean remove(V value, Node curNode, Node prevNode) {
        if (value.compareTo(curNode.value) == 0) {
            if (curNode.left == null && curNode.right == null)
                if (curNode == prevNode.left)
                    prevNode.left = null;
                else
                    prevNode.right = null;
    }
        
        if(value.compareTo(curNode.value) < 0) {
        if (curNode.left == null)
            return false;
        if (curNode.left.value.equals(value)) {
            if (curNode.left.left == null && curNode.left.right == null)
                curNode.left = null;
            else {
                Node temp = curNode.left;
                curNode.left = removeOrReplace(curNode.left);
                curNode.left.left = temp.left;
                curNode.left.right = temp.right;
                this.size--;
                return true;
            } else
            return remove(value, curNode.left);
        }
        if (value.compareTo(curNode.value) > 0) {
            if (curNode.right == null)
                return false;
            if (curNode.right.value.equals(value)) {
                Node temp = curNode.right;
                curNode.right = removeOrReplace(curNode.right);
                curNode.right.left = temp.left;
                curNode.right.right = temp.right;
                this.size--;
                return true;
            } else
                return remove(value, curNode.right);
        }
        return false;
    }

    private Node removeOrReplace(Node delNode) {
        if (delNode.right == null)
            return delNode.left;
        else
            return findToReplaceInRightSibling(delNode.right);
    }

    private Node findToReplaceInRightSibling(Node curNode) {
        if (curNode.left == null)
            return curNode;
        return findToReplaceInRightSibling(curNode.left);
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        boolean flag = true;
        for (Object val : c)
            if (!this.contains(val)) {
                flag = false;
                break;
            }
        return flag;
    }

    @Override
    public boolean addAll(Collection<? extends V> c) {
        boolean flag = false;
        for (V val : c)
            if (this.add(val))
                flag = true;
        return flag;
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        boolean flag = false;
        for (Object val : c)
            if (!this.contains(val))
                flag = this.remove(val);

        return flag;
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        boolean flag = false;
        for (Object val : c)
            if (this.remove(val))
                flag = true;
        return flag;
    }

    @Override
    public void clear() {
        this.size = 0;
        this.clear(root);
    }

    private void clear(Node curNode) {
        if (curNode == null)
            return;
        clear(curNode.left);
        curNode.left = null;
        clear(curNode.right);
        curNode.right = null;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (!(o instanceof TreeSet))
            return false;
        TreeSet<V> set = (TreeSet<V>) o;
        if (this.size != ((TreeSet) o).size())
            return false;

        return containsAll(set) && set.containsAll(this);
    }

    @Override
    public int hashCode() {
        int result = root != null ? root.hashCode() : 0;
        result = 31 * result + size;
        return result;
    }


    public static class Node<V extends Comparable<V>> {
        private final V value;
        private Node left;
        private Node right;

        Node(V value) {
            this.value = value;
        }

        @Override
        public String toString() {
            return "Node{" +
                    "value=" + value +
                    '}';
        }
    }

}
