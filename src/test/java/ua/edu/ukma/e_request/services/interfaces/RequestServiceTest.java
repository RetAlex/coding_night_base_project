package ua.edu.ukma.e_request.services.interfaces;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import ua.edu.ukma.e_oss.config.EOssApplication;
import ua.edu.ukma.e_request.repositories.RequestDAO;
import ua.edu.ukma.e_request.repositories.StatusChangesDAO;
import ua.edu.ukma.e_request.repositories.ThirdPartyTokenDAO;
import ua.edu.ukma.e_request.repositories.UserDAO;
import ua.edu.ukma.e_request.services.implementations.RequestServiceImpl;

/**
 * Created by nasti on 10/14/2018.
 */
@ActiveProfiles("local")
@RunWith(SpringRunner.class)
@SpringBootTest(classes = EOssApplication.class)
public class RequestServiceTest {
    @Autowired
    private RequestDAO requestDAO;
    @Autowired
    private UserDAO userDAO;
    @Autowired
    private ThirdPartyTokenDAO thirdPartyTokenDAO;
    @Autowired
    private StatusChangesDAO statusChangesDAO;
    private RequestServiceImpl requestService;

    @Before
    public void setUp() {
        this.requestService = new RequestServiceImpl(requestDAO, userDAO, thirdPartyTokenDAO, statusChangesDAO);
    }
    @Test
    public void testAssignRequestToMentor() {
        requestService.assignToMentor(1L, 2L);
    }
}
