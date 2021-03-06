package ua.edu.ukma.e_request.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ua.edu.ukma.e_request.entities.StatusChanges;

import java.util.List;

/**
 * Created by a.bondarenko on 10/14/2018.
 */
@Repository
public interface StatusChangesDAO extends JpaRepository<StatusChanges, Long> {
    @Query(nativeQuery = true, value = "SELECT * FROM e_status_changes WHERE request_id=?")
    List<StatusChanges> getStatusChangesByRequestId(@Param("request_id") Long requestId);
}
