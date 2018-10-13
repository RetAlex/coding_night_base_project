package ua.edu.ukma.e_request.utils.validator;

import javax.validation.Constraint;

@Constraint(validatedBy = InFutureValidator.class)
public @interface InFuture {
}
