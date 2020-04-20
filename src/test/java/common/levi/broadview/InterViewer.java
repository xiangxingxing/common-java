package common.levi.broadview;

import com.levi.broadview.Interview;
import org.junit.Assert;
import org.junit.Test;

public class InterViewer {
    @Test
    public void interView33_Test(){
        int[] input = {5,7,6,9,11,10,8};
        boolean res= Interview.verifySequenceOfBST(input);
        Assert.assertTrue(res);

        int[] input2 = {7,4,6,5};
        boolean res2= Interview.verifySequenceOfBST(input2);
        Assert.assertFalse(res2);
    }
}
