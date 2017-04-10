package ArrayListImpl;

import org.junit.Test;

import java.util.*;

import static org.junit.Assert.*;

public class ArrayListTest {
    ArrayList<Integer> arrayList = new ArrayList<>();

    {
        for (int i = 0; i < 5; i++)
            arrayList.add(i);
    }

    @Test
    public void size() throws Exception {
        assertTrue(arrayList.size() == 5);
    }

    @Test
    public void isEmpty() throws Exception {
        assertFalse(arrayList.isEmpty());
        arrayList.clear();
        assertTrue(arrayList.isEmpty());
    }

    @Test
    public void contains() throws Exception {
        assertTrue(arrayList.contains(0));
        assertTrue(arrayList.contains(4));
        assertFalse(arrayList.contains(5));
    }

    @Test
    public void containsAll() throws Exception {
        List<Integer> list = new java.util.ArrayList<>();
        list.add(1);
        list.add(3);
        assertTrue(arrayList.containsAll(list));
        list.add(9);
        assertFalse(arrayList.containsAll(list));
    }

    @Test
    public void toArrayObjects() throws Exception {
        Object[] arr = arrayList.toArray();
        int j = 0;
        for (Object i : arr)
            assertEquals(arr[j++], i);
    }

    @Test
    public void toArrayWithType() throws Exception {
        Integer[] arr = new Integer[7];
        arr = arrayList.toArray(arr);
        for (int i = 0; i < arrayList.size(); i++)
            assertEquals(arr[i], arrayList.get(i));
    }

    @Test
    public void add() throws Exception {
        arrayList.add(7);
        assertTrue(arrayList.contains(7));
    }

    @Test
    public void checkCapacityInflation() throws Exception {
        arrayList.clear();
        for (int i = 0; i < 100; i++) {
            arrayList.add(i);
            assertTrue(arrayList.contains(i));
        }
    }

    @Test
    public void addWithIndex() throws Exception {
        arrayList.add(1, 77);
        assertEquals((int) arrayList.get(0), 0);
        assertEquals((int) arrayList.get(1), 77);
        assertEquals((int) arrayList.get(2), 1);
    }

    @Test
    public void addAll() throws Exception {
        List<Integer> list = new ArrayList<>();
        list.add(5);
        list.add(6);
        arrayList.addAll(list);
        assertTrue(arrayList.containsAll(list));
    }

    @Test
    public void addAllWithIndex() throws Exception {
        List<Integer> list = new ArrayList<>();
        list.add(6);
        list.add(7);
        arrayList.addAll(1, list);
        assertEquals((int) arrayList.get(0), 0);
        assertEquals((int) arrayList.get(1), 6);
        assertEquals((int) arrayList.get(3), 1);
    }

    @Test
    public void remove() throws Exception {
        arrayList.add(7);
        arrayList.add(8);
        assertTrue(arrayList.contains(7));
        assertTrue(arrayList.remove((Object) 7));
        assertFalse(arrayList.contains(7));
        assertTrue(arrayList.contains(8));
    }

    @Test
    public void removeWithIndex() throws Exception {
        assertEquals((int) arrayList.remove(1), 1);
        assertEquals((int) arrayList.get(0), 0);
        assertEquals((int) arrayList.get(1), 2);
    }

    @Test(expected = ClassCastException.class)
    public void removeWithWrongType() throws Exception {
        arrayList.remove("str");
    }

    @Test
    public void removeAll() throws Exception {
        assertTrue(arrayList.removeAll(Arrays.asList(2, 3)));
        assertTrue(arrayList.contains(4));
        assertFalse(arrayList.contains(2));
    }

    @Test
    public void retainAll() throws Exception {
        assertFalse(arrayList.retainAll(Arrays.asList(0, 1, 2, 3, 4, 5, 6, 7, 8, 9)));
        assertTrue(arrayList.retainAll(Arrays.asList(0, 1, 9, 8, 7)));
        assertTrue(arrayList.contains(1));
        assertFalse(arrayList.contains(2));
    }


    @Test
    public void clear() throws Exception {
        arrayList.clear();
        assertFalse(arrayList.contains(0));
        assertEquals(arrayList.size(), 0);
    }

