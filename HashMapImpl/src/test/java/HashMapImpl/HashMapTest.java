package HashMapImpl;

import org.junit.Test;

import java.util.*;


import static org.junit.Assert.*;

public class HashMapTest {

    HashMap<Integer, String> hashMap = new HashMap<>();

    @Test
    public void size() throws Exception {
        Random rnd = new Random();
        int counter = 0;
        for (int i = 0; i < 10000; i++) {
            Integer rn = rnd.nextInt(20000);
            if (hashMap.put(rn, rn + "value") == null) {
                counter++;
                assertEquals(hashMap.size(), counter);
            }
            if (rn % 2 == 0)
                if (hashMap.remove(rn) != null) {
                    counter--;
                    assertEquals(hashMap.size(), counter);
                }
        }

    }

    @Test
    public void isEmpty() throws Exception {
        assertTrue(hashMap.isEmpty());
        hashMap.put(44, "");
        assertFalse(hashMap.isEmpty());
    }

    @Test
    public void containsKey() throws Exception {
        Random rnd = new Random();
        for (int i = 0; i < 1000; i++) {
            Integer rn = rnd.nextInt();
            if (rn % 2 == 0)
                rn = -rn;
            if (rn % 13 == 0)
                rn = null;
            hashMap.put(rn, "");
            assertTrue(hashMap.containsKey(rn));
        }
    }

    @Test
    public void cont() throws Exception {
        int b = 16;
        Random rnd = new Random();
        int[] arr = new int[b + 1];


        for (int i = -100000; i < 100000; i++) {
            int a = rnd.nextInt();
            if (a % 2 == 0)
                a = -a;
            int shift = Math.abs(Integer.numberOfLeadingZeros(b) - Integer.numberOfLeadingZeros(a));
            a = a >>> shift;
            if (a >= b)
                a = Math.abs(a - b);

            arr[a + 1]++;
        }
        System.out.println(Arrays.toString(arr));
    }

    @Test
    public void containsValue() throws Exception {
        Random rnd = new Random();
        for (int i = 0; i < 1000; i++) {
            Integer rn = rnd.nextInt(2000);
            hashMap.put(rn, "s" + i);
            assertTrue("" + i, hashMap.containsValue("s" + i));
            assertFalse("" + i, hashMap.containsValue("s" + i + 1));
        }
    }

    @Test
    public void iterator() throws Exception {//TODO something goes wrong
        Random rnd = new Random();
        TreeMap<Integer, String> standardMap = new TreeMap<>();
        standardMap.put(5000, "");
        for (int i = 0; i < 10000; i++) {
            int rn = rnd.nextInt(100000);
            standardMap.put(rn, "");
        }
        hashMap.putAll(standardMap);
        Iterator<Map.Entry<Integer, String>> iterator = hashMap.entrySet().iterator();
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
        hashMap.put(5000, "s" + 5000);
        for (int i = 0; i < 10000; i++) {
            int rn = rnd.nextInt(10000);
            standardSet.add(rn);
            hashMap.put(rn, "s" + rn);
        }
        for (Integer i : standardSet)
            assertEquals(hashMap.get(i), "s" + i);
    }


    @Test
    public void put() throws Exception {
        Random rnd = new Random();
        Set<Integer> standardSet = new HashSet<>();
        standardSet.add(5000);
        hashMap.put(5000, "");
        for (int i = 0; i < 10000; i++) {
            int rn = rnd.nextInt(10000);
            standardSet.add(rn);
            hashMap.put(rn, "");
        }
        for (Integer i : standardSet)
            assertTrue(hashMap.containsKey(i));
    }

    @Test
    public void putExistingValue() throws Exception {
        for (int i = 0; i < 1000; i++) {
            hashMap.put(i, "s" + i);
            if (i % 2 == 0)
                assertEquals(hashMap.put(i, "ss"), "s" + i);
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
            hashMap.put(rn, "");
        }
        for (int i = 0; i < 100; i++)
            hashMap.remove(list.get(i));

        for (int i = 0; i < list.size(); i++) {
            if (i < 100)
                assertFalse(hashMap.containsKey(list.get(i)));
            else
                assertTrue(hashMap.containsKey(list.get(i)));
        }
    }

