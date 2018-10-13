package ua.edu.ukma.e_request.repositories;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import ua.edu.ukma.e_oss.config.EOssApplication;

@ActiveProfiles("local")
@RunWith(SpringRunner.class)
@SpringBootTest(classes = EOssApplication.class)
public class UserDAOTest {

    @Autowired
    private  UserDAO userDAO;


    @Test
    public void findUserByUsername() {
        System.out.println(userDAO.findUserByUsername("user"));
    }
}