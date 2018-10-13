package ua.edu.ukma.e_request.utils.validator;

import ua.edu.ukma.e_request.resources.enums.Role;

import javax.validation.Constraint;

@Constraint(validatedBy = InFutureValidator.class)
public @interface InFuture {
}
