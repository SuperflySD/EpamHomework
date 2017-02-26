package SD.JavaCourses;
import SD.JavaCourses.BitSet;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;


public class BitSetTest {
    BitSet bs = new BitSet();
    BitSet additionalSet = new BitSet();

    @Test
    public void add() throws Exception {
        bs.add(2,4,6,7,8);

        for (int i=0;i<64;i++) {

            bs.add(i);
            assertTrue (bs.contains(i));
            assertFalse(bs.contains(i-77));

            bs.remove(i);
            assertFalse(bs.contains(i));
        }
    }

    @Test
    public void getAllValues()throws Exception {
        for (int i=-100; i<=100;i+=2) {
            bs.add(i);

        }
        int i=-102;

        for (int val: bs.getAllValues()) {
            assertEquals("" +i+"  ", i+=2, val);
            System.out.print(val + " ");
        }


    }

    @Test
    public void unite() throws Exception {
          for (int i=-11;i<1;i++)
          additionalSet.add(i);

        for (int i=-4;i<5;i++)
            bs.add(i);

        BitSet newUnion = bs.unite(additionalSet);
        System.out.println(newUnion.getAllValues());
    }

    @Test
    public void intersect() throws Exception {
        bs.add(1);
        additionalSet.add(-7,-5,6,0,8,9);
       BitSet inter  = bs.intersect(additionalSet);
        System.out.println(inter.getAllValues());
    }
    @Test
    public void differ() throws Exception {
        bs.add(-5,0,6,11);
        additionalSet.add(-7,-5,6,0,8,9);
        BitSet differ = bs.difference(additionalSet);
        System.out.println(differ.getAllValues());
    }
    @Test
    public void isSubsetOf() throws Exception {
        bs.add(-3,0,9,5,8,77);
        additionalSet.add(8,5,9,-3,0);
        assertTrue(additionalSet.isSubsetOf(bs));
        additionalSet.add(-5);
        assertFalse(additionalSet.isSubsetOf(bs));
    }


}