package ua.edu.ukma.e_request.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ua.edu.ukma.e_request.entities.TechRequest;

import java.util.List;

/**
 * Created by a.bondarenko on 10/14/2018.
 */
@Repository
public interface TechRequestsDAO extends JpaRepository<TechRequest, Long> {
    @Query(nativeQuery = true, value = "SELECT * FROM e_tech_request WHERE request_id=?")
    List<TechRequest> getTechRequestsByRequestId(@Param("request_id") Long requestId);
}
