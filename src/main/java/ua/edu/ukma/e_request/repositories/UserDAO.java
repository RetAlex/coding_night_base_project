package ua.edu.ukma.e_request.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ua.edu.ukma.e_request.entities.User;

import java.util.List;

@Repository
public interface UserDAO extends JpaRepository<User, Long> {

    @Query(value = "select * from e_users u where u.username=?1", nativeQuery = true)
    User findUserByUsername(String username);

    @Query(value = "select * from e_users u where u.role=?1", nativeQuery = true)
    List<User> getUserByRole(String role);
}
