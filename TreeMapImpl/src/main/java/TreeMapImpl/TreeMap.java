package TreeMapImpl;


import java.util.*;

public class TreeMap<K extends Comparable, V> implements Map<K, V> {
    private Entry root;
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
    public boolean containsKey(Object key) {
        if (key == null)
            throw new NullPointerException("This collection can't contain null keys");  //TODO key instance of

        return containsKey((K) key, root);
    }

    private boolean containsKey(K key, Entry curEntry) {
        if (curEntry == null)
            return false;
        if (key.compareTo(curEntry.key) == 0)
            return true;
        if (key.compareTo(curEntry.key) < 0)
            return containsKey(key, curEntry.left);
        else
            return containsKey(key, curEntry.right);
    }

    @Override
    public boolean containsValue(Object value) {
        if (root == null)
            return false;

        return containsValue((V) value, root);
    }

    private boolean containsValue(V value, Entry curEntry) {
        if (curEntry == null)
            return false;
        if (value.equals(curEntry.value))
            return true;
        return containsValue(value, curEntry.left) || containsValue(value, curEntry.right);
    }

    @Override
    public V get(Object key) {
        return get((K) key, root);
    }

    private V get(K key, Entry curEntry) {
        if (curEntry == null)
            return null;
        if (key.compareTo(curEntry.key) == 0)
            return (V) curEntry.value;
        if (key.compareTo(curEntry.key) < 0)
            return get(key, curEntry.left);
        else
            return get(key, curEntry.right);
    }

    @Override
    public V put(K key, V value) {
        Objects.requireNonNull(value, "Null value is not permitted");
        if (root == null) {
            root = new Entry(key, value);
            size++;
            return null;
        } else return put(key, value, root);
    }

    private V put(K key, V value, Entry curEntry) {
        if (key.compareTo(curEntry.key) == 0) {
            V temp = (V) curEntry.value;
            curEntry.value = value;
            return temp;
        }
        if (key.compareTo(curEntry.key) < 0) {
            if (curEntry.left == null) {
                curEntry.left = new Entry(key, value);
                this.size++;
                return null;
            } else
                return put(key, value, curEntry.left);
        } else {
            if (curEntry.right == null) {
                curEntry.right = new Entry(key, value);
                this.size++;
                return null;
            } else
                return put(key, value, curEntry.right);
        }
    }

    @Override
    public V remove(Object key) {
        if (root == null)
            return null;
        return remove((K) key, root, root);
    }

    private V remove(K key, Entry curEntry, Entry prevEntry) {
        if (curEntry == null)
            return null;

        if (key.compareTo(curEntry.key) == 0) {
            if (curEntry.left == null && curEntry.right == null) {
                if (curEntry == prevEntry.left)
                    prevEntry.left = null;
                if (curEntry == prevEntry.right)
                    prevEntry.right = null;
                if (curEntry == root)
                    root = null;
                size--;
                return (V) curEntry.value;

            } else if (curEntry.right == null) {
                if (curEntry == prevEntry.left)
                    prevEntry.left = curEntry.left;
                if (curEntry == prevEntry.right)
                    prevEntry.right = curEntry.left;
                if (curEntry == root)
                    root = curEntry.left;
                size--;
                return (V) curEntry.value;

            } else if (curEntry.right != null) {
                if (curEntry == prevEntry.left) {
                    if (curEntry.right.left == null) {
                        prevEntry.left = curEntry.right;
                        prevEntry.left.left = curEntry.left;
                        size--;
                        return (V) curEntry.value;
                    }
                    prevEntry.left = findReplacementInTheRightChild(curEntry.right, curEntry.right);
                    prevEntry.left.left = curEntry.left;
                    prevEntry.left.right = curEntry.right;
                }
                if (curEntry == prevEntry.right) {
                    if (curEntry.right.left == null) {
                        prevEntry.right = curEntry.right;
                        prevEntry.right.left = curEntry.left;
                        size--;
                        return (V) curEntry.value;
                    }
                    prevEntry.right = findReplacementInTheRightChild(curEntry.right, curEntry.right);
                    prevEntry.right.left = curEntry.left;
                    prevEntry.right.right = curEntry.right;
                }
                if (curEntry == root) {
                    if (root.right.left == null) {
                        root = root.right;
                        root.left = curEntry.left;
                        size--;
                        return (V) root.value;
                    }
                    root = findReplacementInTheRightChild(curEntry.right, curEntry.right);
                    root.left = curEntry.left;
                    root.right = curEntry.right;
                }
                size--;
                return (V) curEntry.value;
            }
        }
        if (key.compareTo(curEntry.key) < 0)
            return remove(key, curEntry.left, curEntry);
        else
            return remove(key, curEntry.right, curEntry);
    }

    private Entry findReplacementInTheRightChild(Entry curEntry, Entry prevEntry) {
        if (curEntry.left == null) {
            prevEntry.left = curEntry.right;
            return curEntry;
        }
        return findReplacementInTheRightChild(curEntry.left, curEntry);
    }

