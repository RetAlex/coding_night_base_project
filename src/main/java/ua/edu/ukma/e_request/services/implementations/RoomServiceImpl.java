package ua.edu.ukma.e_request.services.implementations;

import org.springframework.stereotype.Service;
import ua.edu.ukma.e_request.repositories.RoomDAO;

@Service
public class RoomServiceImpl {

    private final RoomDAO roomDAO;

    public RoomServiceImpl(RoomDAO roomDAO) {
        this.roomDAO = roomDAO;
    }

}
