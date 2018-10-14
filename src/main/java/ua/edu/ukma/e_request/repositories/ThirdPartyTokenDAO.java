package ua.edu.ukma.e_request.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ua.edu.ukma.e_request.entities.ThirdPartyToken;
import ua.edu.ukma.e_request.entities.ThirdPartyToken_Request;

import java.util.List;

/**
 * Created by a.bondarenko on 10/14/2018.
 */
@Repository
public interface ThirdPartyTokenDAO extends JpaRepository<ThirdPartyToken, String> {
    List<ThirdPartyToken_Request> getTokensByRequest(Long id);
}
