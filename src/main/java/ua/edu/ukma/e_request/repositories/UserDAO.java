package ua.edu.ukma.e_request.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ua.edu.ukma.e_request.entities.User;

@Repository
public interface UserDAO extends JpaRepository<User, Long> {

}
