package unit2.task6submarine;

import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class NuclearSubmarineTest {
    @Test
    public void go() throws Exception {
        NuclearSubmarine submarine = new NuclearSubmarine();

        assertEquals(submarine.go(1), "Your submarine can't move");
        assertEquals(submarine.go(0), "Your submarine is going somewhere!");

    }

}