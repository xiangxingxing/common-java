package common;

import com.levi.common.Utilities;
import org.junit.Assert;
import org.junit.Test;

public class UtilityTest {
    @Test
    public void GetMinOfArray(){
        Integer[] array = new Integer[]{19, 9, 41, 3};
        Integer integer = Utilities.minOf(array);
        Assert.assertEquals(integer.intValue(), 3);
    }
}
