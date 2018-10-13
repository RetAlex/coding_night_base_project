package ua.edu.ukma.e_request.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ua.edu.ukma.e_request.entities.Request;
import ua.edu.ukma.e_request.resources.dto.RequestMinInfo;

import javax.persistence.ColumnResult;
import javax.persistence.ConstructorResult;
import javax.persistence.NamedNativeQuery;
import javax.persistence.SqlResultSetMapping;
import java.util.List;

public interface RequestDAO extends JpaRepository<Request, Long> {

    @Query(nativeQuery = true, name = "findAllRequestForUser")
    List<RequestMinInfo> getRequestForUser(String currentUserLogin, int page);
}
