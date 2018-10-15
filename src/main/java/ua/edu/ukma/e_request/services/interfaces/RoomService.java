package ua.edu.ukma.e_request.services.interfaces;


import ua.edu.ukma.e_request.entities.Room;

import java.util.List;
import java.util.Map;

public interface RoomService {
    /**
     * Returns all rooms that are available for events so student have list to choose from
     * @return map with string key - name of rooms group owner, and value - list of descriptions of room (building + name)
     */
    Map<String, List<Room>> getAvailibleRoomsForEvents();
}
