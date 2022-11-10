package homeworkannotrefl.utilit;

/*
 * @author Oksiuta Andrii
 * 09.11.2022
 * 14:00
 */

import homeworkannotrefl.api.annotations.AfterSuite;
import homeworkannotrefl.api.annotations.BeforeSuite;
import homeworkannotrefl.api.annotations.Test;

/**
 * This class is only for methods with annotations @BeforeSuite, @Test, @AfterSuite
 */
public class TestClass<T> {

  private final Class<T> clazz;

  public TestClass(Class<T> clazz) {
    this.clazz = clazz;
  }

  @Test(priority = 1)
  @BeforeSuite
  public void testOne(String number) {
    System.out.println(number + " test 1 " + clazz.getName());
  }

  @Test(priority = 2)
  public void testTwoOne(String number) {
    System.out.println(number + " test 21 " + clazz.getName());
  }

  @Test(priority = 6)
  public void testTwoTwo(String number) {
    System.out.println(number + " test 23 " + clazz.getName());
  }

  @Test(priority = 4)
  public void testTwoThree(String number) {
    System.out.println(number + " test 22 " + clazz.getName());
  }

  @Test(priority = 1)
  @AfterSuite
  public void testThree(String number) {
    System.out.println(number + " test 3 " + clazz.getName());
  }
}
