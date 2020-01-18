import org.junit.Before;
import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TestSort {
    private ForTests ft;
    private List<Integer> li;

    @Before
    public void CreateTests() {
        ft = new ForTests();
        li = new ArrayList<>(Arrays.asList(1, 0, 3, 4, 6, 5, 7, 2));
    }

    @Test
    public void testOfSort() {
        Assert.assertEquals("[0, 1, 2, 3, 4, 5, 6, 7]", ft.sort(li).toString());
    }
}