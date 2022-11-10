package homeworkannotrefl.api.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/*
 * @author Oksiuta Andrii
 * 09.11.2022
 * 13:06
 */

/**
 * this annotation marks method which will be used in order of priority in chain of tasting
 **/
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Test {

  int priority();

}
