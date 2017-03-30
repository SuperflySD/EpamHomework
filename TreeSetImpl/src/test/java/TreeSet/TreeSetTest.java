package TreeSet;

import org.junit.Test;

import java.util.*;
import java.util.stream.IntStream;

import static org.junit.Assert.*;


public class TreeSetTest {
    TreeSet<Integer> treeSet = new TreeSet<Integer>();

    @Test
    public void size() throws Exception {
        Random rnd = new Random();
        int counter = 0;
        for (int i = 0; i < 1000; i++) {
            Integer rn = rnd.nextInt(200);
            if (treeSet.add(rn)) {
                counter++;
                assertEquals(treeSet.size(), counter);
            }
            if (rn % 2 == 0) {
                treeSet.remove(rn);
                counter--;
                assertEquals(treeSet.size(), counter);
            }
        }
    }

    @Test
    public void isEmpty() throws Exception {
        assertTrue(treeSet.isEmpty());
        treeSet.add(44);
        assertFalse(treeSet.isEmpty());

    }

    @Test
    public void contains() throws Exception {
        Random rnd = new Random();
        int counter = 0;
        for (int i = 0; i < 1000; i++) {
            Integer rn = rnd.nextInt(2000);
            treeSet.add(rn);
            assertTrue(treeSet.contains(rn));
        }
    }

    @Test
    public void iterator() throws Exception {
        Random rnd = new Random();
        java.util.TreeSet<Integer> standardSet = new java.util.TreeSet<>();
        standardSet.add(5000);
        for (int i = 0; i < 10000; i++) {
            int rn = rnd.nextInt(100000);
            standardSet.add(rn);
        }
        treeSet.addAll(standardSet);
        Iterator<Integer> itr = treeSet.iterator();
        Iterator<Integer> standardSetIterator = standardSet.iterator();
        int sum = 0;
        int value;
        for (; itr.hasNext();) {
            value = itr.next();
            sum += value;
            assertEquals(value, (int) standardSetIterator.next());
        }
        assertEquals(sum, standardSet.stream().mapToInt(x -> x).sum());
    }

    @Test
    public void iteratorOnVoidCollection() throws Exception {
        Iterator<Integer> itr = treeSet.iterator();
        assertFalse(itr.hasNext());

    }

    @Test
    public void toArray() throws Exception {
        Random rnd = new Random();
        for (int i = 0; i < 1000; i++) {
            Integer rn = rnd.nextInt(1000);
            treeSet.add(rn);
        }
        Object[] arr = treeSet.toArray();
        Integer[] arr1 = treeSet.toArray(new Integer[10]);
        for (int i = 0; i < Math.min(arr.length, arr1.length); i++) {
            assertEquals(arr[i], arr1[i]);
        }
    }

    @Test
    public void toArrayObjects() throws Exception {
        Set<Integer> rndSet = new java.util.TreeSet<>();
        Random rnd = new Random();
        for (int i = 0; i < 20; i++) {
            Integer rn = rnd.nextInt(200);
            rndSet.add(rn);
            treeSet.add(rn);
        }
        Object[] arr = treeSet.toArray();
        for (int i = 0; i < arr.length; i++) {
            assertTrue(rndSet.contains(arr[i]));
        }
    }

    @Test
    public void add() throws Exception {
        treeSet.add(1);
        treeSet.add(2);
        treeSet.add(-100);
        assertTrue(treeSet.contains(1));
        assertTrue(treeSet.contains(-100));
        assertFalse(treeSet.contains(3));
    }

    @Test(expected = NullPointerException.class)
    public void addNull() throws Exception {
        treeSet.add(null);
    }

    @Test
    public void addExistingValue() throws Exception {
        for (int i = 0; i < 1000; i++) {
            assertTrue(treeSet.add(i));
            if (i % 2 == 0)
                assertFalse(treeSet.add(i));
        }
    }

    @Test
    public void remove() throws Exception {
        Random rnd = new Random();
        Set<Integer> standardSet = new java.util.HashSet<>();
        standardSet.add(5000);
        for (int i = 0; i < 10000; i++) {
            int rn = rnd.nextInt(10000);
            standardSet.add(rn);
        }
        ArrayList<Integer> list = new ArrayList<>(standardSet);
        treeSet.addAll(list);
        treeSet.removeAll(list.subList(0, 1000));
        assertTrue(treeSet.containsAll(list.subList(1000, list.size() - 1)));
        for (int i = 0; i < 1000; i++)
            assertFalse(treeSet.contains(list.get(i)));
    }

    @Test
    public void removeFromSpeciallyConstructedTree() throws Exception {
        treeSet.addAll(Arrays.asList(new Integer[]{10, 3, 2, 8, 7, 5, 6}));
        treeSet.remove(8);
        assertFalse(treeSet.contains(8));
        assertTrue(treeSet.containsAll(Arrays.asList(new Integer[]{10, 3, 2, 7, 5, 6})));

    }

    @Test
    public void containsAll() throws Exception {
        List<Integer> rndList = new ArrayList<>();
        Random rnd = new Random();
        for (int i = 0; i < 1000; i++) {
            Integer rn = rnd.nextInt(1000);
            rndList.add(rn);
            treeSet.add(rn);
            assertTrue(treeSet.containsAll(rndList));
        }
        rndList.add(0, -7);
        assertFalse(treeSet.containsAll(rndList));
    }

    @Test
    public void addAll() throws Exception {
        Random rnd = new Random();
        Set<Integer> standardSet = new java.util.HashSet<>();
        for (int i = 0; i < 10000; i++) {
            int rn = rnd.nextInt(10000);
            standardSet.add(rn);
        }
        treeSet.addAll(standardSet);
        assertTrue(treeSet.containsAll(standardSet));
    }

    @Test
    public void retainAll() throws Exception {
        Random rnd = new Random();
        for (int i = 0; i < 1000; i++) {
            Integer rn = rnd.nextInt(1000);
            treeSet.add(rn);
        }
        Object[] arr = treeSet.toArray();
        Integer[] arr1 = treeSet.toArray(new Integer[10]);
        for (int i = 0; i < Math.min(arr.length, arr1.length); i++) {
            assertEquals(arr[i], arr1[i]);
        }
    }

    @Test
    public void removeAll() throws Exception {

    }

    @Test
    public void clear() throws Exception {
        Random rnd = new Random();
        for (int i = 0; i < 10000; i++)
            treeSet.add(rnd.nextInt(70));
        treeSet.add(50);
        assertTrue(treeSet.contains(50));
        treeSet.clear();
        assertFalse(treeSet.contains(50));
    }

    @Test
    public void equals() throws Exception {
        TreeSet treeSet1 = new TreeSet();
        treeSet.add(1);
        treeSet1.add(1);

        /*;
        Random rnd = new Random();
        for (int i = 0; i < 10000; i++) {
            Integer rn = rnd.nextInt(70);
            treeSet.add(rn);
            treeSet1.add(rn);*/
        assertEquals(treeSet, treeSet1);
    }
}
