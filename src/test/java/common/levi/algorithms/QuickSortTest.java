package common.levi.algorithms;

import com.levi.algorithms.sorters.QuickSorter1;
import com.levi.algorithms.sorters.QuickSorter2;
import org.junit.Assert;
import org.junit.Test;

public class QuickSortTest {
    @Test
    public void QuickSort1_Test(){
        Integer[] numbersList = {23, 42, 4, 16, 8, 15, 3, 9, 55, 0, 34, 12, 2, 46, 25, 42};
        QuickSorter1.QuickSort(numbersList);
        Integer[] expectedSortedList = {0, 2, 3, 4, 8, 9, 12, 15, 16, 23, 25, 34, 42, 42, 46, 55};
        Assert.assertArrayEquals(numbersList, expectedSortedList);
    }

    @Test
    public void QuickSort2_Test(){
        Integer[] numbersList = {23, 42, 4, 16, 8, 15, 3, 9, 55, 0, 34, 12, 2, 46, 25, 42};
        QuickSorter2.QuickSort(numbersList);
        Integer[] expectedSortedList = {0, 2, 3, 4, 8, 9, 12, 15, 16, 23, 25, 34, 42, 42, 46, 55};
        Assert.assertArrayEquals(numbersList, expectedSortedList);
    }
}
