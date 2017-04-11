package HashMapImpl;

import java.util.*;

public class HashMap<K, V> implements Map<K, V> {
    private int capacity = 17;
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
        int bucketNum = 0;
        if (key != null)
            bucketNum = calcBucketNum(key.hashCode());
        Entry<K, V> targetEl = this.findElInBucketByKey(bucketNum, (K) key);
        if (targetEl == null)
            return false;

        return true;
    }

    @Override
    public boolean containsValue(Object value) {
        for (int bucketNum = 0; bucketNum < capacity; bucketNum++)
            for (Entry<K, V> entry : this.data[bucketNum])
                if (entry.getValue().equals(value))
                    return true;

        return false;
    }

    @Override
    public V get(Object key) {
        int bucketNum = 0;
        if (key != null)
            bucketNum = calcBucketNum(key.hashCode());
        Entry<K, V> targetEl = this.findElInBucketByKey(bucketNum, (K) key);
        if (targetEl == null)
            return null;
        else
            return targetEl.value;
    }

    @Override
    public V put(K key, V value) {
        int bucketNum = 0;
        if (key != null)
            bucketNum = calcBucketNum(key.hashCode());
        Entry<K, V> targetEl = this.findElInBucketByKey(bucketNum, key);
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
        hashCode = Math.abs(hashCode);
        int shift = Math.abs(Integer.numberOfLeadingZeros(hashCode) - Integer.numberOfLeadingZeros(this.capacity - 1));
        hashCode >>>= shift;
        if (hashCode >= this.capacity - 1)
            hashCode = Math.abs(hashCode - this.capacity + 1);

        return hashCode+1;
    }

    private Entry<K, V> findElInBucketByKey(int bucketNum, K key) {
        if (bucketNum == 0)
            return this.data[0].peekFirst();

        for (Entry<K, V> entry : this.data[bucketNum])
            if (entry.getKey().equals(key))
                return entry;

        return null;
    }

    @Override
    public V remove(Object key) {

        return null;
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
    }

    @Override
    public Set<Map.Entry<K, V>> entrySet() {
        return null;
    }

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
            int result = this.key.hashCode();
            result = 31 * result + (value != null ? value.hashCode() : 0);
            result = 31 * result + (left != null ? left.hashCode() : 0);
            result = 31 * result + (right != null ? right.hashCode() : 0);
            return Integer.parseInt(this.key.toString());
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

            return null;
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
            return HashMap.this.size;
        }

        @Override
        public boolean isEmpty() {
            return HashMap.this.size == 0;
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
                deque.add(null);
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

            }

        }
    }

}



