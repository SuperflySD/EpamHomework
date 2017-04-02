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
            if (rn % 2 == 0) {
                treeMap.remove(rn);
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

   /* @Test
    public void iterator() throws Exception {
        Random rnd = new Random();
        java.util.TreeMap<Integer, String> standardSet = new java.util.TreeMap<>();
        standardSet.put(5000, "");
        for (int i = 0; i < 10000; i++) {
            int rn = rnd.nextInt(100000);
            standardSet.put(rn,"");
        }
        treeMap.putAll(standardSet);
        Iterator<Integer, String> itr = treeMap.iterator();
        Iterator<Integer> standardSetIterator = standardSet.iterator();
        int sum = 0;
        int value;
        for (; standardSetIterator.hasNext();) {
            value = itr.next();
            sum += value;
            assertEquals(value, (int)standardSetIterator.next());
        }
        assertEquals(sum, standardSet.stream().mapToInt(x -> x).sum());
    }

    @Test
    public void iteratorOnVoidCollection() throws Exception {
        Iterator<Integer> itr = treeMap.iterator();
        assertFalse(itr.hasNext());

    }*/

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

   /* @Test
    public void removeFromSpeciallyConstructedTree() throws Exception {
        treeMap.putAll(Arrays.asList(new Integer[]{10, 3, 2, 8, 7, 5, 6}));
        treeMap.remove(8);
        assertFalse(treeMap.contains(8));
        assertTrue(treeMap.containsAll(Arrays.asList(new Integer[]{10, 3, 2, 7, 5, 6})));

    }*/


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
    public void keySet() throws Exception {

    }

    @Test
    public void values() throws Exception {

    }

    @Test
    public void entrySet() throws Exception {
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
    public void removeWithIterator() throws Exception {
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < 10000; i++) {
            if (i % 7 != 0)
                list.add(i);
        }
        Random rnd = new Random();
        while (list.size() != 0) {
            Integer rn = rnd.nextInt(10000);
            if (list.contains(rn)) {
                list.remove((Object) rn);
                treeMap.put(rn, "v" + rn);
            }
        }
    }


}

