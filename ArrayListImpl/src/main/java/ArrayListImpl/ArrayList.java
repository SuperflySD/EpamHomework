package ArrayListImpl;

import java.util.*;


public class ArrayList<T> implements List<T> {
    private int capacity = 10;
    private T[] data = (T[]) new Object[capacity];
    private int size = 0;
    private long modCount = Long.MIN_VALUE;

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
        for (int i = 0; i < this.size; i++)
            if (this.data[i].equals(o))
                return true;
        return false;
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        if (c == null)
            throw new NullPointerException("Input collection can't be null");
        for (Object i : c)
            if (!(this.contains(i)))
                return false;
        return true;
    }

    @Override
    public Object[] toArray() {
        return Arrays.copyOf(this.data, this.size);
    }

    @Override
    public <T> T[] toArray(T[] a) {
        if (a == null)
            throw new NullPointerException("Input array can't be null");

        for (int i = 0; i < Math.min(this.size, a.length); i++)
            a[i] = (T) this.data[i];
        return a;
    }

    @Override
    public boolean add(T value) {
        this.checkAndChangeCapacity(1);

        this.data[this.size] = value;
        size++;
        this.modCount += 1L;
        return true;
    }

    @Override
    public void add(int index, T element) {
        this.checkAndChangeCapacity(1);

        System.arraycopy(this.data, index, this.data, index + 1, this.size - index);
        this.data[index] = element;
        this.size++;
        this.modCount += 1L;
    }

    @Override
    public boolean addAll(Collection<? extends T> c) {
        if (c == null)
            throw new NullPointerException("Input collection can't be null");
        for (T i : c)
            this.add(i);

        return false;
    }

    @Override
    public boolean addAll(int index, Collection<? extends T> c) {
        if (c == null)
            throw new NullPointerException("Input collection can't be null");
        if (index > this.size || index < 0)
            throw new IndexOutOfBoundsException("Suggesting index is more than number of elements in the linked list or less than zero");

        this.checkAndChangeCapacity(c.size());
        System.arraycopy(this.data, index, this.data, index + c.size(), this.size - index);
        int i = index;
        for (T val : c)
            this.data[i++] = val;
        this.modCount += 1L;
        return true;
    }

    @Override
    public boolean remove(Object o) {
        if (this.size == 0)
            return false;
        if (!(o.getClass().isInstance(this.data[0])))
            throw new ClassCastException("Passing parameter type doesn't correspond to the parametrized type of the list");

        for (int i = 0; i < this.size; i++)
            if (this.data[i].equals(o)) {
                System.arraycopy(this.data, i + 1, this.data, i, this.size - i);
                this.size--;
                this.modCount += 1L;
                return true;
            }
        return false;
    }

    @Override
    public T remove(int index) {
        if (index < 0 || index > this.size - 1)
            throw new IndexOutOfBoundsException("Suggesting index is more than number of elements in the list or less than zero");

        T temp = this.data[index];
        System.arraycopy(this.data, index + 1, this.data, index, this.size - index);
        this.size--;
        this.modCount += 1L;
        return temp;
    }


    @Override
    public boolean removeAll(Collection<?> c) {
        if (c == null)
            throw new NullPointerException("Input collection can't be null");
        boolean flag = false;
        for (Object o : c)
            if (this.remove(o))
                flag = true;
        return flag;
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        if (c == null)
            throw new NullPointerException("Input collection can't be null");
        boolean flag = false;
        for (int i=0; i<this.size; i++)
            if (!(c.contains(this.data[i]))) {
                this.remove(this.data[i]);
                flag = true;
            }
        return flag;
    }

    @Override
    public void clear() {
        this.size = 0;
        this.data = (T[]) new Object[10];
        this.modCount += 1L;
    }

    public void trimToSize() {
        this.data = Arrays.copyOf(this.data, this.size);
    }

    @Override
    public T get(int index) {
        if (index < 0 || index > this.size - 1)
            throw new IndexOutOfBoundsException("Suggesting index is more than number of elements in the list or less than zero");

        return this.data[index];
    }

    @Override
    public T set(int index, T element) {
        if (index < 0 || index > this.size - 1)
            throw new IndexOutOfBoundsException("Suggesting index is more than number of elements in the list or less than zero");
        T temp = this.data[index];
        this.data[index] = element;
        return temp;
    }

    @Override
    public int indexOf(Object o) {
        int i = 0;
        for (T val : this) {
            if (val.equals(o))
                return i;
            i++;
        }
        return -1;
    }

    @Override
    public int lastIndexOf(Object o) {
        for (int i = this.size - 1; i >= 0; i--)
            if (this.data[i].equals(o))
                return i;

        return -1;
    }

    @Override
    public Iterator<T> iterator() {
        return new ALIterator<>();
    }

    @Override
    public ListIterator<T> listIterator() {
        return new AListIterator<>();
    }

    @Override
    public ListIterator<T> listIterator(int index) {
        return new AListIterator<>(index);
    }

    @Override
    public List<T> subList(int fromIndex, int toIndex) {
        return new SubList(fromIndex, toIndex);
    }

    private void checkAndChangeCapacity(int numAdd) {
        if ((this.size + numAdd) * 100 / this.capacity > 80) {
            this.capacity = (this.capacity + numAdd) * 3 / 2;
            this.data = Arrays.copyOf(this.data, this.capacity);
        }
    }

    public class ALIterator<E extends T> implements Iterator<T> {
        private int curPosition = -1;
        private long modCount = ArrayList.this.modCount;


        @Override
        public boolean hasNext() {
            return this.curPosition < ArrayList.this.size - 1 && ArrayList.this.size != 0;
        }

        @Override
        public T next() {
            if (this.curPosition + 1 >= ArrayList.this.size)
                throw new IndexOutOfBoundsException("Next call tried to access to a nonexistent element");
            if (this.modCount != ArrayList.this.modCount)
                throw new ConcurrentModificationException("Collection has been concurrently modified not via iterator");

            this.curPosition++;
            return ArrayList.this.data[this.curPosition];
        }

        @Override
        public void remove() {
            if (this.curPosition < 0)
                throw new IllegalStateException("You better call next before deleting");

            ArrayList.this.remove(curPosition);
        }
    }

    public class AListIterator<E extends T> implements ListIterator<T> {
        int curPosition = -1;
        private long modCount = ArrayList.this.modCount;

        @Override
        public boolean hasNext() {
            return this.curPosition < ArrayList.this.size - 1 && ArrayList.this.size != 0;
        }

        @Override
        public T next() {
            if (this.curPosition + 1 >= ArrayList.this.size)
                throw new IndexOutOfBoundsException("Next call tried to access to a nonexistent element");
            if (this.modCount != ArrayList.this.modCount)
                throw new ConcurrentModificationException("Collection has been concurrently modified not via iterator");

            this.curPosition++;
            return ArrayList.this.data[this.curPosition];
        }

        @Override
        public boolean hasPrevious() {
            return this.curPosition > 0 && ArrayList.this.size != 0;
        }

        @Override
        public T previous() {
            if (this.curPosition - 1 < 0)
                throw new IndexOutOfBoundsException("Previous call tried to access to a nonexistent element");
            if (this.modCount!=ArrayList.this.modCount)
                throw new ConcurrentModificationException("Collection has been concurrently modified not via iterator");

            this.curPosition--;
            return ArrayList.this.data[this.curPosition];
        }

        @Override
        public int nextIndex() {
            return this.curPosition + 1;
        }

        @Override
        public int previousIndex() {
            return (this.curPosition == 0 || this.curPosition == -1) ? -1 : curPosition - 1;
        }

        @Override
        public void remove() {
            if (this.curPosition < 0)
                throw new IllegalStateException("You better call next before deleting");

            ArrayList.this.remove(curPosition);
        }

        @Override
        public void set(T value) {
            if (this.curPosition < 0)
                throw new IllegalStateException("You better call next before setting");
            ArrayList.this.data[this.curPosition] = value;
        }

        @Override
        public void add(T value) {
            ArrayList.this.add(curPosition + 1, value);
        }

        public AListIterator() {
        }

        public AListIterator(int index) {
            if (index < 0 || index > ArrayList.this.size - 1)
                throw new IndexOutOfBoundsException("Suggesting index is more than number of elements in the linked list or less than zero");

            this.curPosition = index;
        }
    }

    public class SubList<E extends T> extends ArrayList<T> {
        private int size;
        private int start;
        private int end;
        private long modCount = ArrayList.this.modCount;


        private SubList(int start, int end) {
            this.size = end - start;
            this.start = start;
            this.end = end;
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
        public boolean contains(Object o) {
            for (int i = start; i < this.end; i++)
                if (ArrayList.this.data[i].equals(o))
                    return true;
            return false;
        }

        @Override
        public boolean containsAll(Collection<?> c) {
            if (c == null)
                throw new NullPointerException("Input collection can't be null");
            for (Object i : c)
                if (!(this.contains(i)))
                    return false;
            return true;
        }

        @Override
        public Object[] toArray() {
            return Arrays.copyOfRange(ArrayList.this.data, this.start, this.end);
        }

        @Override
        public <T> T[] toArray(T[] a) {
            if (a == null)
                throw new NullPointerException("Input array can't be null");

            for (int i = start; i < Math.min(this.end, a.length + start); i++)
                a[i] = (T) ArrayList.this.data[i];
            return a;
        }

        @Override
        public T get(int index) {
            if (index < 0 || index > this.size - 1)
                throw new IndexOutOfBoundsException("Suggesting index is more than number of elements in the list or less than zero");

            return ArrayList.this.data[index + start];
        }

        @Override
        public T set(int index, T element) {
            if (index < 0 || index > this.size - 1)
                throw new IndexOutOfBoundsException("Suggesting index is more than number of elements in the list or less than zero");
            T temp = ArrayList.this.data[index + start];
            ArrayList.this.data[index + start] = element;
            return temp;
        }

        @Override
        public int indexOf(Object o) {
            for (int i = this.start; i < this.end; i++)
                if (ArrayList.this.data[i].equals(o))
                    return i - this.start;

            return -1;
        }

        @Override
        public int lastIndexOf(Object o) {
            for (int i = this.end - 1; i >= this.start; i--)
                if (ArrayList.this.data[i].equals(o))
                    return i - start;

            return -1;
        }

        public Iterator<T> iterator() {
            return new Iterator() {
                int curPosition = -1;

                @Override
                public boolean hasNext() {
                    return this.curPosition < SubList.this.size - 1 && SubList.this.size != 0;
                }

                @Override
                public Object next() {
                    if (this.curPosition + 1 >= SubList.this.size)
                        throw new IndexOutOfBoundsException("Next call tried to access to a nonexistent element");
                    if (SubList.this.modCount != ArrayList.this.modCount)
                        throw new ConcurrentModificationException("Collection has been concurrently modified not via iterator");

                    this.curPosition++;
                    return ArrayList.this.data[this.curPosition + SubList.this.start];
                }
            };
        }

        @Override
        public boolean add(Object value) {
            throw new UnsupportedOperationException("Changing of sublist isn't permitted");
        }

        @Override
        public void add(int index, Object element) {
            throw new UnsupportedOperationException("Changing of sublist isn't permitted");
        }

        @Override
        public boolean addAll(Collection c) {
            throw new UnsupportedOperationException("Changing of sublist isn't permitted");
        }

        @Override
        public boolean addAll(int index, Collection c) {
            throw new UnsupportedOperationException("Changing of sublist isn't permitted");
        }

        @Override
        public boolean remove(Object o) {
            throw new UnsupportedOperationException("Changing of sublist isn't permitted");
        }

        @Override
        public T remove(int index) {
            throw new UnsupportedOperationException("Changing of sublist isn't permitted");
        }

        @Override
        public boolean removeAll(Collection c) {
            throw new UnsupportedOperationException("Changing of sublist isn't permitted");
        }

        @Override
        public boolean retainAll(Collection c) {
            throw new UnsupportedOperationException("Changing of sublist isn't permitted");
        }

        @Override
        public void clear() {
            throw new UnsupportedOperationException("Changing of sublist isn't permitted");
        }

        @Override
        public void trimToSize() {
            throw new UnsupportedOperationException("trim to size isn't permitted");
        }

        @Override
        public ListIterator<T> listIterator() {
            throw new UnsupportedOperationException("Iterator of sublist isn't permitted");
        }

        @Override
        public ListIterator<T> listIterator(int index) {
            throw new UnsupportedOperationException("List iterator of sublist isn't permitted");
        }

        @Override
        public List<T> subList(int fromIndex, int toIndex) {
            throw new UnsupportedOperationException("SubList of sublist isn't permitted");
        }
    }
}
