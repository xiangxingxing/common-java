package common.levi.algorithms;

import com.levi.algorithms.sorters.InsertionSorter;
import org.junit.Assert;
import org.junit.Test;

public class InsertionSortTest {
    @Test
    public void insertionSort_test() {
        Integer[] numbersList = {23, 42, 4, 16, 8, 15, 3, 9, 55, 0, 34, 12, 2, 46, 25, 42};
        InsertionSorter.InsertionSort(numbersList);
        Integer[] expectedSortedList = {0, 2, 3, 4, 8, 9, 12, 15, 16, 23, 25, 34, 42, 42, 46, 55};
        Assert.assertArrayEquals(expectedSortedList, numbersList);
    }
}
