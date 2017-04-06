package LinkedListImpl;

import java.util.*;

public class LinkedList<T> implements List<T> {
    private Node<T> head = new Node<T>(null, null, null);
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
    public Iterator<T> iterator() {
        return new LLIterator<>();
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
        Iterator<T> iterator = new LLIterator<>();
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
    public boolean remove(Object o) {
        Node<T> curNode = this.head.next;
        while (curNode != head) {
            if (curNode.value.equals(o)) {
                curNode.previous.next = curNode.next;
                curNode.next.previous = curNode.previous;
                return true;
            }
            curNode = curNode.next;
        }
        return false;
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        for (Object i : c)
            if (!(this.contains(i)))
                return false;
        return true;
    }

    @Override
    public boolean addAll(Collection<? extends T> c) {
        for (T i : c)
            this.add(i);
        return false;
    }

    @Override
    public boolean addAll(int index, Collection<? extends T> c) {
        if (index > this.size)
            throw new IndexOutOfBoundsException("Inserting index is more than number of elements in the linked list");

        Node<T> curNode = this.head.next;
        for (int i = 0; i < index; i++)
            curNode = curNode.next;

        for (T value : c) {
            Node<T> temp = curNode.previous;
            curNode.previous = new Node<>(value, curNode, curNode.previous);
            temp.next = curNode.previous;
            this.size++;
        }
        return true;
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        return false;
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        return false;
    }

    @Override
    public void clear() {

    }

    @Override
    public T get(int index) {
        return null;
    }

    @Override
    public T set(int index, T element) {
        return null;
    }

    @Override
    public void add(int index, T element) {

    }

    @Override
    public T remove(int index) {
        return null;
    }

    @Override
    public int indexOf(Object o) {
        return 0;
    }

    @Override
    public int lastIndexOf(Object o) {
        return 0;
    }

    @Override
    public ListIterator<T> listIterator() {
        return null;
    }

    @Override
    public ListIterator<T> listIterator(int index) {
        return null;
    }

    @Override
    public List<T> subList(int fromIndex, int toIndex) {
        return null;
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

    public class LLIterator<T> implements java.util.Iterator<T> {
        Node<T> curNode = (Node<T>) LinkedList.this.head;

        @Override
        public boolean hasNext() {
            return curNode.next != LinkedList.this.head;
        }

        @Override
        public T next() {
            curNode = curNode.next;
            return curNode.value;
        }

        @Override
        public void remove() {

        }
    }

}
