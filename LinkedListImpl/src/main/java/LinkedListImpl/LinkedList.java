package LinkedListImpl;

import java.util.*;

public class LinkedList<T> implements List<T> {
    private final Node<T> head = new Node<>(null, null, null);
    private int size = 0;

    {
        this.head.next = head;
        this.head.previous = head;
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
        for (T i : this)
            if (i.equals(o))
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
    public Iterator<T> iterator() {
        return new LIterator<>();
    }

    @Override
    public Object[] toArray() {
        Object[] arr = new Object[size];
        int j = 0;
        for (T i : this)
            arr[j++] = i;
        return arr;
    }

    @Override
    public <T> T[] toArray(T[] a) {
        if (a == null)
            throw new NullPointerException("Input array can't be null");
        Iterator<T> iterator = new LIterator<>();
        for (int i = 0; i < Math.min(this.size, a.length); i++)
            a[i] = iterator.next();
        return a;
    }

    @Override
    public boolean add(T value) {
        Node<T> temp = this.head.previous;
        this.head.previous = new Node<>(value, head, head.previous);
        temp.next = head.previous;
        this.size++;
        return true;
    }

    @Override
    public void add(int index, T value) {
        if (index < 0 || index > this.size - 1)
            throw new IndexOutOfBoundsException("Suggesting index is more than number of elements in the linked list or less than zero");

        Node<T> curNode = this.findByIndex(index);
        Node<T> temp = curNode.previous;
        curNode.previous = new Node<>(value, curNode, curNode.previous);
        temp.next = curNode.previous;
        this.size++;
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

        Node<T> curNode = this.findByIndex(index);
        for (T value : c) {
            Node<T> temp = curNode.previous;
            curNode.previous = new Node<>(value, curNode, curNode.previous);
            temp.next = curNode.previous;
            this.size++;
        }
        return true;
    }

    @Override
    public boolean remove(Object o) {
        if (this.head.next == this.head)
            return false;
        if (!(o.getClass().isInstance(this.head.next.value)))
            throw new ClassCastException("Passing parameter type doesn't correspond to the parametrized type of the collection");
        Node<T> curNode = this.head.next;
        while (curNode != head) {
            if (curNode.value.equals(o)) {
                curNode.previous.next = curNode.next;
                curNode.next.previous = curNode.previous;
                this.size--;
                return true;
            }
            curNode = curNode.next;
        }
        return false;
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
    public T remove(int index) {
        Node<T> curNode = this.findByIndex(index);
        curNode.previous.next = curNode.next;
        curNode.next.previous = curNode.previous;
        this.size--;
        return curNode.value;
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        if (c == null)
            throw new NullPointerException("Input collection can't be null");
        boolean flag = false;
        for (T val : this)
            if (!(c.contains(val))) {
                this.remove(val);
                flag = true;
            }
        return flag;
    }

    @Override
    public void clear() {
        for (T val : this)
            this.remove(val);
        this.size = 0;
    }

    @Override
    public T get(int index) {
        if (index < 0 || index > this.size - 1)
            throw new IndexOutOfBoundsException("Suggesting index is more than number of elements in the list or less than zero");

        Node<T> curNode = this.findByIndex(index);
        return curNode.value;
    }

    @Override
    public T set(int index, T value) {
        if (index < 0 || index > this.size - 1)
            throw new IndexOutOfBoundsException("Suggesting index is more than number of elements in the list or less than zero");

        Node<T> curNode = this.findByIndex(index);
        T temp = curNode.value;
        curNode.value = value;
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
        if (this.size == 0)
            return -1;
        Node<T> curNode = this.head.previous;
        int i = this.size - 1;
        while (curNode != this.head) {
            if (curNode.value.equals(o))
                return i;
            curNode = curNode.previous;
            i--;
        }
        return -1;
    }

    @Override
    public ListIterator<T> listIterator() {
        return new LListIterator<>();
    }

    @Override
    public ListIterator<T> listIterator(int index) {
        return new LListIterator<>(index);
    }

    @Override
    public List<T> subList(int fromIndex, int toIndex) {
        Node<T> start = this.findByIndex(fromIndex);
        Node<T> end = this.findByIndex(toIndex);
        LinkedList<T> newLList = new LinkedList<>();
        while (start != end) {
            newLList.addNode(start);
            start = start.next;
        }
        return newLList;
    }

    private Node<T> findByIndex(int index) {
        Node<T> curNode = this.head.next;
        int i = 0;
        while (i != index) {
            curNode = curNode.next;
            i++;
        }
        return curNode;
    }

    private void addNode(Node<T> node) {
        Node<T> temp = this.head.previous;
        this.head.previous = node;
        temp.next = head.previous;
        this.size++;
    }

    private class Node<T> {
        private T value;
        private Node<T> next;
        private Node<T> previous;

        Node(T value, Node<T> next, Node<T> previous) {
            this.value = value;
            this.next = next;
            this.previous = previous;
        }
    }

    public class LIterator<T> implements Iterator<T> {
        Node<T> curNode = (Node<T>) LinkedList.this.head;
        private int curPosition = -1;

        @Override
        public boolean hasNext() {
            return this.curPosition < LinkedList.this.size - 1 && LinkedList.this.size != 0;
        }

        @Override
        public T next() {
            if (this.curPosition + 1 >= LinkedList.this.size)
                throw new IndexOutOfBoundsException("Next call tried to access to a nonexistent element");
            curNode = curNode.next;
            curPosition++;
            return curNode.value;
        }

        @Override
        public void remove() {
            if (curNode == LinkedList.this.head)
                throw new IllegalStateException("You better call next before deleting");
            LinkedList.this.remove(curPosition);

        }
    }

    public class LListIterator<E extends T> implements ListIterator<T> {
        Node<T> curNode = (Node<T>) LinkedList.this.head;
        int curPosition = -1;

        @Override
        public boolean hasNext() {
            return this.curPosition < LinkedList.this.size - 1 && LinkedList.this.size != 0;
        }

        @Override
        public T next() {
            if (this.curPosition + 1 >= LinkedList.this.size)
                throw new IndexOutOfBoundsException("Next call tried to access to a nonexistent element");
            curNode = curNode.next;
            curPosition++;
            return curNode.value;
        }

        @Override
        public boolean hasPrevious() {
            return this.curPosition > 0 && LinkedList.this.size != 0;
        }

        @Override
        public T previous() {
            if (this.curPosition - 1 < 0)
                throw new IndexOutOfBoundsException("Previous call tried to access to a nonexistent element");
            curNode = curNode.previous;
            curPosition--;
            return curNode.value;
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
            if (curNode == LinkedList.this.head)
                throw new IllegalStateException("You better call next before deleting");
            LinkedList.this.remove(curPosition);
        }

        @Override
        public void set(T value) {
            if (curNode == LinkedList.this.head)
                throw new IllegalStateException("You better call next before setting");
            curNode.value = value;
        }

        @Override
        public void add(T value) {
            LinkedList.this.add(curPosition + 1, value);
        }

        public LListIterator() {
        }

        public LListIterator(int index) {
            if (index < 0 || index > LinkedList.this.size - 1)
                throw new IndexOutOfBoundsException("Suggesting index is more than number of elements in the linked list or less than zero");
            this.curNode = LinkedList.this.findByIndex(index);
            this.curPosition = index;
        }


    }

}
