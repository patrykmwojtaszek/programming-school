package pl.kurs.programmingschool.validators;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = {NameValidator.class})
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Name {

    String message() default "Wrong name format!";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};

}
