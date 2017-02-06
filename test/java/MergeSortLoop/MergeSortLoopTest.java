package MergeSortLoop;

import org.junit.Test;

import java.util.Arrays;
import java.util.Random;

import static org.junit.Assert.*;


public class MergeSortLoopTest {
    @Test
    public void sort() throws Exception {

        Random rnd = new Random();
        for (int i = 1; i < 100; i++) {

            int[] testArr = new int[i*11];
            for (int k = 0; k < testArr.length; k++)
                testArr[k] = rnd.nextInt(i*30);


            MergeSortLoop.sort(testArr);
            for (int k = 1; k < testArr.length; k++) {
                boolean check = testArr[k] >= testArr[k - 1];
                assertTrue(check);
            }
            System.out.println(Arrays.toString(testArr));

        }

    }

}