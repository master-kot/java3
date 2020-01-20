import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.logging.*;
import java.util.stream.Collectors;

public class ForTests {
    private static final Logger newLogger = Logger.getLogger(ForTests.class.getName());

    /**
     * Придумал использовать статик секцию, которая позволяет настраивать логгер,
     * без нее при тестировании создается несколько разных хэндлеров
     * и куча лог-файлов, что очень дико выглядит
     */
    static {
        newLogger.setLevel(Level.ALL);
        try {
            newLogger.addHandler(new FileHandler("ForTestsLog.txt", true));
        } catch (IOException e) {
            e.printStackTrace();
        }
        newLogger.getHandlers()[0].setLevel(Level.INFO);
        newLogger.getHandlers()[0].setFormatter(new Formatter() {
            @Override
            public String format(LogRecord record) {
                Date date = new Date();
                return date + " [" + record.getLevel() + "] " + record.getSourceClassName() + "."
                        + record.getSourceMethodName() + ": " + record.getMessage() + "\n";
            }
        });
    }

    //TODO
    /**
     * Написать тесты для метода sort.
     */
    public List<Integer> sort(List<Integer> data) {
        return data.stream().sorted().collect(Collectors.toList());
    }

    /**
     * Написать тесты и переработать метод поиска binarySearch члена массива
     * среди определенного количества членов отсортированного массива
     */
    public int binarySearch(int [] data, int key, int l, int r) {
        if(r >= data.length) {
            r = data.length -1;
            newLogger.log(Level.WARNING,"The value of parameter r = " + r + " is more than the length of array");
        }
        if(l < 0) {
            l = 0;
            newLogger.log(Level.WARNING,"The value of parameter l = " + l +
                    " is less than 0, so the value of l was changed to 0");
        }
        int mid = (l + r) / 2;
        if(data[mid] == key) return mid;
        if(l == r) return -1;
        if(r == l + 1) {
            if (data[r] == key) return r;
            else return -1;
        }
        if(data[mid] > key) return binarySearch(data, key, l, mid);
        return binarySearch(data, key, mid, r);
    }

    /**
     * Написать любых два  метода и тесты для них
     */
    public Integer[] arrayBubbleSort(Integer[] data) {
        for (int i = 0; i < data.length; i++) {
            for (int j = 0; j < data.length - i - 1; j++) {
                if (data[j] > data[j + 1]) {
                    Integer tmp = data[j];
                    data[j] = data[j + 1];
                    data[j + 1] = tmp;
                }
            }
        }
        return data;
    }

    public Integer[] addElementToArray(Integer[] data, int index, int value) {
        if (index > data.length || index < 0) {
            newLogger.log(Level.WARNING,"The value of parameter index is " + index +
                    ". It's out of bounds of the array, so the element has not been added into the array");
            return data;
        }
        Integer[] tmp = new Integer[data.length + 1];
        System.arraycopy(data, 0, tmp, 0, index);
        System.arraycopy(data, index, tmp, index + 1, data.length - index);
        tmp[index] = value;
        return tmp;
    }
}