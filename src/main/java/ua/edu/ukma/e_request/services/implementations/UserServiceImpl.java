package ua.edu.ukma.e_request.services.implementations;

import org.springframework.stereotype.Service;
import ua.edu.ukma.e_request.entities.User;
import ua.edu.ukma.e_request.repositories.UserDAO;
import ua.edu.ukma.e_request.resources.enums.Role;
import ua.edu.ukma.e_request.services.interfaces.UserService;

import java.util.List;
@Service
public class UserServiceImpl implements UserService {

    private final UserDAO userDAO;

    public UserServiceImpl(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    @Override
    public List<User> getUserByRole(Role role) {
        return userDAO.getUserByRole(role.toString());
    }
}
