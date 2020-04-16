package common.levi.algorithms;

import com.levi.algorithms.sorters.MaxHeapSorter;
import com.levi.algorithms.sorters.MinHeapSorter;
import org.junit.Assert;
import org.junit.Test;

public class HeapSortTest {
    @Test
    public void MaxHeapSort_Test() {
        Integer[] numbersList = {23, 42, 4, 16, 8, 15, 3, 9, 55, 0, 34, 12, 2, 46, 25, 42};
        MaxHeapSorter.MaxHeapSort(numbersList);
        Integer[] expectedSortedList = {0, 2, 3, 4, 8, 9, 12, 15, 16, 23, 25, 34, 42, 42, 46, 55};
        Assert.assertArrayEquals(expectedSortedList, numbersList);
    }

    @Test
    public void MinHeapSort_Test() {
        Integer[] numbersList = {23, 42, 4, 16, 8, 15, 3, 9, 55, 0, 34, 12, 2, 46, 25, 42};
        MinHeapSorter.MinHeapSort(numbersList);
        Integer[] expectedSortedList = {55, 46, 42, 42, 34, 25, 23, 16, 15, 12, 9, 8, 4, 3, 2, 0};
        Assert.assertArrayEquals(expectedSortedList, numbersList);
    }
}
