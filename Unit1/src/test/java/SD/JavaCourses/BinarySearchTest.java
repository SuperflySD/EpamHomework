package SD.JavaCourses;

import SD.JavaCourses.BinarySearch;
import org.junit.Test;

import static org.junit.Assert.*;


public class BinarySearchTest {
    @Test
    public void binarySearch() throws Exception {

        int [] arr = new int[]{0,4,7};
        int vSearch = 8;
        int posSearch = BinarySearch.binarySearch(arr, vSearch);


        if (posSearch<0)
            System.out.println("vSearch = " + vSearch + " posSearch= " + posSearch + " false ");


        else {
            assertEquals(vSearch, arr[posSearch]);
            System.out.println("vSearch = " + vSearch + "  position= " + posSearch + " true  " + arr[posSearch]);
        }
    }

}