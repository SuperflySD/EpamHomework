package BitSet;

import org.junit.Test;
import static org.junit.Assert.*;


public class BitSetTest {
    BitSet bs = new BitSet();
    BitSet additionalSet = new BitSet();

    @Test
    public void add() throws Exception {
        bs.add(2,4,6,7,8);

        for (int i=-15000;i<19000;i+=65) {
           //add and contains tests
            bs.add(i);
            assertTrue (bs.contains(i));
            assertFalse(bs.contains(i-77));
            //remove test
            bs.remove(i);
            assertFalse(bs.contains(i));
        }
    }

    @Test
    public void getAllValues()throws Exception {
        for (int i=-100; i<=100;i++) {
            bs.add(i);

        }
        int i=-100;
        System.out.println(bs.getAllValues());
        for (int val: bs.getAllValues()) {
            assertEquals("" +i+"  ", i++, val);
        }

    }

    @Test
    public void union() throws Exception {
          for (int i=-65;i<100;i++)
          additionalSet.add(i);

        for (int i=-650;i<1007;i+=17)
            bs.add(i);

        BitSet newUnion = bs.unite(additionalSet);
        System.out.println(newUnion.getAllValues());
    }

    @Test
    public void intersect() throws Exception {
        bs.add(-5,0,6);
        additionalSet.add(-7,-5,6,0,8,9);
        bs.intersect(additionalSet);
        System.out.println(bs.getAllValues());
    }
    @Test
    public void differ() throws Exception {
        bs.add(-5,0,6);
        additionalSet.add(-7,-5,6,0,8,9);
        bs.difference(additionalSet);
        System.out.println(bs.getAllValues());
    }
    @Test
    public void isSubsetOf() throws Exception {
        bs.add(-3,0,9,5,8);
        additionalSet.add(8,5,9,-3,0);
        System.out.println(additionalSet.isSubsetOf(bs));


    }


}