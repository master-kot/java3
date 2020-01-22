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
        LinkedList<Method> methods = new LinkedList<>();
        Comparator<Method> comparator = new Comparator<Method>() {
            public int compare(Method o1, Method o2) {
                return o1.getAnnotation(Test.class).priority() - (o2.getAnnotation(Test.class).priority());
            }
        };
        int numberOfMethodBefore = -1;
        int numberOfMethodAfter = -1;

        Method[] listOfMethods = c.getMethods();
        for (int i =0; i < listOfMethods.length; i++) {
            if (listOfMethods[i].isAnnotationPresent(Before.class)) {
                if (numberOfMethodBefore == -1) {
                    numberOfMethodBefore = i;
                } else throw new RuntimeException("Before annotation must be only one");
            }
            if (listOfMethods[i].isAnnotationPresent(Test.class)) {
                methods.add(listOfMethods[i]);
            }
            if (listOfMethods[i].isAnnotationPresent(After.class)) {
                if (numberOfMethodAfter == -1) {
                    numberOfMethodAfter = i;
                } else throw new RuntimeException("After annotation must be only one");
            }
        }
        methods.sort(comparator);

        Tests myTests = (Tests)c.newInstance();
        listOfMethods[numberOfMethodBefore].invoke(myTests);
        for (int i = 0; i < methods.size(); i++) {
            methods.get(i).invoke(myTests);
        }
        listOfMethods[numberOfMethodAfter].invoke(myTests);
    }

    public static void main(String[] args) throws InvocationTargetException, IllegalAccessException, InstantiationException {
        Class clazz = Tests.class;
        TestsInvoker.invokeTests(clazz);
    }
}