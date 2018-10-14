package ua.edu.ukma.e_request.services.implementations;

import org.springframework.stereotype.Service;
import ua.edu.ukma.e_request.repositories.RoomDAO;
import ua.edu.ukma.e_request.resources.dto.FondRoom;
import ua.edu.ukma.e_request.services.interfaces.RoomService;

@Service
public class RoomServiceImpl implements RoomService {

    private final RoomDAO roomDAO;

    public RoomServiceImpl(RoomDAO roomDAO) {
        this.roomDAO = roomDAO;
    }

}
