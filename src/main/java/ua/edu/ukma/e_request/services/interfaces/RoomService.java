package ua.edu.ukma.e_request.services.interfaces;


import java.util.Map;

public interface RoomService {
    /**
     * Returns all rooms that are available for events so student have list to choose from
     * @return map with string key - name of rooms group owner, and value - description of the room (building + name)
     */
    Map<String, String> getAvailibleRoomsForEvents();
}
