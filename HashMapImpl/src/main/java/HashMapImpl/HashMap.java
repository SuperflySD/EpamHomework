package HashMapImpl;

import java.util.*;


public class HashMap<K, V> implements Map<K, V> {
    private int capacity = 16;
    private LinkedList<Entry<K, V>>[] data = new LinkedList[capacity];
    private int size;
    private int hashCode;
    private long modCount = Long.MIN_VALUE;

    {
        for (int i = 0; i < this.capacity; i++)
            this.data[i] = new LinkedList();
    }

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
        if (key == null) {
            Entry<K, V> targetEl = this.data[0].pollFirst();
            if (targetEl == null) {
                this.data[0].add(new Entry<>(key, value));
                this.size++;
                this.modCount += 1L;
                return null;
            } else {
                V temp = targetEl.value;
                targetEl.value = value;
                this.modCount += 1L;
                return temp;
            }
        }
        int bucketNum = calcBucketNum(key.hashCode());
        Entry<K, V> targetEl = this.findElInBucket(bucketNum, key);
        if (targetEl == null) {
            this.data[bucketNum].add(new Entry<>(key, value));
            this.size++;
            this.modCount += 1L;
            return null;
        } else {
            V temp = targetEl.value;
            targetEl.value = value;
            this.modCount += 1L;
            return temp;
        }
    }

    private int calcBucketNum(int hashCode) {
        int result = 0;
        while (result > this.capacity - 1) {
            while (hashCode != 0) {
                result += hashCode % 10;
                hashCode /= 10;
            }
            hashCode = result;
        }
        return result;
    }

    private Entry<K, V> findElInBucket(int bucketNum, K key) {
        for (Entry<K, V> entry : this.data[bucketNum])
            if (entry.getKey().equals(key))
                return entry;

        return null;
    }


    private V put(K key, V value, Entry curEntry) {
        if (key.compareTo(curEntry.key) == 0) {
            V temp = (V) curEntry.value;
            this.hashCode -= curEntry.hashCode();
            curEntry.value = value;
            this.hashCode += curEntry.hashCode();
            return temp;
        }
        if (key.compareTo(curEntry.key) < 0) {
            if (curEntry.left == null) {
                curEntry.left = new Entry(key, value);
                this.hashCode += curEntry.left.hashCode();
                this.size++;
                return null;
            } else
                return put(key, value, curEntry.left);
        } else {
            if (curEntry.right == null) {
                curEntry.right = new Entry(key, value);
                this.hashCode += curEntry.right.hashCode();
                this.size++;
                return null;
            } else
                return put(key, value, curEntry.right);
        }
    }

    /*@Override
    public V remove(Object key) {
        Entry<K, V> remEntry = remove((K) key, root, root);
        if (remEntry != null) {
            this.size--;
            this.hashCode -= remEntry.hashCode();
            return remEntry.value;
        } else
            return null;
    }

    private Entry<K, V> remove(K key, Entry<K, V> curEntry, Entry<K, V> prevEntry) {
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
                return curEntry;

            } else if (curEntry.right == null) {
                if (curEntry == prevEntry.left)
                    prevEntry.left = curEntry.left;
                if (curEntry == prevEntry.right)
                    prevEntry.right = curEntry.left;
                if (curEntry == root)
                    root = curEntry.left;
                return curEntry;

            } else if (curEntry.right != null) {
                if (curEntry == prevEntry.left) {
                    if (curEntry.right.left == null) {
                        prevEntry.left = curEntry.right;
                        prevEntry.left.left = curEntry.left;
                        return curEntry;
                    }
                    prevEntry.left = findReplacementInTheRightChild(curEntry.right, curEntry.right);
                    prevEntry.left.left = curEntry.left;
                    prevEntry.left.right = curEntry.right;
                    this.toolForIterator = prevEntry.left;
                }
                if (curEntry == prevEntry.right) {
                    if (curEntry.right.left == null) {
                        prevEntry.right = curEntry.right;
                        prevEntry.right.left = curEntry.left;
                        return curEntry;
                    }
                    prevEntry.right = findReplacementInTheRightChild(curEntry.right, curEntry.right);
                    prevEntry.right.left = curEntry.left;
                    prevEntry.right.right = curEntry.right;
                    this.toolForIterator = prevEntry.right;
                }
                if (curEntry == root) {
                    if (root.right.left == null) {
                        root = root.right;
                        root.left = curEntry.left;
                        return root;
                    }
                    root = findReplacementInTheRightChild(curEntry.right, curEntry.right);
                    root.left = curEntry.left;
                    root.right = curEntry.right;
                }
                return curEntry;
            }
        }
        if (key.compareTo(curEntry.key) < 0)
            return remove(key, curEntry.left, curEntry);
        else
            return remove(key, curEntry.right, curEntry);
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
        if (!(o instanceof Map))
            return false;
        Map<K, V> inputMap = (Map<K, V>) o;
        if (this.size != inputMap.size())
            return false;
        return this.entrySet().equals(inputMap.entrySet());
    }

    @Override
    public int hashCode() {
        return this.hashCode;
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


    private void entrySet(Map.Entry<K, V> curEntry, Set<Map.Entry<K, V>> entrySet) {
        if (curEntry == null)
            return;
        entrySet(curNode.left, arr);
        arr[size++] = curNode.value;
        toArr(curNode.right, arr);
    }


    @Override
    public Set<Map.Entry<K, V>> entrySet() {
        return new EntrySet();
    }

    private Entry findReplacementInTheRightChild(Entry curEntry, Entry prevEntry) {
        if (curEntry.left == null) {
            prevEntry.left = curEntry.right;
            return curEntry;
        }
        return findReplacementInTheRightChild(curEntry.left, curEntry);
    }
}*/

    class Entry<K, V> implements Map.Entry<K, V> {
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
            if (!(o instanceof Map.Entry))
                return false;

            Map.Entry<K, V> inputEntry = (Map.Entry<K, V>) o;
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

        @Override
        public String toString() {
            return "Entry{key=" + key + ", value=" + value + '}';
        }
    }

    private class EntrySet extends AbstractSet<Map.Entry<K, V>> {

        @Override
        public Iterator<Map.Entry<K, V>> iterator() {
            return new LazyIterator();
        }

        @Override
        public Object[] toArray() {
            Object[] arr = new Object[TreeMap.this.size];
            TreeMap.this.size = 0;
            toArr(root, arr);
            TreeMap.this.size = arr.length;
            return arr;
        }

        private void toArr(Entry entry, Object[] arr) {
            if (entry == null)
                return;
            toArr(entry.left, arr);
            arr[size++] = entry;
            toArr(entry.right, arr);
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
        public boolean equals(Object obj) {
            Set inputSet = (Set) obj;
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

        private class LazyIterator implements Iterator<Map.Entry<K, V>> {
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
                if (TreeMap.this.toolForIterator != null) {
                    deque.addFirst(TreeMap.this.toolForIterator);
                    deque.addFirst(null);
                    TreeMap.this.toolForIterator = null;
                }

            }
        }

    }
}
        }

