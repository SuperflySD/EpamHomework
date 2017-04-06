package LinkedListImpl;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static org.junit.Assert.*;

public class LinkedListTest {
    LinkedList<Integer> linkedList = new LinkedList<>();

    {
        for (int i = 0; i < 5; i++) {
            linkedList.add(i);
        }
    }

    @Test
    public void size() throws Exception {
        assertTrue(linkedList.size() == 5);
    }

    @Test
    public void isEmpty() throws Exception {
        assertFalse(linkedList.isEmpty());
        linkedList.clear();
        //assertTrue(linkedList.isEmpty());
    }

    @Test
    public void contains() throws Exception {
        assertTrue(linkedList.contains(0));
        assertTrue(linkedList.contains(4));
        assertFalse(linkedList.contains(5));
    }

    @Test
    public void iterator() throws Exception {
        Iterator<Integer> iterator = linkedList.iterator();
        for (int i = 0; iterator.hasNext(); i++)
            assertTrue(iterator.next().equals(i));
        int j = 0;
        for (int i : linkedList)
            assertTrue(i == j++);
    }

    @Test
    public void toArrayObjects() throws Exception {
        Object[] arr = linkedList.toArray();
        int j = 0;
        for (int i : linkedList)
            assertTrue(arr[j++].equals(i));
    }

    @Test
    public void toArrayWithType() throws Exception {
        Integer[] arr = new Integer[7];
        arr = linkedList.toArray(arr);
        int j = 0;
        for (int i : linkedList)
            assertTrue(arr[j++].equals(i));
    }

    @Test
    public void add() throws Exception {
        linkedList.add(7);
        assertTrue(linkedList.contains(7));
    }

    @Test
    public void remove() throws Exception {
        linkedList.add(7);
        linkedList.add(8);
        assertTrue(linkedList.contains(7));
        assertTrue(linkedList.remove((Object) 7));
        assertFalse(linkedList.contains(7));
        assertTrue(linkedList.contains(8));
    }

    @Test
    public void containsAll() throws Exception {
        List<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(3);
        assertTrue(linkedList.containsAll(list));
        list.add(9);
        assertFalse(linkedList.containsAll(list));
    }

    @Test
    public void addAll() throws Exception {
        List<Integer> list = new ArrayList<>();
        list.add(5);
        list.add(6);
        linkedList.addAll(list);
        assertTrue(linkedList.containsAll(list));
    }

    @Test
    public void addAllWithIndex() throws Exception {
        List<Integer> list = new ArrayList<>();
        list.add(6);
        list.add(7);
        linkedList.addAll(1,list);
        assertTrue(linkedList.contains(0));
        assertTrue(linkedList.containsAll(list));
        assertTrue(linkedList.contains(4));


    }

    @Test
    public void removeAll() throws Exception {
    }

    @Test
    public void retainAll() throws Exception {
    }

    @Test
    public void clear() throws Exception {
    }

    @Test
    public void get() throws Exception {
    }

    @Test
    public void set() throws Exception {
    }

    @Test
    public void add1() throws Exception {
    }

    @Test
    public void remove1() throws Exception {
    }

    @Test
    public void indexOf() throws Exception {
    }

    @Test
    public void lastIndexOf() throws Exception {
    }

    @Test
    public void listIterator() throws Exception {
    }

    @Test
    public void listIterator1() throws Exception {
    }

    @Test
    public void subList() throws Exception {
    }

}