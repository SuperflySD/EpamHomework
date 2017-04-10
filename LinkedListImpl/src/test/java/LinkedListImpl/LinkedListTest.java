package LinkedListImpl;

import org.junit.Test;

import java.util.*;

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
        assertTrue(linkedList.isEmpty());
    }

    @Test
    public void contains() throws Exception {
        assertTrue(linkedList.contains(0));
        assertTrue(linkedList.contains(4));
        assertFalse(linkedList.contains(5));
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
    public void addWithIndex() throws Exception {
        linkedList.add(1, 77);
        assertEquals((int) linkedList.get(0), 0);
        assertEquals((int) linkedList.get(1), 77);
        assertEquals((int) linkedList.get(2), 1);
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
        linkedList.addAll(1, list);
        assertEquals((int) linkedList.get(0), 0);
        assertEquals((int) linkedList.get(1), 6);
        assertEquals((int) linkedList.get(3), 1);

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
    public void removeWithIndex() throws Exception {
        assertEquals((int) linkedList.remove(1), 1);
        assertEquals((int) linkedList.get(0), 0);
        assertEquals((int) linkedList.get(1), 2);
    }

    @Test(expected = ClassCastException.class)
    public void removeWithWrongType() throws Exception {
        linkedList.remove("str");
    }

    @Test
    public void removeAll() throws Exception {
        assertTrue(linkedList.removeAll(Arrays.asList(2, 3)));
        assertTrue(linkedList.contains(4));
        assertFalse(linkedList.contains(2));
    }

    @Test
    public void retainAll() throws Exception {
        assertFalse(linkedList.retainAll(Arrays.asList(0, 1, 2, 3, 4, 5, 6, 7, 8, 9)));
        assertTrue(linkedList.retainAll(Arrays.asList(0, 1, 9, 8, 7)));
        assertTrue(linkedList.contains(1));
        assertFalse(linkedList.contains(2));
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
    public void clear() throws Exception {
        linkedList.clear();
        assertFalse(linkedList.contains(0));
        assertEquals(linkedList.size(), 0);
    }

    @Test
    public void get() throws Exception {
        int i = 0;
        for (int val : linkedList) {
            assertEquals((int) linkedList.get(i), val);
            i++;
        }
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void getWithWrongIndex() throws Exception {
        linkedList.get(5);
    }

    @Test
    public void set() throws Exception {
        assertEquals((int) linkedList.set(0, 55), 0);
        assertEquals((int) linkedList.get(0), 55);
    }

    @Test
    public void indexOf() throws Exception {
        assertEquals(linkedList.indexOf(1), 1);
        assertEquals(linkedList.indexOf(99), -1);
        assertEquals(linkedList.indexOf("str"), -1);
        assertEquals(linkedList.indexOf(null), -1);
    }

    @Test
    public void lastIndexOf() throws Exception {
        linkedList.add(0, 4);
        assertEquals((int) linkedList.lastIndexOf(4), 5);
        linkedList.remove(5);
        assertEquals((int) linkedList.lastIndexOf(4), 0);
        linkedList.remove(0);
        assertEquals((int) linkedList.lastIndexOf(4), -1);
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
    public void iteratorRemove() throws Exception {
        Iterator<Integer> iterator = linkedList.iterator();
        iterator.next();
        iterator.next();
        iterator.remove();
        iterator.next();
        assertEquals((int) linkedList.get(0), 0);
        assertEquals((int) linkedList.get(1), 2);
    }

    @Test(expected = ConcurrentModificationException.class)
    public void removeWhileIteration() throws Exception {
        for (int val : linkedList)
            linkedList.remove(2);

    }

    @Test
    public void listIterator() throws Exception {
        ListIterator<Integer> listIterator = linkedList.listIterator();
        for (int i = 0; listIterator.hasNext(); i++)
            assertEquals((int) listIterator.next(), i);
        for (int i = 3; listIterator.hasPrevious(); i--)
            assertEquals((int) listIterator.previous(), i);
    }

    @Test
    public void listIteratorRemove() throws Exception {
        ListIterator<Integer> listIterator = linkedList.listIterator();
        listIterator.next();
        listIterator.next();
        listIterator.remove();
        listIterator.next();
        assertEquals((int) linkedList.get(0), 0);
        assertEquals((int) linkedList.get(1), 2);
    }

    @Test
    public void listIteratorSet() throws Exception {
        ListIterator<Integer> listIterator = linkedList.listIterator();
        listIterator.next();
        listIterator.next();
        listIterator.set(77);
        assertEquals((int) linkedList.get(0), 0);
        assertEquals((int) linkedList.get(1), 77);
    }

    @Test
    public void listIteratorAdd() throws Exception {
        ListIterator<Integer> listIterator = linkedList.listIterator();
        listIterator.next();
        listIterator.add(88);
        assertEquals((int) linkedList.get(0), 0);
        assertEquals((int) linkedList.get(1), 88);
    }

    @Test
    public void listIteratorWithIndex() throws Exception {
        ListIterator<Integer> listIterator = linkedList.listIterator(1);
        assertEquals((int) listIterator.next(), 2);
    }

    @Test
    public void subList() throws Exception {
        List<Integer> subList = linkedList.subList(1, 3);
        assertEquals((int) subList.get(0), 1);
        assertEquals((int) subList.get(1), 2);
        assertEquals(subList.size(), 2);
        subList.set(0, 77);
        assertEquals((int) linkedList.get(1), 77);
        linkedList.set(1, 99);
        assertEquals((int) subList.get(0), 99);
    }

    @Test
    public void subListIteratorDoesntGoToMainList() throws Exception {
        List<Integer> subList = linkedList.subList(1, 3);
        int i = 0;
        for (int val : subList)
            i++;
        assertEquals(i, 2);
    }

}