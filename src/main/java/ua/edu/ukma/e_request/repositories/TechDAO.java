package ua.edu.ukma.e_request.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ua.edu.ukma.e_request.entities.Tech;

/**
 * Created by a.bondarenko on 10/14/2018.
 */
@Repository
public interface TechDAO extends JpaRepository<Tech, Long> {
}
