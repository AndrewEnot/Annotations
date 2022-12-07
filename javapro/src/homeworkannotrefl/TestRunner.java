package homeworkannotrefl;

/*
 * @author Oksiuta Andrii
 * 09.11.2022
 * 13:07
 */

import homeworkannotrefl.api.annotations.AfterSuite;
import homeworkannotrefl.api.annotations.BeforeSuite;
import homeworkannotrefl.api.exceptions.MyException;
import homeworkannotrefl.api.annotations.Test;
import homeworkannotrefl.utilit.TestClass;
import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class TestRunner {

  public static void main(String[] args) {

    start(TestClass.class);
  }

  public static <T> void start(Class<T> clazz) {

    if (clazz != null) {

      List<Method> methods = Arrays.stream(clazz.getDeclaredMethods()).toList();

      //here we control is in our TestClass more than one method with annotations @BeforeSuite and
      // @AfterSuite in case if there is more than one method - we throw Exception and stop process
      // of testing
      try {
        checkDoubledAnnotation(methods, BeforeSuite.class);
        checkDoubledAnnotation(methods, AfterSuite.class);
      } catch (RuntimeException e) {
        System.out.println(e.getMessage());
        return;
      }

      //here we start method with annotation BeforeSuite and start testing
      invokeMethodWithAnnotation(clazz, methods, BeforeSuite.class);

      //here we start method in order of priority, excluding methods with annotations @BeforeSuite
      //and @AfterSuite
      methods.stream()
          .filter(a -> a.isAnnotationPresent(Test.class))
          .sorted(Comparator.comparing(a -> a.getDeclaredAnnotation(Test.class).priority()))
          .forEach(a -> {
            try {
              a.invoke(clazz.getDeclaredConstructor().newInstance());
            } catch (ReflectiveOperationException e) {
              throw new MyException("Exception with invoking!!!");
            }
          });

      //here we start method with annotation AfterSuite and finishing our testing
      invokeMethodWithAnnotation(clazz, methods, AfterSuite.class);
    }
  }

  private static <T, S extends Annotation> void invokeMethodWithAnnotation(Class<T> clazz,
      List<Method> methods, Class<S> annotation) {
    methods.stream()
        .filter(a -> a.isAnnotationPresent(annotation))
        .forEach(a -> {
          try {
            a.invoke(clazz.getDeclaredConstructor().newInstance());
          } catch (ReflectiveOperationException e) {
            throw new MyException("Exception with invoking!!!");
          }
        });
  }

  private static <T extends Annotation> void checkDoubledAnnotation(List<Method> methods,
      Class<T> annotation) {
    if (methods.stream()
        .filter(a -> a.isAnnotationPresent(annotation))
        .count() > 1) {
      throw new MyException(
          "Excessive method with annotation " + annotation.getName() + " in class-method!");
    }
  }
}
