package ua.edu.ukma.e_request.utils.validator;

import ua.edu.ukma.e_request.entities.User;
import ua.edu.ukma.e_request.repositories.UserDAO;
import ua.edu.ukma.e_request.resources.enums.Role;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class UserRoleValidator implements ConstraintValidator<UserRole, Long> {
    private final UserDAO userDAO;

    private List<Role> roleList;

    public UserRoleValidator(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    @Override
    public void initialize(UserRole constraintAnnotation) {
        roleList = Arrays.asList(constraintAnnotation.requiredRole());
    }

    @Override
    public boolean isValid(Long aLong, ConstraintValidatorContext constraintValidatorContext) {
        Optional<User> user = userDAO.findById(aLong);
        return user.isPresent() && roleList.contains(user.get().getRole());
    }
}
