package ua.edu.ukma.e_request.utils.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import javax.xml.validation.Validator;
import java.util.Date;

public class InFutureValidator implements ConstraintValidator<InFuture, Date> {
    @Override
    public boolean isValid(Date date, ConstraintValidatorContext constraintValidatorContext) {
        return date.after(new Date());
    }
}
