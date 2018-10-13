package ua.edu.ukma.e_request.resources.dto;


import lombok.Data;
import ua.edu.ukma.e_request.entities.Room;

import java.sql.Timestamp;

@Data
public class RequestMinInfo {
    private String eventName;
    private Timestamp startDateTime;
    private Timestamp finishDateTime;
    private Room roomName;
    private String purpose;
}
