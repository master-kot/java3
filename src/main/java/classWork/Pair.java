package classWork;

import java.lang.reflect.Array;
import java.util.Arrays;

public class Pair<K, V> {

    private K key;
    private V value;

    public Pair(K key, V value) {
        this.key = key;
        this.value = value;
    }

    public K getKey() {
        return key;
    }

    public V getValue() {
        return value;
    }

    @Override
    public String toString() {
        System.out.println(value);
        return String.format("(%s, %s)", key.toString(), value.toString());
    }
}
