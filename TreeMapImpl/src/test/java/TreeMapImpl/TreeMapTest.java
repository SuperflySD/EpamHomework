package TreeMapImpl;

import org.junit.Test;

import java.util.*;

import static org.junit.Assert.*;

public class TreeMapTest {
    TreeMap<Integer, String> treeMap = new TreeMap<>();

    @Test
    public void size() throws Exception {
        Random rnd = new Random();
        int counter = 0;
        for (int i = 0; i < 10000; i++) {
            Integer rn = rnd.nextInt(20000);
            if (treeMap.put(rn, rn + "value") == null) {
                counter++;
                assertEquals(treeMap.size(), counter);
            }
            if (rn % 2 == 0)
                if (treeMap.remove(rn) != null) {
                    counter--;
                    assertEquals(treeMap.size(), counter);
                }
        }
    }

    @Test
    public void isEmpty() throws Exception {
        assertTrue(treeMap.isEmpty());
        treeMap.put(44, "");
        assertFalse(treeMap.isEmpty());
    }

    @Test
    public void containsKey() throws Exception {
        Random rnd = new Random();
        for (int i = 0; i < 1000; i++) {
            Integer rn = rnd.nextInt(2000);
            treeMap.put(rn, "");
            assertTrue(treeMap.containsKey(rn));
        }
    }
    @Test(expected = ClassCastException.class)
    public void containsWrongKey() throws Exception {
            treeMap.put(1, "");
            assertTrue(treeMap.containsKey(1));
            treeMap.containsKey("s");
    }

    @Test
    public void containsValue() throws Exception {
        Random rnd = new Random();
        for (int i = 0; i < 1000; i++) {
            Integer rn = rnd.nextInt(2000);
            treeMap.put(rn, "s" + i);
            assertTrue("" + i, treeMap.containsValue("s" + i));
            assertFalse("" + i, treeMap.containsValue("s" + i + 1));
        }
    }

    @Test
    public void iterator() throws Exception {
        Random rnd = new Random();
        java.util.TreeMap<Integer, String> standardMap = new java.util.TreeMap<>();
        standardMap.put(5000, "");
        for (int i = 0; i < 10000; i++) {
            int rn = rnd.nextInt(100000);
            standardMap.put(rn, "");
        }
        treeMap.putAll(standardMap);
        Iterator<Map.Entry<Integer, String>> iterator = treeMap.entrySet().iterator();
        Iterator<Map.Entry<Integer, String>> standardSetIterator = standardMap.entrySet().iterator();
        int sum = 0;
        Map.Entry<Integer, String> entry;
        for (; standardSetIterator.hasNext(); ) {
            entry = iterator.next();
            sum += entry.getKey();
            assertEquals(entry, standardSetIterator.next());
        }
        assertEquals(sum, standardMap.entrySet().stream().mapToInt(x -> x.getKey()).sum());
    }

    @Test
    public void get() throws Exception {
        Random rnd = new Random();
        Set<Integer> standardSet = new HashSet<>();
        standardSet.add(5000);
        treeMap.put(5000, "s" + 5000);
        for (int i = 0; i < 10000; i++) {
            int rn = rnd.nextInt(10000);
            standardSet.add(rn);
            treeMap.put(rn, "s" + rn);
        }
        for (Integer i : standardSet)
            assertEquals(treeMap.get(i), "s" + i);
    }

    @Test
    public void put() throws Exception {
        Random rnd = new Random();
        Set<Integer> standardSet = new HashSet<>();
        standardSet.add(5000);
        treeMap.put(5000, "");
        for (int i = 0; i < 10000; i++) {
            int rn = rnd.nextInt(10000);
            standardSet.add(rn);
            treeMap.put(rn, "");
        }
        for (Integer i : standardSet)
            assertTrue(treeMap.containsKey(i));
    }

    @Test(expected = NullPointerException.class)
    public void putNull() throws Exception {
        treeMap.put(null, null);
    }

    @Test
    public void putExistingValue() throws Exception {
        for (int i = 0; i < 1000; i++) {
            treeMap.put(i, "s" + i);
            if (i % 2 == 0)
                assertEquals(treeMap.put(i, "ss"), "s" + i);
        }
    }

    @Test
    public void remove() throws Exception {
        Random rnd = new Random();
        Set<Integer> standardSet = new HashSet<>();
        ArrayList<Integer> list = new ArrayList<>();
        for (int i = 0; i < 10000; i++) {
            int rn = rnd.nextInt(10000);
            if (!standardSet.contains(rn))
                list.add(rn);
            standardSet.add(rn);
            treeMap.put(rn, "");
        }
        for (int i = 0; i < 100; i++)
            treeMap.remove(list.get(i));

        for (int i = 0; i < list.size(); i++) {
            if (i < 100)
                assertFalse(treeMap.containsKey(list.get(i)));
            else
                assertTrue(treeMap.containsKey(list.get(i)));
        }
    }

    @Test
    public void removeFromSpeciallyConstructedMap() throws Exception {
        treeMap.put(20, "");
        treeMap.put(15, "");
        treeMap.put(25, "");
        treeMap.put(22, "");
        treeMap.put(23, "");
        treeMap.put(21, "");
        treeMap.remove(22);
        assertFalse(treeMap.containsKey(22));
        assertTrue(treeMap.containsKey(20));
        assertTrue(treeMap.containsKey(21));
    }

