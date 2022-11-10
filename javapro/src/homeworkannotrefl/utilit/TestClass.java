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
public class TestClass {

  public TestClass() {
    //This class is only for methods with annotations @BeforeSuite, @Test, @AfterSuite
  }

  @BeforeSuite
  public void testOne() {
    System.out.println(" test 1 ");
  }

  @Test(priority = 2)
  public void testTwoOne() {
    System.out.println(" test 21 ");
  }

  @Test(priority = 6)
  public void testTwoTwo() {
    System.out.println(" test 23 ");
  }

  @Test(priority = 4)
  public void testTwoThree() {
    System.out.println(" test 22 ");
  }

  @AfterSuite
  public void testThree() {
    System.out.println(" test 3 ");
  }
}
