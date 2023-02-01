package pl.kurs.programmingschool.validators;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = {ProgrammingLanguageValidator.class})
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface ProgrammingLanguage {

    String message() default "Not a supported language!";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
