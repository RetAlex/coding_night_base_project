package ua.edu.ukma.e_request.resources.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import ua.edu.ukma.e_request.entities.Room;

import java.sql.Timestamp;
import java.util.Date;

@Data
@AllArgsConstructor
public class RequestMinInfo {
    private String eventName;
    private Date startDateTime;
    private Date finishDateTime;
    private String purpose;

    public RequestMinInfo(){}
}
