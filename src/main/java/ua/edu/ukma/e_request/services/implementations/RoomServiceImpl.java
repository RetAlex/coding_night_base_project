package ua.edu.ukma.e_request.services.implementations;

import org.springframework.stereotype.Service;
import ua.edu.ukma.e_request.repositories.RoomDAO;
import ua.edu.ukma.e_request.services.interfaces.RoomService;

import java.util.List;
import java.util.Map;

@Service
public class RoomServiceImpl implements RoomService {

    private final RoomDAO roomDAO;

    public RoomServiceImpl(RoomDAO roomDAO) {
        this.roomDAO = roomDAO;
    }

    @Override
    public Map<String, List<String>> getAvailibleRoomsForEvents() {
        return null;
    }
}