    @Test
    public void removeFromSpeciallyConstructedMap() throws Exception {
        hashMap.put(20, "");
        hashMap.put(15, "");
        hashMap.put(25, "");
        hashMap.put(22, "");
        hashMap.put(23, "");
        hashMap.put(21, "");

        hashMap.remove(22);
        assertFalse(hashMap.containsKey(22));
        assertTrue(hashMap.containsKey(20));
        assertTrue(hashMap.containsKey(21));
    }


    @Test
    public void putAll() throws Exception {
        Random rnd = new Random();
        Map<Integer, String> standardMap = new java.util.HashMap<>();
        for (int i = 0; i < 10000; i++) {
            int rn = rnd.nextInt(10000);
            standardMap.put(rn, "s" + i);
        }
        hashMap.putAll(standardMap);
        for (Integer i : standardMap.keySet())
            assertTrue(hashMap.containsKey(i));
    }


    @Test
    public void equals() throws Exception {
        TreeMap<Integer, String> treeMap1 = new TreeMap<>();
        Random rnd = new Random();
        for (int i = 0; i < 10000; i++) {
            Integer rn = rnd.nextInt(70);
            hashMap.put(rn, "" + rn);
            treeMap1.put(rn, "" + rn);
            assertEquals(hashMap, treeMap1);
        }
        hashMap.put(1, "s");
        assertNotEquals(hashMap, treeMap1);
    }

    @Test
    public void hashCodeTest() throws Exception {
        hashMap.put(1, "");
        TreeMap<Integer, String> treeMap1 = new TreeMap<>();
        treeMap1.put(1, "");
        treeMap1.put(2, "aaaa");

        assertNotEquals(hashMap.hashCode(), treeMap1.hashCode());
        treeMap1.remove(2);
        assertEquals(hashMap.hashCode(), treeMap1.hashCode());
    }

    @Test
    public void clear() throws Exception {
        Random rnd = new Random();
        for (int i = 0; i < 10000; i++)
            hashMap.put(rnd.nextInt(70), "");
        hashMap.put(50, "");
        assertTrue(hashMap.containsKey(50));
        hashMap.clear();
        assertFalse(hashMap.containsKey(50));

    }

    @Test
    public void keySet() throws Exception {

    }

    @Test
    public void values() throws Exception {

    }

    @Test
    public void entrySetContainsKeysOfStandardSet() throws Exception {
        Random rnd = new Random();
        Map<Integer, String> standardMap = new HashMap<>();
        for (int i = 0; i < 10000; i++) {
            int t = rnd.nextInt(20000);
            standardMap.put(t, "" + i);
            hashMap.put(t, "" + i);
        }
        for (Map.Entry entry : hashMap.entrySet())
            assertTrue(standardMap.containsKey(entry.getKey()));
        for (Map.Entry entry : standardMap.entrySet())
            assertTrue(hashMap.containsKey(entry.getKey()));
    }

    @Test
    public void removeWithIterator() throws Exception {
        Random rnd = new Random();
        Map<Integer, String> standardMap = new TreeMap<>();
        for (int i = 0; i < 10000; i++) {
            int t = rnd.nextInt(20000);
            standardMap.put(t, "" + i);
            hashMap.put(t, "" + i);
        }
        Iterator<Map.Entry<Integer, String>> iterator = hashMap.entrySet().iterator();
        for (; iterator.hasNext(); ) {
            Map.Entry<Integer, String> entry = iterator.next();
            if (entry.getKey() % 7 == 0) {
                iterator.remove();
                assertFalse(hashMap.containsKey(entry.getKey()));
            }
        }
        Iterator<Map.Entry<Integer, String>> standardIterator = standardMap.entrySet().iterator();
        for (; standardIterator.hasNext(); ) {
            Map.Entry<Integer, String> stEntry = standardIterator.next();
            if (stEntry.getKey() % 7 == 0)
                assertFalse(hashMap.containsKey(stEntry.getKey()));
        }

    }
}

