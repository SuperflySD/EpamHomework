package HashMapImpl;

import java.util.*;

public class HashMap<K, V> implements Map<K, V> {
    private int capacity = 17;
    private LinkedList<Entry<K, V>>[] data = new LinkedList[capacity];
    private int size;
    private float loadFactor = 0.75f;
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
        this.checkForRebuilding();

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

    @Override
    public void putAll(Map<? extends K, ? extends V> m) {
        for (Map.Entry<? extends K, ? extends V> entry : m.entrySet())
            this.put(entry.getKey(), entry.getValue());
    }

    @Override
    public V remove(Object key) {
        int bucketNum = 0;
        if (key != null)
            bucketNum = calcBucketNum(key.hashCode());
        Entry<K, V> targetEl = this.findElInBucketByKey(bucketNum, (K) key);
        if (targetEl == null)
            return null;
        else {
            V temp = targetEl.value;
            this.data[bucketNum].remove(targetEl);
            this.size--;
            this.modCount += 1L;
            return temp;
        }
    }

    @Override
    public void clear() {
        this.capacity = 17;
        this.data = new LinkedList[capacity];
        for (int i = 0; i < this.capacity; i++)
            this.data[i] = new LinkedList();
        this.size = 0;
        this.modCount += 1L;
    }

    @Override
    public Set<K> keySet() {
        return this.new KeySet();
    }

    @Override
    public Collection<V> values() {
        return this.new ValuesCollection();
    }

    @Override
    public Set<Map.Entry<K, V>> entrySet() {
        return this.new EntrySet();
    }

    private int calcBucketNum(int hashCode) {
        hashCode = Math.abs(hashCode);
        int shift = Math.abs(Integer.numberOfLeadingZeros(hashCode) - Integer.numberOfLeadingZeros(this.capacity - 1));
        hashCode >>>= shift;
        if (hashCode >= this.capacity - 1)
            hashCode = Math.abs(hashCode - this.capacity + 1);

        return hashCode + 1;
    }

    private void checkForRebuilding() {
        if (this.size > (this.capacity - 1) * this.loadFactor) {
            this.capacity = (this.capacity - 1) * 2 + 1;
            LinkedList<Entry<K, V>>[] tempData = this.data;
            this.data = new LinkedList[capacity];
            for (int i = 0; i < this.capacity; i++)
                this.data[i] = new LinkedList();
            for (LinkedList<Entry<K, V>> lList : tempData)
                for (Entry<K, V> entry : lList)
                    this.putInRebuilding(entry);
        }
    }

    private void putInRebuilding(Entry<K, V> entry) {
        int bucketNum = 0;
        if (entry.getKey() != null)
            bucketNum = calcBucketNum(entry.getKey().hashCode());
        this.data[bucketNum].add(entry);
    }

    private Entry<K, V> findElInBucketByKey(int bucketNum, K key) {
        if (bucketNum == 0)
            return this.data[0].peekFirst();
        for (Entry<K, V> entry : this.data[bucketNum])
            if (entry.getKey().equals(key))
                return entry;

        return null;
    }

    private class Entry<K, V> implements Map.Entry<K, V> {
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
            return result;
        }

        @Override
        public String toString() {
            return "Entry{key=" + key + ", value=" + value + '}';
        }
    }

    private class EntrySet extends AbstractSet<Map.Entry<K, V>> {
        private long modCount = HashMap.this.modCount;

        @Override
        public Iterator<Map.Entry<K, V>> iterator() {
            return this.new EntrySetIterator();
        }

        @Override
        public Object[] toArray() {
            Map.Entry[] arr = new Map.Entry[HashMap.this.size];
            int i = 0;
            for (Map.Entry<K, V> entry : this)
                arr[i++] = entry;
            return arr;
        }

        @Override
        public int size() {
            return HashMap.this.size;
        }

        @Override
        public boolean isEmpty() {
            return HashMap.this.size == 0;
        }

        private class EntrySetIterator implements Iterator<Map.Entry<K, V>> {
            private LinkedList<Entry<K, V>> lList = new LinkedList<>();
            private Entry<K, V> curEntry;

            {
                for (LinkedList<Entry<K, V>> lList : HashMap.this.data)
                    this.lList.addAll(lList);
            }

            @Override
            public boolean hasNext() {
                return !this.lList.isEmpty();
            }

            @Override
            public Map.Entry<K, V> next() {
                if (EntrySet.this.modCount != HashMap.this.modCount)
                    throw new ConcurrentModificationException();
                this.curEntry = this.lList.pollFirst();
                return curEntry;
            }

            @Override
            public void remove() {
                if (HashMap.this.remove(this.curEntry.key) != null)
                    HashMap.this.modCount--;
            }
        }
    }

    private class KeySet extends AbstractSet<K> {
        private long modCount = HashMap.this.modCount;

        @Override
        public Iterator<K> iterator() {
            return this.new KeySetIterator();
        }

        @Override
        public int size() {
            return HashMap.this.size;
        }

        @Override
        public boolean isEmpty() {
            return HashMap.this.size == 0;
        }

        private class KeySetIterator implements Iterator<K> {
            private LinkedList<Entry<K, V>> lList = new LinkedList<>();
            private Entry<K, V> curEntry;

            {
                for (LinkedList<Entry<K, V>> lList : HashMap.this.data)
                    this.lList.addAll(lList);
            }

            @Override
            public boolean hasNext() {
                return !this.lList.isEmpty();
            }

            @Override
            public K next() {
                if (KeySet.this.modCount != HashMap.this.modCount)
                    throw new ConcurrentModificationException();
                this.curEntry = this.lList.pollFirst();
                return curEntry.getKey();
            }

            @Override
            public void remove() {
                if (HashMap.this.remove(this.curEntry.key) != null)
                    HashMap.this.modCount--;
            }
        }
    }

    private class ValuesCollection extends AbstractCollection<V> {
        private long modCount = HashMap.this.modCount;

        @Override
        public Iterator<V> iterator() {
            return this.new ValuesIterator();
        }

        @Override
        public int size() {
            return HashMap.this.size;
        }

        private class ValuesIterator implements Iterator<V> {
            private LinkedList<Entry<K, V>> lList = new LinkedList<>();
            private Entry<K, V> curEntry;

            {
                for (LinkedList<Entry<K, V>> lList : HashMap.this.data)
                    this.lList.addAll(lList);
            }

            @Override
            public boolean hasNext() {
                return !this.lList.isEmpty();
            }

            @Override
            public V next() {
                if (ValuesCollection.this.modCount != HashMap.this.modCount)
                    throw new ConcurrentModificationException();
                this.curEntry = this.lList.pollFirst();
                return curEntry.getValue();
            }

            @Override
            public void remove() {
                if (HashMap.this.remove(this.curEntry.key) != null)
                    HashMap.this.modCount--;
            }
        }
    }

}



