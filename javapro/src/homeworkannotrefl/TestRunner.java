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
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class TestRunner {

  public static void main(String[] args) {

    start(Class.class);
  }

  public static <T> void start(Class<T> clazz) {

    TestClass<T> testRun = new TestClass<>(clazz);

    List<Method> methods = Arrays.stream(testRun.getClass().getMethods())
        .filter(a -> a.getName().contains("test"))
        .toList();

    //here we control is in our TestClass more than one method with annotations @BeforeSuite and
    // @AfterSuite in case if there is more than one method - we throw Exception and stop process
    // of testing
    try {
      if (methods.stream()
          .filter(a -> a.isAnnotationPresent(BeforeSuite.class))
          .count() > 1) {
        throw new MyException("Excessive method with annotation @BeforeSuite in class-method!");
      }
      if (methods.stream()
          .filter(a -> a.isAnnotationPresent(AfterSuite.class))
          .count() > 1) {
        throw new MyException("Excessive method with annotation @AfterSuite in class-method!");
      }
    } catch (RuntimeException e) {
      System.out.println(e.getMessage());
      return;
    }

    //here we start method with annotation BeforeSuite and start testing
    methods.stream()
        .filter(a -> a.isAnnotationPresent(BeforeSuite.class))
        .forEach(a -> {
          try {
            a.invoke(testRun, "Start");
          } catch (ReflectiveOperationException e) {
            throw new MyException("Exception with invoking!!!");
          }
        });

    //here we start method in order of priority, excluding methods with annotations @BeforeSuite
    //and @AfterSuite
    methods.stream()
        .filter(a -> !a.isAnnotationPresent(BeforeSuite.class))
        .filter(a -> !a.isAnnotationPresent(AfterSuite.class))
        .sorted(Comparator.comparing(a -> a.getDeclaredAnnotation(Test.class).priority()))
        .forEach(a -> {
          try {
            a.invoke(testRun, "Process...");
          } catch (ReflectiveOperationException e) {
            throw new MyException("Exception with invoking!!!");
          }
        });

    //here we start method with annotation AfterSuite and finishing our testing
    methods.stream()
        .filter(a -> a.isAnnotationPresent(AfterSuite.class))
        .forEach(a -> {
          try {
            a.invoke(testRun, "Finish!!!");
          } catch (ReflectiveOperationException e) {
            throw new MyException("Exception with invoking!!!");
          }
        });
  }
}
