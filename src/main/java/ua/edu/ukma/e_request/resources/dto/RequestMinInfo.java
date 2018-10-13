package ua.edu.ukma.e_request.resources.dto;


import lombok.Data;
import ua.edu.ukma.e_request.entities.Room;

import javax.persistence.*;
import java.sql.Timestamp;

@SqlResultSetMapping(
        name = "findAllRequestForUser",
        classes = @ConstructorResult(
                targetClass = RequestMinInfo.class,
                columns = {
                        @ColumnResult(name = "eventName"),
                        @ColumnResult(name = "startDateTime"),
                        @ColumnResult(name = "finishDateTime"),
                        @ColumnResult(name = "purpose")
                }
        )
)
@NamedNativeQuery(name = "findAllRequestForUser",
        query = "SELECT " +
                "    u.event_name as eventName, " +
                "    u.start_date_time as startDateTime, " +
                "    u.finished_data_time as finishDateTime, " +
                "    u.puspose as purpose " +
                "FROM " +
                "    e_requests as u " +
                "where  u.param1=:param1 and i.param2=:param2"
)
@Data

public class RequestMinInfo {
    private String eventName;
    private Timestamp startDateTime;
    private Timestamp finishDateTime;
    private Room roomName;
    private String purpose;
}
