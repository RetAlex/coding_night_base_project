package ua.edu.ukma.e_request.repositories;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import ua.edu.ukma.e_oss.config.EOssApplication;
import ua.edu.ukma.e_request.entities.Request;
import ua.edu.ukma.e_request.entities.StatusChanges;
import ua.edu.ukma.e_request.resources.enums.RequestStatus;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

/**
 * Test DAO
 * Created by a.bondarenko on 10/14/2018.
 */
@ActiveProfiles("local")
@RunWith(SpringRunner.class)
@SpringBootTest(classes = EOssApplication.class)
public class StatusChangesDAOTest {
    @Autowired
    private StatusChangesDAO statusChangesDAO;
    @Autowired
    private RequestDAO requestDAO;

    // uncomment this, if you are running tests on fresh database
    /*@Before
    public void setUp() {
        StatusChanges statusChanges = new StatusChanges();
        statusChanges.setTimestamp(Timestamp.valueOf(LocalDateTime.now()));
        statusChanges.setStatus(RequestStatus.NEW);
        Request request = requestDAO.getRequestById(1L);
        statusChanges.setRequest(request);
        statusChangesDAO.saveAndFlush(statusChanges);
    }*/

    @Test
    public void testGetStatusChangesByRequest() {
        List<StatusChanges> statusChangesList = statusChangesDAO.getStatusChangesByRequestId(1L);
        System.out.println(statusChangesList);
    }
}