    @Override
    public void putAll(Map<? extends K, ? extends V> m) {
        for (Map.Entry<? extends K, ? extends V> entry : m.entrySet())
            this.put(entry.getKey(), entry.getValue());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (!(o instanceof TreeMap))
            return false;
        Map<K, V> inputMap = (Map<K, V>) o;
        if(this.size != inputMap.size())
            return false;
        return this.entrySet().equals(inputMap.entrySet());
    }

    @Override
    public int hashCode() {
        return 5;
    }

    @Override
    public void clear() {
        this.size = 0;
        root = null;

    }

    @Override
    public Set<K> keySet() {
        return null;
    }

    @Override
    public Collection<V> values() {
        return null;
    }

/*    private void entrySet(Map.Entry<K, V> curEntry, Set<Map.Entry<K,V>> entrySet) {
        if (curEntry == null)
            return;
        entrySet(curNode.left, arr);
        arr[size++] = curNode.value;
        toArr(curNode.right, arr);
    }*/

    @Override
    public Set<Map.Entry<K, V>> entrySet() {
        return new EntrySet();
    }


    public class Entry<K, V> implements Map.Entry<K, V> {
        private final K key;
        private V value;
        private Entry<K, V> left;
        private Entry<K, V> right;

        Entry(K key, V value) {
            this.key = key;
            this.value = value;
        }

        @Override
        public K getKey() {
            return this.key;
        }

        @Override
        public V getValue() {
            return this.value;
        }

        @Override
        public V setValue(V value) {
            V temp = this.value;
            this.value = value;
            return temp;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o)
                return true;
            if (!(o instanceof Entry))
                return false;

            Entry<K, V> inputEntry = (Entry<K, V>) o;
            if (inputEntry.getValue() == null ? this.getValue() == null : inputEntry.getValue().equals(this.getValue()) &&
                    (inputEntry.getKey().equals(this.getKey())))
                return true;
            else
                return false;
        }

        @Override
        public int hashCode() {
            int result = key.hashCode();
            result = 31 * result + (value != null ? value.hashCode() : 0);
            result = 31 * result + (left != null ? left.hashCode() : 0);
            result = 31 * result + (right != null ? right.hashCode() : 0);
            return result;
        }
    }

    private class EntrySet implements Set<Map.Entry<K, V>> {

        @Override
        public Iterator<Map.Entry<K, V>> iterator() {
            return new LazyIterator();
        }

        @Override
        public Object[] toArray() {
            throw new UnsupportedOperationException();
        }

        @Override
        public <T> T[] toArray(T[] a) {
            throw new UnsupportedOperationException();
        }

        @Override
        public boolean add(Map.Entry<K, V> kvEntry) {
            throw new UnsupportedOperationException();
        }

        @Override
        public int size() {
            return TreeMap.this.size;
        }

        @Override
        public boolean isEmpty() {
            return TreeMap.this.size == 0;
        }

        @Override
        public boolean contains(Object o) {
            throw new UnsupportedOperationException();
        }

        @Override
        public boolean remove(Object o) {

            return false;
        }

        @Override
        public boolean containsAll(Collection<?> c) {
            throw new UnsupportedOperationException();
        }

        @Override
        public boolean addAll(Collection<? extends Map.Entry<K, V>> c) {
            throw new UnsupportedOperationException();
        }

        @Override
        public boolean retainAll(Collection<?> c) {
            return false;
        }

        @Override
        public boolean removeAll(Collection<?> c) {
            return false;
        }

        @Override
        public void clear() {
        }

        @Override
        public boolean equals(Object obj) {
            EntrySet inputSet = (EntrySet) obj;
            boolean result = true;

            Iterator<Map.Entry<K, V>> thisIterator = this.iterator();
            Iterator<Map.Entry<K, V>> inputIterator = inputSet.iterator();
            while (thisIterator.hasNext()) {
                Map.Entry<K, V> entry1 = thisIterator.next();
                Map.Entry<K, V> entry2 = inputIterator.next();
                if (!(entry1.equals(entry2)))
                    return false;
            }
            return result;
        }
    }

    public class LazyIterator implements Iterator<Map.Entry<K, V>> {
        private Deque<Entry<K, V>> deque = new LinkedList<>();
        Entry<K, V> curEntry = null;

        {
            deque.add(root);
        }

        @Override
        public boolean hasNext() {
            return !deque.isEmpty() && !(deque.peekFirst() == null && deque.size() == 1);
        }

        @Override
        public Map.Entry<K, V> next() {
            while (!deque.isEmpty()) {
                if (deque.peekFirst() != null && deque.peekFirst().left != null) {
                    deque.addFirst(deque.peekFirst().left);
                    continue;
                }
                if (deque.peekFirst() == null) {
                    deque.pollFirst();
                    this.curEntry = deque.pollFirst();
                    deque.addFirst(curEntry.right);
                    break;
                }
                this.curEntry = deque.pollFirst();
                deque.addFirst(curEntry.right);
                break;
            }
            return curEntry;
        }

        @Override
        public void remove() {
            TreeMap.this.remove(curEntry.key);

        }
    }

}



