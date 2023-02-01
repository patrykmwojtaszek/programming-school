package pl.kurs.programmingschool.validators;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class NameValidator implements ConstraintValidator<Name, String> {
//    @Override
//    public void initialize(Name constraintAnnotation) {
//
//    }

    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        return s.matches( "[A-Z][a-z]+" );
    }
}
