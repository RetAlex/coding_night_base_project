package ua.edu.ukma.e_request.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ua.edu.ukma.e_request.entities.User;

public interface UserDAO extends JpaRepository<User, Long> {
}
