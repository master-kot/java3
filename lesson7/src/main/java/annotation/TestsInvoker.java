package annotation;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Comparator;

/**
 * Создать класс, который может выполнять «тесты».
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
        //Method methodBefore = null;
        //Method methodAfter = null;
        //Method[] methodsTest;

        int numberOfMethodBefore = 0;
        int numberOfMethodAfter = 0;
        boolean isThereMethodBefore = false;
        boolean isThereMethodAfter = false;

        Method[] listOfMethods = c.getMethods();

        for (int i = 0; i < listOfMethods.length; i++) {
            if (listOfMethods[i].getAnnotation(Before.class) != null) {
                if (!isThereMethodBefore) {
                    //methodBefore = listOfMethods[i];
                    numberOfMethodBefore = i;
                    isThereMethodBefore = false;
                } else throw new RuntimeException("Before must be only one");
            } else if (listOfMethods[i].getAnnotation(Test.class) != null) {
            } else if (listOfMethods[i].getAnnotation(After.class) != null) {
                if (!isThereMethodAfter) {
                    //methodAfter = listOfMethods[i];
                    numberOfMethodAfter = i;
                } else throw new RuntimeException("After must be only one");
            }
        }

        Tests myTests = (Tests)c.newInstance();
        //methodBefore.invoke(myTests);
        //methodAfter.invoke(myTests);
        listOfMethods[numberOfMethodBefore].invoke(myTests);
        listOfMethods[numberOfMethodAfter].invoke(myTests);
    }

    public static void main(String[] args) throws InvocationTargetException, IllegalAccessException, InstantiationException {
        Class clazz = Tests.class;
        new TestsInvoker().invokeTests(clazz);
    }
}
