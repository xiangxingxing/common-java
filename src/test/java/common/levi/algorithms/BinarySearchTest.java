package common.levi.algorithms;

import com.levi.algorithms.search.BinarySearcher;
import org.junit.Assert;
import org.junit.Test;

public class BinarySearchTest {
    @Test
    public void BinarySearch_Test() {

        Character[] str = {'I', 'L', 'a', 'c', 'h', 'n', 'p', 'x'};

        int indexOfc = BinarySearcher.BinarySearch(str, 'c');
        int indexOfn = BinarySearcher.BinarySearch(str, 'n');
        int indexOfx = BinarySearcher.BinarySearch(str, 'x');

        Assert.assertEquals(3,indexOfc);
        Assert.assertEquals(5,indexOfn);
        Assert.assertEquals(7,indexOfx);
    }
}
