package ua.edu.ukma.e_request.services.implementations;

import org.springframework.stereotype.Service;
import ua.edu.ukma.e_request.entities.Room;
import ua.edu.ukma.e_request.repositories.RoomDAO;
import ua.edu.ukma.e_request.services.interfaces.RoomService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class RoomServiceImpl implements RoomService {

    private final RoomDAO roomDAO;

    public RoomServiceImpl(RoomDAO roomDAO) {
        this.roomDAO = roomDAO;
    }

    @Override
    public Map<String, List<Room>> getAvailibleRoomsForEvents() {
        List <Room> all  = roomDAO.findAll();
        List<String> distinctOfOwners = all.stream().map(e->e.getOwner().getUsername()).distinct().collect(Collectors.toList());
        Map<String, List<Room>> distincts = new HashMap<>();

        distinctOfOwners.forEach(o->{
            List<Room> byOwner = all.stream().filter(w->w.getOwner().getUsername().equals(o.toString())).collect(Collectors.toList());
             distincts.put(o.toString(), byOwner);
        });

        return distincts;
    }
}
