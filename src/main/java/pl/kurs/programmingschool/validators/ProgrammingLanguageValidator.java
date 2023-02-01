package pl.kurs.programmingschool.validators;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class ProgrammingLanguageValidator implements ConstraintValidator<ProgrammingLanguage, String> {
    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
//        for (pl.kurs.programmingschool.model.Language value : pl.kurs.programmingschool.model.Language.values()) {
//            if (value.name().equals(s)) {
//                return true;
//            }
//        }
//        return false;

        try {
            pl.kurs.programmingschool.model.Language.valueOf(s);
            return true;
        } catch (IllegalArgumentException e) {
            return false;
        }
    }
}
