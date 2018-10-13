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
                "    u.eventName as " +
                "    u.last_name as userLastName, " +
                "    " +
                "FROM " +
                "    invoice as i " +
                "JOIN user as u on i.user_id=u.id " +
                "LEFT JOIN subscription_package as s on i.subscription_package_id=s.id " +
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
