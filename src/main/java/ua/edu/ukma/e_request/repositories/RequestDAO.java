package ua.edu.ukma.e_request.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ua.edu.ukma.e_request.entities.Request;

import java.util.List;

public interface RequestDAO extends JpaRepository<Request, Long> {

}
