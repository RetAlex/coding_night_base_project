package ua.edu.ukma.e_request.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ua.edu.ukma.e_request.entities.Request;
import ua.edu.ukma.e_request.resources.dto.RequestMinInfo;

import java.util.List;

@Repository
public interface RequestDAO extends JpaRepository<Request, Long> {
    @Query(nativeQuery = true,name = "findAllRequestForUser")
    List<RequestMinInfo> getRequestForUser(@Param("param1") String currentUserLogin, @Param("param2") int page);

    @Query(value = "select true from Request r where r.student.username=?1 and r.id=?2")
    Boolean isAllowedToUpdate(String username, long requestId);
}