    @Test
    public void get() throws Exception {
        int i = 0;
        for (int val : arrayList) {
            assertEquals((int) arrayList.get(i), val);
            i++;
        }
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void getWithWrongIndex() throws Exception {
        arrayList.get(5);
    }

    @Test
    public void set() throws Exception {
        assertEquals((int) arrayList.set(0, 55), 0);
        assertEquals((int) arrayList.get(0), 55);
    }

    @Test
    public void indexOf() throws Exception {
        assertEquals(arrayList.indexOf(1), 1);
        assertEquals(arrayList.indexOf(99), -1);
        assertEquals(arrayList.indexOf("str"), -1);
        assertEquals(arrayList.indexOf(null), -1);
    }

    @Test
    public void lastIndexOf() throws Exception {
        arrayList.add(0, 4);
        assertEquals(arrayList.lastIndexOf(4), 5);
        arrayList.remove(5);
        assertEquals(arrayList.lastIndexOf(4), 0);
    }

    @Test
    public void iterator() throws Exception {
        Iterator<Integer> iterator = arrayList.iterator();
        for (int i = 0; iterator.hasNext(); i++)
            assertTrue(iterator.next().equals(i));
        int j = 0;
        for (int i : arrayList)
            assertTrue(i == j++);
    }

    @Test
    public void iteratorRemove() throws Exception {
        Iterator<Integer> iterator = arrayList.iterator();
        iterator.next();
        iterator.next();
        iterator.remove();
        iterator.next();
        assertEquals((int) arrayList.get(0), 0);
        assertEquals((int) arrayList.get(1), 2);
    }

    @Test(expected = ConcurrentModificationException.class)
    public void addingWhileIteration() throws Exception {
        for (int val : arrayList)
            arrayList.add(5);
    }
    @Test(expected = ConcurrentModificationException.class)
    public void removingWhileIteration() throws Exception {
        for (int val : arrayList)
            arrayList.remove(3);
    }


    @Test
    public void listIterator() throws Exception {
        ListIterator<Integer> listIterator = arrayList.listIterator();
        for (int i = 0; listIterator.hasNext(); i++)
            assertEquals((int) listIterator.next(), i);
        for (int i = 3; listIterator.hasPrevious(); i--)
            assertEquals((int) listIterator.previous(), i);
    }

    @Test
    public void listIteratorRemove() throws Exception {
        ListIterator<Integer> listIterator = arrayList.listIterator();
        listIterator.next();
        listIterator.next();
        listIterator.remove();
        listIterator.next();
        assertEquals((int) arrayList.get(0), 0);
        assertEquals((int) arrayList.get(1), 2);
    }

    @Test
    public void listIteratorSet() throws Exception {
        ListIterator<Integer> listIterator = arrayList.listIterator();
        listIterator.next();
        listIterator.next();
        listIterator.set(77);
        assertEquals((int) arrayList.get(0), 0);
        assertEquals((int) arrayList.get(1), 77);
    }

    @Test
    public void listIteratorAdd() throws Exception {
        ListIterator<Integer> listIterator = arrayList.listIterator();
        listIterator.next();
        listIterator.add(88);
        assertEquals((int) arrayList.get(0), 0);
        assertEquals((int) arrayList.get(1), 88);
    }

    @Test
    public void listIteratorWithIndex() throws Exception {
        ListIterator<Integer> listIterator = arrayList.listIterator(1);
        assertEquals((int) listIterator.next(), 2);
    }

    @Test
    public void subList() throws Exception {

        List<Integer> subList = arrayList.subList(1, 3);
        assertEquals((int) subList.get(0), 1);
        assertEquals((int) subList.get(1), 2);
        assertEquals(subList.size(), 2);
        subList.set(0, 77);
        assertEquals((int) arrayList.get(1), 77);
        arrayList.set(1, 99);
        assertEquals((int) subList.get(0), 99);
    }

    @Test
    public void subListContains() throws Exception {
        List<Integer> subList = arrayList.subList(1, 3);
        assertTrue(subList.contains(1));
        assertFalse(subList.contains(0));
        assertFalse(subList.contains(3));
    }

    @Test
    public void subListLastIndexOf() throws Exception {
        arrayList.add(7);
        arrayList.add(1, 7);
        List<Integer> subList = arrayList.subList(1, 7);
        assertEquals(subList.lastIndexOf(7), 5);
    }

    @Test
    public void subListIteratorDoesntGoToMainList() throws Exception {
        List<Integer> subList = arrayList.subList(1, 3);
        int i = 0;
        for (int val : subList) {
            assertEquals(val, (int) arrayList.get(i + 1));
            i++;
        }
        assertEquals(i, 2);
    }

}