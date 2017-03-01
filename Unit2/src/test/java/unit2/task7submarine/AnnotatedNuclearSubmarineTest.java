package unit2.task7submarine;

import org.junit.Test;

import static org.junit.Assert.*;

public class AnnotatedNuclearSubmarineTest {
    @Test
    public void go() throws Exception {

        AnnotatedNuclearSubmarine submarine = new AnnotatedNuclearSubmarine();

        assertEquals(submarine.go(1), "Your submarine can't move");
        assertEquals(submarine.go(0), "Your submarine is going somewhere!");
        assertEquals(submarine.getClass().getAnnotation(SubmarineName.class).value(), "YellowSubmarine");

    }

}