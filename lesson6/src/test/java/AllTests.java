import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Написать тесты, тестирующие методы класса ForTests
 */
@RunWith(Parameterized.class)
public class AllTests {
    private ForTests ft;
    private int[] arrForSearch;
    private Integer[] arrForSort, arrForAdd;
    private int result, key, l, r, addIndex, addValue;
    private String resultAdd, resultBubbleSort;

    public AllTests(int result, int key, int l, int r,
                    int addIndex, int addValue, String resultAdd,
                    Integer[] arrForSort, String resultBubbleSort) {

        this.result = result;
        this.key = key;
        this.l = l;
        this.r = r;

        this.addIndex = addIndex;
        this.addValue = addValue;
        this.resultAdd = resultAdd;
        this.arrForSort = arrForSort;
        this.resultBubbleSort = resultBubbleSort;
    }

    /**
     * Считаю что это наиболее лучший вариант параметризованного запуска,
     * здесь можно использовать параметры любых классов
     */
    @Parameterized.Parameters
    public static Object[][] data() {
        return new Object[][]{
                {0, 0, 0, 0, 0, 9, "[9, 0, 1, 2, 3, 4, 5, 6, 7]",
                        new Integer[]{4, 3, 2, 1, 0}, "[0, 1, 2, 3, 4]"},
                {1, 1, 0, 5, 2, 8, "[0, 1, 8, 2, 3, 4, 5, 6, 7]",
                        new Integer[]{0}, "[0]"},
                {2, 2, 0, 8, 9, -1, "[0, 1, 2, 3, 4, 5, 6, 7]",
                        new Integer[]{-1, -3, 5, 1, 0}, "[-3, -1, 0, 1, 5]"},
                {-1, 9, 0, 10, -1, 8, "[0, 1, 2, 3, 4, 5, 6, 7]",
                        new Integer[]{0, 2, 4, 6}, "[0, 2, 4, 6]"},
                {-1, -1, -1, 9, 8, 8, "[0, 1, 2, 3, 4, 5, 6, 7, 8]",
                        new Integer[]{-5, -3, -1, 0, 1}, "[-5, -3, -1, 0, 1]"}
        };
    }

    @Before
    public void CreateTests() {
        ft = new ForTests();
        arrForSearch = new int[]{0, 1, 2, 3, 4, 5, 6, 7};
        arrForAdd = new Integer[]{0, 1, 2, 3, 4, 5, 6, 7};
    }

    @Test
    public void testOfBinarySearch() {
        Assert.assertEquals(result, ft.binarySearch(arrForSearch, key, l, r));
    }

    @Test
    public void testOfSort() {
        Assert.assertEquals(resultBubbleSort, ft.sort(new ArrayList<>(Arrays.asList(arrForSort))).toString());
    }

    @Test
    public void testOfBubbleSort() {
        Assert.assertEquals(resultBubbleSort, Arrays.toString(ft.arrayBubbleSort(arrForSort)));
    }

    @Test
    public void testAddElementToArray() {
        Assert.assertEquals(resultAdd, Arrays.toString(ft.addElementToArray(arrForAdd, addIndex, addValue)));
    }
}