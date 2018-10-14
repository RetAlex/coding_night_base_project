package ua.edu.ukma.e_request.repositories;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import ua.edu.ukma.e_oss.config.EOssApplication;

import static org.junit.Assert.*;

@ActiveProfiles("local")
@RunWith(SpringRunner.class)
@SpringBootTest(classes = EOssApplication.class)
public class RequestDAOTest {

    @Autowired
    private  RequestDAO requestDAO;

    @Test
    public void getRequestForUser() {
        System.out.println(requestDAO.getRequestForUser("user",0));
    }

    @Test
    public void isAllowedToUpdate() {
        assert requestDAO.isAllowedToUpdate("username",1);
    }
}