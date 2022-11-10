package homeworkannotrefl.api.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/*
 * @author Oksiuta Andrii
 * 09.11.2022
 * 13:07
 */

/**
 * this annotation marks method which will be used last in chain of tasting
 **/
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface AfterSuite {

}
