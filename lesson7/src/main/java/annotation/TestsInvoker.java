package annotation;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.*;

/**
 * Создать класс, который может выполнять «тесты» методов класса Utility.
 * В качестве тестов выступают классы с наборами методов, снабженных аннотациями @Test.
 * Класс, запускающий тесты, должен иметь статический метод start(Class testClass),
 * которому в качестве аргумента передается объект типа Class.
 * Из «класса-теста» вначале должен быть запущен метод с аннотацией @BeforeSuite,
 * если он присутствует. Далее запускаются методы с аннотациями @Test,
 * а по завершении всех тестов – метод с аннотацией @AfterSuite.
 * К каждому тесту необходимо добавить приоритеты (int-числа от 1 до 10),
 * в соответствии с которыми будет выбираться порядок их выполнения.
 * Если приоритет одинаковый, то порядок не имеет значения.
 * Методы с аннотациями @BeforeSuite и @AfterSuite должны присутствовать в единственном экземпляре.
 * Если это не так – необходимо бросить RuntimeException при запуске «тестирования».
 */

public class TestsInvoker {

    // TODO: 23/01/2020
    public static void invokeTests(Class c) throws InvocationTargetException, InstantiationException, IllegalAccessException {
        ArrayList<Method> methods = new ArrayList<>();
        Method methodBefore = null;
        Method methodAfter = null;

        for (Method listOfMethods : c.getMethods()) {
            if (listOfMethods.isAnnotationPresent(Before.class)) {
                if (methodBefore == null) {
                    methodBefore = listOfMethods;
                } else throw new RuntimeException("Before annotation must be only one");
            }
            if (listOfMethods.isAnnotationPresent(Test.class)) {
                methods.add(listOfMethods);
            }
            if (listOfMethods.isAnnotationPresent(After.class)) {
                if (methodAfter == null) {
                    methodAfter = listOfMethods;
                } else throw new RuntimeException("After annotation must be only one");
            }
        }
        methods.sort(Comparator.comparingInt(o -> o.getAnnotation(Test.class).priority()));

        Tests myTests = (Tests)c.newInstance();
        if (methodBefore != null) methodBefore.invoke(myTests);
        for (Method method : methods) {
            method.invoke(myTests);
        }
        if (methodAfter != null) methodAfter.invoke(myTests);
    }

    public static void main(String[] args) throws InvocationTargetException, IllegalAccessException, InstantiationException {
        Class clazz = Tests.class;
        TestsInvoker.invokeTests(clazz);
    }
}