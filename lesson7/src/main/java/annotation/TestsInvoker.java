package annotation;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Comparator;

public class TestsInvoker {

    public void invokeTests(Class c) throws InvocationTargetException, IllegalAccessException {
        // TODO: 18/11/2019
    }

    public static void main(String[] args) throws InvocationTargetException, IllegalAccessException {
        Class clazz = Tests.class;
        new TestsInvoker().invokeTests(clazz);
    }
}