    @Test
    public void putAll() throws Exception {
        Random rnd = new Random();
        Map<Integer, String> standardMap = new HashMap<>();
        for (int i = 0; i < 10000; i++) {
            int rn = rnd.nextInt(10000);
            standardMap.put(rn, "s" + i);
        }
        treeMap.putAll(standardMap);
        for (Integer i : standardMap.keySet())
            assertTrue(treeMap.containsKey(i));
    }


    @Test
    public void equals() throws Exception {
        TreeMap<Integer, String> treeMap1 = new TreeMap<>();
        Random rnd = new Random();
        for (int i = 0; i < 10000; i++) {
            Integer rn = rnd.nextInt(70);
            treeMap.put(rn, "" + rn);
            treeMap1.put(rn, "" + rn);
            assertEquals(treeMap, treeMap1);
        }
        treeMap.put(1, "s");
        assertNotEquals(treeMap, treeMap1);
    }

    @Test
    public void hashCodeTest() throws Exception {
        treeMap.put(1, "");
        TreeMap<Integer, String> treeMap1 = new TreeMap<>();
        treeMap1.put(1, "");
        treeMap1.put(2, "aaaa");

        assertNotEquals(treeMap.hashCode(), treeMap1.hashCode());
        treeMap1.remove(2);
        assertEquals(treeMap.hashCode(), treeMap1.hashCode());
    }

    @Test
    public void clear() throws Exception {
        Random rnd = new Random();
        for (int i = 0; i < 10000; i++)
            treeMap.put(rnd.nextInt(70), "");
        treeMap.put(50, "");
        assertTrue(treeMap.containsKey(50));
        treeMap.clear();
        assertFalse(treeMap.containsKey(50));
    }

    @Test
    public void keySetIterator() throws Exception {
        for (int i = 0; i < 10000; i++)
            treeMap.put(i, "str" + i);
        Iterator<Integer> iterator = treeMap.keySet().iterator();
        for (int i = 0; iterator.hasNext(); i++)
            assertEquals((int) iterator.next(), i);
    }


    @Test
    public void valuesCollection() throws Exception {
        for (int i = 0; i < 10000; i++)
            treeMap.put(i, "str" + i);
        Iterator<String> iterator = treeMap.values().iterator();
        for (int i = 0; iterator.hasNext(); i++)
            assertEquals(iterator.next(), "str"+i);
    }
    @Test
    public void entrySetContainsKeysOfStandardSet() throws Exception {
        Random rnd = new Random();
        Map<Integer, String> standardMap = new HashMap<>();
        for (int i = 0; i < 10000; i++) {
            int t = rnd.nextInt(20000);
            standardMap.put(t, "" + i);
            treeMap.put(t, "" + i);
        }
        for (Map.Entry entry : treeMap.entrySet())
            assertTrue(standardMap.containsKey(entry.getKey()));
        for (Map.Entry entry : standardMap.entrySet())
            assertTrue(treeMap.containsKey(entry.getKey()));
    }

    @Test
    public void EntrySetIterator() throws Exception {
        for (int i = 0; i < 10000; i++)
            treeMap.put(i, "str" + i);
        Iterator<Map.Entry<Integer, String>> iterator = treeMap.entrySet().iterator();
        for (int i = 0; iterator.hasNext(); i++)
            assertEquals((int) iterator.next().getKey(), i);
    }
    @Test(expected = ConcurrentModificationException.class)
    public void addingWhileIteration() throws Exception {
        for (int i = 0; i < 10000; i++)
            treeMap.put(i, "str" + i);
        for (Map.Entry<Integer, String> entry : treeMap.entrySet())
            treeMap.put(5, "5");
    }

    @Test(expected = ConcurrentModificationException.class)
    public void removingWhileIteration() throws Exception {
        for (int i = 0; i < 10000; i++)
            treeMap.put(i, "str" + i);
        for (Map.Entry<Integer, String> entry : treeMap.entrySet())
            treeMap.remove(5);
    }

    @Test
    public void removeWithIterator() throws Exception {
        Random rnd = new Random();
        Map<Integer, String> standardMap = new java.util.TreeMap<>();
        for (int i = 0; i < 10000; i++) {
            int t = rnd.nextInt(20000);
            standardMap.put(t, "" + i);
            treeMap.put(t, "" + i);
        }
        Iterator<Map.Entry<Integer, String>> iterator = treeMap.entrySet().iterator();
        for (; iterator.hasNext(); ) {
            Map.Entry<Integer, String> entry = iterator.next();
            if (entry.getKey() % 7 == 0) {
                iterator.remove();
                assertFalse(treeMap.containsKey(entry.getKey()));
            }
        }
        Iterator<Map.Entry<Integer, String>> standardIterator = standardMap.entrySet().iterator();
        for (; standardIterator.hasNext(); ) {
            Map.Entry<Integer, String> stEntry = standardIterator.next();
            if (stEntry.getKey() % 7 == 0)
                assertFalse(treeMap.containsKey(stEntry.getKey()));
        }

    }
}

