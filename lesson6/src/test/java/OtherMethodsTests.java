import org.junit.Before;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class OtherMethodsTests {
    private ForTests ft;
    private List<Integer> li;
    private int[] arr;

    @Before
    public void CreateTests() {
        ft = new ForTests();
        li = new ArrayList<>(Arrays.asList(1, 0, 3, 4, 6, 5, 7, 2));
        arr = new int[]{1, 0, 3, 4, 6, 5, 7, 2};
    }

    @Test
    public void testOfSort() {
        Assert.assertEquals("[0, 1, 2, 3, 4, 5, 6, 7]", ft.sort(li).toString());
    }

    @Test
    public void testOfBubbleSort() {
        Assert.assertEquals("[0, 1, 2, 3, 4, 5, 6, 7]", Arrays.toString(ft.bubbleSort(arr)));
    }

    @Test
    public void testAddElementToArray() {
        Assert.assertEquals("[1, 8, 0, 3, 4, 6, 5, 7, 2]", Arrays.toString(ft.addElementToArray(arr, 1, 8)));
    }
}