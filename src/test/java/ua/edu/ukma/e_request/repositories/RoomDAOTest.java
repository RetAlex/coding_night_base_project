package ua.edu.ukma.e_request.repositories;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import ua.edu.ukma.e_oss.config.EOssApplication;
import ua.edu.ukma.e_request.entities.Room;

import java.util.List;

/**
 * Created by a.bondarenko on 10/14/2018.
 */
@ActiveProfiles("local")
@RunWith(SpringRunner.class)
@SpringBootTest(classes = EOssApplication.class)
public class RoomDAOTest {
    @Autowired
    private RoomDAO roomDAO;

    @Test
    public void testGetRoomsByUser() {
        List<Room> rooms = roomDAO.findByUser(1L);
        for (Room room : rooms) {
            System.out.println(room);
        }
    }
}
