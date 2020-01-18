import org.junit.Before;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;

@RunWith(Parameterized.class)
public class TestBinarySearch {
    private ForTests ft;
    private int[] arr;
    private int result, key, l, r;

    public TestBinarySearch(int result, int key, int l, int r, int resultSort) {
        this.result = result;
        this.key = key;
        this.l = l;
        this.r = r;
    }

    @Parameterized.Parameters
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][] {
                {0, 0, 0, 0},
                {1, 1, 0, 5},
                {2, 2, 0, 8},
                {-1, 9, 0, 10},
                {-1, -1, -1, 9}
        });
    }

    @Before
    public void CreateTests() {
        ft = new ForTests();
        arr = new int[]{0, 1, 2, 3, 4, 5, 6, 7, 8};
    }

    @Test
    public void testOfBinarySearch() {
        Assert.assertEquals(result, ft.binarySearch(arr, key, l, r));
    }
}