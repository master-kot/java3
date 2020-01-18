import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class ForTests {

    //TODO
    /**
     * Написать тесты для метода sort.
     */
    public List<Integer> sort(List<Integer> data) {
        return data.stream().sorted().collect(Collectors.toList());
    }

    /** Написать тесты и переработавть метод поиска binarySearch члена массива
     * среди определенного количества членов отсортированного массива
     */
    public int binarySearch(int [] data, int key, int l, int r) {
        if(r >= data.length) r = data.length -1;
        if(l < 0) l = 0;
        int mid = (l + r) / 2;
        if(data[mid] == key) return mid;
        if(l == r) return -1;
        if(r == l + 1 && data[r] != key) return -1;
        if(data[mid] > key) return binarySearch(data, key, l, mid);
        return binarySearch(data, key, mid, r);
    }
}
