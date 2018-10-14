package ua.edu.ukma.e_request.services.interfaces;

import ua.edu.ukma.e_request.entities.User;
import ua.edu.ukma.e_request.resources.dto.FondRoom;
import ua.edu.ukma.e_request.resources.enums.Role;

import java.util.List;

public interface UserService {
    List<User> getUserByRole(Role role);
    List<FondRoom> getAllRooms();
}
